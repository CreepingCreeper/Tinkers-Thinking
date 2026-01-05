package com.creeping_creeper.tinkers_thinking.common.things.effect;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.modifer.defense.SculkBreedModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.harvest.SculkBoostModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.harvest.SculkBoostModule;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.particles.SculkChargeParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

import java.util.Optional;
import java.util.UUID;

import static slimeknights.tconstruct.TConstruct.RANDOM;

public class SculkPowerEffect extends NoMilkEffect implements ModifierUtils {
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
            if (event.getOldEffectInstance() == null){
                Optional<TinkerDataCapability.Holder> dataCap = entity.getCapability(TinkerDataCapability.CAPABILITY).resolve();
                dataCap.ifPresent(data -> {
                    int x = data.get(ModDataKeys.SculkBoost, 0);
                    if (x>0) entity.getAttribute(Attributes.ARMOR_TOUGHNESS).addPermanentModifier(new AttributeModifier(SculkBoostModule.ATTRIBUTE_BONUS, "tinkers_thinking.modifier.sculk_boost", x * 0.2f,
                            AttributeModifier.Operation.MULTIPLY_BASE));
                });
            }
            if (!level.isClientSide() && (event.getOldEffectInstance() == null || effect.getDuration() > event.getOldEffectInstance().getDuration() + 2)){
                level.playSound(null, event.getEntity().getOnPos().above(), SoundEvents.SCULK_BLOCK_CHARGE, SoundSource.PLAYERS, 1.0F, 1.6F + RANDOM.nextFloat() * 0.4F);
                particles(level, entity, new SculkChargeParticleOptions(0), 4);
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
    public void applyEffectTick(LivingEntity living, int amplifier) {
        removeAttribute(living);
    }
    private void removeAttribute(LivingEntity living){
        AttributeInstance attribute1 = living.getAttribute(Attributes.MAX_HEALTH);
        AttributeInstance attribute2 = living.getAttribute(Attributes.ARMOR_TOUGHNESS);
        if (attribute1 != null && attribute1.getModifier(SculkBreedModifier.ATTRIBUTE_BONUS) != null) {
            attribute1.removeModifier(SculkBreedModifier.ATTRIBUTE_BONUS);
            if (living.getHealth()>living.getMaxHealth()){
                living.setHealth(living.getMaxHealth());
            }
        }
        if (attribute2 != null && attribute2.getModifier(SculkBoostModule.ATTRIBUTE_BONUS) != null) {
            attribute2.removeModifier(SculkBoostModule.ATTRIBUTE_BONUS);
        }
    }
}
