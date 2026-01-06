package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModMaterialIds {
    public static final MaterialId electrical_steel = id("electrical_steel");
    private static MaterialId id(String name) {
        return new MaterialId(TinkersThinking.MODID, name);
    }
}
