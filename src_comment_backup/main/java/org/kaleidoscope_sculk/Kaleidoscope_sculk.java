package org.kaleidoscope_sculk;

import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.DrinkBlockEntity;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.kaleidoscope_sculk.network.SonicBoomPacket;
import org.kaleidoscope_sculk.register.ModBlocks;
import org.kaleidoscope_sculk.register.ModDamageTypes;
import org.kaleidoscope_sculk.register.ModDataComponents;
import org.kaleidoscope_sculk.register.ModEffects;
import org.kaleidoscope_sculk.register.ModEntities;
import org.kaleidoscope_sculk.register.ModFluids;
import org.kaleidoscope_sculk.register.ModItems;
import org.kaleidoscope_sculk.register.ModPotions;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Mod(Kaleidoscope_sculk.MODID)
public class Kaleidoscope_sculk {

    public static final String MODID = "kaleidoscope_sculk";

    private static final Logger LOGGER = LogUtils.getLogger();

    public Kaleidoscope_sculk(IEventBus modEventBus) {
        ModItems.ITEMS.register(modEventBus);
        ModItems.CREATIVE_TABS.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModFluids.FLUID_TYPES.register(modEventBus);
        ModFluids.FLUIDS.register(modEventBus);
        ModPotions.POTIONS.register(modEventBus);
        ModDataComponents.DATA_COMPONENTS.register(modEventBus);
        ModDamageTypes.DAMAGE_TYPES.register(modEventBus);

        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::registerPayloads);
    }

    /**
     * 通过反射把 {@code sculk_brew_bottle} 加进主模组（森罗酒馆）酒桶方块实体的 validBlocks，
     * 这样我们的酒瓶方块才能被主模组的 DrinkBlockItem 正常放置。
     */
    @SuppressWarnings("unchecked")
    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                BlockEntityType<DrinkBlockEntity> drinkBe =
                        (BlockEntityType<DrinkBlockEntity>) com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks.DRINK_BE.get();

                Field validBlocksField = BlockEntityType.class.getDeclaredField("validBlocks");
                validBlocksField.setAccessible(true);

                Set<Block> validBlocks = (Set<Block>) validBlocksField.get(drinkBe);
                Set<Block> newValidBlocks = new HashSet<>(validBlocks);
                newValidBlocks.add(ModBlocks.SCULK_BREW_BOTTLE.get());
                validBlocksField.set(drinkBe, newValidBlocks);

                LOGGER.info("已将 sculk_brew_bottle 添加到主模组的 DRINK_BE");
            } catch (Exception e) {
                LOGGER.error("添加方块到 DRINK_BE 失败", e);
            }
        });
    }

    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(SonicBoomPacket.TYPE, SonicBoomPacket.CODEC, SonicBoomPacket::handle);
    }

    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("客户端启动 - {} 已加载", MODID);
        }
    }
}
