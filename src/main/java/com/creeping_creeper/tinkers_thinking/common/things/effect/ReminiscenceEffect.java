package com.creeping_creeper.tinkers_thinking.common.things.effect;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.library.tools.capability.PersistentDataCapability;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;


import static net.minecraft.nbt.Tag.TAG_COMPOUND;

public class ReminiscenceEffect extends TinkerEffect {
    private static final ResourceLocation KEY = new ResourceLocation("tinkers_thinking:n");
    public ReminiscenceEffect(net.minecraft.world.effect.MobEffectCategory typeIn, boolean show) {
        super(typeIn, 0xb83dba, show);
        MinecraftForge.EVENT_BUS.addListener(this::onEffectAdded);
    }
    private void onEffectAdded(MobEffectEvent.Added event) {
        // store entity's current position when the effect is added
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide() && event.getOldEffectInstance() == null && event.getEffectInstance().getEffect() == this) {
            ModDataNBT data = PersistentDataCapability.getOrWarn(entity);
            CompoundTag health = new CompoundTag();
            health.putFloat("health",entity.getHealth());
            data.put(KEY, health);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration == 1;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        ModDataNBT data = PersistentDataCapability.getOrWarn(living);
        if (data.contains(KEY, TAG_COMPOUND)) {
            CompoundTag health = data.getCompound(KEY);
            if (living.isAlive()){
                living.setHealth(health.getFloat("health"));
            }
        }
    }
}
