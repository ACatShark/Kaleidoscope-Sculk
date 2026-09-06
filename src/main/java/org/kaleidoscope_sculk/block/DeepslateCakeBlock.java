package org.kaleidoscope_sculk.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.kaleidoscope_sculk.register.ModDamageTypes;
import org.kaleidoscope_sculk.register.ModItems;

import java.util.ArrayList;
import java.util.List;

public class DeepslateCakeBlock extends Block {

    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 4);
    public static final int MAX_BITES = 4;

    private static final int NUTRITION = 4;
    private static final float SATURATION = 0.2f;
    private static final float BITE_DAMAGE = 2.0f;

    private static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 7.0, 15.0);

    public DeepslateCakeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (!level.getBlockState(pos.below()).isSolid()) {
            return null;
        }
        return this.defaultBlockState().setValue(BITES, 0);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {
        
        if (stack.is(ModItems.DEEPSLATE_CAKE_SLICE.get())) {
            int currentBites = state.getValue(BITES);
            if (currentBites <= 0 || currentBites >= MAX_BITES) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }

            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(BITES, Math.max(0, currentBites - 1)), Block.UPDATE_ALL);
                level.playSound(null, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }

            return ItemInteractionResult.SUCCESS;
        }

        return this.handleEat(state, level, pos, player);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        return this.handleEatInteraction(state, level, pos, player);
    }

    private InteractionResult handleEatInteraction(BlockState state, Level level, BlockPos pos, Player player) {
        int currentBites = state.getValue(BITES);
        if (currentBites >= MAX_BITES || !player.canEat(true)) {
            return InteractionResult.PASS;
        }

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        player.getFoodData().eat(NUTRITION, SATURATION);
        level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS,
                1.0f, level.random.nextFloat() * 0.1f + 0.9f);
        advanceBite(state, level, pos, player, currentBites);

        return InteractionResult.SUCCESS;
    }

    private ItemInteractionResult handleEat(BlockState state, Level level, BlockPos pos, Player player) {
        int currentBites = state.getValue(BITES);
        if (currentBites >= MAX_BITES || !player.canEat(true)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (level.isClientSide) {
            return ItemInteractionResult.SUCCESS;
        }

        player.getFoodData().eat(NUTRITION, SATURATION);
        hurtOnBite(level, player);
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0));

        level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS,
                1.0f, level.random.nextFloat() * 0.1f + 0.9f);
        advanceBite(state, level, pos, player, currentBites);

        return ItemInteractionResult.SUCCESS;
    }

    private void advanceBite(BlockState state, Level level, BlockPos pos, Player player, int currentBites) {
        int newBites = currentBites + 1;
        if (newBites >= MAX_BITES) {
            level.destroyBlock(pos, false, player);
        } else {
            level.setBlock(pos, state.setValue(BITES, newBites), Block.UPDATE_ALL);
        }
    }

    private void hurtOnBite(Level level, Player player) {
        float newHealth = player.getHealth() - BITE_DAMAGE;
        if (newHealth <= 0.0f) {
            DamageSource damageSource = new DamageSource(
                    level.registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(ModDamageTypes.DEEPSLATE_CAKE_SLICE),
                    player
            );
            player.hurt(damageSource, BITE_DAMAGE);
        } else {
            player.setHealth(newHealth);
            player.playSound(SoundEvents.PLAYER_HURT, 1.0f, 1.0f);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> drops = new ArrayList<>();
        int remaining = MAX_BITES - state.getValue(BITES);
        if (remaining > 0) {
            drops.add(new ItemStack(ModItems.DEEPSLATE_CAKE_SLICE.get(), remaining));
        }
        return drops;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return MAX_BITES - state.getValue(BITES);
    }
}
