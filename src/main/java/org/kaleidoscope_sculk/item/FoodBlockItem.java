package org.kaleidoscope_sculk.item;

import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodBlockItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;
import org.kaleidoscope_sculk.block.FoodBiteBlock;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class FoodBlockItem extends BowlFoodBlockItem {

    @Nullable
    private final Supplier<MobEffectInstance> effectSupplier;
    private final float effectChance;
    private final List<Supplier<Item>> extraDropSuppliers;

    public FoodBlockItem(Block block) {
        super(block, getWholeDishFoodProperties(block), getBlockContainer(block));
        if (block instanceof FoodBiteBlock foodBite) {
            this.effectSupplier = foodBite.getEffectSupplier();
            this.effectChance = foodBite.getEffectChance();
            this.extraDropSuppliers = foodBite.getExtraDropSuppliers();
        } else {
            this.effectSupplier = null;
            this.effectChance = 0.0F;
            this.extraDropSuppliers = List.of();
        }
    }

    private static FoodProperties getBlockFoodProperties(Block block) {
        return block instanceof FoodBiteBlock foodBite
                ? foodBite.getFoodProperties()
                : new FoodProperties.Builder().build();
    }

    /**
     * 物品形态（整盘直接吃）使用的营养属性。
     * <p>
     * 与 Cookery 的做法保持一致：方块形态的营养值是「每口」的量，
     * 而物品形态应当代表「整盘」，即 每口营养 × 总口数，饱和度系数不变。
     * 否则直接吃掉物品只能获得 1/maxBites 的营养，摆下来吃才划算，属于数值失衡。
     */
    private static FoodProperties getWholeDishFoodProperties(Block block) {
        FoodProperties biteFood = getBlockFoodProperties(block);
        if (!(block instanceof FoodBiteBlock foodBite)) {
            return biteFood;
        }
        int maxBites = Math.max(1, foodBite.getMaxBites());
        return new FoodProperties(
                biteFood.nutrition() * maxBites,
                biteFood.saturation(),
                biteFood.canAlwaysEat(),
                biteFood.eatSeconds(),
                Optional.empty(),
                biteFood.effects()
        );
    }

    
    @Nullable
    private static ItemLike getBlockContainer(Block block) {
        return null;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        FoodProperties food = stack.getFoodProperties(player);
        if (food != null && player.canEat(food.canAlwaysEat())) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        
        ItemStack result = super.finishUsingItem(stack, level, living);
        giveExtraContainers(level, living);
        applyExtraEffect(level, living);
        return result;
    }

    
    private void giveExtraContainers(Level level, LivingEntity living) {
        if (level.isClientSide) return;
        if (!(living instanceof Player player)) return;

        for (Supplier<Item> supplier : extraDropSuppliers) {
            Item item = supplier.get();
            if (item == null || item == Items.BOWL) continue;
            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(item));
        }
    }

    private void applyExtraEffect(Level level, LivingEntity living) {
        if (level.isClientSide) return;

        if (effectSupplier != null && level.random.nextFloat() < effectChance) {
            MobEffectInstance effect = effectSupplier.get();
            if (effect != null) {
                living.removeEffect(effect.getEffect());
                living.addEffect(effect);
            }
        }
    }
}
