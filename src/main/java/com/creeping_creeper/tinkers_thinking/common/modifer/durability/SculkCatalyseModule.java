package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DurabilityDisplayModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public enum SculkCatalyseModule implements ModifierModule, ToolDamageModifierHook, DurabilityDisplayModifierHook, InventoryTickModifierHook, ModifierRemovalHook, ModifierUtils {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<SculkCatalyseModule>defaultHooks(ModifierHooks.TOOL_DAMAGE,ModifierHooks.DURABILITY_DISPLAY,ModifierHooks.INVENTORY_TICK,ModifierHooks.REMOVE);
    public static final RecordLoadable<SculkCatalyseModule> LOADER = new SingletonLoader<>(INSTANCE);
    public @NotNull RecordLoadable<SculkCatalyseModule> getLoader() {
        return LOADER;
    }
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    private static final ResourceLocation KEY = new ResourceLocation("tinkers_thinking", "sculk_catalyse");
    @Override
    public int onDamageTool(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if (holder!= null&&holder.hasEffect(ModEffects.sculk_power.get())){
            amount -= 1;
        }
        return amount;
    }

    @Nullable
    @Override
    public Boolean showDurabilityBar(IToolStackView tool, ModifierEntry modifier) {
        return  tool.getPersistentData().contains(KEY,1) ? true : null;
    }

    @Override
    public int getDurabilityWidth(IToolStackView tool, ModifierEntry modifier) {
        return 0;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, ModifierEntry modifier) {
        return tool.getPersistentData().contains(KEY,1) ? 0x009295 : -1;
    }
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
      if (holder instanceof Player ) {
          if (holder.hasEffect(ModEffects.sculk_power.get())) {
              tool.getPersistentData().putBoolean(KEY, true);
          }
          if (!holder.hasEffect(ModEffects.sculk_power.get())) {
              tool.getPersistentData().remove(KEY);
          }
      }
    }
    @Nullable
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        if (tool.getModifierLevel(ModModifierIds.SculkCatalyse) == 0) {
            tool.getPersistentData().remove(KEY);
        }
        return null;
    }
}
