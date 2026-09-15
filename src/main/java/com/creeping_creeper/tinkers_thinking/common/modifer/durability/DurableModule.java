package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public record DurableModule(float max_chance) implements ModifierModule, ToolDamageModifierHook, TooltipModifierHook {
    private static final Component prefix = TinkersThinking.makeTranslation("modifier", "durable.chance");
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<DurableModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.TOOL_DAMAGE, ModifierHooks.TOOLTIP);
        LOADER = RecordLoadable.create(FloatLoadable.PERCENT.requiredField("max_chance", DurableModule::max_chance), DurableModule::new);
    }

    public RecordLoadable<DurableModule> getLoader() {
    return LOADER;
}

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public Integer getPriority() {
        return 200; // after , before
    }
    
    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        float chance = (float) Math.min(Math.pow(modifier.getLevel(), 0.5) * 5 * Math.pow(tool.getDamage() / tool.getStats().get(ToolStats.DURABILITY), 2), max_chance);
        int maxDamage = amount;
        // for each damage we will take, if the random number is below chance, reduce
        for (int i = 0; i < maxDamage; i++) {
            if (Modifier.RANDOM.nextFloat() < chance) {
                amount--;
            }
        }
        return amount;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player != null) {
            float chance = (float) Math.min(Math.pow(modifier.getLevel() , 0.5)* 5 * Math.pow(tool.getDamage() / tool.getStats().get(ToolStats.DURABILITY), 2), max_chance);
            TooltipModifierHook.addPercentBoost(modifier.getModifier(), prefix, chance, tooltip);
        }
    }
}