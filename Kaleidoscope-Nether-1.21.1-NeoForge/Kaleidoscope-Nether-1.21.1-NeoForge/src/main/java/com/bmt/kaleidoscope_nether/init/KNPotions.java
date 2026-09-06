package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KNPotions {
    public static final DeferredRegister<Potion> POISONS = DeferredRegister.create(Registries.POTION, KaleidoscopeNether.MOD_ID);

    public static final DeferredHolder<Potion, Potion> MYSTERIOUS_POISON = POISONS.register("mysterious_poison", () -> new Potion(new MobEffectInstance(
            KNEffects.MYSTERIOUS_POISON,
            1500,
            0
    )));

}
