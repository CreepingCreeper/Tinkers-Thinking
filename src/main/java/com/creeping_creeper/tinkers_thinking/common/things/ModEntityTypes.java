package com.creeping_creeper.tinkers_thinking.common.things;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.entity.ModFluidEffectProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,TinkersThinking.MODID);
    public static final RegistryObject<EntityType<ModFluidEffectProjectile>> fluidSpitEntity = ENTITY_TYPES.register("fluid_spit", () ->
            EntityType.Builder.<ModFluidEffectProjectile>of(ModFluidEffectProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).setShouldReceiveVelocityUpdates(false).
                    build(new ResourceLocation(TinkersThinking.MODID,"fluid_spit").toString()));
    public static void register(IEventBus modEventBus){
        ENTITY_TYPES.register(modEventBus);
    }
}
