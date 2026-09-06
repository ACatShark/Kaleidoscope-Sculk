package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.config.MainConfig;
import com.bmt.kaleidoscope_nether.item.*;
import com.bmt.kaleidoscope_nether.item.SpecialFruitBlockItem;
import com.bmt.kaleidoscope_nether.item.StickReturnFoodItem;
import com.bmt.kaleidoscope_nether.util.PrimitiveMacheteTier;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModFoods;
import com.github.ysbbbbbb.kaleidoscopecookery.item.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KNItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, KaleidoscopeNether.MOD_ID);

    // 恶魂皮
    public static final DeferredHolder<Item, Item> GHAST_HIDE = ITEMS.register("ghast_hide",
            () -> new Item(new Item.Properties()));

    // 疣猪兽獠牙
    public static final DeferredHolder<Item, Item> HOGLIN_TUSK = ITEMS.register("hoglin_tusk",
            () -> new Item(new Item.Properties()));

    // 炽足兽岩壳
    public static final DeferredHolder<Item, Item> STRIDER_ROCK_SHELL = ITEMS.register("strider_rock_shell",
            () -> new Item(new Item.Properties().fireResistant()));

    // 镀金碎片
    public static final DeferredHolder<Item, Item> GILDED_FRAGMENT = ITEMS.register("gilded_fragment",
            () -> new Item(new Item.Properties()));

    // 凋零骨头
    public static final DeferredHolder<Item, Item> WITHER_RIB = ITEMS.register("wither_rib",
            () -> new Item(new Item.Properties()));

    // 下界炉灶
    public static final DeferredHolder<Item, Item> NETHER_STOVE = ITEMS.register("nether_stove",
            () -> new BlockItem(KNBlocks.NETHER_STOVE.get(), new Item.Properties()));

    // 星之尘
    public static final DeferredHolder<Item, Item> STAR_DUST = ITEMS.register("star_dust",
            () -> new StarDustItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // 烈焰珍珠
    public static final DeferredHolder<Item, Item> BLAZE_HEART = ITEMS.register("blaze_heart",
            () -> new ThrowableFuelItem(new Item.Properties().stacksTo(16), 3000));

    // 疣猪兽皮
    public static final DeferredHolder<Item, Item> HOGLIN_HIDE = ITEMS.register("hoglin_hide",
            () -> new Item(new Item.Properties()));

    // 吹箭筒
    public static final DeferredHolder<Item, Item> BLOWGUN = ITEMS.register("blowgun",
            () -> new BlowgunItem(new Item.Properties()
                    .durability(384)
                    .rarity(Rarity.UNCOMMON)));

    // 原始砍刀
    public static final DeferredHolder<Item, Item> PRIMITIVE_MACHETE = ITEMS.register("primitive_machete",
            () -> new KitchenKnifeItem(new PrimitiveMacheteTier(),new Item.Properties().durability(2031)));

    // 恶魂挂坠
    public static final DeferredHolder<Item, Item> GHAST_PENDANT = ITEMS.register("ghast_pendant",
            GhastPendantItem::new);

    // 烈焰永恒牛排
    public static final DeferredHolder<Item, Item> EVERLASTING_FLAME_STEAK = ITEMS.register("everlasting_flame_steak",
            () -> new EverlastingFoodItem(KNFoods.EVERLASTING_FLAME_STEAK, MainConfig.EVERLASTING_FLAME_STEAK_COOLDOWN, MainConfig.EVERLASTING_FLAME_STEAK_ENABLED,Rarity.EPIC));

    // 绯红果
    public static final DeferredHolder<Item, Item> CRIMSON_FRUIT = ITEMS.register("crimson_fruit",
            () -> new SpecialFruitBlockItem(KNBlocks.WEEPING_CAVE_VINES.get(), KNFoods.CRIMSON_FRUIT, Rarity.COMMON));

    // 诡异果
    public static final DeferredHolder<Item, Item> WARPED_FRUIT = ITEMS.register("warped_fruit",
            () -> new SpecialFruitBlockItem(KNBlocks.TWISTING_CAVE_VINES.get(), KNFoods.WARPED_FRUIT, Rarity.COMMON));

    // 下界猪儿虫
    public static final DeferredHolder<Item, Item> NETHER_CATERPILLAR = ITEMS.register("nether_caterpillar",
            () -> new WithTooltipsItem(new Item.Properties().food(ModFoods.CATERPILLAR), "nether_caterpillar"));

    // 回魂饭
    public static final DeferredHolder<Item, Item> SOUL_RETURN_RICE = ITEMS.register("soul_return_rice",
            () -> new SoulReturnRiceItem(KNFoods.SOUL_RETURN_RICE));

    // 熔岩烤鸡
    public static final DeferredHolder<Item, Item> LAVA_ROASTED_CHICKEN = ITEMS.register("lava_roasted_chicken",
            () -> new LavaRoastedChickenItem(KNFoods.LAVA_ROASTED_CHICKEN));

    // 星之炖菜
    public static final DeferredHolder<Item, Item> STAR_STEW = ITEMS.register("star_stew",
            () -> new BowlFoodOnlyItem(KNFoods.STAR_STEW));

    // 灵魂椒
    public static final DeferredHolder<Item, Item> SOUL_PEPPER = ITEMS.register("soul_pepper",
            () -> new ChiliItem(4));

    // 灵魂浓汤
    public static final DeferredHolder<Item, Item> SOUL_SOUP = ITEMS.register("soul_soup",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_SOUP));

    // 生炽足兽肉
    public static final DeferredHolder<Item, Item> RAW_STRIDER_MEAT = ITEMS.register("raw_strider_meat",
            () -> new Item(new Item.Properties().food(KNFoods.RAW_STRIDER_MEAT)));

    // 熟炽足兽肉
    public static final DeferredHolder<Item, Item> COOKED_STRIDER_MEAT = ITEMS.register("cooked_strider_meat",
            () -> new Item(new Item.Properties().food(KNFoods.COOKED_STRIDER_MEAT)));

    // 疣猪火腿
    public static final DeferredHolder<Item, Item> HAM = ITEMS.register("ham",
            () -> new Item(new Item.Properties().food(KNFoods.HAM)));

    // 火腿片
    public static final DeferredHolder<Item, Item> HAM_SLICE = ITEMS.register("ham_slice",
            () -> new Item(new Item.Properties().food(KNFoods.HAM_SLICE)));

    // 烤疣猪火腿
    public static final DeferredHolder<Item, Item> ROASTED_HAM = ITEMS.register("roasted_ham",
            () -> new Item(new Item.Properties().food(KNFoods.ROASTED_HAM)));

    // 肉夹馍
    public static final DeferredHolder<Item, Item> ROUJIAMO = ITEMS.register("roujiamo",
            () -> new FoodWithEffectsItem(KNFoods.ROUJIAMO));

    // 红烧炽足兽
    public static final DeferredHolder<Item, Item> BRAISED_STRIDER = ITEMS.register("braised_strider",
            () -> new BowlFoodOnlyItem(KNFoods.BRAISED_STRIDER));

    // 凋零大骨汤
    public static final DeferredHolder<Item, Item> WITHER_BONE_SOUP = ITEMS.register("wither_bone_soup",
            () -> new BowlFoodOnlyItem(KNFoods.WITHER_BONE_SOUP));

    // 炽足兽炖下界疣
    public static final DeferredHolder<Item, Item> STRIDER_NETHER_WART_STEW = ITEMS.register("strider_nether_wart_stew",
            () -> new BowlFoodOnlyItem(KNFoods.STRIDER_NETHER_WART_STEW));

    // 恶魂烤串
    public static final DeferredHolder<Item, Item> GHAST_KABOB = ITEMS.register("ghast_kabob",
            () -> new StickReturnFoodItem(KNFoods.GHAST_KABOB));

    // 恶魂触手
    public static final DeferredHolder<Item, Item> GHAST_TENTACLE = ITEMS.register("ghast_tentacle",
            () -> new Item(new Item.Properties().food(KNFoods.GHAST_TENTACLE)));

    // 烤恶魂触手
    public static final DeferredHolder<Item, Item> ROASTED_GHAST_TENTACLE = ITEMS.register("roasted_ghast_tentacle",
            () -> new Item(new Item.Properties().food(KNFoods.ROASTED_GHAST_TENTACLE)));

    // 恶魂意面
    public static final DeferredHolder<Item, Item> GHAST_PASTA = ITEMS.register("ghast_pasta",
            () -> new BowlFoodOnlyItem(KNFoods.GHAST_PASTA));

    // 岩浆膏浓汤
    public static final DeferredHolder<Item, Item> MAGMA_CREAM_SOUP = ITEMS.register("magma_cream_soup",
            () -> new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_SOUP));

    // 岩浆膏布丁
    public static final DeferredHolder<Item, Item> MAGMA_CREAM_PUDDING = ITEMS.register("magma_cream_pudding",
            () -> new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_PUDDING));

    // 剧毒果
    public static final DeferredHolder<Item, Item> POISONOUS_FRUIT = ITEMS.register("poisonous_fruit",
            () -> new MysteriousPoisonFoodBlockItem(KNBlocks.POISONOUS_FRUIT.get(), KNFoods.POISONOUS_FRUIT, Rarity.COMMON));

    // 剧毒浓汤
    public static final DeferredHolder<Item, Item> POISONOUS_SOUP = ITEMS.register("poisonous_soup",
            () -> new BowlFoodOnlyItem(KNFoods.POISONOUS_SOUP));

    // 灵魂浇汁烤肉
    public static final DeferredHolder<Item, Item> SOUL_GLAZED_ROAST = ITEMS.register("soul_glazed_roast",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_GLAZED_ROAST));

    // 恶魂布丁
    public static final DeferredHolder<Item, Item> GHAST_PUDDING = ITEMS.register("ghast_pudding",
            () -> new BowlFoodOnlyItem(KNFoods.GHAST_PUDDING));

    // 野蛮烤肉
    public static final DeferredHolder<Item, Item> GILDED_BARBARIC_ROAST = ITEMS.register("gilded_barbaric_roast",
            () -> new BowlFoodOnlyItem(KNFoods.GILDED_BARBARIC_ROAST));

    // 下界猪儿虫刺身
    public static final DeferredHolder<Item, Item> NETHER_CATERPILLAR_SASHIMI = ITEMS.register("nether_caterpillar_sashimi",
            () -> new BowlFoodOnlyItem(KNFoods.NETHER_CATERPILLAR_SASHIMI));

    // 黄金烤肉
    public static final DeferredHolder<Item, Item> GOLDEN_ROAST = ITEMS.register("golden_roast",
            () -> new BowlFoodOnlyItem(KNFoods.GOLDEN_ROAST));

    // 下界薯条拼盘
    public static final DeferredHolder<Item, Item> NETHER_FRIES_PLATTER = ITEMS.register("nether_fries_platter",
            () -> new BowlFoodOnlyItem(KNFoods.NETHER_FRIES_PLATTER));

    // 疣猪兽獠牙焖肉
    public static final DeferredHolder<Item, Item> HOGLIN_TUSK_BRAISED_MEAT = ITEMS.register("hoglin_tusk_braised_meat",
            () -> new FoodWithEffectsItem(KNFoods.HOGLIN_TUSK_BRAISED_MEAT));

    // 剧毒恶魂烤肉
    public static final DeferredHolder<Item, Item> POISONOUS_GHAST_ROAST = ITEMS.register("poisonous_ghast_roast",
            () -> new BowlFoodOnlyItem(KNFoods.POISONOUS_GHAST_ROAST));

    // 灵魂椒炒肉
    public static final DeferredHolder<Item, Item> SOUL_PEPPER_STIR_FRY = ITEMS.register("soul_pepper_stir_fry",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_PEPPER_STIR_FRY));

    // 炽足兽岩壳炒肉
    public static final DeferredHolder<Item, Item> STRIDER_SHELL_STIR_FRY = ITEMS.register("strider_shell_stir_fry",
            () -> new BowlFoodOnlyItem(KNFoods.STRIDER_SHELL_STIR_FRY));

    // 下界果切拼盘
    public static final DeferredHolder<Item, Item> FRUIT_PLATTER = ITEMS.register("fruit_platter",
            () -> new BowlFoodOnlyItem(KNFoods.FRUIT_PLATTER));

    // 火腿酸酪
    public static final DeferredHolder<Item, Item> HAM_YOGURT = ITEMS.register("ham_yogurt",
            () -> new BowlFoodOnlyItem(KNFoods.HAM_YOGURT));

    // 酸菜鱼
    public static final DeferredHolder<Item, Item> SAUERKRAUT_FISH = ITEMS.register("sauerkraut_fish",
            () -> new BowlFoodOnlyItem(KNFoods.SAUERKRAUT_FISH));

    // 烈焰浓汤
    public static final DeferredHolder<Item, Item> BLAZE_SOUP = ITEMS.register("blaze_soup",
            () -> new BowlFoodOnlyItem(KNFoods.BLAZE_SOUP));

    // 熔岩果冻
    public static final DeferredHolder<Item, Item> LAVA_JELLY = ITEMS.register("lava_jelly",
            () -> new BowlFoodOnlyItem(KNFoods.LAVA_JELLY));

    // 绯红沙拉
    public static final DeferredHolder<Item, Item> CRIMSON_SALAD = ITEMS.register("crimson_salad",
            () -> new BowlFoodOnlyItem(KNFoods.CRIMSON_SALAD));

    // 绯红菌岩浆膏炖肉
    public static final DeferredHolder<Item, Item> CRIMSON_MAGMA_STEW = ITEMS.register("crimson_magma_stew",
            () -> new BowlFoodOnlyItem(KNFoods.CRIMSON_MAGMA_STEW));

    // 诡异沙拉
    public static final DeferredHolder<Item, Item> WARPED_SALAD = ITEMS.register("warped_salad",
            () -> new BowlFoodOnlyItem(KNFoods.WARPED_SALAD));

    // 灵魂炽足兽烤串
    public static final DeferredHolder<Item, Item> SOUL_STRIDER_KABOB = ITEMS.register("soul_strider_kabob",
            () -> new StickReturnFoodItem(KNFoods.SOUL_STRIDER_KABOB));

    // 黄金烤串
    public static final DeferredHolder<Item, Item> GOLDEN_KABOB = ITEMS.register("golden_kabob",
            () -> new StickReturnFoodItem(KNFoods.GOLDEN_KABOB));

    // 烈焰烤串
    public static final DeferredHolder<Item, Item> BLAZING_KABOB = ITEMS.register("blazing_kabob",
            () -> new StickReturnFoodItem(KNFoods.BLAZING_KABOB));

    // 绯红烤串
    public static final DeferredHolder<Item, Item> CRIMSON_KABOB = ITEMS.register("crimson_kabob",
            () -> new StickReturnFoodItem(KNFoods.CRIMSON_KABOB));

    // 诡异烤串
    public static final DeferredHolder<Item, Item> WARPED_KABOB = ITEMS.register("warped_kabob",
            () -> new StickReturnFoodItem(KNFoods.WARPED_KABOB));

    // 星之恶魂意面
    public static final DeferredHolder<Item, Item> STAR_GHAST_PASTA = ITEMS.register("star_ghast_pasta",
            () -> new BowlFoodOnlyItem(KNFoods.STAR_GHAST_PASTA));

    // 星之炖肉
    public static final DeferredHolder<Item, Item> STAR_STEW_MEAT = ITEMS.register("star_stew_meat",
            () -> new BowlFoodOnlyItem(KNFoods.STAR_STEW_MEAT));

    // 黑苹果沙拉
    public static final DeferredHolder<Item, Item> BLACK_APPLE_SALAD = ITEMS.register("black_apple_salad",
            () -> new BowlFoodOnlyItem(KNFoods.BLACK_APPLE_SALAD));

    // 红宝石牛排
    public static final DeferredHolder<Item, Item> RUBY_STEAK = ITEMS.register("ruby_steak",
            () -> new BowlFoodOnlyItem(KNFoods.RUBY_STEAK));

    // 下界芦苇炖菜
    public static final DeferredHolder<Item, Item> NETHER_REED_STEW = ITEMS.register("nether_reed_stew",
            () -> new BowlFoodOnlyItem(KNFoods.NETHER_REED_STEW));

    // 岩浆膏炒肉
    public static final DeferredHolder<Item, Item> MAGMA_CREAM_STIR_FRY = ITEMS.register("magma_cream_stir_fry",
            () -> new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_STIR_FRY));

    // 岩浆膏炒肉盖饭
    public static final DeferredHolder<Item, Item> MAGMA_CREAM_STIR_FRY_RICE = ITEMS.register("magma_cream_stir_fry_rice",
            () -> new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_STIR_FRY_RICE));

    // 麻婆豆腐
    public static final DeferredHolder<Item, Item> MAPO_TOFU = ITEMS.register("mapo_tofu",
            () -> new BowlFoodOnlyItem(KNFoods.MAPO_TOFU));

    // 麻婆豆腐盖饭
    public static final DeferredHolder<Item, Item> MAPO_TOFU_RICE = ITEMS.register("mapo_tofu_rice",
            () -> new BowlFoodOnlyItem(KNFoods.MAPO_TOFU_RICE));

    // 诡异蛋糕
    public static final DeferredHolder<Item, Item> WARPED_CAKE = ITEMS.register("warped_cake",
            () -> new FoodWithEffectsItem(KNFoods.WARPED_CAKE));

    // 重庆小面
    public static final DeferredHolder<Item, Item> CHONGQING_NOODLES = ITEMS.register("chongqing_noodles",
            () -> new BowlFoodOnlyItem(KNFoods.CHONGQING_NOODLES));

    // 螺蛳粉
    public static final DeferredHolder<Item, Item> LUOSIFEN = ITEMS.register("luosifen",
            () -> new BowlFoodOnlyItem(KNFoods.LUOSIFEN));

    // 灵魂炒肉
    public static final DeferredHolder<Item, Item> SOUL_STIR_FRY_MEAT = ITEMS.register("soul_stir_fry_meat",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_STIR_FRY_MEAT));

    // 灵魂炒肉盖饭
    public static final DeferredHolder<Item, Item> SOUL_STIR_FRY_MEAT_RICE = ITEMS.register("soul_stir_fry_meat_rice",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_STIR_FRY_MEAT_RICE));

    // 焦糖下界猪儿虫
    public static final DeferredHolder<Item, Item> CARAMEL_NETHER_CATERPILLAR = ITEMS.register("caramel_nether_caterpillar",
            () -> new BowlFoodOnlyItem(KNFoods.CARAMEL_NETHER_CATERPILLAR));

    // 焦糖下界猪儿虫盖饭
    public static final DeferredHolder<Item, Item> CARAMEL_NETHER_CATERPILLAR_RICE = ITEMS.register("caramel_nether_caterpillar_rice",
            () -> new BowlFoodOnlyItem(KNFoods.CARAMEL_NETHER_CATERPILLAR_RICE));

    // 生猪灵肉
    public static final DeferredHolder<Item, Item> RAW_PIGLIN_MEAT = ITEMS.register("raw_piglin_meat",
            () -> new Item(new Item.Properties().food(KNFoods.RAW_PIGLIN_MEAT)));

    // 熟猪灵肉
    public static final DeferredHolder<Item, Item> COOKED_PIGLIN_MEAT = ITEMS.register("cooked_piglin_meat",
            () -> new Item(new Item.Properties().food(KNFoods.COOKED_PIGLIN_MEAT)));

    // 诡异霉烂肉
    public static final DeferredHolder<Item, Item> WARPED_HOGLIN_TENDERLOIN_STEW = ITEMS.register("warped_hoglin_tenderloin_stew",
            () -> new BowlFoodOnlyItem(KNFoods.WARPED_HOGLIN_TENDERLOIN_STEW));

    // 麻辣疣猪兽拉面
    public static final DeferredHolder<Item, Item> SPICY_HOGLIN_RAMEN = ITEMS.register("spicy_hoglin_ramen",
            () -> new BowlFoodOnlyItem(KNFoods.SPICY_HOGLIN_RAMEN));

    // 麻辣香锅
    public static final DeferredHolder<Item, Item> SPICY_POT = ITEMS.register("spicy_pot",
            () -> new BowlFoodOnlyItem(KNFoods.SPICY_POT));

    // 麻辣香锅盖饭
    public static final DeferredHolder<Item, Item> SPICY_POT_RICE = ITEMS.register("spicy_pot_rice",
            () -> new BowlFoodOnlyItem(KNFoods.SPICY_POT_RICE));

    // 孟婆汤
    public static final DeferredHolder<Item, Item> FORGETFULNESS_SOUP = ITEMS.register("forgetfulness_soup",
            () -> new ForgetfulnessSoupItem(KNFoods.FORGETFULNESS_SOUP));

    // 蒜蓉生蚝
    public static final DeferredHolder<Item, Item> GARLIC_OYSTERS = ITEMS.register("garlic_oysters",
            () -> new BowlFoodOnlyItem(KNFoods.GARLIC_OYSTERS));

    // 夫妻肺片
    public static final DeferredHolder<Item, Item> COUPLES_LUNG_SLICE = ITEMS.register("couples_lung_slice",
            () -> new BowlFoodOnlyItem(KNFoods.COUPLES_LUNG_SLICE));

    // 胡椒猪肚鸡汤
    public static final DeferredHolder<Item, Item> PEPPER_PORK_BELLY_CHICKEN_SOUP = ITEMS.register("pepper_pork_belly_chicken_soup",
            () -> new BowlFoodOnlyItem(KNFoods.PEPPER_PORK_BELLY_CHICKEN_SOUP));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}