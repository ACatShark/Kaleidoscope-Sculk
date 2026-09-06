package com.bmt.kaleidoscope_nether.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import static com.bmt.kaleidoscope_nether.init.KNEffects.*;
import static net.minecraft.world.effect.MobEffects.*;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;

public class KNFoods {
    // 灵魂羊排
    public static final FoodProperties SOUL_LAMB_CHOP_ITEM = (new FoodProperties.Builder()).nutrition(13).saturationModifier(0.611F)
            .effect(() -> new MobEffectInstance(GHOST, 180 * 20), 1.0F).build();

    public static final FoodProperties SOUL_LAMB_CHOP_BLOCK = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.611F)
            .effect(() -> new MobEffectInstance(GHOST, 240 * 20), 1.0F).build();

    // 烈焰永恒牛排
    public static final FoodProperties EVERLASTING_FLAME_STEAK = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(1.2f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 300, 0), 1.0F)
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 600, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 绯红果
    public static final FoodProperties CRIMSON_FRUIT = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .build();

    // 诡异果
    public static final FoodProperties WARPED_FRUIT = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .build();

    // 回魂饭
    public static final FoodProperties SOUL_RETURN_RICE = new FoodProperties.Builder()
            .nutrition(10).saturationModifier(1.8f)
            .alwaysEdible()
            .build();

    // 熔岩烤鸡
    public static final FoodProperties LAVA_ROASTED_CHICKEN = new FoodProperties.Builder()
            .nutrition(13)
            .saturationModifier(0.615f)
            .alwaysEdible()
            .build();

    // 星之炖菜
    public static final FoodProperties STAR_STEW = new FoodProperties.Builder()
            .nutrition(16).saturationModifier(1.8f)
            .effect(() -> new MobEffectInstance(STAR_BLESSING, 600, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 灵魂浓汤
    public static final FoodProperties SOUL_SOUP = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.667f)
            .effect(() -> new MobEffectInstance(GHOST, 90 * 20, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 生炽足兽肉
    public static final FoodProperties RAW_STRIDER_MEAT = new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.3f)
            .alwaysEdible()
            .build();

    // 熟炽足兽肉
    public static final FoodProperties COOKED_STRIDER_MEAT = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.8f)
            .alwaysEdible()
            .build();

    // 疣猪火腿
    public static final FoodProperties HAM = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.5f)
            .alwaysEdible()
            .build();

    // 火腿片
    public static final FoodProperties HAM_SLICE = new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.2f)
            .alwaysEdible()
            .build();

    // 烤疣猪火腿
    public static final FoodProperties ROASTED_HAM = new FoodProperties.Builder()
            .nutrition(10).saturationModifier(0.8f)
            .alwaysEdible()
            .build();

    // 肉夹馍
    public static final FoodProperties ROUJIAMO = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 80 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 红烧炽足兽
    public static final FoodProperties BRAISED_STRIDER = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 90 * 20, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 凋零大骨汤
    public static final FoodProperties WITHER_BONE_SOUP = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 300 * 20, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 炽足兽炖下界疣
    public static final FoodProperties STRIDER_NETHER_WART_STEW = new FoodProperties.Builder()
            .nutrition(13).saturationModifier(0.615f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 90 * 20, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 恶魂烤串
    public static final FoodProperties GHAST_KABOB = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.9f)
            .alwaysEdible()
            .build();

    // 恶魂触手
    public static final FoodProperties GHAST_TENTACLE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .build();

    // 烤恶魂触手
    public static final FoodProperties ROASTED_GHAST_TENTACLE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
            .alwaysEdible()
            .build();

    // 恶魂意面
    public static final FoodProperties GHAST_PASTA = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(ModEffects.SULFUR, 90 * 20, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 岩浆膏浓汤
    public static final FoodProperties MAGMA_CREAM_SOUP = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.667f)
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 480 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 岩浆膏布丁
    public static final FoodProperties MAGMA_CREAM_PUDDING = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.0f)
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 剧毒果
    public static final FoodProperties POISONOUS_FRUIT = new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.1f)
            .alwaysEdible()
            .build();

    // 剧毒浓汤
    public static final FoodProperties POISONOUS_SOUP = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.667f)
            .effect(() -> new MobEffectInstance(MYSTERIOUS_POISON, 80 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 灵魂浇汁烤肉
    public static final FoodProperties SOUL_GLAZED_ROAST = new FoodProperties.Builder()
            .nutrition(13).saturationModifier(0.615f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 90 * 20, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 恶魂布丁
    public static final FoodProperties GHAST_PUDDING = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 野蛮烤肉
    public static final FoodProperties GILDED_BARBARIC_ROAST = new FoodProperties.Builder()
            .nutrition(24).saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 90 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 下界猪儿虫刺身
    public static final FoodProperties NETHER_CATERPILLAR_SASHIMI = new FoodProperties.Builder()
            .nutrition(19).saturationModifier(0.65f)
            .effect(() -> new MobEffectInstance(ModEffects.MUSTARD, 300 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 黄金烤肉
    public static final FoodProperties GOLDEN_ROAST = new FoodProperties.Builder()
            .nutrition(20).saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 120 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 下界薯条拼盘
    public static final FoodProperties NETHER_FRIES_PLATTER = new FoodProperties.Builder()
            .nutrition(20).saturationModifier(0.55f)
            .effect(() -> new MobEffectInstance(TROPICAL_STRIDER, 300 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 疣猪兽獠牙焖肉
    public static final FoodProperties HOGLIN_TUSK_BRAISED_MEAT = new FoodProperties.Builder()
            .nutrition(20).saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 80 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 剧毒恶魂烤肉
    public static final FoodProperties POISONOUS_GHAST_ROAST = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(MYSTERIOUS_POISON, 60 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 灵魂椒炒肉
    public static final FoodProperties SOUL_PEPPER_STIR_FRY = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(GHOST, 60 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 炽足兽岩壳炒肉
    public static final FoodProperties STRIDER_SHELL_STIR_FRY = new FoodProperties.Builder()
            .nutrition(13).saturationModifier(0.615f)
            .effect(() -> new MobEffectInstance(TROPICAL_STRIDER, 300 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 下界果切拼盘
    public static final FoodProperties FRUIT_PLATTER = new FoodProperties.Builder()
            .nutrition(10).saturationModifier(0.667f)
            .effect(() -> new MobEffectInstance(ModEffects.PRESERVATION, 90 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 火腿酸酪
    public static final FoodProperties HAM_YOGURT = new FoodProperties.Builder()
            .nutrition(13).saturationModifier(0.615f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 90 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 酸菜鱼
    public static final FoodProperties SAUERKRAUT_FISH = new FoodProperties.Builder()
            .nutrition(7).saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 240 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 烈焰浓汤
    public static final FoodProperties BLAZE_SOUP = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.667f)
            .effect(() -> new MobEffectInstance(TROPICAL_STRIDER, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 熔岩果冻
    public static final FoodProperties LAVA_JELLY = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.0f)
            .effect(() -> new MobEffectInstance(TROPICAL_STRIDER, 120 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 绯红沙拉
    public static final FoodProperties CRIMSON_SALAD = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.55f)
            .effect(() -> new MobEffectInstance(CRIMSON, 90 * 20, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 绯红菌岩浆膏炖肉
    public static final FoodProperties CRIMSON_MAGMA_STEW = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 诡异沙拉
    public static final FoodProperties WARPED_SALAD = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.55f)
            .effect(() -> new MobEffectInstance(WARPED, 90 * 20, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 灵魂炽足兽烤串
    public static final FoodProperties SOUL_STRIDER_KABOB = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.9f)
            .effect(() -> new MobEffectInstance(WARPED, 30 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 黄金烤串
    public static final FoodProperties GOLDEN_KABOB = new FoodProperties.Builder()
            .nutrition(10).saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 60 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 烈焰烤串
    public static final FoodProperties BLAZING_KABOB = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.9f)
            .effect(() -> new MobEffectInstance(TROPICAL_STRIDER, 30 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 绯红烤串
    public static final FoodProperties CRIMSON_KABOB = new FoodProperties.Builder()
            .nutrition(4).saturationModifier(0.55f)
            .effect(() -> new MobEffectInstance(CRIMSON, 45 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 诡异烤串
    public static final FoodProperties WARPED_KABOB = new FoodProperties.Builder()
            .nutrition(4).saturationModifier(0.55f)
            .effect(() -> new MobEffectInstance(WARPED, 45 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 星之恶魂意面
    public static final FoodProperties STAR_GHAST_PASTA = new FoodProperties.Builder()
            .nutrition(16).saturationModifier(1.8f)
            .effect(() -> new MobEffectInstance(STAR_BLESSING, 600, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 星之炖肉
    public static final FoodProperties STAR_STEW_MEAT = new FoodProperties.Builder()
            .nutrition(16).saturationModifier(1.8f)
            .effect(() -> new MobEffectInstance(STAR_BLESSING, 600, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 黑苹果沙拉
    public static final FoodProperties BLACK_APPLE_SALAD = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.55f)
            .effect(() -> new MobEffectInstance(ModEffects.PRESERVATION, 90 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 红宝石牛排
    public static final FoodProperties RUBY_STEAK = new FoodProperties.Builder()
            .nutrition(20).saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 240 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 下界芦苇炖菜
    public static final FoodProperties NETHER_REED_STEW = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.667f)
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 480 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 岩浆膏炒肉
    public static final FoodProperties MAGMA_CREAM_STIR_FRY = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 90 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 岩浆膏炒肉盖饭
    public static final FoodProperties MAGMA_CREAM_STIR_FRY_RICE = new FoodProperties.Builder()
            .nutrition(14).saturationModifier(0.643f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 麻婆豆腐
    public static final FoodProperties MAPO_TOFU = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 90 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 麻婆豆腐盖饭
    public static final FoodProperties MAPO_TOFU_RICE = new FoodProperties.Builder()
            .nutrition(14).saturationModifier(0.643f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 诡异蛋糕
    public static final FoodProperties WARPED_CAKE = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.4f)
            .effect(() -> new MobEffectInstance(WARPED, 60 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 重庆小面
    public static final FoodProperties CHONGQING_NOODLES = new FoodProperties.Builder()
            .nutrition(14).saturationModifier(0.643f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 3600), 1.0F)
            .build();

    // 螺蛳粉
    public static final FoodProperties LUOSIFEN = new FoodProperties.Builder()
            .nutrition(14).saturationModifier(0.643f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 3600), 1.0F)
            .build();

    // 灵魂炒肉
    public static final FoodProperties SOUL_STIR_FRY_MEAT = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 90 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 灵魂炒肉盖饭
    public static final FoodProperties SOUL_STIR_FRY_MEAT_RICE = new FoodProperties.Builder()
            .nutrition(14).saturationModifier(0.643f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 焦糖下界猪儿虫
    public static final FoodProperties CARAMEL_NETHER_CATERPILLAR = new FoodProperties.Builder()
            .nutrition(18).saturationModifier(0.36f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 90 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 焦糖下界猪儿虫盖饭
    public static final FoodProperties CARAMEL_NETHER_CATERPILLAR_RICE = new FoodProperties.Builder()
            .nutrition(19).saturationModifier(0.7f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 生猪灵肉
    public static final FoodProperties RAW_PIGLIN_MEAT = new FoodProperties.Builder()
            .nutrition(3).saturationModifier(0.3f)
            .alwaysEdible()
            .build();

    // 熟猪灵肉
    public static final FoodProperties COOKED_PIGLIN_MEAT = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.8f)
            .alwaysEdible()
            .build();

    // 诡异霉烂肉
    public static final FoodProperties WARPED_HOGLIN_TENDERLOIN_STEW = new FoodProperties.Builder()
            .nutrition(10).saturationModifier(0.667f)
            .effect(() -> new MobEffectInstance(ModEffects.FLATULENCE, 35 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 麻辣疣猪兽拉面
    public static final FoodProperties SPICY_HOGLIN_RAMEN = new FoodProperties.Builder()
            .nutrition(14).saturationModifier(0.643f)
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 麻辣香锅
    public static final FoodProperties SPICY_POT = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(ModEffects.VIGOR, 90 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 麻辣香锅盖饭
    public static final FoodProperties SPICY_POT_RICE = new FoodProperties.Builder()
            .nutrition(14).saturationModifier(0.643f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 孟婆汤
    public static final FoodProperties FORGETFULNESS_SOUP = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(1.0f)
            .alwaysEdible()
            .build();

    // 红烧狮子头
    public static final FoodProperties BRAISED_LION_HEAD_ITEM = new FoodProperties.Builder()
            .nutrition(16)
            .saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 80 * 20), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodProperties BRAISED_LION_HEAD_BLOCK = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 80 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 蒜蓉生蚝
    public static final FoodProperties GARLIC_OYSTERS = new FoodProperties.Builder()
            .nutrition(13).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 180 * 20, 0), 1.0F)
            .alwaysEdible()
            .build();

    // 夫妻肺片
    public static final FoodProperties COUPLES_LUNG_SLICE = new FoodProperties.Builder()
            .nutrition(13).saturationModifier(0.611f)
            .effect(() -> new MobEffectInstance(ModEffects.WARMTH, 80 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 卤肉饭
    public static final FoodProperties BRAISED_PORK_RICE_ITEM = new FoodProperties.Builder()
            .nutrition(13)
            .saturationModifier(0.611F)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodProperties BRAISED_PORK_RICE_BLOCK = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.611F)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 胡椒猪肚鸡汤
    public static final FoodProperties PEPPER_PORK_BELLY_CHICKEN_SOUP = new FoodProperties.Builder()
            .nutrition(20).saturationModifier(0.55f)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    // 玉米胡萝卜排骨汤
    public static final FoodProperties CORN_CARROT_PORK_RIB_SOUP_ITEM = new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(0.55F)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodProperties CORN_CARROT_PORK_RIB_SOUP_BLOCK = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.55F)
            .effect(() -> new MobEffectInstance(ModEffects.SATIATED_SHIELD, 180 * 20), 1.0F)
            .alwaysEdible()
            .build();
}