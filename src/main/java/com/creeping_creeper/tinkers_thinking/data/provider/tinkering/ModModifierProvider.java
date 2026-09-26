package com.creeping_creeper.tinkers_thinking.data.provider.tinkering;

import com.creeping_creeper.tinkers_thinking.common.client.ModSlots;
import com.creeping_creeper.tinkers_thinking.common.library.ModPredicate;
import com.creeping_creeper.tinkers_thinking.common.library.variable.SkyLightVariable;
import com.creeping_creeper.tinkers_thinking.common.modifer.defense.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.durability.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.harvest.HungrinessModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.melee.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.HurriedModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.SlingSprintingModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.ranged.*;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import com.creeping_creeper.tinkers_thinking.data.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.data.predicate.block.BlockPredicate;
import slimeknights.mantle.data.predicate.damage.DamageSourcePredicate;
import slimeknights.mantle.data.predicate.entity.HasMobEffectPredicate;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.mantle.data.predicate.item.ItemPredicate;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.data.advancement.AdvancementIds;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.json.LevelingInt;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.json.RandomLevelingValue;
import slimeknights.tconstruct.library.json.predicate.tool.ToolStackPredicate;
import slimeknights.tconstruct.library.json.predicate.tool.ToolVariableRangePredicate;
import slimeknights.tconstruct.library.json.variable.entity.ConditionalEntityVariable;
import slimeknights.tconstruct.library.json.variable.entity.EntityVariable;
import slimeknights.tconstruct.library.json.variable.mining.BlockLightVariable;
import slimeknights.tconstruct.library.json.variable.stat.EntityConditionalStatVariable;
import slimeknights.tconstruct.library.json.variable.tool.ModDataSource;
import slimeknights.tconstruct.library.json.variable.tool.ModDataVariable;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.modifiers.modules.armor.ProtectionModule;
import slimeknights.tconstruct.library.modifiers.modules.behavior.AttributeModule;
import slimeknights.tconstruct.library.modifiers.modules.behavior.ConditionalStatModule;
import slimeknights.tconstruct.library.modifiers.modules.behavior.MaterialRepairModule;
import slimeknights.tconstruct.library.modifiers.modules.build.*;
import slimeknights.tconstruct.library.modifiers.modules.capacity.CapacityBarModule;
import slimeknights.tconstruct.library.modifiers.modules.combat.ConditionalMeleeDamageModule;
import slimeknights.tconstruct.library.modifiers.modules.combat.ConditionalPowerModule;
import slimeknights.tconstruct.library.modifiers.modules.combat.MobEffectModule;
import slimeknights.tconstruct.library.modifiers.modules.interaction.edible.EdibleConsumeDurabilityModule;
import slimeknights.tconstruct.library.modifiers.modules.interaction.edible.EdibleModule;
import slimeknights.tconstruct.library.modifiers.modules.interaction.edible.EdibleRepresentativeItemModule;
import slimeknights.tconstruct.library.modifiers.modules.mining.ConditionalMiningSpeedModule;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.modifiers.modules.util.BooleanPredicate;
import slimeknights.tconstruct.library.modifiers.modules.util.ModifierCondition;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.modifiers.util.ModifierTooltip;
import slimeknights.tconstruct.library.tools.IndestructibleItemEntity;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerEffects;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import static slimeknights.tconstruct.library.json.math.ModifierFormula.*;

public class ModModifierProvider extends AbstractModifierProvider implements IConditionBuilder {
    public ModModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void addModifiers() {
        ToolVariable catalyse = new ModDataVariable(ModModifierIds.SculkCatalyse, ModDataSource.PERSISTENT);
        ToolVariable reverse = new ModDataVariable(ModModifierIds.Reverse, ModDataSource.PERSISTENT);

        // melee
        buildModifier(ModModifierIds.AttackAdvanced).addModule(ConditionalMeleeDamageModule.builder().attacker(LivingEntityPredicate.SPRINTING).eachLevel(3.5f));

        buildModifier(ModModifierIds.BattleAdvanced).addModule(new BattleAdvancedModule(0.005f, 1200));

        MobEffectModule.Builder weaknessBuilder = MobEffectModule.builder(MobEffects.WEAKNESS).time(RandomLevelingValue.perLevel(80.0f, 20.0f)).level(RandomLevelingValue.flat(2.0f))
                .target(LivingEntityPredicate.tag(ModTags.EntityTypes.pigLike));

        buildModifier(ModModifierIds.BaneOfPigs)
                .addModule(weaknessBuilder.buildWeapon())
                .addModule(ConditionalMeleeDamageModule.builder().target(LivingEntityPredicate.tag(ModTags.EntityTypes.pigLike)).eachLevel(5.0f));

        MobEffectModule.Builder fireResistanceBuilder = MobEffectModule.builder(MobEffects.FIRE_RESISTANCE).time(RandomLevelingValue.flat(80))
                .target(new HasMobEffectPredicate(MobEffects.FIRE_RESISTANCE).inverted());

        buildModifier(ModModifierIds.BurningOut).addModule(new BurningOutModule(LevelingValue.eachLevel(0.3f)))
                .addModule(fireResistanceBuilder.buildWeapon());

        MobEffectModule.Builder cataclysmBuilder = MobEffectModule.builder(ModEffects.cataclysm).time(RandomLevelingValue.flat(600)).chance(LevelingValue.eachLevel(0.15f));

        buildModifier(ModModifierIds.Cataclysm).addModule(cataclysmBuilder.buildWeapon());

        buildModifier(ModModifierIds.Clay).addModule(ConditionalMeleeDamageModule.builder().target(LivingEntityPredicate.tag(EntityTypeTags.ARROWS)).eachLevel(100)).levelDisplay(ModifierLevelDisplay.NO_LEVELS);

        MobEffectModule.Builder disarmBuilder = MobEffectModule.builder(ModEffects.disarm).time(RandomLevelingValue.flat(160))
                .target(new HasMobEffectPredicate(ModEffects.modifier_immune.get()).inverted());
        MobEffectModule.Builder disarmBuilder1 = MobEffectModule.builder(ModEffects.modifier_immune.get()).time(RandomLevelingValue.perLevel(190, -30))
                .target(new HasMobEffectPredicate(ModEffects.modifier_immune.get()).inverted());

        buildModifier(ModModifierIds.DensityAdvanced);

        buildModifier(ModModifierIds.Disarm).addModule(disarmBuilder.buildWeapon())
                .addModule(disarmBuilder1.buildWeapon());

        MobEffectModule.Builder fallingBuilder = MobEffectModule.builder(TinkerEffects.repulsive).time(RandomLevelingValue.flat(50)).level(RandomLevelingValue.flat(2))
                .holder(LivingEntityPredicate.ON_GROUND);

        buildModifier(ModModifierIds.Hellish).addModule(ConditionalMeleeDamageModule.builder().target(LivingEntityPredicate.FIRE_IMMUNE.inverted()).eachLevel(2.0f));

        buildModifier(ModModifierIds.FallingAttack).addModule(new FallingAttackModule(0.5f))
                .addModule(fallingBuilder.buildWeapon());

        MobEffectModule.Builder lightlyBuilder = MobEffectModule.builder(ModEffects.quick_attack).time(RandomLevelingValue.flat(60)).level(RandomLevelingValue.perLevel(0, 3))
                .holder(ModPredicate.Entity.IS_HOTBAR_EMPTY);

        buildModifier(ModModifierIds.LightlyAttack).addModule(lightlyBuilder.buildWeapon())
                .addModule(ConditionalStatModule.stat(ToolStats.DRAW_SPEED).percent()
                        .holder(ModPredicate.Entity.IS_HOTBAR_EMPTY)
                        .formula()
                        .variable(LEVEL)
                        .constant(0.3f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build())
                .addModule(AttributeModule.builder(ForgeMod.ENTITY_GRAVITY, AttributeModifier.Operation.MULTIPLY_TOTAL).tooltipStyle(AttributeModule.TooltipStyle.PERCENT).eachLevel(-0.1f));

        MobEffectModule.Builder invisibilitydBuilder = MobEffectModule.builder(MobEffects.INVISIBILITY).time(RandomLevelingValue.flat(200));

        buildModifier(ModModifierIds.Mock)
                .addModule(invisibilitydBuilder.buildWeapon())
                .addModule(ConditionalMeleeDamageModule.builder().percent()
                        .target(new HasMobEffectPredicate(MobEffects.INVISIBILITY))
                        .formula()
                        .variable(LEVEL)
                        .constant(0.2f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build())
                .addModule(ConditionalPowerModule.builder().percent()
                        .target(new HasMobEffectPredicate(MobEffects.INVISIBILITY))
                        .formula()
                        .variable(LEVEL)
                        .constant(0.2f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build());

        MobEffectModule.Builder posionBuilder = MobEffectModule.builder(MobEffects.POISON).time(RandomLevelingValue.perLevel(120f, 20f)).level(RandomLevelingValue.perLevel(0, 3));

        buildModifier(ModModifierIds.Overdose).addModule(posionBuilder.buildWeapon())
                .addModule(ConditionalMeleeDamageModule.builder().target(LivingEntityPredicate.tag(TinkerTags.EntityTypes.SLIMES)).eachLevel(2.0f));

        buildModifier(ModModifierIds.Overdisintegrate).addModule(new OverdisintegrateModule(LevelingInt.eachLevel(4)));
        buildModifier(ModModifierIds.Overfreeze).addModule(new OverfreezeModule(LevelingInt.eachLevel(4)));
        buildModifier(ModModifierIds.Prickly).addModule(new PricklyModule(LevelingValue.eachLevel(0.12f)));
        buildModifier(ModModifierIds.Recalamity).addModule(new RecalamityModule(0.1f));
        buildModifier(ModModifierIds.Rederangement).addModule(RederangementModule.INSTANCE)
                .addModule(AttributeModule.builder(ForgeMod.ENTITY_GRAVITY, AttributeModifier.Operation.MULTIPLY_TOTAL).tooltipStyle(AttributeModule.TooltipStyle.PERCENT).eachLevel(-0.1f));
        buildModifier(ModModifierIds.Redye).addModule(new RedyeModule(LevelingValue.eachLevel(0.06f)));
        buildModifier(ModModifierIds.Repercussion).addModule(new RepercussionModule(new LevelingInt(200, -40)));

        MobEffectModule.Builder repulsiveBuilder = MobEffectModule.builder(TinkerEffects.repulsive).time(RandomLevelingValue.flat(15)).level(RandomLevelingValue.perLevel(0, 2));

        buildModifier(ModModifierIds.Repulsive).addModule(repulsiveBuilder.buildWeapon())
                .addModule(repulsiveBuilder.buildCounter());

        buildModifier(ModModifierIds.SculkDash).addModule(new SculkDashModule(0.25f));

        MobEffectModule.Builder gravityBuilder = MobEffectModule.builder(ModEffects.overweight).time(RandomLevelingValue.flat(120)).level(RandomLevelingValue.flat(5))
                .holder(new HasMobEffectPredicate(ModEffects.sculk_power.get()))
                .target(new HasMobEffectPredicate(ModEffects.modifier_immune.get()).inverted());
        MobEffectModule.Builder gravityBuilder1 = MobEffectModule.builder(ModEffects.jumpless).time(RandomLevelingValue.flat(120))
                .holder(new HasMobEffectPredicate(ModEffects.sculk_power.get()))
                .target(new HasMobEffectPredicate(ModEffects.modifier_immune.get()).inverted());
        MobEffectModule.Builder immuneBuilder = MobEffectModule.builder(ModEffects.modifier_immune.get()).time(RandomLevelingValue.perLevel(360, -80))
                .holder(new HasMobEffectPredicate(ModEffects.sculk_power.get()))
                .target(new HasMobEffectPredicate(ModEffects.modifier_immune.get()).inverted());

        buildModifier(ModModifierIds.SculkGravity).addModule(gravityBuilder.buildWeapon())
                .addModule(gravityBuilder1.buildWeapon())
                .addModule(immuneBuilder.buildWeapon())
                .addModule(AttributeModule.builder(ForgeMod.ENTITY_GRAVITY, AttributeModifier.Operation.MULTIPLY_TOTAL).tooltipStyle(AttributeModule.TooltipStyle.PERCENT).eachLevel(0.05f));

        MobEffectModule.Builder levitateBuilder = MobEffectModule.builder(ModEffects.weightless).time(RandomLevelingValue.flat(120)).level(RandomLevelingValue.flat(2))
                .holder(new HasMobEffectPredicate(ModEffects.sculk_power.get()))
                .target(new HasMobEffectPredicate(ModEffects.modifier_immune.get()).inverted());

        buildModifier(ModModifierIds.SculkLevitate).addModule(levitateBuilder.buildWeapon())
                .addModule(immuneBuilder.buildWeapon())
                .addModule(AttributeModule.builder(ForgeMod.ENTITY_GRAVITY, AttributeModifier.Operation.MULTIPLY_TOTAL).tooltipStyle(AttributeModule.TooltipStyle.PERCENT).eachLevel(-0.05f));

        buildModifier(ModModifierIds.SculkTeleport).addModule(SculkTeleportModule.INSTANCE);
        buildModifier(ModModifierIds.SculkStruggle).addModule(new SculkStruggleModule(LevelingInt.eachLevel(100)))
                .addModule(new ArmorLevelModule(ModDataKeys.SculkStruggle, false, TinkerTags.Items.HELD));

        MobEffectModule.Builder stimulationBuilder = MobEffectModule.builder(MobEffects.DIG_SPEED).time(RandomLevelingValue.perLevel(0, 120)).level(RandomLevelingValue.flat(1)).isAoe(BooleanPredicate.FALSE).chance(LevelingValue.flat(0.25f));

        buildModifier(ModModifierIds.SharpCircumstance).addModule(new SharpCircumstanceModule(LevelingValue.eachLevel(0.125f), LevelingValue.eachLevel(0.08f))).priority(15);
        buildModifier(ModModifierIds.Stimulation).addModule(stimulationBuilder.buildWeapon());

        buildModifier(ModModifierIds.Targeted).addModule(StatBoostModule.multiplyConditional(ToolStats.ATTACK_DAMAGE).flat(0.5f));

        // harvest
        buildModifier(ModModifierIds.Cutting).addModule(
                ConditionalMiningSpeedModule.builder().blocks(BlockPredicate.and(
                        BlockPredicate.tag(Tags.Blocks.STONE), BlockPredicate.tag(Tags.Blocks.COBBLESTONE), BlockPredicate.tag(ModTags.Blocks.cuttable)))
                        .eachLevel(5.0f));
        buildModifier(ModModifierIds.SculkBoost).addModule(
                ConditionalMiningSpeedModule.builder().percent()
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

        buildModifier(ModModifierIds.Shady)
                .addModule(ConditionalMiningSpeedModule.builder().percent()
                        .formula()
                        .constant(15)
                        .customVariable("light", new BlockLightVariable(LightLayer.SKY, 15)).subtract()
                        .variable(LEVEL).multiply()
                        .constant(0.015f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply().build())
                .addModule(ConditionalStatModule.stat(ToolStats.VELOCITY).percent()
                        .formula()
                        .constant(15)
                        .customVariable("light", new SkyLightVariable(LightLayer.SKY, 15)).subtract()
                        .variable(LEVEL).multiply()
                        .constant(0.01f).multiply()
                        .constant(1).add()
                        .variable(VALUE).multiply()
                        .build());
        buildModifier(ModModifierIds.Hungriness).addModule(new HungrinessModule(LevelingValue.eachLevel(0.02f)));
        buildModifier(ModModifierIds.MeltAdvanced) .addModule(new ModifierTraitModule(ModifierIds.lustrous, 1, false));
        MobEffectModule.Builder inspiredBuilder = MobEffectModule.builder(MobEffects.DIG_SPEED).time(RandomLevelingValue.perLevel(0, 100)).level(RandomLevelingValue.flat(2)).isAoe(BooleanPredicate.FALSE).chance(LevelingValue.flat(0.25f));

        buildModifier(ModModifierIds.Inspired).addModule(inspiredBuilder.buildToolUsage());
        // ranged
        buildModifier(ModModifierIds.Atlatl).addModule(AtlatlModule.INSTANCE).levelDisplay(ModifierLevelDisplay.NO_LEVELS);
        buildModifier(ModModifierIds.BideTime)
                .addModule(StatBoostModule.add(ToolStats.PROJECTILE_DAMAGE).flat(0.4f))
                .addModule(StatBoostModule.add(ToolStats.ACCURACY).flat(0.4f))
                .addModule(new BideTimeModule(100));
        buildModifier(ModModifierIds.Coercion).addModule(CoercionModule.INSTANCE);
        buildModifier(ModModifierIds.ChargeAdvanced)
                .addModule(ConditionalStatModule.stat(ToolStats.PROJECTILE_DAMAGE)
                        .formula()
                        .customVariable("charge", new ModDataVariable(ModifierIds.warCharge, ModDataSource.PERSISTENT))
                        .constant(0.04f).multiply()
                        .variable(MULTIPLIER).multiply()
                        .variable(VALUE).add().build());

        MobEffectModule.Builder fronzenBuilder = MobEffectModule.builder(MobEffects.MOVEMENT_SLOWDOWN).time(RandomLevelingValue.perLevel(0, 30)).level(RandomLevelingValue.flat(5));

        buildModifier(ModModifierIds.Fronzen).addModule(fronzenBuilder.buildWeapon());
        buildModifier(ModModifierIds.Impact)
                .addModule(StatBoostModule.multiplyBase(ToolStats.VELOCITY).flat(-0.15f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.PROJECTILE_DAMAGE).flat(0.3f));
        buildModifier(ModModifierIds.Nonsense).addModule(new NonsenseModule(LevelingValue.eachLevel(0.5f)));
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

        buildModifier(ModModifierIds.Seeking);
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

        buildModifier(ModModifierIds.RepeatingAdvanced);
        buildModifier(ModModifierIds.RidingShoot)
                .addModule(ConditionalPowerModule.builder().holder(ModPredicate.Entity.IS_RIDING).eachLevel(1))
                .addModule(RidingShootModule.INSTANCE);

        buildModifier(ModModifierIds.Resisting).addModule(ResistingModule.INSTANCE);
        buildModifier(ModModifierIds.Sinistral).addModule(SinistralModule.INSTANCE);
        buildModifier(ModModifierIds.SwashAdvanced).addModule(new SwashAdvancedModule((LevelingValue.eachLevel(1f))));
        // defense
        buildModifier(ModModifierIds.Antibrute).addModule(AntibruteModule.INSTANCE);
        buildModifier(ModModifierIds.Broad).addModule(StatBoostModule.add(ToolStats.BLOCK_ANGLE).eachLevel(30));

        MobEffectModule.Builder invisibilitydBuilder1 = MobEffectModule.builder(MobEffects.INVISIBILITY).time(RandomLevelingValue.flat(200)).targetSelf(true);

        buildModifier(ModModifierIds.Concealing)
                .addModule(invisibilitydBuilder1.buildCounter())
                .addModule(ProtectionModule.builder().toolItem(ItemPredicate.tag(TinkerTags.Items.ARMOR)).entity(new HasMobEffectPredicate(MobEffects.INVISIBILITY)).eachLevel(1.5f));
        buildModifier(ModModifierIds.CounterAdvanced);
        buildModifier(ModModifierIds.CounterAttack).addModule(new CounterAttackModule(2.0f, LevelingValue.eachLevel(0.35f))).levelDisplay(ModifierLevelDisplay.NO_LEVELS);
        buildModifier(ModModifierIds.Countermeasures).addModule(ProtectionModule.builder().toolItem(ItemPredicate.tag(TinkerTags.Items.ARMOR)).source(DamageSourcePredicate.tag(DamageTypeTags.BYPASSES_ARMOR).inverted()).eachLevel(2.5f));

        buildModifier(ModModifierIds.Crimson).addModule(new CrimsonModule(400));
        buildModifier(ModModifierIds.GlowAdvanced).addModule(new GlowAdvancedModule(LevelingValue.eachLevel(0.1f)));
        buildModifier(ModModifierIds.MagicTransform).addModule(new MagicTransformModule(LevelingValue.eachLevel(0.2f), 0.75f));

        MobEffectModule.Builder fireResistanceBuilder1 = MobEffectModule.builder(MobEffects.FIRE_RESISTANCE).time(RandomLevelingValue.flat(80))
                .target(new HasMobEffectPredicate(MobEffects.FIRE_RESISTANCE).inverted()).targetSelf(true);

        buildModifier(ModModifierIds.Reburning).addModule(new ReburningModule(1))
                .addModule(fireResistanceBuilder1.buildCounter())
                .addModule(new ArmorLevelModule(ModDataKeys.Reburning, false, TinkerTags.Items.HELD));
        buildModifier(ModModifierIds.Remisdirection).addModule(RemisdirectionModule.INSTANCE)
                .addModule(new ArmorLevelModule(ModDataKeys.Remisdirection, false, TinkerTags.Items.HELD));
        buildModifier(ModModifierIds.Retransit).addModule(new RetransitModule(new LevelingInt(240, -20)))
                .addModule(new ArmorLevelModule(ModDataKeys.Retransit, false, TinkerTags.Items.HELD))
                .addModule(AttributeModule.builder(ForgeMod.ENTITY_GRAVITY, AttributeModifier.Operation.MULTIPLY_TOTAL).tooltipStyle(AttributeModule.TooltipStyle.PERCENT).eachLevel(-0.1f));
        buildModifier(ModModifierIds.SculkBreed);
        buildModifier(ModModifierIds.SculkProtection).addModule(new SculkProtectionModule(LevelingValue.ZERO));
        buildModifier(ModModifierIds.SculkSiphon).addModule(new SculkSiphonModule(0.2f));
        buildModifier(ModModifierIds.Shadowing);

        MobEffectModule.Builder spikyBuilder = MobEffectModule.builder(ModEffects.modifier_immune.get()).time(RandomLevelingValue.flat(60))
                .target(new HasMobEffectPredicate(ModEffects.modifier_immune.get()).inverted());

        buildModifier(ModModifierIds.Spiky).addModule(SpikyModule.INSTANCE)
                .addModule(spikyBuilder.buildCounter());
        buildModifier(ModModifierIds.Symbiotic)
                .addModule(EdibleModule.EDIBLE_TRAIT)
                .addModule(new EdibleRepresentativeItemModule(TinkerCommons.jeweledApple))
                .addModule(new EdibleConsumeDurabilityModule(LevelingInt.eachLevel(8)))
                .addModule(new EdibleHealModule(LevelingValue.eachLevel(0.1f)))
                .addModule(StatBoostModule.add(EdibleModule.COUNTER_CHANCE).eachLevel(0.15f));
        buildModifier(ModModifierIds.TeleportAdvanced).addModule(new TeleportAdvancedModule(LevelingInt.eachLevel(300)));

        // durability
        buildModifier(ModModifierIds.DepositionModule).addModule(new DepositionModule(0.8f));
        buildModifier(ModModifierIds.Durable).addModule(new DurableModule(0.9f));
        buildModifier(ModModifierIds.Duritae).addModule(DuritaeModule.INSTANCE);
        buildModifier(ModModifierIds.Overcharge).addModule(new OverchargeModule(100, 200));
        buildModifier(ModModifierIds.Overeat).addModule(new OvereatModule(LevelingValue.eachLevel(0.15f)));
        buildModifier(ModModifierIds.Reverse).levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(new CapacityBarModule(LevelingInt.flat(1), null))
                .addModule(ReverseModule.INSTANCE);
        buildModifier(ModModifierIds.SculkCatalyse).levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(new CapacityBarModule(LevelingInt.flat(1), null))
                .addModule(SculkCatalyseModule.INSTANCE)
                .addModule(new ArmorLevelModule(ModDataKeys.SculkCatalyse, false, TinkerTags.Items.HELD));
        // slot
        buildModifier(ModModifierIds.AncientAbility).addModule(ModifierSlotModule.slot(SlotType.ABILITY).eachLevel(1)).showInTooltips(ModifierTooltip.ShowInTooltips.BONUS_SLOT).levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL);
        buildModifier(ModModifierIds.AncientUpgrade).addModule(ModifierSlotModule.slot(SlotType.UPGRADE).eachLevel(2)).showInTooltips(ModifierTooltip.ShowInTooltips.BONUS_SLOT).levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL);
        buildModifier(ModModifierIds.Bronze).addModule(ModifierSlotModule.slot(ModSlots.ANCIENT).eachLevel(1)).showInTooltips(ModifierTooltip.ShowInTooltips.BONUS_SLOT).levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL);
        buildModifier(ModModifierIds.Ceramic).addModule(ModifierSlotModule.slot(ModSlots.ANCIENT).eachLevel(1)).showInTooltips(ModifierTooltip.ShowInTooltips.BONUS_SLOT).levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL);

        buildModifier(ModModifierIds.Repayed).addModule(ModifierSlotModule.slot(SlotType.UPGRADE).eachLevel(1)).showInTooltips(ModifierTooltip.ShowInTooltips.BONUS_SLOT).levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL);
        buildModifier(ModModifierIds.Silkward).addModule(ModifierSlotModule.slot(SlotType.DEFENSE).eachLevel(2))
                .addModule(StatBoostModule.add(ToolStats.ARMOR).eachLevel(-2.0f));
        buildModifier(ModModifierIds.Soft).addModule(StatBoostModule.multiplyBase(ToolStats.DURABILITY).eachLevel(-0.25f))
                .addModule(MaterialRepairModule.material(MaterialIds.paper).constant(120));
        buildModifier(ModModifierIds.Withernic).addModule(ModifierSlotModule.slot(SlotType.ABILITY).eachLevel(1)).showInTooltips(ModifierTooltip.ShowInTooltips.BONUS_SLOT).levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL);

        // misc
        buildModifier(ModModifierIds.LightlySpeed).addModule(StatBoostModule.add(ToolStats.USE_ITEM_SPEED).eachLevel(0.3f))
                .addModule(AttributeModule.builder(ForgeMod.ENTITY_GRAVITY, AttributeModifier.Operation.MULTIPLY_TOTAL).tooltipStyle(AttributeModule.TooltipStyle.PERCENT).eachLevel(-0.1f));

        buildModifier(ModModifierIds.Hurried).levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(new HurriedModule(LevelingValue.eachLevel(0.0025f)))
                .addModule(AttributeModule.builder(ForgeMod.ENTITY_GRAVITY, AttributeModifier.Operation.MULTIPLY_TOTAL).tooltipStyle(AttributeModule.TooltipStyle.PERCENT).eachLevel(-0.1f));

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
