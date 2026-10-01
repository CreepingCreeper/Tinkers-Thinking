package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.data.provider.SmelteryRecipe;
import com.creeping_creeper.tinkers_thinking.data.provider.assets.*;
import com.creeping_creeper.tinkers_thinking.data.provider.loot.ModLootTableProvider;
import com.creeping_creeper.tinkers_thinking.data.provider.tag.*;
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
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModProvider {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean server = event.includeServer();
        boolean client = event.includeClient();
        // resource pack
        generator.addProvider(client, new ModBlockStateProvider(output, existingFileHelper));
        generator.addProvider(client, new ModItemModelProvider(output, existingFileHelper));

        generator.addProvider(client, new ModToolItemModelProvider(output, existingFileHelper));
        generator.addProvider(client, new ModModifierModelMapProvider(output));
        TinkerPartSpriteProvider ticPartSprites = new TinkerPartSpriteProvider();
        TinkerMaterialSpriteProvider ticMaterialSprites = new TinkerMaterialSpriteProvider();
        ModPartSpriteProvider modPartSprites = new ModPartSpriteProvider();
        ModMaterialSpriteProvider modMaterialSprites = new ModMaterialSpriteProvider();
        generator.addProvider(client, new ModMaterialRenderInfoProvider(output, modMaterialSprites, existingFileHelper));
        generator.addProvider(client, new GeneratorPartTextureJsonGenerator(output, TinkersThinking.MODID, modPartSprites));
        generator.addProvider(client, new MaterialPartTextureGenerator(output, existingFileHelper, ticPartSprites, modMaterialSprites));
        generator.addProvider(client, new MaterialPartTextureGenerator(output, existingFileHelper, modPartSprites, ticMaterialSprites, modMaterialSprites));
        // data pack
        // tag
        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(server, blockTags);
        generator.addProvider(server, new ModItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        generator.addProvider(server, new ModFluidTagProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(server, new ModEntityTypeTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(server, new ModDamageTypeTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(server, new ModBiomeTagsProvider(output, lookupProvider, existingFileHelper));

        generator.addProvider(server, new ModMaterialTagsProvider(output, existingFileHelper));
        generator.addProvider(server, new ModModifierTagsProvider(output, existingFileHelper));

        // loot
        generator.addProvider(server, new ModLootTableProvider(output));

        // recipe
        generator.addProvider(server, new SmelteryRecipe(output));

        // misc
        ModMaterialProvider materials = new ModMaterialProvider(output);
        generator.addProvider(server, materials);
        generator.addProvider(server, new ModModifierProvider(output));
        generator.addProvider(server, new ModStatsProvider(output, materials));
        generator.addProvider(server, new ModTraitsProvider(output, materials));

    }
}
