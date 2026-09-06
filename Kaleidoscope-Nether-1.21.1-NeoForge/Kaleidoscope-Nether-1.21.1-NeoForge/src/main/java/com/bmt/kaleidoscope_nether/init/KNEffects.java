package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.effect.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KNEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, KaleidoscopeNether.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> CRIMSON = EFFECTS.register("crimson",
            () -> new CrimsonEffect(0xFF0000));

    public static final DeferredHolder<MobEffect, MobEffect> WARPED = EFFECTS.register("warped",
            () -> new WarpedEffect(0x00FFA2));

    public static final DeferredHolder<MobEffect, MobEffect> STAR_BLESSING = EFFECTS.register("star_blessing",
            () -> new StarBlessingEffect(0x87CEEB));

    public static final DeferredHolder<MobEffect, MobEffect> GHOST = EFFECTS.register("ghost",
            () -> new GhostEffect(0x4A90E2));

    public static final DeferredHolder<MobEffect, MobEffect> TROPICAL_STRIDER = EFFECTS.register("tropical_strider",
            () -> new TropicalStriderEffect(0xCC3300));

    public static final DeferredHolder<MobEffect, MobEffect> MYSTERIOUS_POISON = EFFECTS.register("mysterious_poison",
            () -> new MysteriousPoisonEffect(0x8A2BE2));
}