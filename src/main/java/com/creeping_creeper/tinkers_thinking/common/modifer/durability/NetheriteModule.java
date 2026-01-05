package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

import static net.minecraft.tags.DamageTypeTags.BYPASSES_ARMOR;

public enum NetheriteModule implements ModifierModule, ToolDamageModifierHook, ModifyDamageModifierHook {
    INSTANCE;
    private static final ResourceLocation KEY = new ResourceLocation("tinkers_thinking", "netherite");
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<NetheriteModule>defaultHooks(ModifierHooks.TOOL_DAMAGE, ModifierHooks.MODIFY_HURT);
    public static final RecordLoadable<NetheriteModule> LOADER = new SingletonLoader<>(INSTANCE);
    public @NotNull RecordLoadable<NetheriteModule> getLoader() {
        return LOADER;
    }
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @org.jetbrains.annotations.Nullable LivingEntity holder) {
        if (tool.getPersistentData().getBoolean(KEY)){
            amount = 0;
            tool.getPersistentData().remove(KEY);
        }
        return amount;
    }
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (source.is(DamageTypeTags.IS_FIRE)&&!source.is(BYPASSES_ARMOR)&&tool.hasTag(TinkerTags.Items.WORN_ARMOR)){
            tool.getPersistentData().putBoolean(KEY, true);
            amount=0;
        }
        return amount;
    }
}
