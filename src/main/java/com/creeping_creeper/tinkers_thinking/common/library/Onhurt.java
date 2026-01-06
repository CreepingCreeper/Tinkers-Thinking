package com.creeping_creeper.tinkers_thinking.common.library;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Onhurt {
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        LivingEntity living = event.getEntity();
        Level level = living.level();
        DamageSource source = event.getSource();
        if (living.hasEffect(ModEffects.cataclysm.get())){
            if (source.is(DamageTypeTags.IS_FIRE)&&!level.isClientSide){
                level.explode(living, living.getX(), living.getY(), living.getZ(), Objects.requireNonNull(living.getEffect(ModEffects.cataclysm.get())).getAmplifier() + 1, Level.ExplosionInteraction.MOB);
                living.removeEffect(ModEffects.cataclysm.get());
            }
            if (source.is(DamageTypes.FREEZE)){
                living.removeEffect(ModEffects.cataclysm.get());
            }
        }
        if (living.hasEffect(ModEffects.disarm.get())){
            living.removeEffect(ModEffects.disarm.get());
        }
    }
}