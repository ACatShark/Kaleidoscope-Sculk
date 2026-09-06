package com.bmt.kaleidoscope_nether.mixins.alexsmobs;

import com.bmt.kaleidoscope_nether.hooks.alexsmobs.SeagullAIStealFromPlayersMixinHooks;
import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.github.alexthe666.alexsmobs.entity.ai.SeagullAIStealFromPlayers;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SeagullAIStealFromPlayers.class)
public class  SeagullAIStealFromPlayersMixin {

    @Shadow(remap = false)
    @Final
    private EntitySeagull seagull;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void beforeItemShrink(CallbackInfo ci, ItemStack foodStack, ItemStack copy) {
        SeagullAIStealFromPlayersMixinHooks.beforeItemShrink(seagull,SeagullAIStealFromPlayers.class.cast(this), ci, foodStack, copy);
    }
}
