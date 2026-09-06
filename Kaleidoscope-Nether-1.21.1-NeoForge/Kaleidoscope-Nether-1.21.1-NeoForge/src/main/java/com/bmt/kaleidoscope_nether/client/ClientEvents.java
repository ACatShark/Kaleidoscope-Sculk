package com.bmt.kaleidoscope_nether.client;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.client.renderer.entity.BlazeHeartProjectileRenderer;
import com.bmt.kaleidoscope_nether.init.KNEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(KNEntities.BLAZE_HEART_PROJECTILE.get(), BlazeHeartProjectileRenderer::new);
    }
}
