package org.kaleidoscope_sculk.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.kaleidoscope_sculk.component.SoulSailData;
import org.kaleidoscope_sculk.register.ModDataComponents;
import org.kaleidoscope_sculk.register.ModItems;

import java.util.List;

public class SoulSailItem extends Item implements Equipable {

    public static final int LEVEL_SOUL = 30;
    public static final int LEVEL_THOUSAND = 60;
    public static final int LEVEL_MYRIAD = 100;

    private static final int BAR_SEGMENTS = 20;
    private static final float SOUL_DROP_CHANCE = 0.3f;

    private final SailType type;
    private final int maxLevel;
    private final int maxXp;

    public SoulSailItem(Properties properties, SailType type) {
        super(properties);
        this.type = type;
        this.maxLevel = type.maxLevel;
        this.maxXp = getTotalXpForLevel(this.maxLevel);
    }

    
    
    

    public static int getTotalXpForLevel(int level) {
        int total = 0;
        for (int i = 0; i < level; i++) {
            total += getXpNeededForLevel(i);
        }
        return total;
    }

    public static int getXpNeededForLevel(int level) {
        if (level >= 30) {
            return 112 + (level - 30) * 9;
        }
        return level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2;
    }

    public static int xpToLevel(int xpPoints) {
        int level = 0;
        int totalXp = 0;

        while (totalXp + getXpNeededForLevel(level) <= xpPoints) {
            totalXp += getXpNeededForLevel(level);
            level++;
        }

        return level;
    }

    
    
    

    public static SoulSailData getSailData(ItemStack stack) {
        SoulSailData data = stack.get(ModDataComponents.SOUL_SAIL_DATA.get());
        return data != null ? data : SoulSailData.DEFAULT;
    }

    public static void setSailData(ItemStack stack, SoulSailData data) {
        stack.set(ModDataComponents.SOUL_SAIL_DATA.get(), data);
    }

    public static void setSailData(ItemStack stack, SailType type, int rotation, int xp) {
        setSailData(stack, new SoulSailData(type.name, xp, rotation));
    }

    public static int getStoredXp(ItemStack stack) {
        return getSailData(stack).storedXp();
    }

    public static int getStoredLevel(ItemStack stack) {
        return xpToLevel(getStoredXp(stack));
    }

    public static void setStoredXp(ItemStack stack, int xp) {
        setSailData(stack, getSailData(stack).withStoredXp(xp));
    }

    public static int getRotation(ItemStack stack) {
        return getSailData(stack).rotation();
    }

    public static void setRotation(ItemStack stack, int rotation) {
        setSailData(stack, getSailData(stack).withRotation(rotation));
    }

    public static SailType getSailTypeFromItem(ItemStack stack) {
        return stack.getItem() instanceof SoulSailItem sailItem ? sailItem.getType() : SailType.SOUL;
    }

    public static int getMaxXpForItem(ItemStack stack) {
        return stack.getItem() instanceof SoulSailItem sailItem ? sailItem.getMaxXp() : getTotalXpForLevel(LEVEL_SOUL);
    }

    public static int getMaxLevelForItem(ItemStack stack) {
        return stack.getItem() instanceof SoulSailItem sailItem ? sailItem.getMaxLevel() : LEVEL_SOUL;
    }

    public static boolean isFullItem(ItemStack stack) {
        return stack.is(ModItems.SOUL_SAIL_FULL.get())
                || stack.is(ModItems.THOUSAND_SOUL_SAIL_FULL.get())
                || stack.is(ModItems.MYRIAD_SOUL_SAIL_FULL.get());
    }

    public static boolean isNonFullItem(ItemStack stack) {
        return stack.is(ModItems.SOUL_SAIL.get())
                || stack.is(ModItems.THOUSAND_SOUL_SAIL.get())
                || stack.is(ModItems.MYRIAD_SOUL_SAIL.get());
    }

    
    
    

    public Item getFullVersion() {
        return switch (this.type) {
            case SOUL -> ModItems.SOUL_SAIL_FULL.get();
            case THOUSAND -> ModItems.THOUSAND_SOUL_SAIL_FULL.get();
            case MYRIAD -> ModItems.MYRIAD_SOUL_SAIL_FULL.get();
        };
    }

    public Item getNonFullVersion() {
        return switch (this.type) {
            case SOUL -> ModItems.SOUL_SAIL.get();
            case THOUSAND -> ModItems.THOUSAND_SOUL_SAIL.get();
            case MYRIAD -> ModItems.MYRIAD_SOUL_SAIL.get();
        };
    }

    public static ItemStack convertToFullItem(ItemStack stack) {
        if (isFullItem(stack)) {
            return stack;
        }

        if (stack.getItem() instanceof SoulSailItem sailItem) {
            ItemStack newStack = new ItemStack(sailItem.getFullVersion());
            setSailData(newStack, getSailData(stack));
            return newStack;
        }

        return stack;
    }

    public static ItemStack convertToNonFullItem(ItemStack stack) {
        if (!isFullItem(stack)) {
            return stack;
        }

        if (stack.getItem() instanceof SoulSailItem sailItem) {
            ItemStack newStack = new ItemStack(sailItem.getNonFullVersion());
            setSailData(newStack, getSailData(stack));
            return newStack;
        }

        return stack;
    }

    
    public boolean storeXp(ItemStack stack, int xpPoints) {
        if (isFullItem(stack)) {
            return false;
        }

        int currentXp = getStoredXp(stack);
        int remaining = this.maxXp - currentXp;
        if (remaining <= 0) {
            return false;
        }

        int newXp = currentXp + Math.min(xpPoints, remaining);
        setStoredXp(stack, newXp);

        return newXp >= this.maxXp;
    }

    
    
    

    public SailType getType() {
        return this.type;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public int getMaxXp() {
        return this.maxXp;
    }

    public String getTypeName() {
        return this.type.displayName;
    }

    public ChatFormatting getColor() {
        return this.type.color;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (getStoredXp(stack) > 0) {
            return this.useSoulSail(level, player, hand, stack);
        }

        
        if (player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            player.setItemSlot(EquipmentSlot.HEAD, stack.copyWithCount(1));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            player.playSound(SoundEvents.ARMOR_EQUIP_GENERIC.value(), 1.0f, 1.0f);
            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    private InteractionResultHolder<ItemStack> useSoulSail(Level level, Player player,
                                                           InteractionHand hand, ItemStack stack) {
        int storedXp = getStoredXp(stack);
        int storedLevel = xpToLevel(storedXp);
        boolean isFull = isFullItem(stack);

        
        if (player.isShiftKeyDown()) {
            if (storedXp <= 0) {
                player.displayClientMessage(Component.literal("§c魂幡中没有经验"), true);
                return InteractionResultHolder.pass(stack);
            }

            if (level.isClientSide) {
                return InteractionResultHolder.success(stack);
            }

            setStoredXp(stack, 0);
            player.giveExperiencePoints(storedXp);
            stack = swapToNonFull(stack, player, hand, isFull);

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5f, 1.0f);
            player.displayClientMessage(Component.literal("§a已取出全部经验！"), true);

            return InteractionResultHolder.success(stack);
        }

        
        if (storedXp > 0) {
            if (level.isClientSide) {
                return InteractionResultHolder.success(stack);
            }

            int neededForNextLevel = getXpNeededForLevel(storedLevel);
            int toWithdraw = Math.min(neededForNextLevel, storedXp);
            setStoredXp(stack, storedXp - toWithdraw);
            player.giveExperiencePoints(toWithdraw);

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5f, 1.0f);
            player.displayClientMessage(Component.literal("§a取出了 §e" + toWithdraw + " §a点经验"), true);

            stack = swapToNonFull(stack, player, hand, isFull);

            return InteractionResultHolder.success(stack);
        }

        
        if (!level.isClientSide) {
            if (isFull) {
                player.displayClientMessage(
                        Component.literal("§e满级" + this.type.displayName + " §7经验已满！"), true);
            } else {
                player.displayClientMessage(Component.literal(
                        "§7" + this.type.displayName + "存储: §e" + storedXp + " §7/ §e" + this.maxXp
                                + " §7点经验 (§e" + storedLevel + "§7级)"), true);
            }
        }

        return InteractionResultHolder.pass(stack);
    }

    
    private ItemStack swapToNonFull(ItemStack stack, Player player, InteractionHand hand, boolean isFull) {
        if (!isFull) {
            return stack;
        }

        ItemStack newStack = convertToNonFullItem(stack);
        player.setItemInHand(hand, newStack);
        return newStack;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (!state.is(Blocks.SOUL_SAND) && !state.is(Blocks.SOUL_SOIL)) {
            return InteractionResult.PASS;
        }

        return this.convertSoulBlock(level, pos, state, context.getPlayer(), context.getItemInHand());
    }

    private InteractionResult convertSoulBlock(Level level, BlockPos pos, BlockState state,
                                               Player player, ItemStack stack) {
        if (!level.isClientSide) {
            Block newBlock = state.is(Blocks.SOUL_SAND) ? Blocks.SAND : Blocks.DIRT;
            level.setBlock(pos, newBlock.defaultBlockState(), Block.UPDATE_ALL);

            if (level.random.nextFloat() < SOUL_DROP_CHANCE) {
                ItemEntity itemEntity = new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        new ItemStack(ModItems.SOUL.get())
                );
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            }

            level.playSound(null, pos, SoundEvents.SOUL_ESCAPE.value(), SoundSource.BLOCKS, 1.0f, 1.0f);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }

    
    
    

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        boolean isFull = isFullItem(stack);
        int storedXp = getStoredXp(stack);
        int storedLevel = xpToLevel(storedXp);

        if (isFull) {
            tooltip.add(Component.literal(this.type.color + this.type.displayName + " §e✦ 满级 ✦"));
        } else {
            tooltip.add(Component.literal(this.type.color + "✦ " + this.type.displayName));
            tooltip.add(Component.translatable("item.kaleidoscope_sculk.soul_sail." + this.type.name + ".tooltip")
                    .withStyle(ChatFormatting.GRAY));
        }

        tooltip.add(Component.literal(buildProgressBar(storedXp, isFull)));

        if (!isFull) {
            tooltip.add(Component.literal("§7经验等级: §e" + storedLevel + " §7/ §e" + this.maxLevel));
            tooltip.add(Component.literal("§7经验点数: §e" + format(storedXp) + " §7/ §e" + format(this.maxXp)));
        }

        tooltip.add(Component.literal(""));
        if (isFull || storedXp > 0) {
            tooltip.add(Component.literal("§e右键 §7取1级  §eShift+右键 §7全取"));
        }

        tooltip.add(Component.literal(""));
        if (this.type == SailType.MYRIAD) {
            tooltip.add(Component.literal("§6✦ 已到达最终形态"));
        } else {
            if (!isFull) {
                int needLevel = this.maxLevel - storedLevel;
                int needXp = this.maxXp - storedXp;
                tooltip.add(Component.literal("§7满级还需: §e" + needLevel + " §7级 (§e" + format(needXp) + " §7点)"));
            }
            tooltip.add(Component.literal("§7满级后 + 8灵魂 → §e" + this.getNextTierName()));
        }
    }

    private String buildProgressBar(int storedXp, boolean isFull) {
        float progress = (float) storedXp / this.maxXp;
        int bars = (int) (progress * BAR_SEGMENTS);

        StringBuilder bar = new StringBuilder("§7[");

        if (isFull) {
            bar.append("§e█".repeat(BAR_SEGMENTS)).append("§7] §e已满级");
            return bar.toString();
        }

        String filledColor = switch (this.type) {
            case SOUL -> "§b";
            case THOUSAND -> "§5";
            case MYRIAD -> "§6";
        };

        for (int i = 0; i < BAR_SEGMENTS; i++) {
            bar.append(i < bars ? filledColor + "█" : "§8░");
        }

        return bar.append("§7]").toString();
    }

    private String getNextTierName() {
        return switch (this.type) {
            case SOUL -> "千魂幡";
            case THOUSAND -> "万魂幡";
            case MYRIAD -> "最终形态";
        };
    }

    private static String format(int value) {
        return String.format("%,d", value);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isFullItem(stack);
    }

    @Override
    public Component getName(ItemStack stack) {
        if (isFullItem(stack)) {
            return Component.literal(this.type.color + this.type.displayName + " §e(满级)");
        }
        return Component.literal(this.type.color + this.type.displayName);
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return super.getDescriptionId(stack) + "." + this.type.name;
    }

    public enum SailType {
        SOUL("soul", LEVEL_SOUL, ChatFormatting.BLUE, "魂幡"),
        THOUSAND("thousand", LEVEL_THOUSAND, ChatFormatting.DARK_PURPLE, "千魂幡"),
        MYRIAD("myriad", LEVEL_MYRIAD, ChatFormatting.GOLD, "万魂幡");

        public final String name;
        public final int maxLevel;
        public final ChatFormatting color;
        public final String displayName;

        SailType(String name, int maxLevel, ChatFormatting color, String displayName) {
            this.name = name;
            this.maxLevel = maxLevel;
            this.color = color;
            this.displayName = displayName;
        }

        public static SailType fromName(String name) {
            for (SailType type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            return SOUL;
        }
    }
}
