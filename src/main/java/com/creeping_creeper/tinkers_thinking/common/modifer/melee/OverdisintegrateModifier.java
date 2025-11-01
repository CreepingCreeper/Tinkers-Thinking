package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class OverdisintegrateModifier extends Modifier implements MeleeHitModifierHook{
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity living = context.getAttacker();
        if (!context.isExtraAttack() && context.isFullyCharged()&&living.isAlive()) {
            LivingEntity target = context.getLivingTarget();
            int x = Math.min(4*modifier.getLevel(),OverslimeModule.INSTANCE.getAmount(tool));
            int y;
            float z = living.getHealth();
            float w = living.getMaxHealth();
            if (z>w*0.5){
                y = 0;
            } else y = z>w*0.25?1:3;
            if (target != null&&x>0) {
                OverslimeModule.INSTANCE.removeAmount(tool, modifier,x);
                target.addEffect(new MobEffectInstance(ModEffects.disintegration.get(),20*x,y));
                target.setLastHurtMob(context.getAttacker());
            }
        }
    }
}
