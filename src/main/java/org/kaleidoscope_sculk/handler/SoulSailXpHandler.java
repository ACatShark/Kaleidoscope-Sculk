package org.kaleidoscope_sculk.handler;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.item.SoulSailItem;

@EventBusSubscriber(modid = Kaleidoscope_sculk.MODID)
public class SoulSailXpHandler {

    @SubscribeEvent
    public static void onPlayerXpPickup(PlayerXpEvent.PickupXp event) {
        Player player = event.getEntity();
        ExperienceOrb orb = event.getOrb();
        int xpPoints = orb.getValue();

        ItemStack mainHand = player.getMainHandItem();
        if (isNonFullSoulSail(mainHand) && tryStoreXp(player, mainHand, xpPoints)) {
            cancelAndDiscard(event, orb);
            return;
        }

        ItemStack offhand = player.getOffhandItem();
        if (isNonFullSoulSail(offhand) && tryStoreXp(player, offhand, xpPoints)) {
            cancelAndDiscard(event, orb);
            return;
        }

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (isNonFullSoulSail(stack) && tryStoreXp(player, stack, xpPoints)) {
                cancelAndDiscard(event, orb);
                return;
            }
        }
    }

    private static void cancelAndDiscard(PlayerXpEvent.PickupXp event, ExperienceOrb orb) {
        event.setCanceled(true);
        orb.discard();
    }

    private static boolean isNonFullSoulSail(ItemStack stack) {
        return SoulSailItem.isNonFullItem(stack);
    }

    private static boolean tryStoreXp(Player player, ItemStack stack, int xpPoints) {
        if (!(stack.getItem() instanceof SoulSailItem sailItem)) {
            return false;
        }

        int beforeXp = SoulSailItem.getStoredXp(stack);
        boolean becameFull = sailItem.storeXp(stack, xpPoints);

        
        if (SoulSailItem.getStoredXp(stack) <= beforeXp) {
            return false;
        }

        if (becameFull) {
            int slot = findSlot(player, stack);
            if (slot != -1) {
                player.getInventory().setItem(slot, SoulSailItem.convertToFullItem(stack));
            }
        }

        player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.3f, 1.5f);
        return true;
    }

    private static int findSlot(Player player, ItemStack target) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            if (player.getInventory().getItem(i) == target) {
                return i;
            }
        }
        return -1;
    }
}
