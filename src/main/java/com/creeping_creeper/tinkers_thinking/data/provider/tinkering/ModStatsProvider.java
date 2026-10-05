package com.creeping_creeper.tinkers_thinking.data.provider.tinkering;

import com.creeping_creeper.tinkers_thinking.common.compat.FakeCurioMainMaterialStat;
import com.creeping_creeper.tinkers_thinking.common.compat.FakeExtraMaterialStat;
import com.creeping_creeper.tinkers_thinking.data.ModMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.*;

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
        addCurios();
    }

    private void addMeleeHarvest() {
        addMaterialStats(MaterialIds.paper, StatlessMaterialStats.BINDING);
        addMaterialStats(MaterialIds.gold,
                new HeadMaterialStats(32, 12f, GOLD, 0f),
                HandleMaterialStats.multipliers()
                        .durability(0.65f)
                        .miningSpeed(1.20f)
                        .attackSpeed(1.10f)
                        .attackDamage(0.90f)
                        .build(),
                StatlessMaterialStats.BINDING);
        // tier1
        addMaterialStats(ModMaterialIds.sponge,
                new HeadMaterialStats(750, 3.0f, WOOD, 0.25f),
                HandleMaterialStats.multipliers()
                        .durability(0.95f)
                        .miningSpeed(0.95f)
                        .attackSpeed(1.05f)
                        .attackDamage(0.80f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.netherrack,
                new HeadMaterialStats(50, 4.5f, STONE, 1.25f),
                HandleMaterialStats.multipliers()
                        .durability(0.80f)
                        .miningSpeed(1.10f)
                        .attackSpeed(1.10f)
                        .attackDamage(1.05f)
                        .build(),
                StatlessMaterialStats.BINDING);

        // tier2
        addMaterialStats(ModMaterialIds.dusk,
                new HeadMaterialStats(325, 4.5f, IRON, 2.25f),
                HandleMaterialStats.multipliers()
                        .durability(0.85f)
                        .miningSpeed(1.05f)
                        .attackSpeed(1.05f)
                        .attackDamage(1.25f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.stabilized_gunpowder,
                new HeadMaterialStats(400, 4.5f, IRON, 2f),
                HandleMaterialStats.multipliers()
                        .durability(1.10f)
                        .miningSpeed(0.90f)
                        .attackSpeed(1.10f)
                        .attackDamage(0.90f)
                        .build(),
                StatlessMaterialStats.BINDING);

        // tier3
        addMaterialStats(ModMaterialIds.obsidian_bronze,
                new HeadMaterialStats(896, 6.5f, DIAMOND, 2.75f),
                HandleMaterialStats.multipliers()
                        .durability(1.05f)
                        .miningSpeed(1.05f)
                        .attackSpeed(0.95f)
                        .attackDamage(1.10f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.beetron,
                new HeadMaterialStats(550, 6.5f, DIAMOND, 2.5f),
                HandleMaterialStats.multipliers()
                        .durability(1.05f)
                        .miningSpeed(1.10f)
                        .attackSpeed(0.85f)
                        .attackDamage(1.10f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.shroomite,
                new HeadMaterialStats(1050, 6.0f, DIAMOND, 2.75f),
                HandleMaterialStats.multipliers()
                        .durability(1.15f)
                        .miningSpeed(0.90f)
                        .attackSpeed(0.90f)
                        .attackDamage(1.05f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.tinkers_bronze,
                new HeadMaterialStats(540, 6.5f, DIAMOND, 2.25f),
                HandleMaterialStats.multipliers()
                        .durability(0.85f)
                        .miningSpeed(1.10f)
                        .attackSpeed(0.95f)
                        .attackDamage(1.10f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.white_chocolate,
                new HeadMaterialStats(350, 4.25f, GOLD, 0.75f),
                HandleMaterialStats.multipliers()
                        .durability(0.80f)
                        .miningSpeed(1.20f)
                        .attackSpeed(1.15f)
                        .attackDamage(1.00f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.black_chocolate,
                new HeadMaterialStats(300, 4.5f, GOLD, 0.75f),
                HandleMaterialStats.multipliers()
                        .durability(0.90f)
                        .miningSpeed(1.05f)
                        .attackSpeed(1.05f)
                        .attackDamage(1.15f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.chillslime_cryogel,
                new HeadMaterialStats(700, 4.5f, IRON, 3f),
                HandleMaterialStats.multipliers()
                        .durability(0.95f)
                        .miningSpeed(0.85f)
                        .attackSpeed(0.90f)
                        .attackDamage(1.15f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.electrical_steel,
                new HeadMaterialStats(960, 6.5f, DIAMOND, 2.75f),
                HandleMaterialStats.multipliers().durability(1.05f).attackDamage(1.15f).attackSpeed(1.15f).miningSpeed(1.1f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.lightite,
                new HeadMaterialStats(780, 5.0f, IRON, 2.25f),
                HandleMaterialStats.multipliers()
                        .durability(1.05f)
                        .miningSpeed(1.25f)
                        .attackSpeed(1.15f)
                        .attackDamage(0.80f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.metherite,
                new HeadMaterialStats(1200, 4.0f, DIAMOND, 3f),
                HandleMaterialStats.multipliers()
                        .durability(1.05f)
                        .miningSpeed(0.85f)
                        .attackSpeed(1.15f)
                        .attackDamage(0.85f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.spectre,
                new HeadMaterialStats(940, 5.0f, DIAMOND, 2.5f),
                HandleMaterialStats.multipliers()
                        .durability(0.85f)
                        .miningSpeed(1.15f)
                        .attackSpeed(1.15f)
                        .attackDamage(0.90f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.chlorophyte,
                new HeadMaterialStats(980, 5.0f, DIAMOND, 2.0f),
                HandleMaterialStats.multipliers()
                        .durability(0.90f)
                        .miningSpeed(1.05f)
                        .attackSpeed(1.15f)
                        .attackDamage(0.95f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.ardite,
                new HeadMaterialStats(1160, 5.5f, DIAMOND, 2.25f),
                HandleMaterialStats.multipliers()
                        .durability(1.25f)
                        .miningSpeed(1.10f)
                        .attackSpeed(1.05f)
                        .attackDamage(1.05f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.bacium,
                new HeadMaterialStats(750, 6.5f, DIAMOND, 3.0f),
                HandleMaterialStats.multipliers().durability(1.1f).attackDamage(1.1f).attackSpeed(1.15f).miningSpeed(0.9f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.tempered_glass,
                new HeadMaterialStats(1800, 4.0f, DIAMOND, 2.5f),
                HandleMaterialStats.multipliers()
                        .durability(1.25f)
                        .miningSpeed(0.90f)
                        .attackSpeed(0.90f)
                        .attackDamage(1.10f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.verdant_frogcroaking,
                new HeadMaterialStats(425,5.0f, GOLD,2.0f),
                HandleMaterialStats.multipliers()
                        .durability(0.95f)
                        .miningSpeed(1.15f)
                        .attackSpeed(1.15f)
                        .attackDamage(0.90f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.pearlescent_frogcroaking, StatlessMaterialStats.BINDING);

        // tier4
        addMaterialStats(ModMaterialIds.copshowium,
                new HeadMaterialStats(764,6.0f, NETHERITE,3f),
                HandleMaterialStats.multipliers()
                        .durability(0.80f)
                        .miningSpeed(1.10f)
                        .attackSpeed(1.25f)
                        .attackDamage(0.95f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.echo_bronze,
                new HeadMaterialStats(425, 6.5f, DIAMOND, 2.75f),
                HandleMaterialStats.multipliers()
                        .durability(0.90f)
                        .miningSpeed(1.1f)
                        .attackSpeed(1.1f)
                        .attackDamage(1.1f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.scarlet_slimestone,
                new HeadMaterialStats(500,5.5f, NETHERITE,3.0f),
                HandleMaterialStats.multipliers()
                        .durability(0.85f)
                        .miningSpeed(0.85f)
                        .attackSpeed(1.15f)
                        .attackDamage(1.15f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.burnt_ashes,
                new HeadMaterialStats(750,5.5f, NETHERITE,2.75f),
                HandleMaterialStats.multipliers()
                        .durability(0.85f)
                        .miningSpeed(0.85f)
                        .attackSpeed(0.95f)
                        .attackDamage(1.05f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.solidified_emptiness,
                new HeadMaterialStats(1200,4.0f, NETHERITE,2.5f),
                HandleMaterialStats.multipliers()
                        .durability(0.80f)
                        .miningSpeed(0.80f)
                        .attackSpeed(1.20f)
                        .attackDamage(1.10f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.stewium,
                new HeadMaterialStats(1540,5.5f, NETHERITE,2.5f),
                HandleMaterialStats.multipliers()
                        .durability(1.15f)
                        .miningSpeed(1.10f)
                        .attackSpeed(0.90f)
                        .attackDamage(0.95f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.warden_steel,
                new HeadMaterialStats(480,6.5f, NETHERITE,2.5f),
                HandleMaterialStats.multipliers()
                        .durability(1.10f)
                        .miningSpeed(1.10f)
                        .attackSpeed(1.15f)
                        .attackDamage(0.95f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.ancient_ceramic,
                new HeadMaterialStats(830,8.0f, NETHERITE,3.5f),
                HandleMaterialStats.multipliers()
                        .durability(0.75f)
                        .miningSpeed(1.20f)
                        .attackSpeed(1.15f)
                        .attackDamage(1.15f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.zith,
                new HeadMaterialStats(1000,4.5f,DIAMOND,3f),
                HandleMaterialStats.multipliers()
                        .durability(0.85f)
                        .miningSpeed(0.90f)
                        .attackSpeed(1.10f)
                        .attackDamage(1.10f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.bound_chain, StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.shimmerslime,
                new HeadMaterialStats(1650,4.5f, NETHERITE,2.75f),
                HandleMaterialStats.multipliers()
                        .durability(1.25f)
                        .miningSpeed(1.05f)
                        .attackSpeed(1.10f)
                        .attackDamage(1.00f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.colorite,
                new HeadMaterialStats(1350,4.5f, NETHERITE,3.5f),
                HandleMaterialStats.multipliers()
                        .durability(0.85f)
                        .miningSpeed(0.85f)
                        .attackSpeed(1.20f)
                        .attackDamage(1.10f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.errorite,
                new HeadMaterialStats(1150,3.5f, NETHERITE,4f),
                HandleMaterialStats.multipliers()
                        .durability(0.85f)
                        .miningSpeed(0.85f)
                        .attackSpeed(0.85f)
                        .attackDamage(1.20f)
                        .build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(ModMaterialIds.adamantium,
                new HeadMaterialStats(1200,4f, DIAMOND,3f),
                HandleMaterialStats.multipliers()
                        .durability(1.15f)
                        .miningSpeed(0.90f)
                        .attackSpeed(1.15f)
                        .attackDamage(0.90f)
                        .build(),
                StatlessMaterialStats.BINDING);
    }

    private void addRanged() {
        addMaterialStats(MaterialIds.paper, StatlessMaterialStats.BOWSTRING);
        addMaterialStats(MaterialIds.gold,
                new LimbMaterialStats(32, 0.15f, 0.1f, -0.2f),
                new GripMaterialStats(-0.35f, 0.05f, 0f));
        // tier1
        // tier2
        addMaterialStats(ModMaterialIds.stabilized_gunpowder,
                new LimbMaterialStats(400, -0.1f,0.05f,0.2f),
                new GripMaterialStats(0.1f,0.1f,2f));

        // tier3
        addMaterialStats(ModMaterialIds.obsidian_bronze,
                new LimbMaterialStats(896,-0.15f,0.15f,0f),
                new GripMaterialStats(0.05f,-0.05f,2.75f));
        addMaterialStats(ModMaterialIds.beetron,
                new LimbMaterialStats(550,0.15f,-0.05f,0.05f),
                new GripMaterialStats(0.05f,0.05f,2.5f));
        addMaterialStats(ModMaterialIds.shroomite,
                new LimbMaterialStats(1050,-0.10f,0.05f,0.10f),
                new GripMaterialStats(0.15f,0.05f,2.75f));
        addMaterialStats(ModMaterialIds.tinkers_bronze,
                new LimbMaterialStats(540,0.15f,-0.05f,0.10f),
                new GripMaterialStats(-0.15f,0.05f,2.25f));
        addMaterialStats(ModMaterialIds.electrical_steel,
                new LimbMaterialStats(960, -0.1f, 0.1f, 0.1f),
                new GripMaterialStats(0.05f, 0.15f, 2.75f));
        addMaterialStats(ModMaterialIds.lightite,
                new LimbMaterialStats(780,0.15f,-0.10f,0.05f),
                new GripMaterialStats(0.15f,0.10f,2.25f));
        addMaterialStats(ModMaterialIds.metherite,
                new LimbMaterialStats(1200,0.05f,0.15f,-0.1f),
                new GripMaterialStats(0.05f,0.05f,3f));
        addMaterialStats(ModMaterialIds.spectre,
                new LimbMaterialStats(940,0.10f,-0.10f,-0.05f),
                new GripMaterialStats(-0.15f,0.10f,2.5f));
        addMaterialStats(ModMaterialIds.chlorophyte,
                new LimbMaterialStats(980,0.15f,-0.10f,0.05f),
                new GripMaterialStats(-0.1f,0.05f,2.0f));
        addMaterialStats(ModMaterialIds.ardite,
                new LimbMaterialStats(1160, -0.1f, 0.1f, 0.05f),
                new GripMaterialStats(0.25f, 0.15f, 2.25f));
        addMaterialStats(ModMaterialIds.tempered_glass,
                new LimbMaterialStats(1800,-0.05f,0.1f,0.05f),
                new GripMaterialStats(0.25f,0.05f,2.5f));
        addMaterialStats(ModMaterialIds.ochre_frogcroaking,
                new LimbMaterialStats(485,0.05f,0.05f,-0.05f),
                new GripMaterialStats(-0.05f,0.05f,2.0f));
        addMaterialStats(ModMaterialIds.pearlescent_frogcroaking, StatlessMaterialStats.BOWSTRING);


        // tier4
        addMaterialStats(ModMaterialIds.copshowium,
                new LimbMaterialStats(764,0.10f,-0.05f,0.20f),
                new GripMaterialStats(-0.2f,0.15f,3f));
        addMaterialStats(ModMaterialIds.echo_bronze,
                new LimbMaterialStats(425,0.05f,0.15f,0.05f),
                new GripMaterialStats(-0.1f,0.05f,2.75f));
        addMaterialStats(ModMaterialIds.burnt_ashes,
                new LimbMaterialStats(750,0.10f,0.05f,-0.10f),
                new GripMaterialStats(-0.15f,0.05f,2.75f));
        addMaterialStats(ModMaterialIds.solidified_emptiness,
                new LimbMaterialStats(1200,0.25f,-0.15f,0.10f),
                new GripMaterialStats(-0.2f,0.10f,2.5f));
        addMaterialStats(ModMaterialIds.stewium,
                new LimbMaterialStats(1540,-0.15f,0.05f,-0.05f),
                new GripMaterialStats(0.15f,0.10f,2.5f));
        addMaterialStats(ModMaterialIds.zith,
                new LimbMaterialStats(1000,-0.05f,0.2f,0.05f),
                new GripMaterialStats(-0.15f,0.1f,3f));
        addMaterialStats(ModMaterialIds.shimmerslime,
                new LimbMaterialStats(1650,0.05f,0f,0.1f),
                new GripMaterialStats(0.25f,0.1f,2.5f));
        addMaterialStats(ModMaterialIds.colorite,
                new LimbMaterialStats(1350,-0.1f,0.15f,-0.1f),
                new GripMaterialStats(-0.15f,-0.1f,3.5f));
        addMaterialStats(ModMaterialIds.adamantium,
                new LimbMaterialStats(1200,0.05f,0.15f,-0.1f),
                new GripMaterialStats(0.15f,0.05f,3f));
    }

    private void addAmmo() {
        addMaterialStats(ModMaterialIds.chillslime_cryogel, StatlessMaterialStats.ARROW_HEAD);
        addMaterialStats(ModMaterialIds.dusk, StatlessMaterialStats.ARROW_HEAD);

        addMaterialStats(ModMaterialIds.sugar_cane, StatlessMaterialStats.ARROW_SHAFT);
        addMaterialStats(ModMaterialIds.bacium, StatlessMaterialStats.ARROW_SHAFT);

        addMaterialStats(ModMaterialIds.shroomite_compound, StatlessMaterialStats.FLETCHING);
        addMaterialStats(ModMaterialIds.spectre_compound, StatlessMaterialStats.FLETCHING);
        addMaterialStats(ModMaterialIds.chlorophyte_compound, StatlessMaterialStats.FLETCHING);
        addMaterialStats(ModMaterialIds.lightite_compound, StatlessMaterialStats.FLETCHING);
    }

    private void addArmor() {
        addMaterialStats(MaterialIds.paper, StatlessMaterialStats.MAILLE, StatlessMaterialStats.CUIRASS, StatlessMaterialStats.SHIELD_CORE);

        // tier1
        addMaterialStats(ModMaterialIds.dusk, StatlessMaterialStats.SHIELD_CORE);
        // tier2

        // tier3
        addArmorShieldStats(ModMaterialIds.obsidian_bronze,
                PlatingMaterialStats.builder()
                        .durabilityFactor(26f)
                        .armor(2,5,7,2)
                        .toughness(1.5f)
                        .knockbackResistance(0.1f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.shroomite,
                PlatingMaterialStats.builder()
                        .durabilityFactor(33f)
                        .armor(2.5f,4.5f,6.5f,2.5f)
                        .toughness(0.5f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.tinkers_bronze,
                PlatingMaterialStats.builder()
                        .durabilityFactor(17f)
                        .armor(2.0f,4.0f,6.0f,2.0f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.chillslime_cryogel,
                PlatingMaterialStats.builder()
                        .durabilityFactor(22f)
                        .armor(1,4,6,1)
                        .toughness(1f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.electrical_steel,
                PlatingMaterialStats.builder().
                        durabilityFactor(30).
                        armor(3, 5, 7, 3).
                        toughness(1.5f).
                        knockbackResistance(0.05f),
                StatlessMaterialStats.MAILLE);


        addArmorShieldStats(ModMaterialIds.lightite,
                PlatingMaterialStats.builder()
                        .durabilityFactor(24f)
                        .armor(1.5f,3.5f,5.5f,1.5f)
                        .toughness(1f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.metherite,
                PlatingMaterialStats.builder()
                        .durabilityFactor(37f)
                        .armor(3,6,8,3)
                        .toughness(1f)
                        .knockbackResistance(0.1f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.spectre,
                PlatingMaterialStats.builder()
                        .durabilityFactor(30f)
                        .armor(1.5f,3.5f,5.5f,1.5f)
                        .toughness(1.5f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.chlorophyte,
                PlatingMaterialStats.builder()
                        .durabilityFactor(31f)
                        .armor(1.5f,3.5f,5.5f,1.5f),
                StatlessMaterialStats.MAILLE);

        addMaterialStats(ModMaterialIds.gilded_silky_cloth, StatlessMaterialStats.MAILLE, StatlessMaterialStats.CUIRASS);

        addArmorShieldStats(ModMaterialIds.ardite,
                PlatingMaterialStats.builder()
                        .durabilityFactor(36f)
                        .armor(2, 5, 7, 2)
                        
                        .knockbackResistance(0.15f),
                StatlessMaterialStats.MAILLE);
        // tier4
        addArmorShieldStats(ModMaterialIds.copshowium,
                PlatingMaterialStats.builder()
                        .durabilityFactor(24f)
                        .armor(2.5f,5.5f,7.5f,2.5f)
                        .toughness(1.5f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.echo_bronze,
                PlatingMaterialStats.builder()
                        .durabilityFactor(26f)
                        .armor(2f,5f,6f,2f)
                        .toughness(1.5f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.burnt_ashes,
                PlatingMaterialStats.builder()
                        .durabilityFactor(23f)
                        .armor(2.5f,4.5f,6.5f,2.5f)
                        
                        .knockbackResistance(0.10f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.stewium,
                PlatingMaterialStats.builder()
                        .durabilityFactor(48f)
                        .armor(3,6,8,3)
                        .toughness(1.5f)
                        .knockbackResistance(0.05f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.warden_steel,
                PlatingMaterialStats.builder()
                        .durabilityFactor(30f)
                        .armor(2,5,7,2)
                        .toughness(1.5f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.ancient_ceramic,
                PlatingMaterialStats.builder()
                        .durabilityFactor(26f)
                        .armor(2,5,7,2)
                        .toughness(3.5f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.zith,
                PlatingMaterialStats.builder()
                        .durabilityFactor(31f)
                        .armor(2,5,7,2)
                        
                        .knockbackResistance(0.15f),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.shimmerslime,
                PlatingMaterialStats.builder()
                        .durabilityFactor(60f)
                        .armor(2,5,7,2)
                        .toughness(2f),
                StatlessMaterialStats.MAILLE);

        addMaterialStats(ModMaterialIds.soul_vine, StatlessMaterialStats.MAILLE, StatlessMaterialStats.SHIELD_CORE);

        addArmorShieldStats(ModMaterialIds.errorite,
                PlatingMaterialStats.builder()
                        .durabilityFactor(36f)
                        .armor(4,7,9,4),
                StatlessMaterialStats.MAILLE);

        addArmorShieldStats(ModMaterialIds.adamantium,
                PlatingMaterialStats.builder()
                        .durabilityFactor(37f)
                        .armor(3,6,8,3)
                        .toughness(1f)
                        .knockbackResistance(0.1f),
                StatlessMaterialStats.MAILLE);
    }


    private void addSlimesuit() {
        addMaterialStats(ModMaterialIds.bacium, RepairStats.shell(150));
        addMaterialStats(MaterialIds.paper, RepairStats.laces(60));
        addMaterialStats(ModMaterialIds.gilded_silky_cloth, RepairStats.laces(78));
    }

    private void addMisc() {
        
    }

    private void addCurios() {
        addOptionalStats(ModMaterialIds.echo_bronze, FakeExtraMaterialStat.CURIO_EXTRA, new FakeCurioMainMaterialStat(0.15f, 5.0f, 0.1f, 0.1f, 0.1f));
        addOptionalStats(ModMaterialIds.lightite, FakeExtraMaterialStat.CURIO_EXTRA, new FakeCurioMainMaterialStat(0.05f, 12.0f, 0.2f, 0.05f, 0.05f));
        addOptionalStats(ModMaterialIds.tinkers_bronze, FakeExtraMaterialStat.CURIO_EXTRA, new FakeCurioMainMaterialStat(0.15f, 15.0f, 0.05f, 0.0f, 0.0f));
        addOptionalStats(ModMaterialIds.warden_steel, FakeExtraMaterialStat.CURIO_EXTRA, new FakeCurioMainMaterialStat(0.1f, 8.0f, 0.15f, 0.08f, 0.08f));
    }

    @Override
    public String getName() {
        return "TiT Material Stats";
    }
}
