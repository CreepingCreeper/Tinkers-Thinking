package com.creeping_creeper.tinkers_thinking.data.provider.tinkering;

import com.creeping_creeper.tinkers_thinking.data.ModMaterialIds;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.tools.stats.RepairStats;

import static slimeknights.tconstruct.library.materials.MaterialRegistry.AMMO;
import static slimeknights.tconstruct.library.materials.MaterialRegistry.MELEE_HARVEST;

public class ModTraitsProvider extends AbstractMaterialTraitDataProvider {
    public ModTraitsProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialTraits() {
        addDefaultTraits(MaterialIds.paper, ModModifierIds.Soft);

        addDefaultTraits(ModMaterialIds.electrical_steel, ModModifierIds.Repulsive);
        addTraits(ModMaterialIds.bacium, MELEE_HARVEST, ModModifierIds.BaneOfPigs);
        addTraits(ModMaterialIds.bacium, AMMO, ModModifierIds.Resisting);
        addDefaultTraits(MaterialIds.gold, ModModifierIds.Spiky);
        addDefaultTraits(ModMaterialIds.gilded_silky_cloth, ModModifierIds.Silkward);

        MaterialStatsId shell = RepairStats.SHELL.getId();
        addTraits(ModMaterialIds.bacium, shell, ModModifierIds.Antibrute);

        MaterialStatsId laces = RepairStats.LACES.getId();
    }

    @Override
    public String getName() {
        return "TiT Material Traits";
    }
}
