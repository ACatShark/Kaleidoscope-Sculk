package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.init.KNEffects;
import com.bmt.kaleidoscope_nether.item.GhastPendantItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class HostilityHandler {

    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof Mob mob)) {
            return;
        }

        LivingEntity newTarget = event.getNewAboutToBeSetTarget();
        if (!(newTarget instanceof Player player)) {
            return;
        }

        if (mob instanceof Ghast && GhastPendantItem.isWearingGhastPendant(player)) {
            event.setNewAboutToBeSetTarget(null);
            return;
        }

        if (player.hasEffect(KNEffects.WARPED)) {
            event.setNewAboutToBeSetTarget(null);
        }
    }
}