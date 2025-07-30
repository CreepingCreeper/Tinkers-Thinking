package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.modifer.ModModifiers;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.shared.TinkerEffects;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.Objects;

public class FallingAttackModifier extends Modifier implements MeleeDamageModifierHook{
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE);
    }
    @Override
    public float getMeleeDamage(IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        float h = context.getAttacker().fallDistance;
        int p =3+tool.getModifierLevel(ModModifiers.DensityAdvanced.getId());
        float x = (float) (Objects.requireNonNull(context.getAttacker().getAttribute(ForgeMod.ENTITY_GRAVITY.get())).getValue()/0.08);
        if (!context.isExtraAttack() && context.isFullyCharged()&&x>0) {
            damage += (Math.min(6,2*h)+Math.min(8,h)+(1+0.5*p)*h)*x;
            context.getAttacker().resetFallDistance();
            if (context.getAttacker().isFallFlying()) {
                TinkerEffects.repulsive.get().apply(context.getAttacker(), 50, h > 5 ? 3 : 1);
            }
        }
        return damage;
    }
}
