package org.kaleidoscope_sculk.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.register.ModItems;

@EventBusSubscriber(modid = Kaleidoscope_sculk.MODID)
public class SoulFireDropHandler {

    private static final float DROP_CHANCE = 0.03f; 

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getLevel().isClientSide()) return;

        BlockState state = event.getState();
        if (!state.is(Blocks.SOUL_FIRE)) return;

        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();

        if (level.getRandom().nextFloat() < DROP_CHANCE) {
            ItemEntity drop = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    ModItems.SOUL.get().getDefaultInstance()
            );
            drop.setDefaultPickUpDelay();
            level.addFreshEntity(drop);
        }
    }
}
