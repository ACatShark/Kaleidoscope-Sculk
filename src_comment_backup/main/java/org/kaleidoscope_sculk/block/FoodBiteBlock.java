package org.kaleidoscope_sculk.block;

import com.github.ysbbbbbb.kaleidoscopecookery.item.quality.Quality;
import com.github.ysbbbbbb.kaleidoscopecookery.item.quality.QualityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock.DEFAULT_QUALITY;
import static com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock.QUALITY;

/**
 * 幽匿方块食物。
 * <p>
 * 直接继承森罗厨房的 {@code FoodBiteBlock}，复用其咬口状态（bites）、朝向与
 * 品质（Quality）系统；同时保留本模组的「额外掉落（返还容器）」与「每口触发效果」逻辑。
 */
public class FoodBiteBlock extends com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock {

    // 返还 / 额外掉落（碗、红蜡烛、花盆、幽匿尖啸体等，可多个）
    private final List<Supplier<Item>> extraDropSuppliers;

    // 每口触发的效果
    @Nullable
    private final Supplier<MobEffectInstance> effectSupplier;
    private final float effectChance;

    /** 最简构造：无返还、无效果 */
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape) {
        this(foodProperties, maxBites, shape, (List<Supplier<Item>>) null, null, 0.0F);
    }

    /** 带返还物品（单个） */
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape,
                         @Nullable Supplier<Item> extraDropSupplier) {
        this(foodProperties, maxBites, shape,
                extraDropSupplier == null ? null : List.of(extraDropSupplier), null, 0.0F);
    }

    /** 带每口效果 */
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape,
                         @Nullable Supplier<MobEffectInstance> effectSupplier,
                         float effectChance) {
        this(foodProperties, maxBites, shape, (List<Supplier<Item>>) null, effectSupplier, effectChance);
    }

    /** 带返还物品（单个）+ 每口效果 */
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape,
                         @Nullable Supplier<Item> extraDropSupplier,
                         @Nullable Supplier<MobEffectInstance> effectSupplier,
                         float effectChance) {
        this(foodProperties, maxBites, shape,
                extraDropSupplier == null ? null : List.of(extraDropSupplier), effectSupplier, effectChance);
    }

    /** 带返还物品（多个）+ 每口效果 */
    public FoodBiteBlock(FoodProperties foodProperties, int maxBites, VoxelShape shape,
                         @Nullable List<Supplier<Item>> extraDropSuppliers,
                         @Nullable Supplier<MobEffectInstance> effectSupplier,
                         float effectChance) {
        super(foodProperties, maxBites, null);
        this.extraDropSuppliers = extraDropSuppliers == null
                ? List.<Supplier<Item>>of()
                : List.copyOf(extraDropSuppliers);
        this.effectSupplier = effectSupplier;
        this.effectChance = effectChance;
        this.setAABB(shape);
    }

    public FoodProperties getFoodProperties() {
        return this.foodProperties;
    }

    /** 第一个返还物（作为手持食用的主容器），没有则为 null */
    @Nullable
    public Supplier<Item> getExtraDropSupplier() {
        return extraDropSuppliers.isEmpty() ? null : extraDropSuppliers.get(0);
    }

    /** 全部返还物列表 */
    public List<Supplier<Item>> getExtraDropSuppliers() {
        return extraDropSuppliers;
    }

    @Nullable
    public Supplier<MobEffectInstance> getEffectSupplier() {
        return effectSupplier;
    }

    public float getEffectChance() {
        return effectChance;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                            Player player, BlockHitResult hit) {
        // 已吃到最后一口：下一次交互直接移除方块（容器返还交给 getDrops() 处理）
        if (state.getValue(getBites()) >= getMaxBites()) {
            if (!level.isClientSide) {
                level.destroyBlock(pos, true, player);
            }
            return InteractionResult.SUCCESS;
        }
        if (!player.canEat(foodProperties.canAlwaysEat())) {
            return InteractionResult.PASS;
        }
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        return eat(level, pos, state, player);
    }

    /**
     * 复用森罗厨房的完整食用流程（品质加成、{@link FoodProperties} 效果、音效、咬口递增），
     * 在其基础上补充本模组的「每口额外效果」。最后一口（末阶段模型，如 bite4）会先显示，
     * 并在下一次交互时由 {@link #useWithoutItem} 移除方块；容器返还统一交给 getDrops() 处理。
     */
    @Override
    protected InteractionResult eat(Level level, BlockPos pos, BlockState state, Player player) {
        InteractionResult result = super.eat(level, pos, state, player);
        if (!result.consumesAction()) {
            return result;
        }

        // 每口额外效果
        applyEffect(level, player);

        return result;
    }

    /** 应用效果的方法 */
    protected void applyEffect(Level level, Player player) {
        if (level.isClientSide) return;

        if (effectSupplier != null && level.random.nextFloat() < effectChance) {
            MobEffectInstance effect = effectSupplier.get();
            if (effect != null) {
                // 如果已有同类型效果，先移除再添加（刷新持续时间）
                player.removeEffect(effect.getEffect());
                player.addEffect(effect);
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> drops = new ArrayList<>();

        if (state.getValue(getBites()) == 0) {
            // 没吃过：掉落整个食物方块（品质方块会带上品质数据）
            ItemStack stack = new ItemStack(this.asItem());
            int qualityNum = state.getValue(QUALITY);
            if (qualityNum != DEFAULT_QUALITY) {
                QualityUtils.setQuality(stack, Quality.BY_ID.apply(qualityNum));
            }
            drops.add(stack);
        } else if (!extraDropSuppliers.isEmpty()) {
            // 吃过（含吃完）：掉落容器 / 额外物品（按 Cookery 设计，创造模式同样返还）
            for (Supplier<Item> supplier : extraDropSuppliers) {
                Item extraItem = supplier.get();
                if (extraItem != null) {
                    drops.add(new ItemStack(extraItem));
                }
            }
        }

        return drops;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        int currentBites = state.getValue(getBites());
        int maxBites = getMaxBites();
        return (maxBites - currentBites) * 15 / maxBites;
    }
}
