package com.creeping_creeper.tinkers_thinking.common.modifer.harvest;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public record HungrinessModule(LevelingValue rate) implements ModifierModule, ConditionalStatModifierHook, BreakSpeedModifierHook, TooltipModifierHook {
    private static final Component MINING_SPEED = TinkersThinking.makeTranslation("modifier", "hungriness.mining_speed");
    private static final Component VELOCITY = TinkersThinking.makeTranslation("modifier", "hungriness.velocity");

    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<HungrinessModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.CONDITIONAL_STAT, ModifierHooks.BREAK_SPEED, ModifierHooks.TOOLTIP);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(HungrinessModule::rate), HungrinessModule::new);
    }

    public RecordLoadable<HungrinessModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public Integer getPriority() {
        return 55;
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.VELOCITY && living instanceof Player player) {
            return baseValue*(1 + (20 - ((player.getFoodData().getFoodLevel())) * rate.compute(modifier)));
        }
        return baseValue;
    }

    @Override
    public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (!isEffective || event.getEntity() == null) {
            return;
        }
        event.setNewSpeed(event.getNewSpeed() * (1 + (20 - (event.getEntity().getFoodData().getFoodLevel())) * rate.compute(modifier)));
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        float boost;
        if ((harvest || tool.hasTag(TinkerTags.Items.RANGED)) && player != null) {
            Component prefix = harvest ? MINING_SPEED : VELOCITY;
            boost = (20 - (player.getFoodData().getFoodLevel())) * rate.compute(modifier);
            TooltipModifierHook.addPercentBoost(modifier.getModifier(), prefix, boost, tooltip);
        }
    }
}

