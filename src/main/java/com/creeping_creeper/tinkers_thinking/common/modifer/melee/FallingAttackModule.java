package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraftforge.common.ForgeMod;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;
import java.util.Objects;

public record FallingAttackModule(float rate) implements ModifierModule, MeleeDamageModifierHook, MonsterMeleeHitModifierHook.RedirectAfter {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<FallingAttackModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_DAMAGE, ModifierHooks.MONSTER_MELEE_DAMAGE);
        LOADER = RecordLoadable.create(FloatLoadable.FROM_ZERO.requiredField("rate", FallingAttackModule::rate), FallingAttackModule::new);
    }

    public RecordLoadable<FallingAttackModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        float h = context.getAttacker().fallDistance;
        int p = 3 + tool.getModifierLevel(ModModifierIds.DensityAdvanced);
        float x = (float) (Objects.requireNonNull(context.getAttacker().getAttribute(ForgeMod.ENTITY_GRAVITY.get())).getValue() / 0.08);
        if (!context.isExtraAttack() && context.isFullyCharged() && x > 0) {
            damage += (Math.min(6, 2 * h) + Math.min(8, h) + (1 + rate * p) * h) * x;
            context.getAttacker().resetFallDistance();
        }
        return damage;
    }
}
