package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.effect.CrimsonEffect;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = "kaleidoscope_nether")
public class KNEventSubscriber {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            float originalDamage = event.getNewDamage();
            float modifiedDamage = CrimsonEffect.calculateDamageBonus(attacker, originalDamage);
            if (modifiedDamage != originalDamage) {
                event.setNewDamage(modifiedDamage);
            }
        }
    }
}