package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.init.KNEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class DamageEventHandler {

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(KNEffects.TROPICAL_STRIDER)) {
            if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
                event.setCanceled(true);
                entity.clearFire();
            }
        }
    }
}