package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.block.*;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.StoveBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

public class KNBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, KaleidoscopeNether.MOD_ID);

    public static final Supplier<BlockBehaviour.Properties> CROP_DEFAULT_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);

    public static final Supplier<BlockBehaviour.Properties> CAVE_VINES_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY);

    public static final DeferredHolder<Block, PoisonousFruit> POISONOUS_FRUIT = BLOCKS.register("poisonous_fruit",
            () -> new PoisonousFruit(CROP_DEFAULT_PROPERTIES.get()));

    public static final DeferredHolder<Block, SoulPepper> SOUL_PEPPER = BLOCKS.register("soul_pepper",
            () -> new SoulPepper(CROP_DEFAULT_PROPERTIES.get()));

    public static final DeferredHolder<Block, TwistingCaveVinesPlant> TWISTING_CAVE_VINES_PLANT = BLOCKS.register("twisting_cave_vines_plant",
            () -> new TwistingCaveVinesPlant(CAVE_VINES_PROPERTIES.get()));

    public static final DeferredHolder<Block, TwistingCaveVinesHead> TWISTING_CAVE_VINES = BLOCKS.register("twisting_cave_vines",
            () -> new TwistingCaveVinesHead(CAVE_VINES_PROPERTIES.get()));

    public static final DeferredHolder<Block, WeepingCaveVinesPlant> WEEPING_CAVE_VINES_PLANT = BLOCKS.register("weeping_cave_vines_plant",
            () -> new WeepingCaveVinesPlant(CAVE_VINES_PROPERTIES.get()));

    public static final DeferredHolder<Block, WeepingCaveVinesHead> WEEPING_CAVE_VINES = BLOCKS.register("weeping_cave_vines",
            () -> new WeepingCaveVinesHead(CAVE_VINES_PROPERTIES.get()));

    // 下界炉灶
    public static final DeferredHolder<Block, StoveBlock> NETHER_STOVE = BLOCKS.register("nether_stove",
            StoveBlock::new);
}
