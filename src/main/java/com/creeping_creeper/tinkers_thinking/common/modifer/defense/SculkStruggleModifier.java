package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class SculkStruggleModifier extends Modifier implements MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,  ModifierHooks.MELEE_HIT);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack()) {
            LivingEntity attacker = context.getAttacker();
            int x = 0;
            int y = 0;
            if (attacker.hasEffect(ModEffects.last_effort.get())){
                x = attacker.getEffect(ModEffects.last_effort.get()).getDuration();
                y = attacker.getEffect(ModEffects.last_effort.get()).getAmplifier();
            }
            attacker.addEffect(new MobEffectInstance(ModEffects.last_effort.get(),80 * modifier.getLevel()+x,y,true,true));
        }
    }
}
