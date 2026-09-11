package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.ToolEnergyCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

import static slimeknights.tconstruct.library.tools.capability.ToolEnergyCapability.ENERGY_HANDLER;
import static slimeknights.tconstruct.library.tools.capability.ToolEnergyCapability.MAX_STAT;

public record OverchargeModule(LevelingValue amount) implements ModifierModule, InventoryTickModifierHook, ToolStatsModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<OverchargeModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.INVENTORY_TICK, ModifierHooks.TOOL_STATS);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(OverchargeModule::amount), OverchargeModule::new);
    }

    public @NotNull RecordLoadable<OverchargeModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
    return DEFAULT_HOOKS;
}

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        MAX_STAT.add(builder, builder.getStat(OverslimeModule.OVERSLIME_STAT) * 100);
    }
    @Override
    public void onInventoryTick(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, Level world, @NotNull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        // update 1 times a second, but skip when active (messes with pulling bow back)
        if (!world.isClientSide && holder.tickCount % 20 == 0 && holder.getUseItem() != stack) {
            // ensure we have overslime
            int x =ToolEnergyCapability.getEnergy(tool);
            if (OverslimeModule.getCapacity(tool) > OverslimeModule.INSTANCE.getAmount(tool) && x >= 200) {
                int y = Math.min(x / 200, modifier.getLevel());
                OverslimeModule.INSTANCE.addAmount(tool, modifier, y);
                ToolEnergyCapability.setEnergy(tool,  ToolEnergyCapability.getEnergy(tool) - 200 * y);
            }
        }
    }
}
