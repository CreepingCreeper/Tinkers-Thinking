package com.creeping_creeper.tinkers_thinking.common.library;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Objects;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnDeath implements ModifierUtils {
    @SubscribeEvent
    public void onLivingDying(LivingDeathEvent event){
        LivingEntity living = event.getEntity();
        DamageSource source = event.getSource();
        if (living instanceof Player player && !source.is(DamageTypeTags.BYPASSES_RESISTANCE)) {
            if (player.hasEffect(ModEffects.last_effort.get())) {
                event.setCanceled(true);
                player.setHealth(1);
                block(living);
                if (player.getEffect(ModEffects.last_effort.get()).getAmplifier() == 0) {
                    int x = Objects.requireNonNull(player.getEffect(ModEffects.last_effort.get())).getDuration();
                    player.addEffect(new MobEffectInstance(ModEffects.last_effort.get(), x, 1));
                }
            } else {
                Optional<TinkerDataCapability.Holder> dataCap = living.getCapability(TinkerDataCapability.CAPABILITY).resolve();
                dataCap.ifPresent(data -> {
                    int level = data.get(ModDataKeys.SculkStruggle, 0);
                    if (level > 0&& player.hasEffect(ModEffects.sculk_power.get())) {
                        event.setCanceled(true);
                        player.setHealth(1);
                        block(living);
                        player.addEffect(new MobEffectInstance(ModEffects.last_effort.get(), level * 60 + 180, 1));
                        Minecraft.getInstance().gameRenderer.displayItemActivation(ModCommonItems.warden_steel.getIngot().getDefaultInstance());
                    }
                });
            }
        }
    }
}
