package com.bmt.kaleidoscope_nether.advancement;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KNAdvancementTriggerRegistry {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
        DeferredRegister.create(Registries.TRIGGER_TYPE, KaleidoscopeNether.MOD_ID);

    public static final DeferredHolder<CriterionTrigger<?>, KNAdvancementTrigger> SIMPLE_ID =
        TRIGGERS.register("simple_id", KNAdvancementTrigger::new);
}
