package com.creeping_creeper.tinkers_thinking.common.library.variable;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import slimeknights.mantle.data.loadable.primitive.BooleanLoadable;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;

public record FoodDataVariable(boolean saturation, float fallback) implements ToolVariable {
    public static final RecordLoadable<FoodDataVariable> LOADER = RecordLoadable.create(
            BooleanLoadable.INSTANCE.requiredField("saturation", FoodDataVariable::saturation),
            FloatLoadable.ANY.requiredField("fallback", FoodDataVariable::fallback),
            FoodDataVariable::new);


    @Override
    public float getValue(IToolStackView tool) {
        return fallback;
    }

    @Override
    public float getValue(IToolStackView tool, EquipmentContext context, @Nullable LivingEntity target, @Nullable DamageSource source, @Nullable EquipmentSlot slotType) {
        if (context != null) {
            Entity entity = context.getEntity();
            if (entity instanceof Player player){
                FoodData foodData = player.getFoodData();
                return saturation ? foodData.getSaturationLevel() : foodData.getFoodLevel();
            }
        }
        return fallback;
    }

    @Override
    public RecordLoadable<? extends ToolVariable> getLoader() {
        return LOADER;
    }
}
