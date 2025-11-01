package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class ModModifierIds {
    public static final ModifierId DensityAdvanced = id("density_advanced");
    public static final ModifierId RepeatingAdvanced = id("repeating_advanced");

    private ModModifierIds() {}
    private static ModifierId id(String name) {
        return new ModifierId(TinkersThinking.MODID, name);
    }
}
