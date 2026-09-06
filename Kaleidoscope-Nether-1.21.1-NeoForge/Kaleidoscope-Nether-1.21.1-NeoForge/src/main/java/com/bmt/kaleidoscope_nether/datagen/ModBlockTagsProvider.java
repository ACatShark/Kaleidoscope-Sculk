package com.bmt.kaleidoscope_nether.datagen;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.api.KNTags;
import com.bmt.kaleidoscope_nether.init.KNBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, KaleidoscopeNether.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(KNTags.Blocks.SOUL_SOIL_SAND)
                .add(
                        Blocks.SOUL_SAND,
                        Blocks.SOUL_SOIL
                );

        tag(BlockTags.CLIMBABLE)
                .add(
                        KNBlocks.WEEPING_CAVE_VINES.get(),
                        KNBlocks.WEEPING_CAVE_VINES_PLANT.get(),
                        KNBlocks.TWISTING_CAVE_VINES.get(),
                        KNBlocks.TWISTING_CAVE_VINES_PLANT.get()
                );
    }
}
