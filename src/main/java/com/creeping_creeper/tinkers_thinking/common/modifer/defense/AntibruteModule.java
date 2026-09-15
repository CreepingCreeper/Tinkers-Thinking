package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public enum AntibruteModule implements ModifierModule, ModifyDamageModifierHook {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<AntibruteModule>defaultHooks(ModifierHooks.MODIFY_HURT);
    public static final RecordLoadable<AntibruteModule> LOADER = new SingletonLoader<>(INSTANCE);
    public RecordLoadable<AntibruteModule> getLoader() {
        return LOADER;
    }
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        float x = living.getHealth()/2;
        if (!source.is(DamageTypeTags.BYPASSES_RESISTANCE) && !context.getEntity().hasEffect(ModEffects.antibrute_cooldown.get()) && amount>x) {
            ModifierUtils.addEffect(context.getEntity(), ModEffects.antibrute_cooldown.get(), 160, 1);
            ModifierUtils.block(living);
            return x;
        }
        return amount;
    }
}
