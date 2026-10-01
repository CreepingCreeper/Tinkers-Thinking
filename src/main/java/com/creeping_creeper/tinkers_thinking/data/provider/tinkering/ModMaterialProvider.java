package com.creeping_creeper.tinkers_thinking.data.provider.tinkering;

import com.creeping_creeper.tinkers_thinking.data.ModMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class ModMaterialProvider extends AbstractMaterialDataProvider {
    public ModMaterialProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addMaterials() {
        // tier 1
        material(ModMaterialIds.sugar_cane).tier(1).sort(1).craftable();
        material(ModMaterialIds.sponge).tier(1).sort(2).craftable();
        material(ModMaterialIds.netherrack).tier(1).sort(12).craftable();

        // tier 2
        material(ModMaterialIds.dusk).tier(2).sort(2).craftable();
        material(ModMaterialIds.stabilized_gunpowder).tier(2).sort(2).craftable();

        // tier 3
        material(ModMaterialIds.obsidian_bronze).tier(3).sort(0);
        material(ModMaterialIds.beetron).tier(3).sort(1);
        material(ModMaterialIds.echo_bronze).tier(3).sort(1);
        material(ModMaterialIds.shroomite).tier(3).sort(1);
        material(ModMaterialIds.shroomite_compound).tier(3).sort(1);
        material(ModMaterialIds.tinkers_bronze).tier(3).sort(1);
        material(ModMaterialIds.white_chocolate).tier(3).sort(1);
        material(ModMaterialIds.black_chocolate).tier(3).sort(2);
        material(ModMaterialIds.chillslime_cryogel).tier(3).sort(2).craftable();
        material(ModMaterialIds.electrical_steel).tier(3).sort(ORDER_WEAPON);
        material(ModMaterialIds.lightite).tier(3).sort(2);
        material(ModMaterialIds.lightite_compound).tier(3).sort(2).craftable();
        material(ModMaterialIds.metherite).tier(3).sort(2);
        material(ModMaterialIds.spectre).tier(3).sort(2);
        material(ModMaterialIds.spectre_compound).tier(3).sort(2).craftable();
        material(ModMaterialIds.chlorophyte).tier(3).sort(3);
        material(ModMaterialIds.chlorophyte_compound).tier(3).sort(3).craftable();
        material(ModMaterialIds.gilded_silky_cloth).tier(3).sort(ORDER_SPECIAL).craftable();
        material(ModMaterialIds.ardite).tier(3).sort(10);
        material(ModMaterialIds.bacium).tier(3).sort(ORDER_NETHER).craftable();
        material(ModMaterialIds.tempered_glass).tier(3).sort(10);
        material(ModMaterialIds.ochre_frogcroaking).tier(3).sort(11).craftable();
        material(ModMaterialIds.verdant_frogcroaking).tier(3).sort(13).craftable();
        material(ModMaterialIds.pearlescent_frogcroaking).tier(3).sort(20).craftable();

        // tier 4
        material(ModMaterialIds.ancient_ceramic).tier(4).sort(2);
        material(ModMaterialIds.scarlet_slimestone).tier(4).sort(2).craftable();
        // copshowium 带条件判断，不适用简单builder
        material(ModMaterialIds.stewium).tier(4).sort(10);
        material(ModMaterialIds.burnt_ashes).tier(4).sort(12);
        material(ModMaterialIds.solidified_emptiness).tier(4).sort(12);
        material(ModMaterialIds.warden_steel).tier(4).sort(13);
        material(ModMaterialIds.zith).tier(4).sort(19);
        material(ModMaterialIds.bound_chain).tier(4).sort(20).craftable();
        material(ModMaterialIds.shimmerslime).tier(4).sort(20);
        material(ModMaterialIds.soul_vine).tier(4).sort(20).craftable();
        material(ModMaterialIds.colorite).tier(4).sort(22);
        material(ModMaterialIds.errorite).tier(4).sort(22);
        material(ModMaterialIds.adamantium).tier(4).sort(23);


    }

    @Override
    public String getName() {
        return "TiT Materials";
    }

}