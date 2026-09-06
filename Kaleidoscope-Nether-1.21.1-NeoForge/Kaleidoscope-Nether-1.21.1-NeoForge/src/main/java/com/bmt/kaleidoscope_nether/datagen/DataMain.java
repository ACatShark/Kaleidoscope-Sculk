package com.bmt.kaleidoscope_nether.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class DataMain {
    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        DatapackBuiltinEntriesProvider datapackBuiltinEntriesProvider = generator.addProvider(event.includeServer(),
                new DatapackBuiltinEntriesProvider(
                        generator.getPackOutput(),
                        event.getLookupProvider(),
                        new RegistrySetBuilder().
                                add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrap).
                                add(Registries.PLACED_FEATURE, ModPlacements::bootstrap).
                                add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifier::bootstrap).
                                add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)

                        ,
                        Set.of(KaleidoscopeNether.MOD_ID)
                ));

        CompletableFuture<HolderLookup.Provider> registryProvider = datapackBuiltinEntriesProvider.getRegistryProvider();
        generator.addProvider(event.includeServer(), new ModAdvancementProvider(generator.getPackOutput(), registryProvider, event.getExistingFileHelper()));

        BlockTagsProvider blockTagsProvider = generator.addProvider(event.includeServer(), new ModBlockTagsProvider(generator.getPackOutput(), registryProvider, event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new ModItemTagProvider(generator.getPackOutput(), registryProvider, blockTagsProvider.contentsGetter(), event.getExistingFileHelper()));


        generator.addProvider(event.includeServer(), new ModBlockModelProvider(generator.getPackOutput(), event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new ModBlockStateProvider(generator.getPackOutput(), event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new ModLootTableProvider(generator.getPackOutput(), registryProvider));

    }


}
