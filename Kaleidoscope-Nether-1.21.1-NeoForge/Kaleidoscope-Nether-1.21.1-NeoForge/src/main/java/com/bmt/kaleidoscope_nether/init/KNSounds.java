package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KNSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, KaleidoscopeNether.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> LAVA_ROASTED_CHICKEN_EAT = registerSoundEvent("lava_roasted_chicken_eat");
    public static final DeferredHolder<SoundEvent, SoundEvent> STAR_DUST_REPAIR = registerSoundEvent("star_dust_repair");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        ResourceLocation location = KaleidoscopeNether.id(name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(location));
    }
}