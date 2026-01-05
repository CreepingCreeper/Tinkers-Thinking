package com.creeping_creeper.tinkers_thinking.common.integration;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.creeping_creeper.tinkers_thinking.common.register.ModModifiers;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import com.github.tartaricacid.touhoulittlemaid.api.event.MaidPickupEvent;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Optional;

public class TouhouLittleMaidPlugin implements ModifierUtils {
    @SubscribeEvent
    public void onMaidPickupXp(MaidPickupEvent.ExperienceResult event) {
        ExperienceOrb exp = event.getExperienceOrb();
        EntityMaid maid = event.getMaid();
        Optional<TinkerDataCapability.Holder> dataCap = maid.getCapability(TinkerDataCapability.CAPABILITY).resolve();
        dataCap.ifPresent(data -> {
            if (data.get(ModDataKeys.SculkCatalyse, 0) > 0) {
                int time = exp.getValue() * 60;
                if (maid.hasEffect(ModEffects.sculk_power.get())) {
                    time = time + maid.getEffect(ModEffects.sculk_power.get()).getDuration();
                }
                addEffect(maid, ModEffects.sculk_power.get(), time);
                event.setCanceled(true);
                exp.discard();
            }
        });
    }
}
