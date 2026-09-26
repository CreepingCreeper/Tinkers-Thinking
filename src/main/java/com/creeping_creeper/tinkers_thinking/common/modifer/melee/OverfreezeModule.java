package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingInt;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record OverfreezeModule(LevelingInt max) implements ModifierModule, MeleeHitModifierHook, ModifyDamageModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<OverfreezeModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT, ModifierHooks.MODIFY_DAMAGE);
        LOADER = RecordLoadable.create(LevelingInt.LOADABLE.directField(OverfreezeModule::max), OverfreezeModule::new);
    }

    public RecordLoadable<OverfreezeModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack() && context.isFullyCharged()) {
            LivingEntity target = context.getLivingTarget();
            int x = Math.min(max.compute(modifier), OverslimeModule.INSTANCE.getAmount(tool));
            if (target != null && x > 0) {
                OverslimeModule.INSTANCE.removeAmount(tool, modifier,x);
                target.addEffect(new MobEffectInstance(ModEffects.freezing_cold.get(), 40 * x, modifier.getLevel()));
                target.setLastHurtMob(context.getAttacker());
            }
        }
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, net.minecraft.world.entity.EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        Entity attacker = source.getEntity();
        int x = Math.min(max.compute(modifier), OverslimeModule.INSTANCE.getAmount(tool));
        if (attacker instanceof LivingEntity living&& attacker.isAlive() && x>0) {
            OverslimeModule.INSTANCE.removeAmount(tool, modifier,x);
            living.addEffect(new MobEffectInstance(ModEffects.freezing_cold.get(), 40 * x, modifier.getLevel()));
            living.setLastHurtMob(context.getEntity());
        }
        return amount;
    }
}