package org.kaleidoscope_sculk.handler;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.register.ModEffects;

@EventBusSubscriber(modid = Kaleidoscope_sculk.MODID)
public class AbyssDamageHandler {

    private static Holder<MobEffect> abyssCache;

    private static Holder<MobEffect> abyss() {
        Holder<MobEffect> holder = abyssCache;
        if (holder == null) {
            holder = ModEffects.ABYSS.getDelegate();
            abyssCache = holder;
        }
        return holder;
    }

    private static final int START_DEPTH = 0;
    private static final int MAX_DEPTH = -60;
    private static final float BASE_MAX_BONUS_DAMAGE = 5.0f;
    private static final float BONUS_PER_LEVEL = 3.0f;


    private static float calculateBonusDamage(double y, int amplifier) {
        if (y >= START_DEPTH) {
            return 0.0f;
        }

        double depth = START_DEPTH - y;

        double maxDepthRange = START_DEPTH - MAX_DEPTH;


        double clampedDepth = Math.min(depth, maxDepthRange);

        double k = 15.0;

        double ratio = clampedDepth / (clampedDepth + k);

        float maxBonus = BASE_MAX_BONUS_DAMAGE + (amplifier * BONUS_PER_LEVEL);

        float bonusDamage = (float) (ratio * maxBonus);

        return Math.min(maxBonus, Math.max(0.0f, bonusDamage));
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        var source = event.getSource();
        LivingEntity attacker = null;

        if (source.getEntity() instanceof LivingEntity living) {
            attacker = living;
        } else if (source.getDirectEntity() instanceof LivingEntity living) {
            attacker = living;
        }

        if (!(attacker instanceof Player player)) return;

        MobEffectInstance effect = player.getEffect(abyss());
        if (effect == null) return;

        int amplifier = effect.getAmplifier();  

        float bonusDamage = calculateBonusDamage(player.getY(), amplifier);

        if (bonusDamage > 0.0f) {
            float originalDamage = event.getAmount();
            float newDamage = originalDamage + bonusDamage;
            event.setAmount(newDamage);
        }
    }
}
