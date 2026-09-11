package com.creeping_creeper.tinkers_thinking.data.provider.tinkering;

import com.creeping_creeper.tinkers_thinking.common.modifer.misc.SlingSprintingModule;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.data.PackOutput;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.modules.util.ModifierCondition;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;

public class ModModifierProvider extends AbstractModifierProvider {
    public ModModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {
        buildModifier(ModModifierIds.Sprinting).levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL).addModule(new SlingSprintingModule(new LevelingValue(3.5F, 3), 1.5f, LivingEntityPredicate.ANY, ModifierCondition.ANY_TOOL));

    }

    @Override
    public String getName() {
        return "TiT Modifier Provider";
    }
}
