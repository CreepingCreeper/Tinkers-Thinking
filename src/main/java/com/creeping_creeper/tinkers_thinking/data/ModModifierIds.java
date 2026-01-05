package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class ModModifierIds {
    public static final ModifierId SculkCatalyse = id("sculk_catalyse");
    public static final ModifierId DensityAdvanced = id("density_advanced");
    public static final ModifierId RepeatingAdvanced = id("repeating_advanced");
    public static final ModifierId CounterAdvanced = id("counter_advanced");
    public static final ModifierId Seeking = id("seeking");

    private ModModifierIds() {}
    private static ModifierId id(String name) {
        return new ModifierId(TinkersThinking.MODID, name);
    }
}
