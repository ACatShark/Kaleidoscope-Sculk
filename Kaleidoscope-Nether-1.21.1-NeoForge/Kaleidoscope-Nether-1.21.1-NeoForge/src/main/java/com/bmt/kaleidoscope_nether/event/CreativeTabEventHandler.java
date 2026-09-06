package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.init.KNItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.Objects;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class CreativeTabEventHandler {

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceLocation artifactsTab = KaleidoscopeNether.fromNamespaceAndPath("artifacts", "main");
        if (event.getTabKey().location().equals(artifactsTab)) {
            event.accept(KNItems.EVERLASTING_FLAME_STEAK.get());
        }

        if (event.getTabKey().location().equals(KaleidoscopeNether.fromNamespaceAndPath("kaleidoscope_cookery", "cookery_food"))) {
            for (ItemStack itemStack : event.getParentEntries().toArray(ItemStack[]::new)) {
                if (Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(itemStack.getItem()))
                    .getNamespace().equals(KaleidoscopeNether.MOD_ID)) {
                    event.remove(itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
        }
    }
}