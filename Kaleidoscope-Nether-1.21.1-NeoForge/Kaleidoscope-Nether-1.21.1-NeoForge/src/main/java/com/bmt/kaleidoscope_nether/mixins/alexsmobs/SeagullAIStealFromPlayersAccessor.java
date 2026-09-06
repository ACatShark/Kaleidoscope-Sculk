package com.bmt.kaleidoscope_nether.mixins.alexsmobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.github.alexthe666.alexsmobs.entity.ai.SeagullAIStealFromPlayers;

import net.minecraft.world.entity.player.Player;

@Mixin(SeagullAIStealFromPlayers.class)
public interface SeagullAIStealFromPlayersAccessor {
    @Accessor(value = "target", remap = false)
    Player KN$getTarget();
}
