package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public enum SculkStruggleModule implements ModifierModule, MeleeHitModifierHook, ModifierUtils {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<SculkStruggleModule>defaultHooks(ModifierHooks.MELEE_HIT);
    public static final RecordLoadable<SculkStruggleModule> LOADER = new SingletonLoader<>(INSTANCE);
    public @NotNull RecordLoadable<SculkStruggleModule> getLoader() {
        return LOADER;
    }
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity attacker = context.getAttacker();
        LivingEntity living = context.getLivingTarget();
        if (!context.isExtraAttack()&&attacker.hasEffect(ModEffects.sculk_power.get())&&living!=null&&living.isDeadOrDying()) {
            int x = 0;
            int y = 0;
            if (attacker.hasEffect(ModEffects.last_effort.get())){
                x = attacker.getEffect(ModEffects.last_effort.get()).getDuration();
                y = attacker.getEffect(ModEffects.last_effort.get()).getAmplifier();
            }
            addEffect(attacker,ModEffects.last_effort.get(),100 * modifier.getLevel()+x,y);
        }
    }
}
