package com.creeping_creeper.tinkers_thinking;

import com.creeping_creeper.tinkers_thinking.common.networking.ModMessages;
import com.creeping_creeper.tinkers_thinking.common.recipes.ModRecipes;
import com.creeping_creeper.tinkers_thinking.common.things.*;
import com.creeping_creeper.tinkers_thinking.common.things.block.entity.ModBlockEntities;
import com.creeping_creeper.tinkers_thinking.common.things.fluid.ModFluidTypes;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModPotions;
import com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.ModModifiers;
import com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.SculkCatalyseModifier;
import com.creeping_creeper.tinkers_thinking.common.world.ModConfiguredFeatures;
import com.creeping_creeper.tinkers_thinking.common.world.ModPlacedFeatures;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkersThinking.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersThinking
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "tinkers_thinking";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public TinkersThinking()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.registers(modEventBus);
        ModBlocks.registers(modEventBus);
        ModFluids.registers(modEventBus);
        ModFluidTypes.registers(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModEffects.registers(modEventBus);
        ModPotions.registers(modEventBus);
        ModConfiguredFeatures.registers(modEventBus);
        ModPlacedFeatures.registers(modEventBus);
        ModBlockEntities.registers(modEventBus);
        ModRecipes.registers(modEventBus);
        ModModifiers.regeisters(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }
    public void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
        MinecraftForge.EVENT_BUS.register(new SculkCatalyseModifier());
        ModPotions.setup();
    }
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)

        {
        }
    }
}
