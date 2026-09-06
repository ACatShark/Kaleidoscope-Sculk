package com.bmt.kaleidoscope_nether;

import com.bmt.kaleidoscope_nether.advancement.KNAdvancementTriggerRegistry;
import com.bmt.kaleidoscope_nether.config.MainConfig;
import com.bmt.kaleidoscope_nether.integration.KaleidoscopeDollIntegration;
import com.bmt.kaleidoscope_nether.init.*;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(KaleidoscopeNether.MOD_ID)
public class KaleidoscopeNether {
    public static final String MOD_ID = "kaleidoscope_nether";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public KaleidoscopeNether(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        KNBlocks.BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        KNItems.ITEMS.register(modEventBus);
        KNEffects.EFFECTS.register(modEventBus);
        KNSounds.SOUND_EVENTS.register(modEventBus);
        KNEntities.ENTITIES.register(modEventBus);
        KNPotions.POISONS.register(modEventBus);
        KNRecipes.register(modEventBus);
        KNAdvancementTriggerRegistry.TRIGGERS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        KNCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        if (ModList.get().isLoaded("kaleidoscope_doll")) {
            KaleidoscopeDollIntegration.register(modEventBus);
        }
        modContainer.registerConfig(ModConfig.Type.COMMON, MainConfig.SPEC);
        KNFoodBiteRegistry.init();
        NeoForge.EVENT_BUS.addListener(KNBrewingRecipes::registerBrewingRecipes);
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.tryBuild(MOD_ID, name);
    }

    public static ResourceLocation fromNamespaceAndPath(String path, String name) {
        return ResourceLocation.tryBuild(path, name);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(KNComposterRegistry::register);
    }
}
