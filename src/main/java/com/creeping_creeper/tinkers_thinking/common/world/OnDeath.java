package com.creeping_creeper.tinkers_thinking.common.world;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.library.FindBySlot;
import com.creeping_creeper.tinkers_thinking.common.modifer.ModModifiers;
import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.TinkerDamageTypes;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnDeath implements FindBySlot {
    @SubscribeEvent
    public void onLivingDying(LivingDeathEvent event){
        LivingEntity living = event.getEntity();
        if (living instanceof Player player&&!event.getSource().is(DamageTypeTags.BYPASSES_RESISTANCE)&&!event.getSource().is(TinkerDamageTypes.BLEEDING)) {
            int modifierLevel = getItemModifierLevel(player, ModModifiers.SculkStruggle.getId());
            if (player.hasEffect(ModEffects.sculk_power.get())&&modifierLevel>0&&!player.hasEffect(ModEffects.last_effort.get())) {
                event.setCanceled(true);
                player.setHealth(1);
                player.addEffect(new MobEffectInstance(ModEffects.last_effort.get(), modifierLevel * 30 + 90, 1));
            }
            if (player.hasEffect(ModEffects.last_effort.get())) {
                event.setCanceled(true);
                player.setHealth(1);
                if (Objects.requireNonNull(player.getEffect(ModEffects.last_effort.get())).getAmplifier() == 0) {
                    int x = Objects.requireNonNull(player.getEffect(ModEffects.last_effort.get())).getDuration();
                    player.addEffect(new MobEffectInstance(ModEffects.last_effort.get(), x, 1));
                }
            }
        }
    }
}
