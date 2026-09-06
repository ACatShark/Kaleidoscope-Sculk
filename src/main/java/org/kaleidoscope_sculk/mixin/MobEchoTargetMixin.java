package org.kaleidoscope_sculk.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.kaleidoscope_sculk.register.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 回响效果的「无法被索敌」逻辑。
 * <p>
 * 直接拦截 {@link Mob#getTarget()}：只要目标生物带有回响效果，
 * 无论该目标此前是否已被锁定，AI 视角下都视为没有目标，
 * 因此任何敌对生物都无法锁定、追踪或攻击带回响的实体。
 * <p>
 * 性能说明：{@link Mob#getTarget()} 是极高频率调用点（每个 AI goal 每 tick 多次读取），
 * 因此做两点优化而不改变玩法：① 用静态缓存复用效果 Holder，避免每 tick 反复解析注册表；
 * ② 先对最常见的「无目标」情况短路。
 */
@Mixin(Mob.class)
public class MobEchoTargetMixin {

    @Unique
    private static Holder<MobEffect> echoCache;

    @Unique
    private static Holder<MobEffect> echo() {
        Holder<MobEffect> holder = echoCache;
        if (holder == null) {
            holder = ModEffects.ECHO.getDelegate();
            echoCache = holder;
        }
        return holder;
    }

    @Inject(method = "getTarget", at = @At("RETURN"), cancellable = true)
    private void kaleidoscope_sculk$hideEchoHolderFromTargeting(CallbackInfoReturnable<LivingEntity> cir) {
        LivingEntity target = cir.getReturnValue();
        if (target == null) return;
        if (target.hasEffect(echo())) {
            cir.setReturnValue(null);
        }
    }
}
