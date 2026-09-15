package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.primitive.IntLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.capability.ToolEnergyCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public record OverchargeModule(float max_energy_rate, int overslime_rate) implements ModifierModule, InventoryTickModifierHook, ToolStatsModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<OverchargeModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.INVENTORY_TICK, ModifierHooks.TOOL_STATS);
        LOADER = RecordLoadable.create(
                FloatLoadable.FROM_ZERO.requiredField("max_energy_rate", OverchargeModule::max_energy_rate),
                IntLoadable.FROM_ZERO.requiredField("overslime_rate", OverchargeModule::overslime_rate),
                OverchargeModule::new);
    }

    public RecordLoadable<OverchargeModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
    return DEFAULT_HOOKS;
}

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolEnergyCapability.MAX_STAT.add(builder, builder.getStat(OverslimeModule.OVERSLIME_STAT) * max_energy_rate);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        // update 1 times a second, but skip when active (messes with pulling bow back)
        if (!world.isClientSide && holder.tickCount % 20 == 0 && holder.getUseItem() != stack) {
            // ensure we have overslime
            int x =ToolEnergyCapability.getEnergy(tool);
            if (OverslimeModule.getCapacity(tool) > OverslimeModule.INSTANCE.getAmount(tool) && x >= overslime_rate) {
                int y = Math.min(x / overslime_rate, modifier.getLevel());
                OverslimeModule.INSTANCE.addAmount(tool, modifier, y);
                ToolEnergyCapability.setEnergy(tool,  ToolEnergyCapability.getEnergy(tool) - overslime_rate * y);
            }
        }
    }
}
