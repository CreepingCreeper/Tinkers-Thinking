package com.creeping_creeper.tinkers_thinking.data.provider.tinkering;

import com.creeping_creeper.tinkers_thinking.data.ModMaterialIds;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.modifiers.util.OptionalModifier;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.tools.stats.RepairStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import static slimeknights.tconstruct.library.materials.MaterialRegistry.*;

public class ModTraitsProvider extends AbstractMaterialTraitDataProvider {
    public ModTraitsProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialTraits() {
        MaterialStatsId shell = RepairStats.SHELL.getId();
        MaterialStatsId laces = RepairStats.LACES.getId();
        MaterialStatsId shield_core = StatlessMaterialStats.SHIELD_CORE.getIdentifier();
        // tier 1
        addDefaultTraits(ModMaterialIds.sugar_cane, ModModifierIds.Nonsense);
        addTraits(ModMaterialIds.sponge, MELEE_HARVEST, ModModifierIds.Disarm);
        addDefaultTraits(ModMaterialIds.netherrack, ModModifierIds.Hellish);

        // tier 2
        addTraits(ModMaterialIds.dusk, AMMO, ModModifierIds.RidingShoot);
        addTraits(ModMaterialIds.dusk, MELEE_HARVEST, ModModifierIds.Overdose, TinkerModifiers.overslime.getId());
        addTraits(ModMaterialIds.dusk, shield_core, ModModifierIds.Spiky);

        addTraits(ModMaterialIds.stabilized_gunpowder, MELEE_HARVEST, ModModifierIds.Cataclysm);

        // tier3
        addDefaultTraits(ModMaterialIds.obsidian_bronze, ModModifierIds.Duritae);

        addDefaultTraits(ModMaterialIds.beetron, ModModifierIds.Hungriness);

        addDefaultTraits(ModMaterialIds.echo_bronze, ModModifierIds.SculkCatalyse, ModModifierIds.SculkBoost);

        addTraits(ModMaterialIds.shroomite, RANGED, ModModifierIds.Shady);
        addTraits(ModMaterialIds.shroomite, MELEE_HARVEST, ModModifierIds.Shady);
        addTraits(ModMaterialIds.shroomite, ARMOR, ModModifierIds.Shadowing);

        addDefaultTraits(ModMaterialIds.shroomite_compound, ModModifierIds.Nocturnal);

        addTraits(ModMaterialIds.tinkers_bronze, RANGED, ModModifierIds.Impact);
        addTraits(ModMaterialIds.tinkers_bronze, MELEE_HARVEST, ModModifierIds.Cutting);
        addTraits(ModMaterialIds.tinkers_bronze, ARMOR, ModModifierIds.Countermeasures);

        addTraits(ModMaterialIds.white_chocolate, MELEE_HARVEST, ModModifierIds.Inspired);

        addTraits(ModMaterialIds.black_chocolate, MELEE_HARVEST, ModModifierIds.Stimulation);

        addTraits(ModMaterialIds.chillslime_cryogel, AMMO, ModModifierIds.Fronzen);
        addTraits(ModMaterialIds.chillslime_cryogel, MELEE_HARVEST, ModModifierIds.Overfreeze, TinkerModifiers.overslime.getId());
        addTraits(ModMaterialIds.chillslime_cryogel, ARMOR, ModModifierIds.Overfreeze, TinkerModifiers.overslime.getId());

        addDefaultTraits(ModMaterialIds.electrical_steel, ModModifierIds.Repulsive);

        addTraits(ModMaterialIds.lightite, RANGED, ModModifierIds.LightlyAttack);
        addTraits(ModMaterialIds.lightite, MELEE_HARVEST, ModModifierIds.LightlyAttack);
        addTraits(ModMaterialIds.lightite, ARMOR, ModModifierIds.Hurried);

        addDefaultTraits(ModMaterialIds.lightite_compound, ModModifierIds.BideTime);

        addDefaultTraits(ModMaterialIds.metherite, ModModifierIds.MagicTransform);

        addTraits(ModMaterialIds.spectre, RANGED, ModModifierIds.Mock);
        addTraits(ModMaterialIds.spectre, MELEE_HARVEST, ModModifierIds.Mock);
        addTraits(ModMaterialIds.spectre, ARMOR, ModModifierIds.Concealing);

        addDefaultTraits(ModMaterialIds.spectre_compound, ModModifierIds.Coercion);

        addDefaultTraits(ModMaterialIds.chlorophyte, ModModifierIds.Symbiotic);

        addTraits(ModMaterialIds.chlorophyte_compound, AMMO, ModModifierIds.Seeking);

        addDefaultTraits(ModMaterialIds.gilded_silky_cloth, ModModifierIds.Silkward);

        addDefaultTraits(ModMaterialIds.ardite, ModModifierIds.Deposition);

        addTraits(ModMaterialIds.bacium, MELEE_HARVEST, ModModifierIds.BaneOfPigs);
        addTraits(ModMaterialIds.bacium, AMMO, ModModifierIds.Resisting);
        addTraits(ModMaterialIds.bacium, shell, ModModifierIds.Antibrute);

        addDefaultTraits(ModMaterialIds.tempered_glass, ModModifierIds.Durable);

        addTraits(ModMaterialIds.ochre_frogcroaking, RANGED, ModModifierIds.SculkCatalyse, ModModifierIds.SculkGravity);

        addTraits(ModMaterialIds.verdant_frogcroaking, MELEE_HARVEST, ModModifierIds.SculkCatalyse, ModModifierIds.SculkLevitate);

        addDefaultTraits(ModMaterialIds.pearlescent_frogcroaking, ModModifierIds.SculkCatalyse, ModModifierIds.SculkTeleport);

        // tier4
        addDefaultTraits(ModMaterialIds.ancient_ceramic, ModModifierIds.SculkCatalyse, ModModifierIds.SculkSiphon);
        
        addDefaultTraits(ModMaterialIds.scarlet_slimestone, ModModifierIds.Overdisintegrate, TinkerModifiers.overslime.getId());

        addTraits(ModMaterialIds.copshowium, RANGED, ModModifierIds.Reverse, ModModifierIds.Rederangement);
        addTraits(ModMaterialIds.copshowium, MELEE_HARVEST, ModModifierIds.Reverse, ModModifierIds.Rederangement);
        addTraits(ModMaterialIds.copshowium, ARMOR, ModModifierIds.Reverse, ModModifierIds.Retransit);
        
        addDefaultTraits(ModMaterialIds.stewium, ModModifierIds.Overeat, TinkerModifiers.overslime.getId());

        addTraits(ModMaterialIds.burnt_ashes, RANGED, ModModifierIds.BurningOut);
        addTraits(ModMaterialIds.burnt_ashes, MELEE_HARVEST, ModModifierIds.BurningOut);
        addTraits(ModMaterialIds.burnt_ashes, ARMOR, ModModifierIds.Reburning);

        addDefaultTraits(ModMaterialIds.solidified_emptiness, ModModifierIds.Reverse);
        addDefaultTraits(ModMaterialIds.solidified_emptiness, ModModifierIds.Recalamity);

        addDefaultTraits(ModMaterialIds.warden_steel, ModModifierIds.SculkCatalyse, ModModifierIds.SculkStruggle);

        addDefaultTraits(ModMaterialIds.zith, ModModifierIds.SharpCircumstance);

        addDefaultTraits(ModMaterialIds.bound_chain, ModModifierIds.SculkCatalyse, ModModifierIds.SculkDash);

        addDefaultTraits(ModMaterialIds.shimmerslime, ModModifierIds.Overcharge, TinkerModifiers.overslime.getId());

        addDefaultTraits(ModMaterialIds.soul_vine, ModModifierIds.SculkCatalyse, ModModifierIds.SculkBreed);

        addTraits(ModMaterialIds.colorite, RANGED, ModModifierIds.Reverse, ModModifierIds.Recharge);
        addTraits(ModMaterialIds.colorite, MELEE_HARVEST, ModModifierIds.Reverse, ModModifierIds.Redye);

        addTraits(ModMaterialIds.errorite, MELEE_HARVEST, ModModifierIds.Reverse, ModModifierIds.Repercussion);
        addTraits(ModMaterialIds.errorite, ARMOR, ModModifierIds.Reverse, ModModifierIds.Remisdirection);

        addDefaultTraits(ModMaterialIds.adamantium, ModModifierIds.Crimson);

        // overwrite
        addDefaultTraits(MaterialIds.paper, ModModifierIds.Soft);
        addDefaultTraits(MaterialIds.gold, ModModifierIds.Spiky);

        MaterialStatsId CURIO = new MaterialStatsId(ResourceLocation.fromNamespaceAndPath("tinkers_ingenuity", "curio"));

        addTraits(ModMaterialIds.echo_bronze, CURIO, new OptionalModifier(ModModifierIds.SculkCatalyseCurio), new OptionalModifier(ModModifierIds.SculkHeal));
        addTraits(ModMaterialIds.lightite, CURIO, new OptionalModifier(ModModifierIds.LightlySpeedCurio));
        addTraits(ModMaterialIds.tinkers_bronze, CURIO, new OptionalModifier(ModModifierIds.CuttingCurio));
        addTraits(ModMaterialIds.warden_steel, CURIO,new OptionalModifier(ModModifierIds.SculkCatalyseCurio), new OptionalModifier(ModModifierIds.SculkStruggleCurio));
    }

    @Override
    public String getName() {
        return "TiT Material Traits";
    }
}
