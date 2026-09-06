package com.bmt.kaleidoscope_nether.datagen.subAdvancements;

import com.bmt.kaleidoscope_nether.api.KNTags;
import com.bmt.kaleidoscope_nether.init.KNEffects;
import com.bmt.kaleidoscope_nether.init.KNItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class KNAdvances implements AdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.@NotNull Provider registries, @NotNull Consumer<AdvancementHolder> saver, @NotNull ExistingFileHelper existingFileHelper) {
        // Root advancement - 使用 mod_items 标签作为触发条件
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(KNItems.BLOWGUN.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.root.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.root.description"), 
                        ResourceLocation.parse("kaleidoscope_nether:textures/advancement/background.png"), 
                        AdvancementType.TASK, true, true, false)
                .addCriterion("mod_items", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(KNTags.Items.MOD_ITEMS).build()))
                .save(saver, "kaleidoscope_nether/root");

        // Star Dust - 获得星尘
        AdvancementHolder starDust = Advancement.Builder.advancement()
                .parent(root)
                .display(KNItems.STAR_DUST.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.star_dust.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.star_dust.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("star_dust", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.STAR_DUST.get()))
                .save(saver, "kaleidoscope_nether/star_dust");
        
        // Star Blessing Buff - 获得星之祝福效果
        AdvancementHolder starBlessingBuff = Advancement.Builder.advancement()
                .parent(starDust)
                .display(KNItems.STAR_STEW.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.star_blessing_buff.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.star_blessing_buff.description"), 
                        null, AdvancementType.GOAL, true, true, false)
                .addCriterion("star_blessing_buff", EffectsChangedTrigger.TriggerInstance.hasEffects(
                        MobEffectsPredicate.Builder.effects().and(KNEffects.STAR_BLESSING)))
                .save(saver, "kaleidoscope_nether/star_blessing_buff");
        
        // Soul Return Rice - 食用魂归饭
        AdvancementHolder soulReturnRice = Advancement.Builder.advancement()
                .parent(starDust)
                .display(KNItems.SOUL_RETURN_RICE.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.soul_return_rice.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.soul_return_rice.description"), 
                        null, AdvancementType.GOAL, true, true, false)
                .addCriterion("soul_return_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SOUL_RETURN_RICE.get()))
                .save(saver, "kaleidoscope_nether/soul_return_rice");

        // Star Diet - 食用所有星之食物
        AdvancementHolder starDiet = Advancement.Builder.advancement()
                .parent(starBlessingBuff)
                .display(KNItems.STAR_STEW_MEAT.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.star_diet.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.star_diet.description"), 
                        null, AdvancementType.CHALLENGE, true, true, false)
                .addCriterion("soul_return_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SOUL_RETURN_RICE.get()))
                .addCriterion("star_stew", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.STAR_STEW.get()))
                .addCriterion("star_ghast_pasta", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.STAR_GHAST_PASTA.get()))
                .addCriterion("star_stew_meat", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.STAR_STEW_MEAT.get()))
                .requirements(AdvancementRequirements.Strategy.AND)
                .save(saver, "kaleidoscope_nether/star_diet");

        // Nether Caterpillar - 获得地狱毛虫
        AdvancementHolder netherCaterpillar = Advancement.Builder.advancement()
                .parent(root)
                .display(KNItems.NETHER_CATERPILLAR.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.nether_caterpillar.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.nether_caterpillar.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("nether_caterpillar", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.NETHER_CATERPILLAR.get()))
                .save(saver, "kaleidoscope_nether/nether_caterpillar");

        // Nether Caterpillar Feast - 食用所有毛虫料理
        AdvancementHolder netherCaterpillarFeast = Advancement.Builder.advancement()
                .parent(netherCaterpillar)
                .display(KNItems.NETHER_CATERPILLAR_SASHIMI.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.nether_caterpillar_feast.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.nether_caterpillar_feast.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("nether_caterpillar_sashimi", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.NETHER_CATERPILLAR_SASHIMI.get()))
                .addCriterion("caramel_nether_caterpillar", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.CARAMEL_NETHER_CATERPILLAR.get()))
                .addCriterion("caramel_nether_caterpillar_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.CARAMEL_NETHER_CATERPILLAR_RICE.get()))
                .requirements(AdvancementRequirements.Strategy.AND)
                .save(saver, "kaleidoscope_nether/nether_caterpillar_feast");

        // Hoglin Tusk - 获得疣猪兽獠牙
        AdvancementHolder hoglinTusk = Advancement.Builder.advancement()
                .parent(root)
                .display(KNItems.HOGLIN_TUSK.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.hoglin_tusk.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.hoglin_tusk.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("hoglin_tusk", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.HOGLIN_TUSK.get()))
                .save(saver, "kaleidoscope_nether/hoglin_tusk");
        
        // Hoglin Tusk Braised Meat - 食用獠牙红烧肉
        AdvancementHolder hoglinTuskBraisedMeat = Advancement.Builder.advancement()
                .parent(hoglinTusk)
                .display(KNItems.HOGLIN_TUSK_BRAISED_MEAT.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.hoglin_tusk_braised_meat.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.hoglin_tusk_braised_meat.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("hoglin_tusk_braised_meat", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.HOGLIN_TUSK_BRAISED_MEAT.get()))
                .save(saver, "kaleidoscope_nether/hoglin_tusk_braised_meat");

        // Tusk Knife - 获得原始砍刀
        AdvancementHolder tuskKnife = Advancement.Builder.advancement()
                .parent(hoglinTusk)
                .display(KNItems.PRIMITIVE_MACHETE.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.tusk_knife.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.tusk_knife.description"), 
                        null, AdvancementType.GOAL, true, true, false)
                .addCriterion("primitive_machete", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.PRIMITIVE_MACHETE.get()))
                .save(saver, "kaleidoscope_nether/tusk_knife");

        // Lava Roasted Chicken - 食用岩浆烤鸡
        AdvancementHolder lavaRoastedChicken = Advancement.Builder.advancement()
                .parent(root)
                .display(KNItems.LAVA_ROASTED_CHICKEN.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.lava_roasted_chicken.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.lava_roasted_chicken.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("lava_roasted_chicken", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.LAVA_ROASTED_CHICKEN.get()))
                .save(saver, "kaleidoscope_nether/lava_roasted_chicken");

        // Wither Rib - 获得凋零肋骨
        AdvancementHolder witherRib = Advancement.Builder.advancement()
                .parent(root)
                .display(KNItems.WITHER_RIB.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.wither_rib.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.wither_rib.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("wither_rib", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.WITHER_RIB.get()))
                .save(saver, "kaleidoscope_nether/wither_rib");

        // Wither Bone Soup - 食用凋零骨汤
        AdvancementHolder witherBoneSoup = Advancement.Builder.advancement()
                .parent(witherRib)
                .display(KNItems.WITHER_BONE_SOUP.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.wither_bone_soup.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.wither_bone_soup.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("wither_bone_soup", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.WITHER_BONE_SOUP.get()))
                .save(saver, "kaleidoscope_nether/wither_bone_soup");

        // Gilded Fragment - 获得镀金碎片
        AdvancementHolder gildedFragment = Advancement.Builder.advancement()
                .parent(root)
                .display(KNItems.GILDED_FRAGMENT.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.gilded_fragment.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.gilded_fragment.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("gilded_fragment", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.GILDED_FRAGMENT.get()))
                .save(saver, "kaleidoscope_nether/gilded_fragment");

        // Gilded Barbaric Roast - 食用镀金蛮荒烤肉
        AdvancementHolder gildedBarbaricRoast = Advancement.Builder.advancement()
                .parent(gildedFragment)
                .display(KNItems.GILDED_BARBARIC_ROAST.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.gilded_barbaric_roast.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.gilded_barbaric_roast.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("gilded_barbaric_roast", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.GILDED_BARBARIC_ROAST.get()))
                .save(saver, "kaleidoscope_nether/gilded_barbaric_roast");

        // Nether Agriculturist - 获得所有地狱作物
        AdvancementHolder netherAgriculturist = Advancement.Builder.advancement()
                .parent(root)
                .display(KNItems.CRIMSON_FRUIT.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.nether_agriculturist.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.nether_agriculturist.description"), 
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("crimson_fruit", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.CRIMSON_FRUIT.get()))
                .addCriterion("warped_fruit", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.WARPED_FRUIT.get()))
                .addCriterion("poisonous_fruit", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.POISONOUS_FRUIT.get()))
                .addCriterion("soul_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.SOUL_PEPPER.get()))
                .requirements(AdvancementRequirements.Strategy.AND)
                .save(saver, "kaleidoscope_nether/nether_agriculturist");

        // Eight Cuisines - 食用所有八大菜系料理
        AdvancementHolder eightCuisines = Advancement.Builder.advancement()
                .parent(root)
                .display(KNItems.ROUJIAMO.get(), 
                        Component.translatable("advancements.kaleidoscope_nether.eight_cuisines.title"), 
                        Component.translatable("advancements.kaleidoscope_nether.eight_cuisines.description"), 
                        null, AdvancementType.GOAL, true, true, false)
                .addCriterion("spicy_pot", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SPICY_POT.get()))
                .addCriterion("spicy_pot_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SPICY_POT_RICE.get()))
                .addCriterion("mapo_tofu", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.MAPO_TOFU.get()))
                .addCriterion("mapo_tofu_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.MAPO_TOFU_RICE.get()))
                .addCriterion("chongqing_noodles", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.CHONGQING_NOODLES.get()))
                .addCriterion("luosifen", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.LUOSIFEN.get()))
                .addCriterion("sauerkraut_fish", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SAUERKRAUT_FISH.get()))
                .addCriterion("roujiamo", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.ROUJIAMO.get()))
                .addCriterion("forgetfulness_soup", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.FORGETFULNESS_SOUP.get()))
//                .addCriterion("braised_lion_head", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.BRAISED_LION_HEAD.get()))
//                .addCriterion("braised_pork_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.BRAISED_PORK_RICE.get()))
                .addCriterion("garlic_oysters", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.GARLIC_OYSTERS.get()))
                .addCriterion("couples_lung_slice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.COUPLES_LUNG_SLICE.get()))
                .addCriterion("pepper_pork_belly_chicken_soup", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.PEPPER_PORK_BELLY_CHICKEN_SOUP.get()))
//                .addCriterion("corn_carrot_pork_rib_soup", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.CORN_CARROT_PORK_RIB_SOUP.get()))
                .requirements(AdvancementRequirements.Strategy.AND)
                .save(saver, "kaleidoscope_nether/eight_cuisines");
    }
}
