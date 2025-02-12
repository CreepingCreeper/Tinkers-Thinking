package com.creeping_creeper.tinkers_thinking.common.world;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.entity.ModEntityTypes;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModToolItems;
import com.creeping_creeper.tinkers_thinking.common.things.block.entity.ModBlockEntities;
import com.creeping_creeper.tinkers_thinking.common.things.block.renderer.DryingRackBlockEntityRenderer;
import com.creeping_creeper.tinkers_thinking.common.things.entity.renderer.ModFluidEffectProjectileRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import slimeknights.tconstruct.common.ClientEventBase;
import slimeknights.tconstruct.library.client.model.TinkerItemProperties;


@SuppressWarnings("unused")

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ToolClientEvents extends ClientEventBase  {
    @SubscribeEvent
    static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            TinkerItemProperties.registerToolProperties(ModToolItems.paxel.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.knife.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.mace.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.arrow_thrower.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.magma_staff.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.quartz_staff.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.clay_staff.asItem());
            TinkerItemProperties.registerCrossbowProperties(ModToolItems.repeating_crossbow.asItem());
        });
    }
    @SubscribeEvent
    public static void registerRenderes(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.Drying_Rack.get(), DryingRackBlockEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.fluidSpitEntity.get(), ModFluidEffectProjectileRenderer::new);
    }
}