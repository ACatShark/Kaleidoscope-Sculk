package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.entity.BlazeHeartProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KNEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = 
        DeferredRegister.create(Registries.ENTITY_TYPE, KaleidoscopeNether.MOD_ID);
    
    public static final DeferredHolder<EntityType<?>, EntityType<BlazeHeartProjectile>> BLAZE_HEART_PROJECTILE = 
        ENTITIES.register("blaze_heart_projectile", 
            () -> EntityType.Builder.<BlazeHeartProjectile>of(BlazeHeartProjectile::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .build("blaze_heart_projectile"));
}