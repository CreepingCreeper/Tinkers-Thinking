package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;

public abstract class ModModifiers {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersThinking.MODID);
    public static final StaticModifier<Modifier> Deposition = MODIFIERS.register("deposition",DepositionModifier ::new);
    public static final StaticModifier<Modifier> Symbiotic = MODIFIERS.register("symbiotic", SymbioticModifier::new);
    public static final StaticModifier<Modifier> Stimulation = MODIFIERS.register("stimulation", StimulationModifier::new);
    public static final StaticModifier<Modifier> Inspired = MODIFIERS.register("inspired", InspiredModifier::new);
    public static final StaticModifier<Modifier> Mock = MODIFIERS.register("mock", MockModifier::new);
    public static final StaticModifier<Modifier> Concealer = MODIFIERS.register("concealing", ConcealingModifier::new);
    public static final StaticModifier<Modifier> Shady = MODIFIERS.register("shady", ShadyModifier::new);
    public static final StaticModifier<Modifier> Shadowing = MODIFIERS.register("shadowing", ShadowingModifier::new);
    public static final StaticModifier<Modifier> Repulsive = MODIFIERS.register("repulsive",RepulsiveModifier::new);
    public static final StaticModifier<Modifier> Prickly = MODIFIERS.register("prickly",PricklyModifier::new);
    public static final StaticModifier<Modifier> Sprinting = MODIFIERS.register("sprinting",SprintingModifier::new);
    public static final StaticModifier<Modifier> Duritae = MODIFIERS.register("duritae",SprintingModifier::new);
    public static final StaticModifier<Modifier> Overeat = MODIFIERS.register("overeat",OvereatModifier::new);
    public static final StaticModifier<Modifier> Overbearing = MODIFIERS.register("overbearing",OverbearingModifier::new);
    public static final StaticModifier<Modifier> Disarm = MODIFIERS.register("disarm",DisarmModifier::new);
    public static final StaticModifier<Modifier> SculkCatalyse = MODIFIERS.register("sculk_catalyse",SculkCatalyseModifier::new);
    public static final StaticModifier<Modifier> SculkBoost = MODIFIERS.register("sculk_boost",SculkBoostModifier::new);
    public static final StaticModifier<Modifier> SculkLevitate = MODIFIERS.register("sculk_levitate",SculkLevitateModifier::new);
    public static final StaticModifier<Modifier> GravityTeleport = MODIFIERS.register("sculk_gravity",SculkGravityModifier::new);
    public static final StaticModifier<Modifier> SculkTeleport = MODIFIERS.register("sculk_teleport",SculkTeleportModifier::new);
    public static final StaticModifier<Modifier> FallingAttack = MODIFIERS.register("falling_attack",FallingAttackModifier::new);
    public static final StaticModifier<Modifier> Density = MODIFIERS.register("density",DensityModifier::new);
    public static final StaticModifier<Modifier> Spiky = MODIFIERS.register("spiky",SpikyModifier::new);
    public static final StaticModifier<Modifier> Hungriness = MODIFIERS.register("hungriness",HungrinessModifier::new);
    public static final StaticModifier<Modifier> BurningOut = MODIFIERS.register("burning_out", BurningOutModifier::new);
    public static final StaticModifier<Modifier> Reburning = MODIFIERS.register("reburning", ReburningModifier::new);
    public static final StaticModifier<Modifier> LightlyAttack = MODIFIERS.register("lightly_attack",LightlyAttackModifier::new);
    public static final StaticModifier<Modifier> test = MODIFIERS.register("test", TestModifier::new);
    public static final StaticModifier<Modifier> test2 = MODIFIERS.register("test_a", Test2Modifier::new);

    public static void regeisters(IEventBus eventBus) {
        MODIFIERS.register(eventBus);}

    protected abstract void registerHooks(ModuleHookMap.Builder hookBuilder);
}
