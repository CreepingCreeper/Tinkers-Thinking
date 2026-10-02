package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.Level;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.json.variable.entity.EntityLightVariable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record ShadowingModule(LevelingValue rate) implements ModifierModule, ModifyDamageModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<ShadowingModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MODIFY_DAMAGE);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(ShadowingModule::rate), ShadowingModule::new);
    }

    public RecordLoadable<ShadowingModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public Integer getPriority() {
        return 75;
    }
    
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (source.getEntity() != null) {
            Level level = context.getLevel();
            amount -= Math.abs(EntityLightVariable.getLightLevel(level, null, context.getEntity().blockPosition()) - EntityLightVariable.getLightLevel(level, null, source.getEntity().blockPosition())) * rate.compute(modifier);
        }
        return Math.max(0, amount);
    }
}
