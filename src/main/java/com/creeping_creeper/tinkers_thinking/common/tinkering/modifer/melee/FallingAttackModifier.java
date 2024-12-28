package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.ModModifiers;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class FallingAttackModifier extends Modifier implements MeleeDamageModifierHook, MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE,ModifierHooks.MELEE_HIT);
    }
    @Override
    public float getMeleeDamage(IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        float h = context.getAttacker().fallDistance;
        int d =3+tool.getModifierLevel(ModModifiers.Density.getId());
        if (h>1.5){
            damage += d*h;
        }
        return damage;
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack() && context.isFullyCharged()) {
            context.getAttacker().resetFallDistance();
                TinkerModifiers.repulsiveEffect.get().apply(context.getAttacker(), 50, 2);
        }
    }
}
