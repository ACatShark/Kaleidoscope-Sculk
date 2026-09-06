package com.bmt.kaleidoscope_nether.integration;

import java.util.LinkedHashMap;
import java.util.Map;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.config.GeneralConfig;
import com.github.ysbbbbbb.kaleidoscopedoll.event.ModRegisterEvent;
import com.github.ysbbbbbb.kaleidoscopedoll.init.ModCreativeTabs;
import com.github.ysbbbbbb.kaleidoscopedoll.item.DollEntityItem;
import com.github.ysbbbbbb.kaleidoscopedoll.item.DollItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public class KaleidoscopeDollIntegrationImpl {
    private static final Map<ResourceLocation, Block> DOLL_BLOCKS = new LinkedHashMap<>();
    private static final Map<ResourceLocation, Item> DOLL_ITEMS = new LinkedHashMap<>();
    private static final Map<ResourceLocation, Item> ENTITY_DOLL_ITEMS = new LinkedHashMap<>();

    private static final Map<String, String> AUTHOR_DOLL_DEFINITIONS = Map.of(
            "doll_0", "contributor_0",
            "doll_1", "contributor_1",
            "doll_2", "contributor_2",
            "doll_3", "contributor_3",
            "doll_4", "contributor_4",
            "doll_5", "contributor_5"
    );

    private static final Map<String, String> SPONSOR_DOLL_DEFINITIONS = Map.of(
            "doll_6", "contributor_6",
            "doll_7", "contributor_7",
            "doll_8", "contributor_8",
            "doll_9", "contributor_9",
            "doll_10", "contributor_10",
            "doll_11", "contributor_11",
            "doll_12", "contributor_12"
    );

    private static final Map<String, String> DOLL_DEFINITIONS = new LinkedHashMap<>() {{
        putAll(AUTHOR_DOLL_DEFINITIONS);
        putAll(SPONSOR_DOLL_DEFINITIONS);
    }};

    private static final Map<String, String> ENTITY_DOLL_DEFINITIONS = Map.ofEntries(
            Map.entry("entity_doll_0", "doll_0"),
            Map.entry("entity_doll_1", "doll_1"),
            Map.entry("entity_doll_2", "doll_2"),
            Map.entry("entity_doll_3", "doll_3"),
            Map.entry("entity_doll_4", "doll_4"),
            Map.entry("entity_doll_5", "doll_5"),
            Map.entry("entity_doll_6", "doll_6"),
            Map.entry("entity_doll_7", "doll_7"),
            Map.entry("entity_doll_8", "doll_8"),
            Map.entry("entity_doll_9", "doll_9"),
            Map.entry("entity_doll_10", "doll_10"),
            Map.entry("entity_doll_11", "doll_11"),
            Map.entry("entity_doll_12", "doll_12")
    );

    public static void register(IEventBus modEventBus) {
        modEventBus.register(KaleidoscopeDollIntegrationImpl.class);
    }

    private static boolean isDollModLoaded() {
        return !ModList.get().isLoaded(KaleidoscopeDoll.MOD_ID);
    }

    @SubscribeEvent
    public static void registerBlocks(RegisterEvent event) {
        if (isDollModLoaded()) {
            return;
        }

        if (event.getRegistryKey().equals(Registries.BLOCK)) {
            DOLL_DEFINITIONS.keySet().forEach(dollId -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, dollId);
                DollBlock block = new DollBlock();
                DOLL_BLOCKS.put(id, block);
                event.register(Registries.BLOCK, id, () -> block);

                String tooltipKey = DOLL_DEFINITIONS.get(dollId);
                ModRegisterEvent.SPECIAL_TOOLTIPS.put(id, tooltipKey);
            });
        }
    }

    @SubscribeEvent
    public static void registerItems(RegisterEvent event) {
        if (isDollModLoaded()) {
            return;
        }

        if (event.getRegistryKey().equals(Registries.ITEM)) {
            DOLL_DEFINITIONS.forEach((dollId, tooltipKey) -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, dollId);
                Block block = DOLL_BLOCKS.get(id);
                if (block != null) {
                    DollItem item = new DollItem(block, tooltipKey);
                    DOLL_ITEMS.put(id, item);
                    event.register(Registries.ITEM, id, () -> item);
                }
            });

            ENTITY_DOLL_DEFINITIONS.forEach((entityDollId, blockDollId) -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, entityDollId);
                DollEntityItem item = new DollEntityItem();
                ENTITY_DOLL_ITEMS.put(id, item);
                event.register(Registries.ITEM, id, () -> item);
            });
        }
    }

    @SubscribeEvent
    public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (isDollModLoaded()) {
            return;
        }

        if (event.getTab() == ModCreativeTabs.AUTHOR_DOLL_TAB.get()) {
            AUTHOR_DOLL_DEFINITIONS.keySet().forEach(dollId -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, dollId);
                Item item = DOLL_ITEMS.get(id);
                if (item != null) {
                    event.accept(item);
                }
            });
        }

        if (event.getTab() == ModCreativeTabs.PLAYER_DOLL_TAB.get()) {
            if (GeneralConfig.ENABLE_SPONSORED_DOLL.get()) {
                SPONSOR_DOLL_DEFINITIONS.keySet().forEach(dollId -> {
                    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, dollId);
                    Item item = DOLL_ITEMS.get(id);
                    if (item != null) {
                        event.accept(item);
                    }
                });
            }
        }

        if (event.getTab() == ModCreativeTabs.ENTITY_DOLL_TAB.get()) {
            ENTITY_DOLL_DEFINITIONS.forEach((entityDollId, blockDollId) -> {
                ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, blockDollId);
                Block block = DOLL_BLOCKS.get(blockId);

                if (block != null) {
                    ItemStack stack = DollEntityItem.createItemWithBlockState(block.defaultBlockState());
                    event.accept(stack);
                }
            });
        }
    }
}