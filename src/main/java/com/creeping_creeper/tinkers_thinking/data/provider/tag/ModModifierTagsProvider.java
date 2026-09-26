package com.creeping_creeper.tinkers_thinking.data.provider.tag;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierTagProvider;

public class ModModifierTagsProvider extends AbstractModifierTagProvider {
    public ModModifierTagsProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, TinkersThinking.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TinkerTags.Modifiers.BLOCK_WHILE_CHARGING).add(ModModifierIds.Sprinting);
        tag(TinkerTags.Modifiers.CHARGE_EMPTY_BOW_WITH_DRAWTIME).add(ModModifierIds.Sprinting);
        tag(TinkerTags.Modifiers.DRILL_ATTACKS).add(ModModifierIds.Sprinting);
        tag(TinkerTags.Modifiers.OVERSLIME_FRIEND).add(ModModifierIds.Overcharge, ModModifierIds.Overdisintegrate, ModModifierIds.Overdose, ModModifierIds.Overfreeze, ModModifierIds.Overeat);
    }

    @Override
    public @NotNull String getName() {
        return "Slime World Modifier Tag Provider";
    }
}
