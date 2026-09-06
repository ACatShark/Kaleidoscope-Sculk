package com.bmt.kaleidoscope_nether.compat.jei.category;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.crafting.recipe.StarDustUpgradeRecipe;
import com.bmt.kaleidoscope_nether.init.KNItems;
import com.bmt.kaleidoscope_nether.init.KNRecipes;
import com.google.common.collect.Lists;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StarDustUpgradeRecipeCategory implements IRecipeCategory<RecipeHolder<StarDustUpgradeRecipe>> {
    public static final RecipeType<RecipeHolder<StarDustUpgradeRecipe>> TYPE = RecipeType.createRecipeHolderType(
            ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "star_dust")
    );

    private static final ResourceLocation BG = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "textures/gui/jei/star_dust.png");
    private static final Component TITLE = Component.translatable("jei.kaleidoscope_nether.star_dust");

    public static final int WIDTH = 116;
    public static final int HEIGHT = 54;

    private final IDrawable bgDraw;
    private final IDrawable iconDraw;

    public StarDustUpgradeRecipeCategory(IGuiHelper guiHelper) {
        this.bgDraw = guiHelper.createDrawable(BG, 0, 0, WIDTH, HEIGHT);
        this.iconDraw = guiHelper.createDrawableItemStack(KNItems.STAR_DUST.get().getDefaultInstance());
    }

    public static List<RecipeHolder<StarDustUpgradeRecipe>> getRecipes() {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) {
            return List.of();
        }
        List<RecipeHolder<StarDustUpgradeRecipe>> recipes = Lists.newArrayList();
        recipes.addAll(level.getRecipeManager().getAllRecipesFor(KNRecipes.STAR_DUST_UPGRADE_RECIPE.get()));
        return recipes;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<StarDustUpgradeRecipe> recipe, @NotNull IFocusGroup focuses) {
        Ingredient input = recipe.value().getIngredient();
        ItemStack output = recipe.value().getResult();
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 18)
                .addIngredients(input);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 18)
                .addItemStack(output);
    }

    @Override
    public void draw(@NotNull RecipeHolder<StarDustUpgradeRecipe> recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.bgDraw.draw(guiGraphics);
    }

    @Override
    public @NotNull RecipeType<RecipeHolder<StarDustUpgradeRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return TITLE;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    @Nullable
    public IDrawable getIcon() {
        return iconDraw;
    }
}