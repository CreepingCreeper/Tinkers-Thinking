package com.creeping_creeper.tinkers_thinking.common.modifer.curio;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.xiaoyue.tinkers_ingenuity.content.shared.holder.CurioStackView;
import com.xiaoyue.tinkers_ingenuity.content.shared.hooks.specail.TinkersCurioModifierHook;
import com.xiaoyue.tinkers_ingenuity.register.TIHooks;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;

import java.util.List;

public record SculkHealModule(LevelingValue amount) implements ModifierModule, TinkersCurioModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SculkHealModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(TIHooks.TINKERS_CURIO);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SculkHealModule::amount), SculkHealModule::new);
    }

    public RecordLoadable<SculkHealModule> getLoader() {
    return LOADER;
}

    public List<ModuleHook<?>> getDefaultHooks() {
    return DEFAULT_HOOKS;
}


    @Override
    public void onCurioTick(CurioStackView curio, int level, LivingEntity entity) {
        if (entity.tickCount % 60/ level == 0 && entity.hasEffect(ModEffects.sculk_power.get()) && entity.getHealth() < entity.getMaxHealth()) {
            entity.heal(amount.compute(level));
        }
    }
}
