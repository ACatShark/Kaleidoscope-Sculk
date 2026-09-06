package com.bmt.kaleidoscope_nether.integration;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;

public class KaleidoscopeDollIntegration {
    private static final String DOLL_MOD_ID = "kaleidoscope_doll";

    public static void register(IEventBus modEventBus) {
        if (ModList.get().isLoaded(DOLL_MOD_ID)) {
            KaleidoscopeDollIntegrationImpl.register(modEventBus);
        }
    }
}
