package com.creeping_creeper.tinkers_thinking.data.provider.tag;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.data.ModDamageTypes;
import com.creeping_creeper.tinkers_thinking.data.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerDamageTypes;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagsProvider extends DamageTypeTagsProvider {
    public ModDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TinkersThinking.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider lookupProvider) {
        //vanilla
        tag(DamageTypeTags.BYPASSES_ARMOR).add(ModDamageTypes.last_effort);
        tag(DamageTypeTags.BYPASSES_RESISTANCE).add(ModDamageTypes.last_effort);
        //self
        tag(ModTags.DamageTypes.DROP_ASHES).add(DamageTypes.FREEZE, DamageTypes.DROWN, TinkerDamageTypes.FLUID_COLD.melee(), TinkerDamageTypes.FLUID_COLD.ranged(), TinkerDamageTypes.WATER.melee(),
                TinkerDamageTypes.WATER.ranged());
    }
}
