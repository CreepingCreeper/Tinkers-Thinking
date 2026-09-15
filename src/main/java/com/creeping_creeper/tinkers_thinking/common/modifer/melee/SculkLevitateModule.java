package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record SculkLevitateModule(LevelingValue amount) implements ModifierModule, MeleeHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SculkLevitateModule> LOADER;

    public RecordLoadable<SculkLevitateModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack() && context.isFullyCharged()) {
            LivingEntity attacker = context.getAttacker();
            LivingEntity target = context.getLivingTarget();
            if (attacker.hasEffect(ModEffects.sculk_power.get())&&target!=null&&!target.hasEffect(ModEffects.modifier_immune.get())) {
                ModifierUtils.addEffect(target,ModEffects.weightless.get(),120,2);
                ModifierUtils.addEffect(target,ModEffects.modifier_immune.get(),(int) (amount.eachLevel()/modifier.getLevel()));
          }
        }
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SculkLevitateModule::amount), SculkLevitateModule::new);
    }
}
