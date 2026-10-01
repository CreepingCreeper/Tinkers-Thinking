package com.creeping_creeper.tinkers_thinking.common.compat;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public enum FakeExtraMaterialStat implements IMaterialStats {
    CURIO_EXTRA("curio_extra");

    private final MaterialStatType<FakeExtraMaterialStat> type;
    FakeExtraMaterialStat(String name) {
        this.type = MaterialStatType.singleton(new MaterialStatsId(ResourceLocation.fromNamespaceAndPath("tinkers_ingenuity", name)), this);
    }

    @Override
    public MaterialStatType<?> getType() {
        return type;
    }

    @Override
    public List<Component> getLocalizedInfo() {
        return List.of();
    }

    @Override
    public List<Component> getLocalizedDescriptions() {
        return List.of();
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {

    }
}
