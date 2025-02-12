package com.creeping_creeper.tinkers_thinking.common.things.entity;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.block.entity.ModBlockEntities;
import com.creeping_creeper.tinkers_thinking.common.things.block.renderer.DryingRackBlockEntityRenderer;
import com.creeping_creeper.tinkers_thinking.common.things.entity.ModFluidEffectProjectile;
import com.creeping_creeper.tinkers_thinking.common.things.entity.renderer.ModFluidEffectProjectileRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.EntityTypeDeferredRegister;

public class ModEntityTypes{
    protected static final EntityTypeDeferredRegister ENTITIES = new EntityTypeDeferredRegister(TinkersThinking.MODID);

    public static final RegistryObject<EntityType<ModFluidEffectProjectile>> fluidSpitEntity = ENTITIES.register("fluid_spit", () ->
            EntityType.Builder.<ModFluidEffectProjectile>of(ModFluidEffectProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).setShouldReceiveVelocityUpdates(false));
    public static void registers(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
