package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class ModModifierIds {
    //melee
    public static final ModifierId BattleAdvanced = id("battle_advanced");
    public static final ModifierId BurningOut = id("burning_out");
    public static final ModifierId Cataclysm = id("cataclysm");
    public static final ModifierId Disarm = id("disarm");
    public static final ModifierId FallingAttack = id("falling_attack");
    public static final ModifierId LightlyAttack = id("lightly_attack");
    public static final ModifierId Mock = id("mock");
    public static final ModifierId Overdisintegrate = id("overdisintegrate");
    public static final ModifierId Overfreeze = id("overfreeze");
    public static final ModifierId Prickly = id("prickly");
    public static final ModifierId Recalamity = id("recalamity");
    public static final ModifierId Rederangement = id("rederangement");
    public static final ModifierId Redye = id("redye");
    public static final ModifierId Repercussion = id("repercussion");
    public static final ModifierId Repulsive = id("repulsive");
    public static final ModifierId SculkDash = id("sculk_dash");
    public static final ModifierId SculkGravity = id("sculk_gravity");
    public static final ModifierId SculkLevitate = id("sculk_levitate");
    public static final ModifierId SculkStruggle = id("sculk_struggle");
    public static final ModifierId SculkTeleport = id("sculk_teleport");
    public static final ModifierId SharpCircumstance = id("sharp_circumstance");
    public static final ModifierId Stimulation = id("stimulation");

    public static final ModifierId BaneOfPigs = id("bane_of_pigs");
    public static final ModifierId DensityAdvanced = id("density_advanced");

    // harvest
    public static final ModifierId Inspired = id("inspired");
    public static final ModifierId Hungriness = id("hungriness");
    public static final ModifierId SculkBoost = id("sculk_boost");
    public static final ModifierId Shady = id("shady");
    //ranged
    public static final ModifierId Atlatl = id("atlatl");
    public static final ModifierId Coercion = id("coercion");
    public static final ModifierId Fronzen = id("fronzen");
    public static final ModifierId Nonsense = id("nonsense");
    public static final ModifierId Nocturnal = id("nocturnal");
    public static final ModifierId BideTime = id("bide_time");
    public static final ModifierId Recharge = id("recharge");
    public static final ModifierId RidingShoot = id("riding_shoot");
    public static final ModifierId Resisting = id("resisting");
    public static final ModifierId Sinistral = id("sinistral");
    public static final ModifierId SwashAdvanced = id("swash_advanced");

    public static final ModifierId RepeatingAdvanced = id("repeating_advanced");
    public static final ModifierId CounterAdvanced = id("counter_advanced");
    public static final ModifierId Seeking = id("seeking");
    // defense
    public static final ModifierId Antibrute = id("antibrute");
    public static final ModifierId Concealing = id("concealing");
    public static final ModifierId CounterAttack = id("counter_attack");
    public static final ModifierId Crimson = id("crimson");
    public static final ModifierId GlowAdvanced = id("glow_advanced");
    public static final ModifierId MagicTransform = id("magic_transform");
    public static final ModifierId Reburning = id("reburning");
    public static final ModifierId Remisdirection = id("remisdirection");
    public static final ModifierId Retransit = id("retransit");
    public static final ModifierId SculkBreed = id("sculk_breed");
    public static final ModifierId SculkProtection = id("sculk_protection");
    public static final ModifierId SculkSiphon = id("sculk_siphon");
    public static final ModifierId Shadowing = id("shadowing");
    public static final ModifierId Spiky = id("spiky");
    public static final ModifierId Symbiotic = id("symbiotic");
    public static final ModifierId TeleportAdvanced = id("teleport_advanced");

    public static final ModifierId Silkward = id("silkward");
    // durability
    public static final ModifierId SculkCatalyse = id("sculk_catalyse");
    public static final ModifierId Reverse = id("reverse");
    public static final ModifierId DepositionModule = id("deposition");
    public static final ModifierId Duritae = id("duritae");
    public static final ModifierId Overeat = id("overeat");
    public static final ModifierId Durable = id("durable");
    public static final ModifierId Overcharge = id("overcharge");
    public static final ModifierId SlingSprinting = id("sling_sprinting");

    // misc
    public static final ModifierId Sprinting = id("sprinting");
    public static final ModifierId Hurried = id("hurried");

    // tinkers_ingenuity
    public static final ModifierId SculkCatalyseCurio = id("sculk_catalyse_curio");
    public static final ModifierId SculkHeal = id("sculk_heal");

    private ModModifierIds() {}
    private static ModifierId id(String name) {
        return new ModifierId(TinkersThinking.MODID, name);
    }
}
