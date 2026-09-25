package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import net.minecraft.world.entity.player.Player;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.primitive.IntLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record BattleAdvancedModule(float rate, int max) implements ModifierModule, MeleeDamageModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<BattleAdvancedModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_DAMAGE);
        LOADER = RecordLoadable.create(FloatLoadable.FROM_ZERO.requiredField("rate", BattleAdvancedModule::rate),
                IntLoadable.FROM_ZERO.requiredField("max_time", BattleAdvancedModule::max), BattleAdvancedModule::new);
    }

    public RecordLoadable<BattleAdvancedModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        Player player = context.getPlayerAttacker();
        if (player != null && player.isBlocking()) {
            float bonus =  rate * Math.min (max , modifier.getLevel() * (tool.getItem().getUseDuration(player.getUseItem()) - player.getUseItemRemainingTicks()));
            damage *=  1.0f + bonus;
        }
        return damage;
    }
}
