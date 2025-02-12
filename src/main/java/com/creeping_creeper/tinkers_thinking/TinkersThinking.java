package com.creeping_creeper.tinkers_thinking;

import com.creeping_creeper.tinkers_thinking.common.networking.ModMessages;
import com.creeping_creeper.tinkers_thinking.common.recipes.ModRecipes;
import com.creeping_creeper.tinkers_thinking.common.things.block.entity.ModBlockEntities;
import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import com.creeping_creeper.tinkers_thinking.common.things.entity.ModEntityTypes;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModPotions;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModToolItems;
import com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.ModModifiers;
import com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.durability.SculkCatalyseModifier;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.utils.Util;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkersThinking.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersThinking
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "tinkers_thinking";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
 
    public TinkersThinking()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModCommonItems.registers(bus);
        ModToolItems.registers(bus);
        ModBlockEntities.registers(bus);
        ModRecipes.registers(bus);
        ModEntityTypes.registers(bus);
        ModEffects.registers(bus);
        ModPotions.registers(bus);
        bus.register(new ModModifiers());
        bus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ModModifiers.initRegisters();
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
    public static String makeTranslationKey(String base, String name) {
        return Util.makeTranslationKey(base, getResource(name));
    }
    public static MutableComponent makeTranslation(String base, String name) {
        return Component.translatable(makeTranslationKey(base, name));
    }
    public static MutableComponent makeTranslation(String base, String name, Object... arguments) {
        return Component.translatable(makeTranslationKey(base, name), arguments);
    }
    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(MODID, name);
    }
}
