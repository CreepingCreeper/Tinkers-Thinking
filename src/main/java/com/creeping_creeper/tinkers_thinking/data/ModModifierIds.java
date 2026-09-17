package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class ModModifierIds {
    public static final ModifierId Repulsive = id("repulsive");
    public static final ModifierId BaneOfPigs = id("bane_of_pigs");
    public static final ModifierId Antibrute = id("antibrute");

    public static final ModifierId DensityAdvanced = id("density_advanced");
    public static final ModifierId RepeatingAdvanced = id("repeating_advanced");
    public static final ModifierId CounterAdvanced = id("counter_advanced");
    public static final ModifierId Seeking = id("seeking");
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

    // defense
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
