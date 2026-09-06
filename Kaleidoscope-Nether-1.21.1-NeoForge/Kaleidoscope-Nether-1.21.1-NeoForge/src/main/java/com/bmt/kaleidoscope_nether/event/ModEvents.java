package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.init.KNEffects;
import com.bmt.kaleidoscope_nether.init.KNPotions;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class ModEvents {
    private static PotionContents getArrowPotionContents(Arrow arrow) {
        ItemStack pickupStack = arrow.getPickupItemStackOrigin();
        return pickupStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        if (event.getSource().getDirectEntity() instanceof Arrow arrow) {
            PotionContents potionContents = getArrowPotionContents(arrow);
            if (potionContents.potion().isPresent() &&
                    potionContents.potion().get().value() == KNPotions.MYSTERIOUS_POISON.get()) {

                MobEffectInstance instance = event.getEntity().getEffect(KNEffects.MYSTERIOUS_POISON);

                if (instance != null) {
                    event.getEntity().addEffect(new MobEffectInstance(
                            KNEffects.MYSTERIOUS_POISON,
                            1500 / 8,
                            instance.getAmplifier() + 1
                    ));
                } else {
                    event.getEntity().addEffect(new MobEffectInstance(
                            KNEffects.MYSTERIOUS_POISON,
                            1500,
                            0
                    ));
                }
            }
        }
    }
}