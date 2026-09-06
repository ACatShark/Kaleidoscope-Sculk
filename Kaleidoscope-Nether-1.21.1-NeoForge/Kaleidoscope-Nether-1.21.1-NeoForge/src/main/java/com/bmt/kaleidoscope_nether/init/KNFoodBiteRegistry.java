package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class KNFoodBiteRegistry {
    public static ResourceLocation SOUL_LAMB_CHOP;
    public static ResourceLocation BRAISED_PORK_RICE;
    public static ResourceLocation BRAISED_LION_HEAD;
    public static ResourceLocation CORN_CARROT_PORK_RIB_SOUP;

    public static void init() {
        FoodBiteRegistry registry = new FoodBiteRegistry();
        SOUL_LAMB_CHOP = registry.registerFoodData(KaleidoscopeNether.id("soul_lamb_chop"), FoodBiteRegistry.FoodData
                .create(3, KNFoods.SOUL_LAMB_CHOP_BLOCK, KNFoods.SOUL_LAMB_CHOP_ITEM)
        );

        BRAISED_PORK_RICE = registry.registerFoodData(KaleidoscopeNether.id("braised_pork_rice"), FoodBiteRegistry.FoodData
                .create(4, KNFoods.BRAISED_PORK_RICE_BLOCK, KNFoods.BRAISED_PORK_RICE_ITEM)
                .bowlAABB()
        );

        BRAISED_LION_HEAD = registry.registerFoodData(KaleidoscopeNether.id("braised_lion_head"), FoodBiteRegistry.FoodData
                .create(5, KNFoods.BRAISED_LION_HEAD_BLOCK, KNFoods.BRAISED_LION_HEAD_ITEM)
        );

        CORN_CARROT_PORK_RIB_SOUP = registry.registerFoodData(KaleidoscopeNether.id("corn_carrot_pork_rib_soup"), FoodBiteRegistry.FoodData
                .create(3, KNFoods.CORN_CARROT_PORK_RIB_SOUP_BLOCK, KNFoods.CORN_CARROT_PORK_RIB_SOUP_ITEM)
                .setLootItem(Items.FLOWER_POT)
                .soupPotAABB()
                .potSoupAnimateTick()
        );

    }

}
