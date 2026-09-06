package com.bmt.kaleidoscope_nether.crafting.serializer;

import com.bmt.kaleidoscope_nether.crafting.recipe.StarDustUpgradeRecipe;
import com.bmt.kaleidoscope_nether.init.KNRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class StarDustUpgradeRecipeSerializer implements RecipeSerializer<StarDustUpgradeRecipe> {
    public static final MapCodec<StarDustUpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(StarDustUpgradeRecipe::getIngredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(StarDustUpgradeRecipe::getResult)
            ).apply(instance, (ingredient, result) ->
                    new StarDustUpgradeRecipe(KNRecipes.STAR_DUST_UPGRADE_RECIPE.get(), KNRecipes.STAR_DUST_UPGRADE_SERIALIZER.get(), ingredient, result)
            )
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, StarDustUpgradeRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, StarDustUpgradeRecipe::getIngredient,
            ItemStack.STREAM_CODEC, StarDustUpgradeRecipe::getResult,
            (ingredient, result) ->
                    new StarDustUpgradeRecipe(KNRecipes.STAR_DUST_UPGRADE_RECIPE.get(), KNRecipes.STAR_DUST_UPGRADE_SERIALIZER.get(), ingredient, result)
    );

    @Override
    public @NotNull MapCodec<StarDustUpgradeRecipe> codec() {
        return CODEC;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, StarDustUpgradeRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}