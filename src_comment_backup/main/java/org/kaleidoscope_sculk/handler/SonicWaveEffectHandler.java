package org.kaleidoscope_sculk.handler;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.register.ModEffects;

@EventBusSubscriber(modid = Kaleidoscope_sculk.MODID)
public class SonicWaveEffectHandler {

    @SubscribeEvent
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        MobEffectInstance newEffect = event.getEffectInstance();

        if (newEffect == null || !newEffect.getEffect().is(ModEffects.SONIC_WAVE.getDelegate())) {
            return;
        }

        var entity = event.getEntity();
        if (!(entity instanceof Player player)) return;
        if (player.level().isClientSide) return;

        MobEffectInstance existingEffect = player.getEffect(ModEffects.SONIC_WAVE.getDelegate());

        if (existingEffect != null) {
            // 与原版叠加规则一致：取较高等级、较长持续时间，而不是把等级相加导致伤害失控
            int totalAmplifier = Math.max(existingEffect.getAmplifier(), newEffect.getAmplifier());
            int totalDuration = Math.max(existingEffect.getDuration(), newEffect.getDuration());

            totalAmplifier = Math.min(totalAmplifier, 20);

            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);

            player.removeEffect(ModEffects.SONIC_WAVE.getDelegate());

            player.addEffect(new MobEffectInstance(
                    ModEffects.SONIC_WAVE.getDelegate(),
                    totalDuration,
                    totalAmplifier
            ));
        }
    }
}
