package com.creeping_creeper.tinkers_thinking.data.provider.tinkering;

import com.creeping_creeper.tinkers_thinking.data.ModMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.DIAMOND;

public class ModStatsProvider extends AbstractMaterialStatsDataProvider {
    public ModStatsProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialStats() {
        addMeleeHarvest();
        addRanged();
        addAmmo();
        addArmor();
        addSlimesuit();
        addMisc();
    }

    private void addMeleeHarvest() {
        addMaterialStats(ModMaterialIds.electrical_steel,
                new HeadMaterialStats(960, 6.5f, DIAMOND, 2.75f),
                HandleMaterialStats.multipliers().durability(1.05f).attackDamage(1.15f).attackSpeed(1.15f).miningSpeed(1.1f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.bacium,
                new HeadMaterialStats(750, 6.5f, DIAMOND, 3.0f),
                HandleMaterialStats.multipliers().durability(1.1f).attackDamage(1.1f).attackSpeed(1.15f).miningSpeed(0.9f).build(),
                StatlessMaterialStats.BINDING);
    }

    private void addRanged() {
        addMaterialStats(ModMaterialIds.electrical_steel,
                new LimbMaterialStats(960, -0.1f, 0.1f, 0.1f),
                new GripMaterialStats(0.05f, 0.15f, 2.75f));

    }

    private void addAmmo() {
        addMaterialStats(ModMaterialIds.bacium, StatlessMaterialStats.ARROW_SHAFT);
    }

    private void addArmor() {
        addArmorShieldStats(ModMaterialIds.electrical_steel, PlatingMaterialStats.builder().durabilityFactor(30).armor(3, 5, 7, 3).toughness(1.5f).knockbackResistance(0.05f), StatlessMaterialStats.MAILLE);
        addMaterialStats(ModMaterialIds.gilded_silky_cloth, StatlessMaterialStats.MAILLE);
    }

    private void addSlimesuit() {
        addMaterialStats(ModMaterialIds.bacium, RepairStats.shell(150));
        addMaterialStats(ModMaterialIds.gilded_silky_cloth, RepairStats.shell(78));
    }

    private void addMisc() {
        
    }

    @Override
    public String getName() {
        return "TiT Material Stats";
    }
}
