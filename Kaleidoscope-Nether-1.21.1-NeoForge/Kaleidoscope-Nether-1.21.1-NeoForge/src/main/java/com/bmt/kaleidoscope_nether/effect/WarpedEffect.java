package com.bmt.kaleidoscope_nether.effect;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.init.KNEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class WarpedEffect extends MobEffect {
    public WarpedEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }

    @Override
    public void onEffectAdded(@NotNull LivingEntity livingEntity, int amplifier) {
        super.onEffectAdded(livingEntity, amplifier);

        if (!(livingEntity instanceof Player player)) {
            return;
        }

        double range = 40.0F;

        TargetingConditions targetingCondition = TargetingConditions.forCombat()
                .ignoreLineOfSight()
                .selector((e) -> {
                    if (e instanceof Mob mob) {
                        return mob.getTarget() == player;
                    }
                    return false;
                });

        player.level().getNearbyEntities(
                Mob.class,
                targetingCondition,
                player,
                player.getBoundingBox().inflate(range)
        ).forEach(entityTargetingCaster -> {
            entityTargetingCaster.setTarget(null);
            entityTargetingCaster.targetSelector.getAvailableGoals().forEach(WrappedGoal::stop);
            entityTargetingCaster.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
        });
    }

    @SubscribeEvent
    public static void onDealDamage(LivingIncomingDamageEvent event) {
        Entity sourceEntity = event.getSource().getEntity();
        if (sourceEntity instanceof LivingEntity livingAttacker) {
            if (livingAttacker.hasEffect(KNEffects.WARPED)) {
                livingAttacker.removeEffect(KNEffects.WARPED);
            }
        }
    }
}