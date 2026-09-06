package org.kaleidoscope_sculk.handler;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.register.ModEffects;

@EventBusSubscriber(modid = Kaleidoscope_sculk.MODID)
public class SonicWaveEffectHandler {

    private static Holder<MobEffect> sonicWaveCache;

    private static Holder<MobEffect> sonicWave() {
        Holder<MobEffect> holder = sonicWaveCache;
        if (holder == null) {
            holder = ModEffects.SONIC_WAVE.getDelegate();
            sonicWaveCache = holder;
        }
        return holder;
    }

    @SubscribeEvent
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        var entity = event.getEntity();
        if (!(entity instanceof Player player)) return;
        if (player.level().isClientSide) return;

        MobEffectInstance newEffect = event.getEffectInstance();
        if (newEffect == null || !newEffect.getEffect().is(sonicWave())) {
            return;
        }

        MobEffectInstance existingEffect = player.getEffect(sonicWave());

        if (existingEffect != null) {

            int totalAmplifier = Math.max(existingEffect.getAmplifier(), newEffect.getAmplifier());
            int totalDuration = Math.max(existingEffect.getDuration(), newEffect.getDuration());

            totalAmplifier = Math.min(totalAmplifier, 20);

            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);

            player.removeEffect(sonicWave());

            player.addEffect(new MobEffectInstance(
                    sonicWave(),
                    totalDuration,
                    totalAmplifier
            ));
        }
    }
}
