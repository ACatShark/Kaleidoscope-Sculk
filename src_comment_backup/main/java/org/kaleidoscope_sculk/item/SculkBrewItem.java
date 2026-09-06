package org.kaleidoscope_sculk.item;

import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.DrinkBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.data.DrinkEffectData;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.resources.DrinkEffectDataReloadListener;
import com.github.ysbbbbbb.kaleidoscopetavern.item.BottleBlockItem;
import com.github.ysbbbbbb.kaleidoscopetavern.item.IHasContainer;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 幽匿菠萝啤：复用主模组（森罗酒馆）的 {@link BottleBlockItem}，喝完返还空瓶。
 */
public class SculkBrewItem extends BottleBlockItem implements IHasContainer {

    private static final int USE_DURATION = 32;

    public SculkBrewItem(Block block) {
        super(block, new Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return USE_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        BlockState state = level.getBlockState(pos);
        Block self = this.getBlock();

        if (player != null && !player.isShiftKeyDown()) {
            InteractionResult result = this.use(level, player, context.getHand()).getResult();
            return result == InteractionResult.CONSUME ? InteractionResult.CONSUME_PARTIAL : result;
        }

        if (player != null && this.tryIncreaseCount(self, state, level, pos, stack, player)) {
            return InteractionResult.SUCCESS;
        }

        return this.place(new BlockPlaceContext(context));
    }

    private boolean tryIncreaseCount(Block self, BlockState state, Level level, BlockPos pos,
                                     ItemStack stack, Player player) {
        if (!(self instanceof DrinkBlock drink) || !state.is(self)
                || !drink.tryIncreaseCount(level, pos, state, stack)) {
            return false;
        }

        SoundType soundType = state.getSoundType(level, pos, player);
        SoundEvent sound = this.getPlaceSound(state, level, pos, player);
        level.playSound(player, pos, sound, SoundSource.BLOCKS,
                (soundType.getVolume() + 1.0f) / 2.0f, soundType.getPitch() * 0.8f);

        if (!player.isCreative()) {
            stack.shrink(1);
        }

        return true;
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level level, @Nullable Player player,
                                                 ItemStack stack, BlockState state) {
        if (level.getBlockEntity(pos) instanceof DrinkBlockEntity drinkBe && drinkBe.addItem(stack)) {
            drinkBe.refresh();
        }

        return super.updateCustomBlockEntityTag(pos, level, player, stack, state);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        this.addDrinkEffect(stack, level, entity);

        if (entity instanceof Player player && !player.isCreative()) {
            stack.shrink(1);
        }

        return this.returnContainerToEntity(stack, level, entity);
    }

    protected void addDrinkEffect(ItemStack drink, Level level, LivingEntity entity) {
        DrinkEffectData effectData = DrinkEffectDataReloadListener.INSTANCE.get(drink.getItem());
        if (effectData == null) return;

        List<List<DrinkEffectData.Entry>> effects = effectData.effects();
        if (effects.isEmpty()) return;

        int brewLevel = BottleBlockItem.getBrewLevel(drink);
        if (brewLevel < 1) return;

        brewLevel = Math.min(brewLevel, effects.size());

        for (DrinkEffectData.Entry entry : effects.get(brewLevel - 1)) {
            if (!level.isClientSide && level.random.nextFloat() < entry.probability()) {
                entity.addEffect(new MobEffectInstance(entry.effect(), entry.duration() * 20, entry.amplifier()));
            }
        }
    }

    @Override
    public Item getContainerItem() {
        return BuiltInRegistries.ITEM.get(ResourceLocation.parse("kaleidoscope_tavern:empty_bottle"));
    }

    @Override
    public ItemStack returnContainerToEntity(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player && !player.getAbilities().instabuild) {
            ItemStack container = new ItemStack(this.getContainerItem());
            if (!player.getInventory().add(container)) {
                player.drop(container, false);
            }
        }

        return stack;
    }
}
