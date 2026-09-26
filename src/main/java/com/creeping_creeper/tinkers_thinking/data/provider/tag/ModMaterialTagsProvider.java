package com.creeping_creeper.tinkers_thinking.data.provider.tag;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.tinkering.AbstractMaterialTagProvider;

public class ModMaterialTagsProvider extends AbstractMaterialTagProvider {
    public ModMaterialTagsProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, TinkersThinking.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
       // tag(TinkerTags.Materials.HARVEST).add(ModMaterialIds.slimeBronze);
       // tag(TinkerTags.Materials.HEAVY).add(ModMaterialIds.slimeBronze);

    }

    @Override
    public @NotNull String getName() {
        return "Slime World Material Tag Provider";
    }
}
