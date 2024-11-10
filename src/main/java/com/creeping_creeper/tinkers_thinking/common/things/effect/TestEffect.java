package com.creeping_creeper.tinkers_thinking.common.things.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

import java.util.UUID;

public class TestEffect extends NoMilkEffect {
    private static final UUID ATTRIBUTE_BONUS = UUID.fromString("2307DE5E-7CE8-4030-940E-514C1F170001");
    public TestEffect(MobEffectCategory typeIn, int color, boolean show) {
        super(typeIn, color, show);
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration == 1;
    }
    
    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        AttributeInstance attribute = living.getAttribute(Attributes.MAX_HEALTH);
        if (attribute != null && attribute.getModifier(ATTRIBUTE_BONUS) != null) {
            attribute.removeModifier(ATTRIBUTE_BONUS);
            if (living.getHealth()>living.getMaxHealth()){
                living.setHealth(living.getMaxHealth());
            }
        }
    }
}
