package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DisplayNameModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DurabilityDisplayModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.capacity.CapacitySourceModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public enum SculkCatalyseModule implements ModifierModule, ToolDamageModifierHook, DurabilityDisplayModifierHook, InventoryTickModifierHook, CapacitySourceModule, DisplayNameModifierHook {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.TOOL_DAMAGE, ModifierHooks.DURABILITY_DISPLAY, ModifierHooks.INVENTORY_TICK, ModifierHooks.DISPLAY_NAME);
    public static final RecordLoadable<SculkCatalyseModule> LOADER = new SingletonLoader<>(INSTANCE);

    public RecordLoadable<SculkCatalyseModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if (!ModifierUtils.reverse(tool, modifier)){
            amount -= 1;
        }
        return amount;
    }

    @Override
    public Boolean showDurabilityBar(IToolStackView tool, ModifierEntry modifier) {
        return !ModifierUtils.reverse(tool, modifier);
    }

    @Override
    public int getDurabilityWidth(IToolStackView tool, ModifierEntry modifier) {
        return 0;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, ModifierEntry modifier) {
        return ModifierUtils.reverse(tool, modifier) ? -1 : 0x009295;
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
      if (holder instanceof Player && holder.hasEffect(ModEffects.sculk_power.get())) {
              CapacitySourceModule.apply(tool, modifier, 1,1);
      }else CapacitySourceModule.apply(tool, modifier, 1, 0);
    }

    @Override
    public ModifierId owner() {
        return ModModifierIds.SculkCatalyse;
    }

    @Override
    public Component getDisplayName(IToolStackView tool, ModifierEntry modifier, Component name, @Nullable RegistryAccess access) {
        return modifier.getDisplayName().copy();
    }
}
