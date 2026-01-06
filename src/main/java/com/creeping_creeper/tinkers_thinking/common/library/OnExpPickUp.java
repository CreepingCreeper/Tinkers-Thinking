package com.creeping_creeper.tinkers_thinking.common.library;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Optional;

public class OnExpPickUp implements ModifierUtils {
    @SubscribeEvent
    public void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
        ExperienceOrb exp = event.getOrb();
        Player player = event.getEntity();
        Optional<TinkerDataCapability.Holder> dataCap = player.getCapability(TinkerDataCapability.CAPABILITY).resolve();
        dataCap.ifPresent(data -> {
            if (data.get(ModDataKeys.SculkCatalyse, 0) > 0) {
                int time = exp.getValue() * 60;
                if (player.hasEffect(ModEffects.sculk_power.get())) {
                    time = time + player.getEffect(ModEffects.sculk_power.get()).getDuration() ;
                }
                addEffect(player, ModEffects.sculk_power.get(), time);
                event.setCanceled(true);
                exp.discard();
            }
        });
    }
}

