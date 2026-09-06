package org.kaleidoscope_sculk.register;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodOnlyItem;
import org.kaleidoscope_sculk.Kaleidoscope_sculk;
import org.kaleidoscope_sculk.entity.AncientBoneFragmentProjectile;
import org.kaleidoscope_sculk.item.DeepslateCakeSliceItem;
import org.kaleidoscope_sculk.item.EchoSeedItem;
import org.kaleidoscope_sculk.item.ErosionKnifeItem;
import org.kaleidoscope_sculk.item.FoodBlockItem;
import org.kaleidoscope_sculk.item.SculkBrewItem;
import org.kaleidoscope_sculk.item.SculkBranchItem;
import org.kaleidoscope_sculk.item.SilentKitchenKnife;
import org.kaleidoscope_sculk.item.SoulItem;
import org.kaleidoscope_sculk.item.SoulSailItem;

import static com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.VIGOR;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Kaleidoscope_sculk.MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Kaleidoscope_sculk.MODID);

    // 方块物品
    public static final DeferredItem<FoodBlockItem> SCULK_STEW_BLOCK_ITEM = ITEMS.register(
            "sculk_stew_block",
            () -> new FoodBlockItem(ModBlocks.SCULK_STEW_BLOCK.get())
    );

    // 深板岩炉灶
    public static final DeferredItem<BlockItem> DEEPSLATE_STOVE = ITEMS.registerItem(
            "deepslate_stove",
            properties -> new BlockItem(ModBlocks.DEEPSLATE_STOVE.get(), properties),
            new Item.Properties()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> KALEIDOSCOPE_SCULK_TAB =
            CREATIVE_TABS.register("kaleidoscope_sculk_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + Kaleidoscope_sculk.MODID))
                    .icon(() -> new ItemStack(ModItems.COOKED_WARDEN_TENDRIL_BOWL.get()))
                    .displayItems((parameters, output) -> {
                        // 其他方块
                        output.accept(ModItems.DEEPSLATE_STOVE.get());

                        // 原料
                        output.accept(ModItems.WARDEN_TENDRIL.get());
                        output.accept(ModItems.ANCIENT_BONE_FRAGMENT.get());
                        output.accept(ModItems.SCULK_FUNGUS.get());
                        output.accept(ModItems.SCULK_PINAPPLE.get());
                        output.accept(ModItems.BOIL_SCULK_PINAPPLE.get());
                        output.accept(ModItems.SCULK_CATERPILLAR.get());
                        output.accept(ModItems.SOUL.get());
                        output.accept(ModItems.SCULK_BRANCH.get());
                        output.accept(ModItems.EERIE_MEAT.get());
                        output.accept(ModItems.SCULK_DUST.get());
                        output.accept(ModItems.SCULK_DOUGH.get());
                        output.accept(ModItems.SILENT_UPGRADE_SMITHING_TEMPLATE.get());

                        output.accept(ModItems.EROSION_KITCHEN_KNIFE.get());
                        output.accept(ModItems.SILENT_KITCHEN_KNIFE.get());

                        // 作物
                        output.accept(ModItems.ECHO_SEED.get());

                        // 食物
                        output.accept(ModItems.SCULK_FLESH.get());

                        output.accept(ModItems.SCULK_FUNGUS_SOUP.get());
                        output.accept(ModItems.COOKED_WARDEN_TENDRIL_BOWL.get());
                        output.accept(ModItems.ANCIENT_BRITTLE_BONE_FRAGMENTS.get());
                        output.accept(ModItems.PORK_ANCIENT_BONE_SOUP.get());
                        output.accept(ModItems.SOUL_PANCAKE.get());
                        output.accept(ModItems.COOKED_EERIE_MEAT.get());

                        // 魂幡
                        output.accept(ModItems.SOUL_SAIL.get());
                        output.accept(ModItems.THOUSAND_SOUL_SAIL.get());
                        output.accept(ModItems.MYRIAD_SOUL_SAIL.get());

                        // 方块食物
                        output.accept(ModItems.SCULK_STEW_BLOCK_ITEM.get());
                        output.accept(ModItems.SCULK_PORK_RIBS_BLOCK_ITEM.get());
                        output.accept(ModItems.SCULK_CHICKEN_STEW_BLOCK_ITEM.get());
                        output.accept(ModItems.ANCIENT_CITY_STYLE_SASHIMI.get());
                        output.accept(ModItems.SCULK_LAMB_CHOP.get());

                        output.accept(ModItems.DEEPSLATE_CAKE_SLICE.get());
                        output.accept(ModItems.SCULK_JUICE_BUCKET.get());

                        // 幽匿菠萝啤
                        output.accept(ModItems.SCULK_BREW.get());
                    })
                    .build());

    // 幽匿菠萝啤（由主模组的 DrinkBlockItem 承载）
    public static final DeferredItem<SculkBrewItem> SCULK_BREW = ITEMS.register(
            "sculk_brew",
            () -> new SculkBrewItem(ModBlocks.SCULK_BREW_BOTTLE.get())
    );

    // 幽匿果汁桶
    public static final DeferredItem<BucketItem> SCULK_JUICE_BUCKET = ITEMS.register(
            "sculk_juice_bucket",
            () -> new BucketItem(ModFluids.SCULK_JUICE.get(),
                    new Item.Properties().stacksTo(16).craftRemainder(Items.BUCKET))
    );

    // 魂幡
    public static final DeferredItem<Item> SOUL_SAIL =
            ITEMS.register("soul_sail", () -> new SoulSailItem(new Item.Properties(), SoulSailItem.SailType.SOUL));

    public static final DeferredItem<Item> SOUL_SAIL_FULL =
            ITEMS.register("soul_sail_full", () -> new SoulSailItem(new Item.Properties(), SoulSailItem.SailType.SOUL));

    // 千魂幡
    public static final DeferredItem<Item> THOUSAND_SOUL_SAIL =
            ITEMS.register("thousand_soul_sail", () -> new SoulSailItem(new Item.Properties(), SoulSailItem.SailType.THOUSAND));

    public static final DeferredItem<Item> THOUSAND_SOUL_SAIL_FULL =
            ITEMS.register("thousand_soul_sail_full", () -> new SoulSailItem(new Item.Properties(), SoulSailItem.SailType.THOUSAND));

    // 万魂幡
    public static final DeferredItem<Item> MYRIAD_SOUL_SAIL =
            ITEMS.register("myriad_soul_sail", () -> new SoulSailItem(new Item.Properties(), SoulSailItem.SailType.MYRIAD));

    public static final DeferredItem<Item> MYRIAD_SOUL_SAIL_FULL =
            ITEMS.register("myriad_soul_sail_full", () -> new SoulSailItem(new Item.Properties(), SoulSailItem.SailType.MYRIAD));

    // 深板岩蛋糕
    public static final DeferredItem<BlockItem> DEEPSLATE_CAKE = ITEMS.registerItem(
            "deepslate_cake",
            properties -> new BlockItem(ModBlocks.DEEPSLATE_CAKE.get(), properties),
            new Item.Properties()
    );

    public static final DeferredItem<DeepslateCakeSliceItem> DEEPSLATE_CAKE_SLICE = ITEMS.registerItem(
            "deepslate_cake_slice",
            DeepslateCakeSliceItem::new,
            new Item.Properties()
    );

    // 幽匿羊排
    public static final DeferredItem<FoodBlockItem> SCULK_LAMB_CHOP = ITEMS.register(
            "sculk_lamb_chop",
            () -> new FoodBlockItem(ModBlocks.SCULK_LAMB_CHOP_BLOCK.get())
    );

    public static final DeferredHolder<Item, FoodBlockItem> ANCIENT_CITY_STYLE_SASHIMI =
            ITEMS.register("ancient_city_style_sashimi",
                    () -> new FoodBlockItem(ModBlocks.ANCIENT_CITY_STYLE_SASHIMI_BLOCK.get())
            );

    // 静匿升级模板
    public static final DeferredItem<Item> SILENT_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem(
            "silent_upgrade_smithing_template",
            properties -> new Item(properties),
            new Item.Properties()
    );

    // 静匿菜刀
    public static final DeferredItem<SilentKitchenKnife> SILENT_KITCHEN_KNIFE = ITEMS.register(
            "silent_kitchen_knife",
            SilentKitchenKnife::new  // 直接使用 Supplier
    );

    // 熟幽寂肉
    public static final DeferredItem<Item> COOKED_EERIE_MEAT = ITEMS.registerItem(
            "cooked_eerie_meat",
            Item::new,
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(0.8f)
                            .build())
    );

    // 幽匿菠萝
    public static final DeferredItem<Item> SCULK_PINAPPLE = ITEMS.registerItem(
            "sculk_pinapple",
            Item::new,
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.4f)
                            .build())
    );

    public static final DeferredItem<Item> SCULK_DUST = ITEMS.registerItem(
            "sculk_dust",
            Item::new,
            new Item.Properties()
    );

    // 幽匿面团
    public static final DeferredItem<Item> SCULK_DOUGH = ITEMS.registerItem(
            "sculk_dough",
            Item::new,
            new Item.Properties()
    );

    // 灵魂
    public static final DeferredItem<Item> SOUL = ITEMS.registerItem(
            "soul",
            SoulItem::new,
            new Item.Properties()
                    .stacksTo(64)
    );

    // 幽匿枝
    public static final DeferredItem<Item> SCULK_BRANCH = ITEMS.registerItem(
            "sculk_branch",
            SculkBranchItem::new,
            new Item.Properties()
                    .stacksTo(64)
    );

    // 侵蚀菜刀
    public static final DeferredItem<ErosionKnifeItem> EROSION_KITCHEN_KNIFE = ITEMS.register(
            "erosion_knife",
            ErosionKnifeItem::new
    );

    // 幽匿炖鸡煲方块物品
    public static final DeferredItem<FoodBlockItem> SCULK_CHICKEN_STEW_BLOCK_ITEM = ITEMS.register(
            "sculk_chicken_stew_block",
            () -> new FoodBlockItem(ModBlocks.SCULK_CHICKEN_STEW_BLOCK.get())
    );

    // 幽寂肉
    public static final DeferredItem<Item> EERIE_MEAT = ITEMS.registerItem(
            "eerie_meat",
            Item::new,
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.3f)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 200, 0), 1.0f)
                            .build())
    );

    // 回响之种
    public static final DeferredItem<Item> ECHO_SEED = ITEMS.registerItem(
            "echo_seed",
            properties -> new EchoSeedItem(ModBlocks.ECHO_CROP.get(), properties),
            new Item.Properties()
    );

    // 监守者触须
    public static final DeferredItem<Item> WARDEN_TENDRIL = ITEMS.registerItem(
            "warden_tendril",
            Item::new,
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.2f)
                            .alwaysEdible()
                            .build())
    );

    // 煮菠萝
    public static final DeferredItem<BowlFoodOnlyItem> BOIL_SCULK_PINAPPLE = ITEMS.register(
            "boil_sculk_pinapple",
            () -> new BowlFoodOnlyItem(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationModifier(0.8f)
                    .alwaysEdible()
                    .effect(() -> new MobEffectInstance(ModEffects.ECHO.getDelegate(), 3600, 0), 1.0f)
                    .build())
    );

    // 炒监守者触须
    public static final DeferredItem<BowlFoodOnlyItem> COOKED_WARDEN_TENDRIL_BOWL = ITEMS.register(
            "cooked_warden_tendril_bowl",
            () -> new BowlFoodOnlyItem(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationModifier(0.5f)
                    .alwaysEdible()
                    .effect(() -> new MobEffectInstance(ModEffects.SONIC_WAVE.getDelegate(), 6000, 2), 1.0f)
                    .build())
    );

    // 幽匿炖大骨
    public static final DeferredItem<BowlFoodOnlyItem> PORK_ANCIENT_BONE_SOUP = ITEMS.register(
            "pork_ancient_bone_soup",
            () -> new BowlFoodOnlyItem(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationModifier(1.5f)
                    .alwaysEdible()
                    .effect(() -> new MobEffectInstance(VIGOR.getDelegate(), 6000, 0), 1.0f)  // 森罗厨房：活力 (05:00)
                    .effect(() -> new MobEffectInstance(ModEffects.SONIC_WAVE.getDelegate(), 6000, 0), 1.0f)
                    .build())
    );

    // 幽匿真菌汤
    public static final DeferredItem<BowlFoodOnlyItem> SCULK_FUNGUS_SOUP = ITEMS.register(
            "sculk_fungus_soup",
            () -> new BowlFoodOnlyItem(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationModifier(0.5f)
                    .alwaysEdible()
                    .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 600, 0), 1.0f)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0f)
                    .build())
    );

    // 远古骨碎片 - 防火 + 可投掷
    public static final DeferredItem<Item> ANCIENT_BONE_FRAGMENT = ITEMS.registerItem(
            "ancient_bone_fragment",
            properties -> new Item(properties) {
                @Override
                public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
                    ItemStack stack = player.getItemInHand(hand);

                    if (!level.isClientSide) {
                        AncientBoneFragmentProjectile projectile = new AncientBoneFragmentProjectile(level, player);
                        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.5f, 1.0f);
                        level.addFreshEntity(projectile);
                    }

                    player.playSound(SoundEvents.SNOWBALL_THROW, 0.5f, 0.8f);

                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }

                    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
                }
            },
            new Item.Properties()
                    .stacksTo(16)
                    .fireResistant()
    );

    // 幽匿真菌
    public static final DeferredItem<Item> SCULK_FUNGUS = ITEMS.registerItem(
            "sculk_fungus",
            Item::new,
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.2f)
                            .alwaysEdible()
                            .build())
    );

    // 灵魂薄饼
    public static final DeferredItem<Item> SOUL_PANCAKE = ITEMS.registerItem(
            "soul_pancake",
            Item::new,
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(7)
                            .alwaysEdible()
                            .saturationModifier(0.6f)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0f)
                            .build())
    );

    // 骨头脆片
    public static final DeferredItem<Item> ANCIENT_BRITTLE_BONE_FRAGMENTS = ITEMS.registerItem(
            "ancient_brittle_bone_fragments",
            Item::new,
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(6)
                            .saturationModifier(1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1800, 1), 1.0f)
                            .build())
    );

    // 幽匿猪儿虫
    public static final DeferredItem<Item> SCULK_CATERPILLAR = ITEMS.registerItem(
            "sculk_caterpillar",
            Item::new,
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(18)
                            .saturationModifier(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 600, 0), 1.0f)
                            .effect(() -> new MobEffectInstance(ModEffects.ECHO.getDelegate(), 3600, 0), 1.0f)
                            .build())
    );

    public static final DeferredItem<Item> SCULK_FLESH = ITEMS.registerItem(
            "sculk_flesh",
            Item::new,
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.5f)
                            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 200, 0), 1.0f)
                            .build())
    );

    // 幽匿猪肋排块
    public static final DeferredItem<FoodBlockItem> SCULK_PORK_RIBS_BLOCK_ITEM = ITEMS.register(
            "sculk_pork_ribs_block",
            () -> new FoodBlockItem(ModBlocks.SCULK_PORK_RIBS_BLOCK.get())
    );
}
