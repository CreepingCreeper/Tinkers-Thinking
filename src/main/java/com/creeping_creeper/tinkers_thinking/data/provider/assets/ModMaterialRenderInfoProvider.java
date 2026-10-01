package com.creeping_creeper.tinkers_thinking.data.provider.assets;

import com.creeping_creeper.tinkers_thinking.data.ModMaterialIds;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class ModMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {
    public ModMaterialRenderInfoProvider(PackOutput packOutput, @Nullable AbstractMaterialSpriteProvider materialSprites, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, materialSprites, existingFileHelper);
    }

    @Override
    protected void addMaterialRenderInfo() {
        // tier1
        buildRenderInfo(ModMaterialIds.sugar_cane).color(0x7CCC35).luminosity(5).fallbacks("cloth");
        buildRenderInfo(ModMaterialIds.sponge).color(0xC1BC4E).luminosity(3).fallbacks("wood");
        buildRenderInfo(ModMaterialIds.netherrack).color(0x562121).luminosity(8).fallbacks("rock");

        // tier2
        buildRenderInfo(ModMaterialIds.dusk).color(0x4118CF).luminosity(3).fallbacks("slime_wood");
        buildRenderInfo(ModMaterialIds.stabilized_gunpowder).color(0xFF727272).luminosity(0).fallbacks("crystal");

        // tier3
        buildRenderInfo(ModMaterialIds.obsidian_bronze).color(0xA28B48).luminosity(6).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.beetron).color(0xB6484C).luminosity(6).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.echo_bronze).color(0x29DFEB).luminosity(6).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.shroomite).color(0x008CF4).luminosity(4).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.shroomite_compound).color(0x008CF4).luminosity(4).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.tinkers_bronze).color(0xF9CF72).luminosity(6).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.white_chocolate).color(0xDBA76D).luminosity(5).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.black_chocolate).color(0x4F2B19).luminosity(2).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.chillslime_cryogel).color(0xFF7CF6FC).luminosity(0).fallbacks("slime_wood","crystal");
        buildRenderInfo(ModMaterialIds.lightite).color(0xDDD8D8).luminosity(7).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.lightite_compound).color(0xDDD8D8).luminosity(7).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.metherite).color(0x5A575A).luminosity(0).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.spectre).color(0x58DBFF).luminosity(5).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.spectre_compound).color(0x58DBFF).luminosity(5).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.chlorophyte).color(0x75D813).luminosity(5).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.chlorophyte_compound).color(0x75D813).luminosity(5).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.gilded_silky_cloth).color(0xFFFDF55F).luminosity(0).fallbacks("cloth");
        buildRenderInfo(ModMaterialIds.ardite).color(0xCD4418).luminosity(5).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.bacium).color(0xE17022).luminosity(6).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.tempered_glass).color(0xFFD2D4D4).luminosity(0).fallbacks("crystal");
        buildRenderInfo(ModMaterialIds.ochre_frogcroaking).color(0xF7DF92).luminosity(4).fallbacks("wood");
        buildRenderInfo(ModMaterialIds.verdant_frogcroaking).color(0xBDE1B7).luminosity(4).fallbacks("wood");
        buildRenderInfo(ModMaterialIds.pearlescent_frogcroaking).color(0xD4BFC8).luminosity(0).fallbacks("wood");

        // tier4
        buildRenderInfo(ModMaterialIds.ancient_ceramic).color(0x3C8EB0).luminosity(5).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.scarlet_slimestone).color(0xA52548).luminosity(5).fallbacks("slime_wood","crystal");
        buildRenderInfo(ModMaterialIds.copshowium).color(0xFF9727).luminosity(5).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.stewium).color(0xD28155).luminosity(5).fallbacks("slime_metal","metal");
        buildRenderInfo(ModMaterialIds.burnt_ashes).color(0x8A2F13).luminosity(5).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.solidified_emptiness).color(0x00E7B5).luminosity(5).fallbacks("crystal");
        buildRenderInfo(ModMaterialIds.warden_steel).color(0xBBC39B).luminosity(4).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.zith).color(0xF793B1).luminosity(6).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.bound_chain).color(0xBF9C81).luminosity(0).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.shimmerslime).color(0xFFF264).luminosity(7).fallbacks("slime_metal","metal");
        buildRenderInfo(ModMaterialIds.soul_vine).color(0x20B519).luminosity(0).fallbacks("wood");
        buildRenderInfo(ModMaterialIds.colorite).color(0xFAAB1C).luminosity(6).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.errorite).color(0x6C006C).luminosity(3).fallbacks("metal");
        buildRenderInfo(ModMaterialIds.adamantium).color(0xB80000).luminosity(5).fallbacks("metal");
    }

    @Override
    public @NotNull String getName() {
        return "Slime World Material Render Info Provider";
    }
}
