package org.kaleidoscope_sculk.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.common.ItemAbility;

import static net.neoforged.neoforge.common.ItemAbilities.SWORD_DIG;

public class SilentKitchenKnife extends SwordItem {

    private static final float ATTACK_DAMAGE = 5.0f;
    private static final float ATTACK_SPEED = -2.2f;

    public SilentKitchenKnife() {
        super(Tiers.DIAMOND, new Properties()
                .durability(1800)
                .attributes(SwordItem.createAttributes(Tiers.DIAMOND, ATTACK_DAMAGE, ATTACK_SPEED))
        );
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        
        return itemAbility == SWORD_DIG;
    }
}
