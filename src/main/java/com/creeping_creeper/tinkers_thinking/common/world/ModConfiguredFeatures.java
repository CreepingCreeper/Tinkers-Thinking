package com.creeping_creeper.tinkers_thinking.common.world;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.ModItems;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY,TinkersThinking.MODID);
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_ARDITE_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModItems.ardite_ore.get().defaultBlockState())
    ));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CHLOROPHYLL_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModItems.chlorophyll_ore.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModItems.deepslate_chlorophyll_ore.get().defaultBlockState())
    ));
    public static final RegistryObject<ConfiguredFeature<?,?>>NETHER_ARDITE_ORE = CONFIGURED_FEATURES.register("nether_ardite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(NETHER_ARDITE_ORES.get(),4)));
    public static final RegistryObject<ConfiguredFeature<?,?>>OVERWORLD_CHLOROPHYLL_ORE = CONFIGURED_FEATURES.register("chlorophyll_ore",
            () -> new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(OVERWORLD_CHLOROPHYLL_ORES.get(),6)));
    public static void registers(IEventBus modEventBus){
        CONFIGURED_FEATURES.register(modEventBus);
    }
}
