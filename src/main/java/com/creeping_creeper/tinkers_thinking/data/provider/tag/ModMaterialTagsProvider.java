package com.creeping_creeper.tinkers_thinking.data.provider.tag;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.data.ModMaterialIds;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractMaterialTagProvider;

public class ModMaterialTagsProvider extends AbstractMaterialTagProvider {
    public ModMaterialTagsProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, TinkersThinking.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TinkerTags.Materials.COMPATABILITY_ALLOYS).addOptional(ModMaterialIds.copshowium);

        tag(TinkerTags.Materials.GENERAL).add(ModMaterialIds.ardite, ModMaterialIds.stewium, ModMaterialIds.chlorophyte, ModMaterialIds.electrical_steel, ModMaterialIds.obsidian_bronze,
                ModMaterialIds.tempered_glass);
        tag(TinkerTags.Materials.HARVEST).add(ModMaterialIds.tinkers_bronze, ModMaterialIds.white_chocolate, ModMaterialIds.shroomite, ModMaterialIds.echo_bronze, ModMaterialIds.beetron);
        tag(TinkerTags.Materials.MELEE).add(ModMaterialIds.netherrack, ModMaterialIds.dusk, ModMaterialIds.lightite, ModMaterialIds.sponge, ModMaterialIds.black_chocolate,
                ModMaterialIds.spectre, ModMaterialIds.verdant_frogcroaking, ModMaterialIds.ochre_frogcroaking, ModMaterialIds.pearlescent_frogcroaking, ModMaterialIds.burnt_ashes, ModMaterialIds.bacium,
                ModMaterialIds.warden_steel, ModMaterialIds.bound_chain, ModMaterialIds.chillslime_cryogel, ModMaterialIds.metherite, ModMaterialIds.stabilized_gunpowder, ModMaterialIds.scarlet_slimestone,
                ModMaterialIds.ancient_ceramic, ModMaterialIds.solidified_emptiness, ModMaterialIds.colorite, ModMaterialIds.errorite, ModMaterialIds.adamantium)
                .addOptional(ModMaterialIds.copshowium);

        tag(TinkerTags.Materials.BALANCED).add(ModMaterialIds.ardite, ModMaterialIds.stewium, ModMaterialIds.chlorophyte, ModMaterialIds.electrical_steel, ModMaterialIds.obsidian_bronze,
                ModMaterialIds.verdant_frogcroaking, ModMaterialIds.ochre_frogcroaking, ModMaterialIds.pearlescent_frogcroaking, ModMaterialIds.adamantium);
        tag(TinkerTags.Materials.HEAVY).add(ModMaterialIds.tinkers_bronze, ModMaterialIds.spectre, ModMaterialIds.burnt_ashes, ModMaterialIds.solidified_emptiness, ModMaterialIds.colorite)
                .addOptional(ModMaterialIds.copshowium);
        tag(TinkerTags.Materials.LIGHT).add(ModMaterialIds.lightite, ModMaterialIds.shroomite, ModMaterialIds.echo_bronze, ModMaterialIds.beetron);

        tag(TinkerTags.Materials.BARTERED).add(ModMaterialIds.stewium, ModMaterialIds.burnt_ashes, ModMaterialIds.bacium);
        tag(TinkerTags.Materials.NETHER).add(ModMaterialIds.ardite, ModMaterialIds.stewium, ModMaterialIds.burnt_ashes, ModMaterialIds.bacium);

    }

    @Override
    public @NotNull String getName() {
        return "Slime World Material Tag Provider";
    }
}
