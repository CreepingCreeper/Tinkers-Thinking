package com.creeping_creeper.tinkers_thinking.common.things.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class StrengthResetEffect extends NoMilkEffect {
    public StrengthResetEffect(MobEffectCategory typeIn, int color, boolean show) {
        super(typeIn, color, show);
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration == 1;
    }
    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        living.attackStrengthTicker += 2000;
    }
}
