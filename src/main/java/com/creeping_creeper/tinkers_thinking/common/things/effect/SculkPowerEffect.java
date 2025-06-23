package com.creeping_creeper.tinkers_thinking.common.things.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

import java.util.UUID;

public class SculkPowerEffect extends NoMilkEffect {
    private static final UUID ATTRIBUTE_SculkBreed = UUID.fromString("2307DE5E-7CE8-4030-940E-514C1F170001");
    public SculkPowerEffect(MobEffectCategory typeIn, int color, boolean show) {
        super(typeIn, color, show);
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration == 1;
    }
    
    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        AttributeInstance attribute1 = living.getAttribute(Attributes.MAX_HEALTH);
        if (attribute1 != null && attribute1.getModifier(ATTRIBUTE_SculkBreed) != null) {
            attribute1.removeModifier(ATTRIBUTE_SculkBreed);
            if (living.getHealth()>living.getMaxHealth()){
                living.setHealth(living.getMaxHealth());
            }
        }
    }
}
