package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.init.KNEffects;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class StarBlessingEffectEvents {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        if (event.getEntity().hasEffect(KNEffects.STAR_BLESSING)) {
            event.setNewDamage(event.getNewDamage() * 0.2F);
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        if (event.getEffectInstance().getEffect().value() == KNEffects.STAR_BLESSING.get()) {
            List<net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect>> effectsToRemove = new ArrayList<>();
            for (var entry : event.getEntity().getActiveEffectsMap().entrySet()) {
                MobEffectInstance effect = entry.getValue();
                if (effect.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
                    effectsToRemove.add(effect.getEffect());
                }
            }
            for (var effect : effectsToRemove) {
                event.getEntity().removeEffect(effect);
            }
        }
    }
}