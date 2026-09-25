package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record SculkStruggleModule(LevelingValue time) implements ModifierModule, MeleeHitModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SculkStruggleModule> LOADER;

    public RecordLoadable<SculkStruggleModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SculkStruggleModule::time), SculkStruggleModule::new);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity attacker = context.getAttacker();
        LivingEntity living = context.getLivingTarget();
        if (!context.isExtraAttack() && attacker.hasEffect(ModEffects.sculk_power.get()) && living!=null && living.isDeadOrDying()) {
            int x = 0;
            int y = 0;
            if (attacker.hasEffect(ModEffects.last_effort.get())){
                x = attacker.getEffect(ModEffects.last_effort.get()).getDuration();
                y = attacker.getEffect(ModEffects.last_effort.get()).getAmplifier();
            }
            ModifierUtils.addEffect(attacker,ModEffects.last_effort.get(), (int) (time.compute(modifier) + x),y);
        }
    }
}
