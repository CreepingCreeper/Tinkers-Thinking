package com.creeping_creeper.tinkers_thinking.common.things;

import com.creeping_creeper.tinkers_thinking.common.things.entity.ModFluidEffectProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes extends ModModule{
    public static final RegistryObject<EntityType<ModFluidEffectProjectile>> fluidSpitEntity = ENTITIES.register("fluid_spit", () ->
            EntityType.Builder.<ModFluidEffectProjectile>of(ModFluidEffectProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).setShouldReceiveVelocityUpdates(false));
}
