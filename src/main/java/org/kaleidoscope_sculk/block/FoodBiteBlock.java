package org.kaleidoscope_sculk.block;

import com.github.ysbbbbbb.kaleidoscopecookery.item.quality.Quality;
import com.github.ysbbbbbb.kaleidoscopecookery.item.quality.QualityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock.DEFAULT_QUALITY;
import static com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock.QUALITY;

public class FoodBiteBlock extends com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock {

    
    private final List<Supplier<Item>> extraDropSuppliers;

    
    @Nullable
    private final List<Supplier<MobEffectInstance>> effectSuppliers;
    private final float effectChance;

    
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape) {
        this(foodProperties, maxBites, shape, (List<Supplier<Item>>) null, (List<Supplier<MobEffectInstance>>) null, 0.0F);
    }

    
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape,
                         @Nullable Supplier<Item> extraDropSupplier) {
        this(foodProperties, maxBites, shape,
                extraDropSupplier == null ? null : List.of(extraDropSupplier), null, 0.0F);
    }

    
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape,
                         @Nullable Supplier<MobEffectInstance> effectSupplier,
                         float effectChance) {
        this(foodProperties, maxBites, shape, (List<Supplier<Item>>) null,
                effectSupplier == null ? null : List.of(effectSupplier), effectChance);
    }

    
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape,
                         @Nullable Supplier<Item> extraDropSupplier,
                         @Nullable Supplier<MobEffectInstance> effectSupplier,
                         float effectChance) {
        this(foodProperties, maxBites, shape,
                extraDropSupplier == null ? null : List.of(extraDropSupplier),
                effectSupplier == null ? null : List.of(effectSupplier), effectChance);
    }

    
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape,
                         @Nullable List<Supplier<Item>> extraDropSuppliers,
                         @Nullable List<Supplier<MobEffectInstance>> effectSuppliers,
                         float effectChance) {
        super(foodProperties, maxBites, null);
        this.extraDropSuppliers = extraDropSuppliers == null
                ? List.<Supplier<Item>>of()
                : List.copyOf(extraDropSuppliers);
        this.effectSuppliers = effectSuppliers == null
                ? List.<Supplier<MobEffectInstance>>of()
                : List.copyOf(effectSuppliers);
        this.effectChance = effectChance;
        this.setAABB(shape);
    }

    public FoodProperties getFoodProperties() {
        return this.foodProperties;
    }

    
    @Nullable
    public Supplier<Item> getExtraDropSupplier() {
        return extraDropSuppliers.isEmpty() ? null : extraDropSuppliers.get(0);
    }

    
    public List<Supplier<Item>> getExtraDropSuppliers() {
        return extraDropSuppliers;
    }

    @Nullable
    public Supplier<MobEffectInstance> getEffectSupplier() {
        return effectSuppliers.isEmpty() ? null : effectSuppliers.get(0);
    }

    public List<Supplier<MobEffectInstance>> getEffectSuppliers() {
        return effectSuppliers;
    }

    public float getEffectChance() {
        return effectChance;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                            Player player, BlockHitResult hit) {
        
        if (state.getValue(getBites()) >= getMaxBites()) {
            if (!level.isClientSide) {
                level.destroyBlock(pos, true, player);
            }
            return InteractionResult.SUCCESS;
        }
        if (!player.canEat(foodProperties.canAlwaysEat())) {
            return InteractionResult.PASS;
        }
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        return eat(level, pos, state, player);
    }

    
    @Override
    protected InteractionResult eat(Level level, BlockPos pos, BlockState state, Player player) {
        InteractionResult result = super.eat(level, pos, state, player);
        if (!result.consumesAction()) {
            return result;
        }

        applyEffect(level, player);

        return result;
    }

    
    protected void applyEffect(Level level, Player player) {
        if (level.isClientSide) return;
        if (effectChance <= 0.0f) return;

        for (Supplier<MobEffectInstance> supplier : effectSuppliers) {
            if (level.random.nextFloat() < effectChance) {
                MobEffectInstance effect = supplier.get();
                if (effect != null) {
                    player.removeEffect(effect.getEffect());
                    player.addEffect(effect);
                }
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> drops = new ArrayList<>();

        if (state.getValue(getBites()) == 0) {
            
            ItemStack stack = new ItemStack(this.asItem());
            int qualityNum = state.getValue(QUALITY);
            if (qualityNum != DEFAULT_QUALITY) {
                QualityUtils.setQuality(stack, Quality.BY_ID.apply(qualityNum));
            }
            drops.add(stack);
        } else if (!extraDropSuppliers.isEmpty()) {
            
            for (Supplier<Item> supplier : extraDropSuppliers) {
                Item extraItem = supplier.get();
                if (extraItem != null) {
                    drops.add(new ItemStack(extraItem));
                }
            }
        }

        return drops;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        int currentBites = state.getValue(getBites());
        int maxBites = getMaxBites();
        return (maxBites - currentBites) * 15 / maxBites;
    }
}
