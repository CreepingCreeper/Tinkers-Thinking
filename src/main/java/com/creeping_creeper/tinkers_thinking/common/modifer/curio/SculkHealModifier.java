package com.creeping_creeper.tinkers_thinking.common.modifer.curio;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.xiaoyue.tinkers_ingenuity.content.shared.holder.CurioStackView;
import com.xiaoyue.tinkers_ingenuity.content.shared.hooks.specail.TinkersCurioModifierHook;
import com.xiaoyue.tinkers_ingenuity.register.TIHooks;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;

public class SculkHealModifier extends Modifier implements TinkersCurioModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TIHooks.TINKERS_CURIO);
    }
    @Override
    public void onCurioTick(CurioStackView curio, int level, LivingEntity entity) {
        if (entity.tickCount % 60/level == 0 && entity.hasEffect(ModEffects.sculk_power.get()) && entity.getHealth() < entity.getMaxHealth()) {
            entity.heal(1);
        }
    }
}
