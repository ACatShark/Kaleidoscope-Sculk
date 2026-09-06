package com.bmt.kaleidoscope_nether.item;

import com.bmt.kaleidoscope_nether.config.MainConfig;
import com.bmt.kaleidoscope_nether.crafting.recipe.StarDustUpgradeRecipe;
import com.bmt.kaleidoscope_nether.init.KNSounds;
import com.bmt.kaleidoscope_nether.init.KNRecipes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class StarDustItem extends Item {

    public StarDustItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack starDustStack, @NotNull ItemStack incoming, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess carriedSlotAccessor) {
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) {
            return false;
        }

        if (incoming.isEmpty()) {
            return false;
        } else {
            if (tryUpgradeItem(player, starDustStack, incoming, carriedSlotAccessor)) {
                return true;
            }
            return repairTargetItem(player, starDustStack, incoming, carriedSlotAccessor);
        }
    }

    @Override
    public boolean overrideStackedOnOther(@NotNull ItemStack starDustStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) {
            return false;
        }

        ItemStack targetStack = slot.getItem();
        if (targetStack.isEmpty()) {
            return false;
        } else {
            if (tryUpgradeItem(player, starDustStack, targetStack, slot)) {
                return true;
            }
            return repairTargetItem(player, starDustStack, targetStack, slot);
        }
    }

    private boolean tryUpgradeItem(Player player, ItemStack starDustStack, ItemStack targetStack, Object target) {
        if (player.level().isClientSide()) {
            return false;
        }

        ServerLevel serverLevel = (ServerLevel) player.level();
        Optional<RecipeHolder<StarDustUpgradeRecipe>> recipeHolder = serverLevel.getRecipeManager()
                .getRecipeFor(KNRecipes.STAR_DUST_UPGRADE_RECIPE.get(), new SingleRecipeInput(targetStack), serverLevel);

        if (recipeHolder.isEmpty()) {
            return false;
        }

        if (starDustStack.isEmpty()) {
            return false;
        }

        StarDustUpgradeRecipe recipe = recipeHolder.get().value();

        starDustStack.shrink(1);
        targetStack.shrink(1);

        ItemStack result = recipe.getResult().copy();

        if (target instanceof Slot slot) {
            if (!targetStack.isEmpty()) {
                if (!player.addItem(result)) {
                    player.drop(result, false);
                }
            } else {
                slot.set(result);
            }
            slot.setChanged();
        } else if (target instanceof SlotAccess slotAccess) {
            slotAccess.set(result);
        }

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                KNSounds.STAR_DUST_REPAIR.get(), SoundSource.PLAYERS, 0.8F, 1.0F);
        return true;
    }

    private boolean repairTargetItem(Player player, ItemStack starDustStack, ItemStack targetStack, Object target) {
        if (!targetStack.isDamageableItem()) {
            return false;
        }

        if (targetStack.getDamageValue() == 0) {
            return false;
        }

        if (starDustStack.isEmpty()) {
            return false;
        }

        return performRepair(player, starDustStack, targetStack, target);
    }

    private boolean performRepair(Player player, ItemStack starDustStack, ItemStack targetStack, Object target) {
        if (player.level().isClientSide()) {
            return false;
        }

        int maxDurability = targetStack.getMaxDamage();
        int currentDamage = targetStack.getDamageValue();

        int repairAmount = (int) (maxDurability * MainConfig.STAR_DUST_REPAIR_PERCENTAGE.get() / 100.0f) + MainConfig.STAR_DUST_REPAIR_FLAT.get();
        int newDamage = Math.max(0, currentDamage - repairAmount);

        targetStack.setDamageValue(newDamage);

        starDustStack.shrink(1);

        if (target instanceof Slot slot) {
            slot.setChanged();
        } else if (target instanceof SlotAccess slotAccess) {
            slotAccess.set(targetStack);
        }

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                KNSounds.STAR_DUST_REPAIR.get(), SoundSource.PLAYERS, 0.8F, 1.0F);

        return true;
    }
}