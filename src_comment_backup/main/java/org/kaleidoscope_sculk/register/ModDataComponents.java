package org.kaleidoscope_sculk.register;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.component.SoulSailData;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Kaleidoscope_sculk.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SoulSailData>> SOUL_SAIL_DATA =
            DATA_COMPONENTS.register("soul_sail_data",
                    () -> DataComponentType.<SoulSailData>builder()
                            .persistent(SoulSailData.CODEC)
                            .networkSynchronized(SoulSailData.STREAM_CODEC)
                            .build());
}
