package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.data.provider.ModFluidTagProvider;
import com.creeping_creeper.tinkers_thinking.data.provider.SmelteryRecipe;
import com.creeping_creeper.tinkers_thinking.data.provider.tinkering.ModMaterialProvider;
import com.creeping_creeper.tinkers_thinking.data.provider.tinkering.ModModifierProvider;
import com.creeping_creeper.tinkers_thinking.data.provider.tinkering.ModStatsProvider;
import com.creeping_creeper.tinkers_thinking.data.provider.tinkering.ModTraitsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModProvider {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean server = event.includeServer();
        boolean client = event.includeClient();

        generator.addProvider(server, new ModFluidTagProvider(output, lookupProvider, existingFileHelper));

        generator.addProvider(server, new SmelteryRecipe(output));

        ModMaterialProvider materials = new ModMaterialProvider(output);
        generator.addProvider(server, materials);
        generator.addProvider(server, new ModModifierProvider(output));
        generator.addProvider(server, new ModStatsProvider(output, materials));
        generator.addProvider(server, new ModTraitsProvider(output, materials));

    }
}
