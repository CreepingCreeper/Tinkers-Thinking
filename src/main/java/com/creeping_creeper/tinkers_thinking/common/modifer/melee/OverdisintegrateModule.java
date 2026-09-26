package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingInt;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record OverdisintegrateModule(LevelingInt max) implements ModifierModule, MeleeHitModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<OverdisintegrateModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT);
        LOADER = RecordLoadable.create(LevelingInt.LOADABLE.directField(OverdisintegrateModule::max), OverdisintegrateModule::new);
    }

    public RecordLoadable<OverdisintegrateModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity living = context.getAttacker();
        if (!context.isExtraAttack() && context.isFullyCharged() && living.isAlive()) {
            LivingEntity target = context.getLivingTarget();
            int x = Math.min(max.compute(modifier), OverslimeModule.INSTANCE.getAmount(tool));
            int y;
            float z = living.getHealth();
            float w = living.getMaxHealth();
            if (z > w * 0.5){
                y = 0;
            } else y = z > w * 0.25 ? 1 : 3;
            if (target != null && x > 0) {
                OverslimeModule.INSTANCE.removeAmount(tool, modifier, x);
                target.addEffect(new MobEffectInstance(ModEffects.disintegration.get(),20 * x, y));
                target.setLastHurtMob(context.getAttacker());
            }
        }
    }
}
