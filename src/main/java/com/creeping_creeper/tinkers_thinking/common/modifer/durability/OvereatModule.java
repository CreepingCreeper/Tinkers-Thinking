package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record OvereatModule(LevelingValue chance) implements ModifierModule, InventoryTickModifierHook {
private static final List<ModuleHook<?>> DEFAULT_HOOKS;
public static final RecordLoadable<OvereatModule> LOADER;

static {
    DEFAULT_HOOKS = HookProvider.defaultHooks();
    LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(OvereatModule::chance), OvereatModule::new);
}

public RecordLoadable<OvereatModule> getLoader() {
    return LOADER;
}

public List<ModuleHook<?>> getDefaultHooks() {
    return DEFAULT_HOOKS;
}


    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        // update 1 times a second, but skip when active (messes with pulling bow back)
        if (!world.isClientSide && holder.tickCount % 20 == 0 && holder.getUseItem() != stack) {
            // ensure we have overslime
            // has a 15% chance of restoring each second per level
            if (0 < OverslimeModule.INSTANCE.getAmount(tool) && 0 < tool.getDamage() && Modifier.RANDOM.nextFloat() < (chance.compute(modifier))) {
                ToolDamageUtil.repair(tool, 1);
                if (Modifier.RANDOM.nextFloat() < 0.33){
                    OverslimeModule.INSTANCE.removeAmount(tool, modifier,1);
                }
            }
        }
    }
}
