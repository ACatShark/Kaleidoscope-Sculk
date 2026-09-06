package org.kaleidoscope_sculk;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.kaleidoscope_sculk.block.FoodBiteBlock;
import org.kaleidoscope_sculk.register.ModItems;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Kaleidoscope_sculk.MODID)
public class ModTooltipHandler {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();

        
        if (itemStack.getItem() == ModItems.WARDEN_TENDRIL.get()) {
            event.getToolTip().add(1, Component.translatable("item.kaleidoscope_sculk.warden_tendril.tooltip")
                    .withStyle(ChatFormatting.GRAY));
        }

        
        if (itemStack.getItem() == ModItems.ANCIENT_BONE_FRAGMENT.get()) {
            event.getToolTip().add(1, Component.translatable("item.kaleidoscope_sculk.ancient_bone_fragment.tooltip")
                    .withStyle(ChatFormatting.GRAY));
        }

        
        if (itemStack.getItem() == ModItems.SCULK_FUNGUS.get()) {
            event.getToolTip().add(1, Component.translatable("item.kaleidoscope_sculk.sculk_fungus.tooltip")
                    .withStyle(ChatFormatting.GRAY));
        }

        
        if (itemStack.getItem() == ModItems.ECHO_SEED.get()) {
            event.getToolTip().add(1, Component.translatable("item.kaleidoscope_sculk.echo_seed.tooltip")
                    .withStyle(ChatFormatting.GRAY));
        }

        
        if (itemStack.getItem() == ModItems.SCULK_BRANCH.get()) {
            event.getToolTip().add(1, Component.translatable("item.kaleidoscope_sculk.sculk_branch.tooltip")
                    .withStyle(ChatFormatting.GRAY));
        }

        
        if (itemStack.getItem() == ModItems.SCULK_PINAPPLE.get()) {
            event.getToolTip().add(1, Component.translatable("item.kaleidoscope_sculk.sculk_pinapple.tooltip")
                    .withStyle(ChatFormatting.DARK_GRAY));
        }

        
        if (itemStack.getItem() == ModItems.SCULK_CATERPILLAR.get()) {
            event.getToolTip().add(1, Component.empty());
            event.getToolTip().add(2, Component.translatable("item.kaleidoscope_sculk.echo")
                    .withStyle(ChatFormatting.BLUE));
        }

        
        if (itemStack.getItem() == ModItems.ANCIENT_BRITTLE_BONE_FRAGMENTS.get()) {
            event.getToolTip().add(1, Component.empty());
            event.getToolTip().add(2, Component.translatable("item.kaleidoscope_sculk.strengthII1800")
                    .withStyle(ChatFormatting.BLUE));
        }

        
        if (itemStack.getItem() == ModItems.SOUL.get()) {
            event.getToolTip().add(1, Component.translatable("item.kaleidoscope_sculk.soul.tooltip")
                    .withStyle(ChatFormatting.GRAY));
        }

        
        if (itemStack.getItem() == ModItems.SOUL_PANCAKE.get()) {
            event.getToolTip().add(1, Component.translatable("item.kaleidoscope_sculk.soul_pancake.tooltip")
                    .withStyle(ChatFormatting.DARK_GRAY));
            event.getToolTip().add(2, Component.empty());
            event.getToolTip().add(3, Component.translatable("item.kaleidoscope_sculk.resistance")
                    .withStyle(ChatFormatting.BLUE));
            event.getToolTip().add(4, Component.translatable("item.kaleidoscope_sculk.regeneration")
                    .withStyle(ChatFormatting.BLUE));
        }

        
        if (itemStack.getItem() instanceof BlockItem blockItem
                && blockItem.getBlock() instanceof FoodBiteBlock foodBite) {
            for (Supplier<MobEffectInstance> supplier : foodBite.getEffectSuppliers()) {
                MobEffectInstance instance = supplier.get();
                if (instance == null) continue;
                event.getToolTip().add(Component.translatable("potion.withDuration",
                                Component.translatable(instance.getEffect().value().getDescriptionId()),
                                MobEffectUtil.formatDuration(instance, 1.0f, 20.0f))
                        .withStyle(instance.getEffect().value().getCategory().getTooltipFormatting()));
            }
        }
    }
}
