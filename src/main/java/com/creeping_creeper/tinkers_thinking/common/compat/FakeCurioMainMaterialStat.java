package com.creeping_creeper.tinkers_thinking.common.compat;

import net.minecraft.network.chat.Component;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.materials.stats.IRepairableMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public record FakeCurioMainMaterialStat(float curio_movement_speed, float curio_max_health, float curio_armor, float curio_melee_attack, float curio_projectile_attack) implements IRepairableMaterialStats {
    public static final MaterialStatsId ID = new MaterialStatsId("tinkers_ingenuity", "curio_main");
    public static final MaterialStatType<FakeCurioMainMaterialStat> TYPE = new MaterialStatType(ID, new FakeCurioMainMaterialStat(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), RecordLoadable.create(FloatLoadable.ANY.defaultField("curio_movement_speed", 0.0F, true, FakeCurioMainMaterialStat::curio_movement_speed), FloatLoadable.ANY.defaultField("curio_max_health", 0.0F, true, FakeCurioMainMaterialStat::curio_max_health), FloatLoadable.ANY.defaultField("curio_armor", 0.0F, true, FakeCurioMainMaterialStat::curio_armor), FloatLoadable.ANY.defaultField("curio_melee_attack", 0.0F, true, FakeCurioMainMaterialStat::curio_melee_attack), FloatLoadable.ANY.defaultField("curio_projectile_attack", 0.0F, true, FakeCurioMainMaterialStat::curio_projectile_attack), FakeCurioMainMaterialStat::new));

    @Override
    public int durability() {
        return 0;
    }

    @Override
    public MaterialStatType<?> getType() {
        return TYPE;
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
    public MaterialStatsId getIdentifier() {
        return ID;
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {

    }
}
