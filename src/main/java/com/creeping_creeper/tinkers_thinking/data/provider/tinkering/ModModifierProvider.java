package com.creeping_creeper.tinkers_thinking.data.provider.tinkering;

import com.creeping_creeper.tinkers_thinking.common.library.ModPredicate;
import com.creeping_creeper.tinkers_thinking.common.modifer.durability.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.HurriedModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.SlingSprintingModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.ranged.*;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.data.advancement.AdvancementIds;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.json.LevelingInt;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.json.predicate.tool.ToolStackPredicate;
import slimeknights.tconstruct.library.json.predicate.tool.ToolVariableRangePredicate;
import slimeknights.tconstruct.library.json.variable.entity.ConditionalEntityVariable;
import slimeknights.tconstruct.library.json.variable.entity.EntityVariable;
import slimeknights.tconstruct.library.json.variable.stat.EntityConditionalStatVariable;
import slimeknights.tconstruct.library.json.variable.tool.ModDataSource;
import slimeknights.tconstruct.library.json.variable.tool.ModDataVariable;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.modifiers.modules.behavior.AttributeModule;
import slimeknights.tconstruct.library.modifiers.modules.behavior.ConditionalStatModule;
import slimeknights.tconstruct.library.modifiers.modules.build.*;
import slimeknights.tconstruct.library.modifiers.modules.capacity.CapacityBarModule;
import slimeknights.tconstruct.library.modifiers.modules.combat.ConditionalPowerModule;
import slimeknights.tconstruct.library.modifiers.modules.mining.ConditionalMiningSpeedModule;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.modifiers.modules.util.ModifierCondition;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.IndestructibleItemEntity;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.data.ModifierIds;

import static slimeknights.tconstruct.library.json.math.ModifierFormula.LEVEL;
import static slimeknights.tconstruct.library.json.math.ModifierFormula.VALUE;

public class ModModifierProvider extends AbstractModifierProvider implements IConditionBuilder {
    public ModModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {
        ToolVariable catalyse =  new ModDataVariable(ModModifierIds.SculkCatalyse, ModDataSource.PERSISTENT);
        ToolVariable reverse =  new ModDataVariable(ModModifierIds.Reverse, ModDataSource.PERSISTENT);

        // melee

        // harvest
        buildModifier(ModModifierIds.SculkBoost).addModule(
                ConditionalMiningSpeedModule.builder()
                        .percent()
                        .formula()
                        .customVariable("catalyse", catalyse)
                        .variable(LEVEL).multiply()
                        .constant(0.2f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build())
                .addModule(ConditionalStatModule.stat(ToolStats.DRAW_SPEED).percent()
                        .formula()
                        .customVariable("catalyse", catalyse)
                        .variable(LEVEL).multiply()
                        .constant(0.2f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build())
                .addModule(AttributeModule.builder(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.MULTIPLY_BASE)
                        .tooltipStyle(AttributeModule.TooltipStyle.PERCENT).toolTag(TinkerTags.Items.WORN_ARMOR)
                        .tool(ToolStackPredicate.and(ToolStackPredicate.tag(TinkerTags.Items.ARMOR), ToolVariableRangePredicate.min(catalyse, 1, false)))
                        .eachLevel(0.2f));

        buildModifier(ModModifierIds.SculkBoost).addModule(
                        ConditionalMiningSpeedModule.builder()
                                .percent()
                                .formula()
                                .customVariable("catalyse", catalyse)
                                .variable(LEVEL).multiply()
                                .constant(0.2f).multiply()
                                .constant(1).add()
                                .variable(VALUE).multiply()
                                .build())
                .addModule(ConditionalStatModule.stat(ToolStats.DRAW_SPEED).percent()
                        .formula()
                        .customVariable("catalyse", catalyse)
                        .variable(LEVEL).multiply()
                        .constant(0.2f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build());
        //ranged
        buildModifier(ModModifierIds.Atlatl).addModule(AtlatlModule.INSTANCE);
        buildModifier(ModModifierIds.Coercion).addModule(CoercionModule.INSTANCE);
        buildModifier(ModModifierIds.Fronzen).addModule(CoercionModule.INSTANCE);
        buildModifier(ModModifierIds.Nonsense).addModule(new NonsenseModule(new LevelingValue(0, 0.5f)));
        buildModifier(ModModifierIds.Nocturnal)
                .addModule(ConditionalStatModule.stat(ToolStats.VELOCITY)
                        .formula()
                        .customVariable("bonus", new EntityConditionalStatVariable(new ConditionalEntityVariable(
                        ModPredicate.Entity.IS_DAY,
                        new EntityVariable.Constant(-0.35f),
                        new EntityVariable.Constant(0.35f)
                ), 0F))
                .variable(LEVEL).multiply()
                .variable(VALUE).add()
                .build());
        buildModifier(ModModifierIds.BideTime)
                .addModule(StatBoostModule.add(ToolStats.PROJECTILE_DAMAGE).flat(0.4f))
                .addModule(StatBoostModule.add(ToolStats.ACCURACY).flat(0.4f))
                .addModule(new BideTimeModule(100));

        buildModifier(ModModifierIds.Recharge)
                .addModule(ConditionalStatModule.stat(ToolStats.DRAW_SPEED).percent()
                        .formula()
                        .customVariable("reverse", reverse)
                        .variable(LEVEL).multiply()
                        .constant(-0.15f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                .build())
                .addModule(ConditionalStatModule.stat(ToolStats.VELOCITY).percent()
                        .formula()
                        .customVariable("reverse", reverse)
                        .variable(LEVEL).multiply()
                        .constant(0.2f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build())
                .addModule(ConditionalStatModule.stat(ToolStats.ACCURACY).percent()
                        .formula()
                        .customVariable("reverse", reverse)
                        .constant(0).equal()
                        .variable(LEVEL).multiply()
                        .constant(-0.15f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build())
                .addModule(ConditionalStatModule.stat(ToolStats.PROJECTILE_DAMAGE).percent()
                        .formula()
                        .customVariable("reverse", reverse).nonNegative()
                        .constant(0).equal()
                        .variable(LEVEL).multiply()
                        .constant(0.2f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build());

        buildModifier(ModModifierIds.RidingShoot)
                .addModule(ConditionalPowerModule.builder().holder(ModPredicate.Entity.IS_RIDING).eachLevel(1))
                .addModule(RidingShootModule.INSTANCE);

        buildModifier(ModModifierIds.Resisting).addModule(ResistingModule.INSTANCE);
        buildModifier(ModModifierIds.Sinistral).addModule(SinistralModule.INSTANCE);
        buildModifier(ModModifierIds.SwashAdvanced).addModule(new SwashAdvancedModule((new LevelingValue(0, 1f))));
        // defense
        buildModifier(ModModifierIds.Silkward).addModule(ModifierSlotModule.slot(SlotType.DEFENSE).eachLevel(2))
                .addModule(StatBoostModule.add(ToolStats.ARMOR).eachLevel(-2.0f));

        // durability
        buildModifier(ModModifierIds.DepositionModule).addModule(new DepositionModule(0.8f));
        buildModifier(ModModifierIds.Durable).addModule(new DurableModule(0.9f));
        buildModifier(ModModifierIds.Duritae).addModule(DuritaeModule.INSTANCE);
        buildModifier(ModModifierIds.Overcharge).addModule(new OverchargeModule(100, 200));
        buildModifier(ModModifierIds.Overeat).addModule(new OvereatModule(new LevelingValue(0, 0.15f)));
        buildModifier(ModModifierIds.Reverse).levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(new CapacityBarModule(LevelingInt.flat(1), null))
                .addModule(ReverseModule.INSTANCE);
        buildModifier(ModModifierIds.SculkCatalyse).levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(new CapacityBarModule(LevelingInt.flat(1), null))
                .addModule(SculkCatalyseModule.INSTANCE)
                .addModule(new ArmorLevelModule(ModDataKeys.SculkCatalyse, false, TinkerTags.Items.HELD));
        // misc
        buildModifier(ModModifierIds.Hurried).levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(new HurriedModule(new LevelingValue(0, 0.0025f)));
        buildModifier(ModModifierIds.Sprinting).levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL).addModule(new SlingSprintingModule(new LevelingValue(2F, 1.5f), 1.5f, LivingEntityPredicate.ANY, ModifierCondition.ANY_TOOL));
        // overwrite
        buildModifier(ModifierIds.netherite)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(NetheriteModule.INSTANCE)
                .addModule(new RarityModule(Rarity.RARE))
                .addModule(new VolatileFlagModule(IndestructibleItemEntity.INDESTRUCTIBLE_ENTITY))
                .addModule(StatBoostModule.multiplyBase(ToolStats.DURABILITY).flat(0.2f))
                // armor
                .addModule(StatBoostModule.add(ToolStats.ARMOR_TOUGHNESS).flat(1))
                .addModule(StatBoostModule.add(ToolStats.KNOCKBACK_RESISTANCE).flat(0.05f))
                // melee harvest
                .addModule(StatBoostModule.multiplyBase(ToolStats.ATTACK_DAMAGE).flat(0.2f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.MINING_SPEED).flat(0.25f))
                .addModule(SetStatModule.set(ToolStats.HARVEST_TIER).value(Tiers.NETHERITE))
                // ranged
                .addModule(StatBoostModule.multiplyBase(ToolStats.VELOCITY).flat(0.1f))
                // achievement
                .addModule(new VolatileFlagModule(AdvancementIds.NETHERITE));
    }

    @Override
    public String getName() {
        return "TiT Modifier Provider";
    }
}
