package com.creeping_creeper.tinkers_thinking.common.things.effect;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.core.particles.SculkChargeParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class SculkPowerEffect extends NoMilkEffect {
    public SculkPowerEffect(MobEffectCategory typeIn, int color, boolean show) {
        super(typeIn, color, show);
        MinecraftForge.EVENT_BUS.addListener(this::onEffectAdded);
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

}
