package com.creeping_creeper.tinkers_thinking.common.things.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class LastEffortEffect extends NoMilkEffect {
    public LastEffortEffect(MobEffectCategory typeIn, int color, boolean show) {
        super(typeIn, color, show);
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration == 1 && amplifier == 1;
    }
    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        Level level = living.level();
        if (!level.isClientSide) {
            living.hurt(TinkerDamageTypes.source(level.registryAccess(), TinkerDamageTypes.BLEEDING), 99999);
        }
    }
}
