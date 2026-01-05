package com.creeping_creeper.tinkers_thinking;

import com.creeping_creeper.tinkers_thinking.common.client.ModSlots;
import com.creeping_creeper.tinkers_thinking.common.integration.ModularGolemsPlugin;
import com.creeping_creeper.tinkers_thinking.common.integration.TouhouLittleMaidPlugin;
import com.creeping_creeper.tinkers_thinking.common.library.OnDeath;
import com.creeping_creeper.tinkers_thinking.common.library.OnExpPickUp;
import com.creeping_creeper.tinkers_thinking.common.library.Onhurt;
import com.creeping_creeper.tinkers_thinking.common.networking.ModMessages;
import com.creeping_creeper.tinkers_thinking.common.recipes.ModRecipes;
import com.creeping_creeper.tinkers_thinking.common.register.*;
import com.creeping_creeper.tinkers_thinking.common.client.ClientEvents;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.utils.Util;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkersThinking.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersThinking
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "tinkers_thinking";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
 
    public TinkersThinking()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRecipes.registers(bus);
        ModPotions.registers(bus);
        ModSounds.SOUND_EVENTS.register(bus);
        bus.register(new ModModifiers());
        bus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ModModifiers.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientEvents::onConstruct);
        bus.register(new ModCommonItems());
        bus.register(new ModToolItems());
        bus.register(new ModFluids());
        bus.register(new ModEffects());
        bus.register(new ModBlockEntities());
        bus.register(new ModEntities());

        ModModule.initRegisters();
        ModDataKeys.init();
    }
    public void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
        MinecraftForge.EVENT_BUS.register(new OnExpPickUp());
        if (ModList.get().isLoaded("touhou_little_maid")){
            MinecraftForge.EVENT_BUS.register(new TouhouLittleMaidPlugin());
        }
        if (ModList.get().isLoaded("modulargolems")){
            MinecraftForge.EVENT_BUS.register(new ModularGolemsPlugin());
        }
        MinecraftForge.EVENT_BUS.register(new OnDeath());
        MinecraftForge.EVENT_BUS.register(new Onhurt());
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ModSlots::init);
        ModPotions.init();
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
    public static String makeDescriptionId(String type, String name) {
        return type + "." + MODID + "." + name;
    }
    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(MODID, name);
    }
    public static <T> TinkerDataCapability.TinkerDataKey<T> createKey(String name) {
        return TinkerDataCapability.TinkerDataKey.of(getResource(name));
    }
}
