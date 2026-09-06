package com.bmt.kaleidoscope_nether.datagen;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifier {
    public static final ResourceKey<BiomeModifier> POISONOUS_FRUIT = ResourceKey.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS,
            KaleidoscopeNether.id("poisonous_fruit")
    );
    public static final ResourceKey<BiomeModifier> TWISTING_CAVE_VINES = ResourceKey.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS,
            KaleidoscopeNether.id("twisting_cave_vines")
    );

    public static final ResourceKey<BiomeModifier> WEEPING_CAVE_VINES = ResourceKey.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS,
            KaleidoscopeNether.id("weeping_cave_vines")
    );

    public static void bootstrap(BootstrapContext<BiomeModifier> bootstrap) {
        HolderGetter<Biome> biomes = bootstrap.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = bootstrap.lookup(Registries.PLACED_FEATURE);
        bootstrap.register(POISONOUS_FRUIT,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_NETHER),
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacements.POISONOUS_FRUIT)),
                        GenerationStep.Decoration.UNDERGROUND_DECORATION
                )
        );

        bootstrap.register(TWISTING_CAVE_VINES,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(biomes.get(Biomes.WARPED_FOREST).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacements.TWISTING_CAVE_VINES)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        bootstrap.register(WEEPING_CAVE_VINES,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(biomes.get(Biomes.CRIMSON_FOREST).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacements.WEEPING_CAVE_VINES)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );
    }
}
