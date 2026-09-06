package org.kaleidoscope_sculk.register;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;

@EventBusSubscriber(modid = Kaleidoscope_sculk.MODID, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ANCIENT_BONE_FRAGMENT_PROJECTILE.get(),
                ThrownItemRenderer::new);
    }
}
