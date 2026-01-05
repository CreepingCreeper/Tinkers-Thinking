package com.creeping_creeper.tinkers_thinking.common.integration;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.humanoid.HumanoidGolemEntity;
import dev.xkmc.modulargolems.events.event.GolemHandleExpEvent;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Optional;

public class ModularGolemsPlugin implements ModifierUtils {
    @SubscribeEvent
    public void onGolemPickupXp(GolemHandleExpEvent event) {
        ExperienceOrb exp = event.getOrb();
        AbstractGolemEntity<?, ?> golem = event.getEntity();
        Optional<TinkerDataCapability.Holder> dataCap = golem.getCapability(TinkerDataCapability.CAPABILITY).resolve();
        dataCap.ifPresent(data -> {
            if (golem instanceof HumanoidGolemEntity && data.get(ModDataKeys.SculkCatalyse, 0) > 0) {
                int time = exp.getValue() * 60;
                if (golem.hasEffect(ModEffects.sculk_power.get())) {
                    time = time + golem.getEffect(ModEffects.sculk_power.get()).getDuration();
                }
                addEffect(golem, ModEffects.sculk_power.get(), time);
                exp.discard();
            }
        });
    }
}
