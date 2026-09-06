package org.kaleidoscope_sculk.handler;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.effect.SculkDashEffect;
import org.kaleidoscope_sculk.register.ModEffects;

@EventBusSubscriber(modid = Kaleidoscope_sculk.MODID)
public class SculkDashCleanupHandler {

    private static Holder<MobEffect> sculkDashCache;

    private static Holder<MobEffect> sculkDash() {
        Holder<MobEffect> holder = sculkDashCache;
        if (holder == null) {
            holder = ModEffects.SCULK_DASH.getDelegate();
            sculkDashCache = holder;
        }
        return holder;
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEffect().is(sculkDash())) {
            SculkDashEffect.clearModifiers(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() != null &&
                event.getEffectInstance().getEffect().is(sculkDash())) {
            SculkDashEffect.clearModifiers(event.getEntity());
        }
    }
}
