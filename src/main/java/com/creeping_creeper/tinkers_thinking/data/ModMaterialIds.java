package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class ModMaterialIds {
    public static final MaterialId electrical_steel = id("electrical_steel");
    public static final MaterialId bacium = id("bacium");
    private static MaterialId id(String name) {
        return new MaterialId(TinkersThinking.MODID, name);
    }
}
