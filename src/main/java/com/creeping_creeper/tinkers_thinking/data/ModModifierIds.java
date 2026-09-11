package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.SlingSprintingModule;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class ModModifierIds {
    public static final ModifierId Repulsive = id("repulsive");
    public static final ModifierId BaneOfPigs = id("bane_of_pigs");
    public static final ModifierId Antibrute = id("antibrute");

    public static final ModifierId DensityAdvanced = id("density_advanced");
    public static final ModifierId RepeatingAdvanced = id("repeating_advanced");
    public static final ModifierId CounterAdvanced = id("counter_advanced");
    public static final ModifierId Seeking = id("seeking");

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
    public static final ModifierId CurioLevel = id("curio_level");
    public static final ModifierId SculkHeal = id("sculk_heal");

    private ModModifierIds() {}
    private static ModifierId id(String name) {
        return new ModifierId(TinkersThinking.MODID, name);
    }
}
