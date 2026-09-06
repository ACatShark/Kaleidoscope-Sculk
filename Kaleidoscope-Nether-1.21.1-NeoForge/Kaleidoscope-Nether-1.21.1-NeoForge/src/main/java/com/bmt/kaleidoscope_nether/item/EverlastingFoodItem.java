package com.bmt.kaleidoscope_nether.item;

import com.github.ysbbbbbb.kaleidoscopecookery.config.ClientConfig;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.CompatRegistry;
import com.github.ysbbbbbb.kaleidoscopecookery.item.quality.Quality;
import com.github.ysbbbbbb.kaleidoscopecookery.item.quality.QualityUtils;
import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class EverlastingFoodItem extends Item {
    private final Supplier<Integer> eatingCooldown;
    private final Supplier<Boolean> isEnabled;
    private final List<MobEffectInstance> effectInstances = Lists.newArrayList();
    private final Function<Quality, List<MobEffectInstance>> effectCache = Util.memoize(
            quality -> QualityUtils.modifyEffects(this.effectInstances, quality)
    );
    private final BiFunction<Quality, FoodProperties, FoodProperties> foodPropertiesCache = Util.memoize(
            (quality, raw) -> QualityUtils.modifyFoodProperties(raw, quality)
    );

    public EverlastingFoodItem(FoodProperties food, Supplier<Integer> eatingCooldown, Supplier<Boolean> isEnabled, Rarity rarity) {
        super(new Item.Properties().food(food).rarity(rarity));
        this.eatingCooldown = eatingCooldown;
        this.isEnabled = isEnabled;
        food.effects().forEach(effect -> {
            if (effect.probability() >= 1F) {
                effectInstances.add(effect.effect());
            }
        });
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        FoodProperties raw = super.getFoodProperties(stack, entity);
        if (!QualityUtils.hasQuality(stack) || raw == null) {
            return raw;
        }
        Quality quality = QualityUtils.getQuality(stack);
        return this.foodPropertiesCache.apply(quality, raw);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String key = "tooltip.%s.%s.maxim".formatted(id.getNamespace(), id.getPath());
        MutableComponent full = Component.translatable(key).withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC);
        String text = full.getString();
        for (String line : text.split("\n")) {
            if (!line.isEmpty()) {
                tooltip.add(Component.literal(line).withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
            } else {
                tooltip.add(CommonComponents.EMPTY);
            }
        }

        boolean showEffect = !this.effectInstances.isEmpty()
                && CompatRegistry.SHOW_POTION_EFFECT_TOOLTIPS
                && ClientConfig.SHOW_FOOD_EFFECT_TOOLTIPS.get();

        if (QualityUtils.hasQuality(stack)) {
            Quality quality = QualityUtils.getQuality(stack);
            tooltip.add(quality.getTooltip());
            if (showEffect) {
                tooltip.add(CommonComponents.space());
                PotionContents.addPotionTooltip(this.effectCache.apply(quality), tooltip::add, 1.0F, context.tickRate());
            }
        } else {
            tooltip.add(CommonComponents.space());
            PotionContents.addPotionTooltip(this.effectInstances, tooltip::add, 1.0F, context.tickRate());
        }

        tooltip.add(Component.translatable("item.kaleidoscope_nether.everlasting_flame_steak.tooltip.line1")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, @NotNull Level world, @NotNull LivingEntity entity) {
        if (stack.has(DataComponents.FOOD)) {
            entity.eat(world, stack.copy());
            addCooldown(entity, eatingCooldown.get());
        }
        return stack;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!isEnabled.get()) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
        return super.use(level, player, hand);
    }

    private void addCooldown(LivingEntity entity, int cooldown) {
        if (entity instanceof Player player) {
            player.getCooldowns().addCooldown(this, cooldown);
        }
    }
}