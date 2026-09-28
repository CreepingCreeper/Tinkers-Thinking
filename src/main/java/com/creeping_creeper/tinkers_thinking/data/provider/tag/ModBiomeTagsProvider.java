package com.creeping_creeper.tinkers_thinking.data.provider.tag;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.data.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    private final ResourceLocation ICHOR_CAVES = ResourceLocation.fromNamespaceAndPath("slimeworld", "ichor_caves");

    public ModBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TinkersThinking.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider lookupProvider) {
        //self
        tag(ModTags.Biomes.CHLOROPHYLL_ORE_SPAWN).add(Biomes.LUSH_CAVES, Biomes.MANGROVE_SWAMP).addTag(BiomeTags.IS_JUNGLE).addOptional(ICHOR_CAVES);
    }
}
