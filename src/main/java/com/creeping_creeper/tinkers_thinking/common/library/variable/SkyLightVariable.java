package com.creeping_creeper.tinkers_thinking.common.library.variable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.TinkerLoadables;
import slimeknights.tconstruct.library.json.variable.entity.EntityLightVariable;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;

public record SkyLightVariable(@Nullable LightLayer lightLayer, float fallback) implements ToolVariable {
    public static final RecordLoadable<SkyLightVariable> LOADER = RecordLoadable.create(
            TinkerLoadables.LIGHT_LAYER.nullableField("light_layer", SkyLightVariable::lightLayer),
            FloatLoadable.ANY.requiredField("fallback", SkyLightVariable::fallback),
            SkyLightVariable::new);


    @Override
    public float getValue(IToolStackView tool) {
        return fallback;
    }

    @Override
    public float getValue(IToolStackView tool, EquipmentContext context, @Nullable LivingEntity target, @Nullable DamageSource source, @Nullable EquipmentSlot slotType) {
        if (context != null) {
            Entity entity = context.getEntity();
            BlockPos pos = entity.blockPosition();
            return EntityLightVariable.getLightLevel(context.getLevel(), LightLayer.SKY, pos);
        }
        return fallback;
    }

    @Override
    public float getValue(IToolStackView tool, @Nullable PlayerEvent.BreakSpeed event, @Nullable Player player, @Nullable Direction sideHit) {
        BlockPos pos;
        if (player != null) {
            pos = player.blockPosition();
            return EntityLightVariable.getLightLevel(player.level(), LightLayer.SKY, pos);
        }
        return fallback;
    }

    @Override
    public RecordLoadable<? extends ToolVariable> getLoader() {
        return LOADER;
    }
}
