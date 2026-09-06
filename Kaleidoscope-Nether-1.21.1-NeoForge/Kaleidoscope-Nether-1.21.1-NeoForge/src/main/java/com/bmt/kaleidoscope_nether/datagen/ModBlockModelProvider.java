package com.bmt.kaleidoscope_nether.datagen;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.List;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KaleidoscopeNether.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        List<String> cropBlocksId = new ArrayList<>();
        cropBlocksId.add("poisonous_fruit");
        cropBlocksId.add("soul_pepper");
        for (int i = 0; i < 8; i++) {
            for (String string : cropBlocksId) {
                cross("block/%s/stage%d".formatted(string, i), KaleidoscopeNether.id("block/%s/stage%d".formatted(string, i))).renderType("cutout");
            }
        }

        cross("twisting_cave_vines",KaleidoscopeNether.id("block/twisting_cave_vines")).renderType("cutout");
        cross("twisting_cave_vines_berries",KaleidoscopeNether.id("block/twisting_cave_vines_berries")).renderType("cutout");

        cross("twisting_cave_vines_plant",KaleidoscopeNether.id("block/twisting_cave_vines_plant")).renderType("cutout");
        cross("twisting_cave_vines_plant_berries",KaleidoscopeNether.id("block/twisting_cave_vines_plant_berries")).renderType("cutout");

        cross("weeping_cave_vines_plant",KaleidoscopeNether.id("block/weeping_cave_vines_plant")).renderType("cutout");
        cross("weeping_cave_vines_plant_berries",KaleidoscopeNether.id("block/weeping_cave_vines_plant_berries")).renderType("cutout");

        cross("weeping_cave_vines",KaleidoscopeNether.id("block/weeping_cave_vines")).renderType("cutout");
        cross("weeping_cave_vines_berries",KaleidoscopeNether.id("block/weeping_cave_vines_berries")).renderType("cutout");

    }


}
