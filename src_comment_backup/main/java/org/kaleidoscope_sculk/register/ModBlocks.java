package org.kaleidoscope_sculk.register;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import static com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.WARMTH;
import static com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.VIGOR;
import static com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.MUSTARD;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.block.DeepslateCakeBlock;
import org.kaleidoscope_sculk.block.DeepslateStoveBlock;
import org.kaleidoscope_sculk.block.EchoCropBlock;
import org.kaleidoscope_sculk.block.EchoCropTopBlock;
import org.kaleidoscope_sculk.block.FoodBiteBlock;
import org.kaleidoscope_sculk.block.SculkDrinkBlock;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, Kaleidoscope_sculk.MODID);

    // 芥末效果来自森罗厨房（kaleidoscope_cookery:MUSTARD），硬依赖
    public static final int MUSTARD_DURATION = 6000; // 5 分钟

    public static final VoxelShape SHAPE_MEDIUM = Block.box(4.0, 0.0, 4.0, 12.0, 7.0, 12.0);
    public static final VoxelShape PLATE = Block.box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);
    public static final VoxelShape SHAPE_MEDIUM_WITH_MAT = Shapes.or(
            Block.box(4.0, 0.0, 4.0, 12.0, 7.0, 12.0),
            Block.box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0)
    );

    public static final VoxelShape SHAPE_PORK_RIBS = Block.box(3.0, 0.0, 3.0, 13.0, 6.0, 13.0);

    public static final VoxelShape SHAPE_PORK_RIBS_PLATE = Shapes.or(
            Block.box(3.0, 0.0, 3.0, 13.0, 6.0, 13.0),
            Block.box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0)
    );

    // 幽匿菠萝啤（酒瓶，可食用 2 次）
    public static final DeferredHolder<Block, SculkDrinkBlock> SCULK_BREW_BOTTLE =
            BLOCKS.register("sculk_brew_bottle", () -> new SculkDrinkBlock(
                    2,
                    Shapes.box(0.3125, 0.0, 0.3125, 0.6875, 0.625, 0.6875),
                    Shapes.box(0.3125, 0.0, 0.3125, 0.6875, 0.625, 0.6875)
            ));

    // 深板岩蛋糕
    public static final DeferredHolder<Block, DeepslateCakeBlock> DEEPSLATE_CAKE =
            BLOCKS.register("deepslate_cake", () -> new DeepslateCakeBlock(
                    Block.Properties.of()
                            .sound(SoundType.DEEPSLATE)
                            .strength(0.5f)
                            .noOcclusion()
                            .instabreak()
            ));

    // 灶台
    public static final DeferredHolder<Block, DeepslateStoveBlock> DEEPSLATE_STOVE =
            BLOCKS.register("deepslate_stove", () -> new DeepslateStoveBlock());

    public static final DeferredHolder<Block, FoodBiteBlock> ANCIENT_CITY_STYLE_SASHIMI_BLOCK =
            BLOCKS.register("ancient_city_style_sashimi", () -> new FoodBiteBlock(
                    new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(10)
                            .saturationModifier(1.1f)
                            .build(),
                    4,
                    PLATE,
                    List.<Supplier<Item>>of(
                            () -> Items.RED_CANDLE,       // 返还红色蜡烛
                            () -> Items.SCULK_SHRIEKER,   // 返还幽匿尖啸体
                            () -> Items.BOWL              // 返还碗
                    ),
                    ModBlocks::createMustardEffect,  // 森罗厨房的芥末效果（食用时才解析）
                    1.0f
            ));

    public static final DeferredHolder<Block, FoodBiteBlock> SCULK_STEW_BLOCK =
            BLOCKS.register("sculk_stew_block", () -> new FoodBiteBlock(
                    new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(9)
                            .saturationModifier(1.0f)
                            .build(),
                    3,
                    SHAPE_MEDIUM_WITH_MAT,
                    () -> Items.FLOWER_POT,  // 吃完返还花盆
                    () -> new MobEffectInstance(WARMTH.getDelegate(), 6000, 0),  // 森罗厨房：温暖 (05:00)
                    1.0f
            ));

    public static final DeferredHolder<Block, FoodBiteBlock> SCULK_CHICKEN_STEW_BLOCK =
            BLOCKS.register("sculk_chicken_stew_block", () -> new FoodBiteBlock(
                    new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(10)
                            .saturationModifier(1.2f)
                            .build(),
                    3,
                    SHAPE_MEDIUM_WITH_MAT,
                    () -> Items.FLOWER_POT,  // 吃完返还花盆
                    () -> new MobEffectInstance(WARMTH.getDelegate(), 2400, 0),  // 森罗厨房：温暖 (02:00)
                    1.0f
            ));

    public static final DeferredHolder<Block, FoodBiteBlock> SCULK_LAMB_CHOP_BLOCK =
            BLOCKS.register("sculk_lamb_chop_block", () -> new FoodBiteBlock(
                    new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(13)
                            .saturationModifier(2.3f)
                            .build(),
                    3,
                    PLATE,
                    List.<Supplier<Item>>of(
                            () -> Items.BOWL,                 // 返还碗
                            () -> ModItems.SCULK_FUNGUS.get() // 返还幽匿真菌
                    ),
                    () -> new MobEffectInstance(ModEffects.SCULK_DASH.getDelegate(), 6000, 0),  // 幽匿疾行效果
                    1.0f
            ));

    public static final DeferredHolder<Block, FoodBiteBlock> SCULK_PORK_RIBS_BLOCK =
            BLOCKS.register("sculk_pork_ribs_block", () -> new FoodBiteBlock(
                    new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(12)
                            .saturationModifier(0.8f)
                            .build(),
                    4,
                    PLATE,
                    () -> Items.BOWL  // 吃完返还碗
            ));

    public static final DeferredHolder<Block, EchoCropBlock> ECHO_CROP =
            BLOCKS.register("echo_crop", () -> new EchoCropBlock(
                    Block.Properties.of()
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .noOcclusion()
            ));

    public static final DeferredHolder<Block, EchoCropTopBlock> ECHO_CROP_TOP =
            BLOCKS.register("echo_crop_top", () -> new EchoCropTopBlock(
                    Block.Properties.of()
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .noOcclusion()
            ));

    /**
     * 取森罗厨房的芥末效果（kaleidoscope_cookery:MUSTARD），硬依赖，必存在。
     */
    public static Holder<MobEffect> getMustardEffect() {
        return MUSTARD.getDelegate();
    }

    /**
     * 直接复用森罗厨房的芥末效果。
     */
    public static MobEffectInstance createMustardEffect() {
        return new MobEffectInstance(MUSTARD.getDelegate(), MUSTARD_DURATION, 0);
    }
}
