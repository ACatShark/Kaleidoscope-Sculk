package com.bmt.kaleidoscope_nether.init;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

public class KNBrewingRecipes {

    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(
                Potions.AWKWARD,
                KNItems.POISONOUS_FRUIT.get(),
                KNPotions.MYSTERIOUS_POISON
        );
    }
}
