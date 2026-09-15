package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;
import java.util.List;

public enum DuritaeModule implements ModifierModule, ToolDamageModifierHook {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.TOOL_DAMAGE);
    public static final RecordLoadable<DuritaeModule> LOADER = new SingletonLoader<>(INSTANCE);

    public RecordLoadable<DuritaeModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
    return DEFAULT_HOOKS;
}


    @Override
    public Integer getPriority() {
        return 185; // after , before
    }

    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder)  {
               float chance1 = (float) Math.pow(0.70, modifier.getLevel());
               float chance2 =  (float) Math.pow(0.95, modifier.getLevel());
               int maxDamage = amount;
               // for each damage we will take, if the random number is below chance, reduce
               for (int i = 0; i < maxDamage; i++) {
                    if (Modifier.RANDOM.nextFloat() > chance1) {
                        amount--;
                    }
                    if (Modifier.RANDOM.nextFloat() > chance2) {
                        amount++;
                    }
            }
        return amount;
    }
}
