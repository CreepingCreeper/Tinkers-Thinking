package com.creeping_creeper.tinkers_thinking.common.things.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

import java.util.Objects;
import java.util.UUID;

public class DisintegrationEffect extends NoMilkEffect {
    public DisintegrationEffect(net.minecraft.world.effect.MobEffectCategory typeIn, int color, boolean show) {
        super(typeIn, color, show);
    }
    @Override
    public boolean isDurationEffectTick(int tick, int amplifier) {
        return tick > 0 && tick % 20 == 0;
    }
    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        LivingEntity lastAttacker = living.getLastHurtMob();
        DamageSource source = TinkerDamageTypes.source(living.level().registryAccess(), TinkerDamageTypes.BLEEDING, lastAttacker);
        ToolAttackUtil.attackEntitySecondary(source, (float) (living.getMaxHealth()*0.01*(amplifier+1)), living, living, true);
    }
}
