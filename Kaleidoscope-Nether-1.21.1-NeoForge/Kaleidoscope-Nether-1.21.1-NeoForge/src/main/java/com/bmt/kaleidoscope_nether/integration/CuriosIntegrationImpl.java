package com.bmt.kaleidoscope_nether.integration;

import net.minecraft.world.entity.player.Player;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

class CuriosIntegrationImpl {
    static boolean hasItemInCurios(Player player, Class<?> itemClass) {
        try {
            List<SlotResult> curios = CuriosApi.getCuriosInventory(player)
                    .map(handler -> handler.findCurios(stack -> itemClass.isInstance(stack.getItem())))
                    .orElse(List.of());

            return !curios.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
