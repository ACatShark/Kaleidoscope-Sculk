package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.crafting.recipe.StarDustUpgradeRecipe;
import com.bmt.kaleidoscope_nether.crafting.serializer.StarDustUpgradeRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KNRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, KaleidoscopeNether.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, KaleidoscopeNether.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, StarDustUpgradeRecipeSerializer> STAR_DUST_UPGRADE_SERIALIZER =
            RECIPE_SERIALIZERS.register("star_dust", StarDustUpgradeRecipeSerializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<StarDustUpgradeRecipe>> STAR_DUST_UPGRADE_RECIPE =
            RECIPE_TYPES.register("star_dust", () -> RecipeType.simple(
                    ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "star_dust")
            ));

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}