package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.shared.TinkerEffects;

import java.util.Objects;

public class FallingAttackModifier extends Modifier implements MeleeDamageModifierHook, MonsterMeleeHitModifierHook.RedirectAfter{
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.MONSTER_MELEE_DAMAGE);
    }
    @Override
    public float getMeleeDamage(IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        float h = context.getAttacker().fallDistance;
        int p =3+tool.getModifierLevel(ModModifierIds.DensityAdvanced);
        float x = (float) (Objects.requireNonNull(context.getAttacker().getAttribute(ForgeMod.ENTITY_GRAVITY.get())).getValue()/0.08);
        if (!context.isExtraAttack() && context.isFullyCharged()&&x>0) {
            damage += (float) ((Math.min(6,2*h)+Math.min(8,h)+(1+0.5*p)*h)*x);
            context.getAttacker().resetFallDistance();
            if (context.getAttacker().isFallFlying()) {
                TinkerEffects.repulsive.get().apply(context.getAttacker(), 50, h > 5 ? 3 : 1);
            }
        }
        return damage;
    }
}
