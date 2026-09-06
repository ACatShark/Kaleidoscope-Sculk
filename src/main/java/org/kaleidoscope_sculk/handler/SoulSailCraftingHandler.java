package org.kaleidoscope_sculk.handler;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.component.SoulSailData;
import org.kaleidoscope_sculk.item.SoulSailItem;

/**
 * 修复魂幡升级合成丢失经验数据的问题。
 * <p>
 * 升级配方 {@code soul_sail_full_to_thousand}、{@code thousand_soul_sail_full_to_myriad}
 * 使用 vanilla 无序合成，产出品不会自动继承输入满级魂幡上的 {@code SoulSailData} 组件，
 * 导致玩家辛苦存下的经验在升级时被清零。这里在合成完成时手动把经验与朝向迁移到产物。
 */
@EventBusSubscriber(modid = Kaleidoscope_sculk.MODID)
public class SoulSailCraftingHandler {

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        
        ItemStack crafted = event.getCrafting();

        
        if (!(crafted.getItem() instanceof SoulSailItem)) {
            return;
        }
        if (SoulSailItem.isFullItem(crafted)) {
            return;
        }

        
        Container inventory = event.getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack ingredient = inventory.getItem(i);
            if (ingredient.getItem() instanceof SoulSailItem && SoulSailItem.isFullItem(ingredient)) {
                SoulSailData src = SoulSailItem.getSailData(ingredient);
                SoulSailItem.SailType newType = ((SoulSailItem) crafted.getItem()).getType();
                
                SoulSailItem.setSailData(crafted, src.withType(newType.name));
                break;
            }
        }
    }
}
