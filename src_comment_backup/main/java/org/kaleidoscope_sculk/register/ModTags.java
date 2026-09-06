package org.kaleidoscope_sculk.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;

public class ModTags {

    /**
     * 厨刀标签，挂在 {@code data/kaleidoscope_sculk/tags/item/kitchen_knives.json} 下，
     * 支持通过数据包扩展，也兼容其他模组的厨刀。
     */
    public static final TagKey<Item> KITCHEN_KNIVES = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(Kaleidoscope_sculk.MODID, "kitchen_knives")
    );
}
