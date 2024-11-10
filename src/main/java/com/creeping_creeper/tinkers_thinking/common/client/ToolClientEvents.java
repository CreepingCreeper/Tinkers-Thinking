package com.creeping_creeper.tinkers_thinking.common.client;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.ModItems;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import slimeknights.mantle.data.listener.ISafeManagerReloadListener;
import slimeknights.tconstruct.common.ClientEventBase;
import slimeknights.tconstruct.library.client.materials.MaterialTooltipCache;
import slimeknights.tconstruct.library.client.model.DynamicTextureLoader;
import slimeknights.tconstruct.library.client.model.TinkerItemProperties;
import slimeknights.tconstruct.library.client.modifiers.ModifierModelManager;
import slimeknights.tconstruct.library.modifiers.ModifierManager;


@SuppressWarnings("unused")

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ToolClientEvents extends ClientEventBase {
    private static final ISafeManagerReloadListener MODIFIER_RELOAD_LISTENER = manager -> ModifierManager.INSTANCE.getAllValues().forEach(modifier -> modifier.clearCache(PackType.CLIENT_RESOURCES));
    public ToolClientEvents() {
    }
    @SubscribeEvent
    static void addResourceListener(RegisterClientReloadListenersEvent manager) {
        ModifierModelManager.init(manager);
        MaterialTooltipCache.init(manager);
        DynamicTextureLoader.init(manager);
        manager.registerReloadListener(MODIFIER_RELOAD_LISTENER);
    }
    @SubscribeEvent
    static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            TinkerItemProperties.registerToolProperties(ModItems.paxel.get().asItem());
            TinkerItemProperties.registerToolProperties(ModItems.knife.get().asItem());
            TinkerItemProperties.registerToolProperties(ModItems.mace.get().asItem());
            TinkerItemProperties.registerToolProperties(ModItems.arrow_thrower.get().asItem());
            TinkerItemProperties.registerToolProperties(ModItems.magma_staff.get().asItem());
            TinkerItemProperties.registerToolProperties(ModItems.quartz_staff.get().asItem());
            TinkerItemProperties.registerToolProperties(ModItems.clay_staff.get().asItem());
        });
    }
}