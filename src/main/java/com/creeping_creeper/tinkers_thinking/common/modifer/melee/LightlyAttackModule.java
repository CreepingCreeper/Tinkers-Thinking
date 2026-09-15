package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public record LightlyAttackModule(LevelingValue amount) implements ModifierModule, MeleeHitModifierHook, ConditionalStatModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<LightlyAttackModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT,ModifierHooks.CONDITIONAL_STAT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(LightlyAttackModule::amount),LightlyAttackModule::new);
    }

    public RecordLoadable<LightlyAttackModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    private static boolean isEmpty(LivingEntity living, int stack){
        return living.getSlot(stack).get().isEmpty();
    }
    private static boolean isAllEmpty(LivingEntity living){
        int x = 0;
        for (int i=0;i<9;i++){
            if (!isEmpty(living,i)) {
                x++;
            }
        }
        return x < 2;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack()) {
            if (isAllEmpty(context.getAttacker())) {
                ModifierUtils.addEffect(context.getAttacker(), ModEffects.quick_attack.get(), 60, modifier.getLevel()*3-1);
            }
        }
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
          if (isAllEmpty(living)) {
              if (stat == ToolStats.DRAW_SPEED) {
                  return baseValue * (1 + (amount.compute(modifier)));
              }
          }
        return baseValue;
    }
}
