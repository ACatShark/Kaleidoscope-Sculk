package com.bmt.kaleidoscope_nether.crafting.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class StarDustUpgradeRecipe extends SingleItemRecipe {
    public StarDustUpgradeRecipe(RecipeType<?> type, RecipeSerializer<?> serializer, Ingredient ingredient, ItemStack result) {
        super(type, serializer, "", ingredient, result);
    }

    @Override
    public boolean matches(SingleRecipeInput inv, @NotNull Level level) {
        return this.ingredient.test(inv.getItem(0));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public ItemStack getResult() {
        return this.result;
    }
}