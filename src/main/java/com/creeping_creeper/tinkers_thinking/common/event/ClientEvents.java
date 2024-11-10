package com.creeping_creeper.tinkers_thinking.common.event;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.ModEntityTypes;
import com.creeping_creeper.tinkers_thinking.common.things.block.entity.ModBlockEntities;
import com.creeping_creeper.tinkers_thinking.common.things.block.renderer.DryingRackBlockEntityRenderer;
import com.creeping_creeper.tinkers_thinking.common.things.entity.renderer.ModFluidEffectProjectileRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {@Mod.EventBusSubscriber(modid = TinkersThinking.MODID,value= Dist.CLIENT)
public static class ClientForgeEvents{
}

    @Mod.EventBusSubscriber(modid = TinkersThinking.MODID,value = Dist.CLIENT,bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerRenderes(EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(ModBlockEntities.Drying_Rack.get(), DryingRackBlockEntityRenderer::new);
            event.registerEntityRenderer(ModEntityTypes.fluidSpitEntity.get(), ModFluidEffectProjectileRenderer::new);
        }
    }
}