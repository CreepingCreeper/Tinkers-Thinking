package com.creeping_creeper.tinkers_thinking.data.provider.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider extends LootTableProvider {
    private static final Set<ResourceLocation> REQUIRED_TABLES = Set.of();

    public ModLootTableProvider(PackOutput packOutput) {
        super(packOutput, REQUIRED_TABLES, List.of(
                new SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)
        ));
    }

}
