package com.creeping_creeper.tinkers_thinking.common.things.effect;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.modifer.defense.SculkBreedModule;
import net.minecraft.core.particles.SculkChargeParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class SculkPowerEffect extends NoMilkEffect {
    public SculkPowerEffect(MobEffectCategory typeIn, int color, boolean show) {
        super(typeIn, color, show);
        MinecraftForge.EVENT_BUS.addListener(this::onEffectAdded);
        MinecraftForge.EVENT_BUS.addListener(this::onEffectRemove);
    }

    private void onEffectAdded(MobEffectEvent.Added event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        MobEffectInstance effect = event.getEffectInstance();
        if (effect.getEffect() == this){
            if (!level.isClientSide() && (event.getOldEffectInstance() == null || effect.getDuration() > event.getOldEffectInstance().getDuration() + 2)){
                level.playSound(null, event.getEntity().getOnPos().above(), SoundEvents.SCULK_BLOCK_CHARGE, SoundSource.PLAYERS, 1.0F, 1.6F + Modifier.RANDOM.nextFloat() * 0.4F);
                ModifierUtils.particles(level, entity, new SculkChargeParticleOptions(0), 4);
            }
        }
    }

    private void onEffectRemove(MobEffectEvent.Remove event) {
        if (event.getEffect() == this) removeAttribute(event.getEntity());
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration == 1;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity living, int amplifier) {
        removeAttribute(living);
    }

    private void removeAttribute(LivingEntity living){
        AttributeInstance attribute1 = living.getAttribute(Attributes.MAX_HEALTH);
        if (attribute1 != null && attribute1.getModifier(SculkBreedModule.ATTRIBUTE_BONUS) != null) {
            attribute1.removeModifier(SculkBreedModule.ATTRIBUTE_BONUS);
            if (living.getHealth()>living.getMaxHealth()){
                living.setHealth(living.getMaxHealth());
            }
        }
    }
}
