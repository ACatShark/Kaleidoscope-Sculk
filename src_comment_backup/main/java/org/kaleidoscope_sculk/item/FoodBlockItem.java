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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;
import org.kaleidoscope_sculk.block.FoodBiteBlock;

import java.util.List;
import java.util.function.Supplier;

/**
 * 方块食物对应的物品。
 * <p>
 * 继承森罗厨房的 {@link BowlFoodBlockItem}：
 * 容器的返还交给 {@code FoodProperties.usingConvertsTo}（由原版食用流程处理），
 * 同时接入森罗厨房的品质（Quality）系统与效果 tooltip。
 * 本模组原有的「每口触发额外效果」逻辑在此保留。
 */
public class FoodBlockItem extends BowlFoodBlockItem {

    @Nullable
    private final Supplier<MobEffectInstance> effectSupplier;
    private final float effectChance;
    private final List<Supplier<Item>> extraDropSuppliers;

    public FoodBlockItem(Block block) {
        super(block, getBlockFoodProperties(block), getBlockContainer(block));
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

    /** 食用后返还的容器（碗 / 红蜡烛 / 远古骨片），没有则为 null */
    @Nullable
    private static ItemLike getBlockContainer(Block block) {
        if (block instanceof FoodBiteBlock foodBite) {
            Supplier<Item> supplier = foodBite.getExtraDropSupplier();
            return supplier == null ? null : supplier.get();
        }
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
        // 主容器（第一个返还物）由父类（usingConvertsTo）返还，额外容器在此手动返还
        ItemStack result = super.finishUsingItem(stack, level, living);
        giveExtraContainers(level, living);
        applyExtraEffect(level, living);
        return result;
    }

    /** 返还额外的容器（第 2 个及以后），主容器已由 usingConvertsTo 处理 */
    private void giveExtraContainers(Level level, LivingEntity living) {
        if (level.isClientSide || extraDropSuppliers.size() <= 1) return;
        if (!(living instanceof Player player)) return;

        for (int i = 1; i < extraDropSuppliers.size(); i++) {
            Item item = extraDropSuppliers.get(i).get();
            if (item != null) {
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(item));
            }
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
