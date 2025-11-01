package com.creeping_creeper.tinkers_thinking.common.world;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModBlockEntities;
import com.creeping_creeper.tinkers_thinking.common.register.ModEntities;
import com.creeping_creeper.tinkers_thinking.common.register.ModToolItems;
import com.creeping_creeper.tinkers_thinking.common.things.block.renderer.DryingRackBlockEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.FontManager;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import slimeknights.tconstruct.library.client.model.TinkerItemProperties;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ClientEvents {
    public static void onConstruct() {
        ModBook.initBook();
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.Drying_Rack.get(), DryingRackBlockEntityRenderer::new);
    }
    @SubscribeEvent
    static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            TinkerItemProperties.registerToolProperties(ModToolItems.paxel.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.knife.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.mace.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.arrow_thrower.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.magma_staff.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.quartz_staff.asItem());
            TinkerItemProperties.registerToolProperties(ModToolItems.clay_staff.asItem());
            TinkerItemProperties.registerCrossbowProperties(ModToolItems.repeating_crossbow.asItem());
            EntityRenderDispatcher renderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        });
        ModBook.FANTASTIC_GADGETRY.fontRenderer = unicodeFontRender();
    }
    private static Font unicodeRenderer;
    public static Font unicodeFontRender() {
        if (unicodeRenderer == null)
            unicodeRenderer = new Font(rl -> {
                FontManager resourceManager = Minecraft.getInstance().fontManager;
                return resourceManager.fontSets.get(Minecraft.UNIFORM_FONT);
            }, false);

        return unicodeRenderer;
    }
}