package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModMaterialIds {
    private static MaterialId id(String name) {
        return new MaterialId(TinkersThinking.MODID, name);
    }
}
