package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.modifer.defense.SymbioticModule;
import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.registry.GenericLoaderRegistry;
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

public record SculkLevitateModule(LevelingValue amount) implements ModifierModule, MeleeHitModifierHook{
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SculkLevitateModule> LOADER;

    public @NotNull RecordLoadable<SculkLevitateModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack() && context.isFullyCharged()) {
            LivingEntity attacker = context.getAttacker();
            LivingEntity target = context.getLivingTarget();
            if (attacker.hasEffect(ModEffects.sculk_power.get())&&target!=null&&!target.hasEffect(ModEffects.modifier_immune.get())) {
                target.addEffect(new MobEffectInstance(MobEffects.LEVITATION,20,1,true,true));
                target.addEffect(new MobEffectInstance(ModEffects.weightless.get(),120,2,true,true));
                target.addEffect(new MobEffectInstance(ModEffects.modifier_immune.get(), (int) (amount.eachLevel()/modifier.getLevel()), 1));
            }
        }
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SculkLevitateModule::amount), SculkLevitateModule::new);
    }
}
