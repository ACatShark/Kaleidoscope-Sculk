package org.kaleidoscope_sculk.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.common.ItemAbility;

import static net.neoforged.neoforge.common.ItemAbilities.SWORD_DIG;

public class ErosionKnifeItem extends SwordItem {

    public ErosionKnifeItem() {
        super(Tiers.STONE, new Properties()
                .durability(500)
                .attributes(SwordItem.createAttributes(Tiers.STONE, 3, -2.0f))
        );
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        
        return itemAbility == SWORD_DIG;
    }
}
