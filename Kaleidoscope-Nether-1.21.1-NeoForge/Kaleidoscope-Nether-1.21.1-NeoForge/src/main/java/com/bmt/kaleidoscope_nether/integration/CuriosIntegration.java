package com.bmt.kaleidoscope_nether.integration;

import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.ModList;

public class CuriosIntegration {
    private static final String CURIOS_MOD_ID = "curios";

    public static boolean hasItemInCurios(Player player, Class<?> itemClass) {
        if (ModList.get().isLoaded(CURIOS_MOD_ID)) {
            return CuriosIntegrationImpl.hasItemInCurios(player, itemClass);
        }
        return false;
    }
}