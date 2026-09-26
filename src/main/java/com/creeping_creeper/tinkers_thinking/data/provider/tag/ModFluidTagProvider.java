package com.creeping_creeper.tinkers_thinking.data.provider.tag;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.datagen.MantleTags;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.common.TinkerTags;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unchecked")
public class ModFluidTagProvider extends FluidTagsProvider {
    public ModFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TinkersThinking.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        // tconstruct
        tag(TinkerTags.Fluids.METAL_TOOLTIPS).addTags(ModFluids.moltenTinkersBronze.getTag(), ModFluids.moltenAdamantium.getTag(), ModFluids.moltenLightite.getTag(), ModFluids.moltenBlackChocolate.getTag(),
                ModFluids.moltenWhiteChocolate.getTag(), ModFluids.moltenChlorophyte.getTag(), ModFluids.moltenSpectre.getTag(), ModFluids.moltenShroomite.getTag(), ModFluids.moltenObsidianBronze.getTag(), ModFluids.moltenElectricalSteel.getTag(),
                ModFluids.moltenEchoBronze.getTag(), ModFluids.moltenBeetron.getTag(), ModFluids.moltenWardenSteel.getTag(), ModFluids.moltenZith.getTag(), ModFluids.moltenShimmerslime.getTag(), ModFluids.moltenAncientCeramic.getTag());
        tag(TinkerTags.Fluids.GLASS_TOOLTIPS).addTag(ModFluids.moltenTemperedGlass.getTag());
        tag(TinkerTags.Fluids.SLIME_TOOLTIPS).addTags(ModFluids.pulp.getTag(), ModFluids.chillslime.getTag(), ModFluids.scarletslime.getTag(), ModFluids.colorLiquid.getTag(), ModFluids.errorLiquid.getTag());
        tag(TinkerTags.Fluids.SMALL_GEM_TOOLTIPS).addTags(ModFluids.syrup.getTag(), ModFluids.moltenEcho.getTag(), ModFluids.liquidSculkPower.getTag(), ModFluids.emptiness.getTag());
        tag(MantleTags.Fluids.SOUP).addTags(ModFluids.moltenCocoa.getTag());

        // self
        fluidTag(ModFluids.moltenArdite);
        fluidTag(ModFluids.moltenTinkersBronze);
        fluidTag(ModFluids.moltenLightite);
        fluidTag(ModFluids.moltenChlorophyte);
        fluidTag(ModFluids.moltenSpectre);
        fluidTag(ModFluids.moltenShroomite);
        fluidTag(ModFluids.moltenObsidianBronze);
        fluidTag(ModFluids.moltenElectricalSteel);
        fluidTag(ModFluids.moltenBeetron);
        fluidTag(ModFluids.moltenEchoBronze);
        fluidTag(ModFluids.moltenWardenSteel);
        fluidTag(ModFluids.moltenZith);
        fluidTag(ModFluids.moltenShimmerslime);
        fluidTag(ModFluids.moltenAdamantium);
        fluidTag(ModFluids.moltenTemperedGlass);
        fluidTag(ModFluids.scarletslime);
        fluidTag(ModFluids.moltenAncientCeramic);
        fluidTag(ModFluids.reburnAshes);
        fluidTag(ModFluids.moltenEcho);
        fluidTag(ModFluids.liquidSculkPower);
        fluidTag(ModFluids.chillslime);
        fluidTag(ModFluids.colorLiquid);
        fluidTag(ModFluids.errorLiquid);
        fluidTag(ModFluids.syrup);
        fluidTag(ModFluids.pulp);
        fluidTag(ModFluids.emptiness);
        fluidTag(ModFluids.moltenCocoa);
        fluidTag(ModFluids.moltenBlackChocolate);
        fluidTag(ModFluids.moltenWhiteChocolate);
    }

    private void fluidTag(FlowingFluidObject<?> fluid) {
        tag(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
        TagKey<Fluid> tag = fluid.getCommonTag();
        if (tag != null) {
            tag(tag).addTag(fluid.getLocalTag());
        }
    }
}
