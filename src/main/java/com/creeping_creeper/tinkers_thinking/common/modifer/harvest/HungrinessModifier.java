package com.creeping_creeper.tinkers_thinking.common.modifer.harvest;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.behavior.AttributeModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public class HungrinessModifier extends Modifier implements ConditionalStatModifierHook, BreakSpeedModifierHook,TooltipModifierHook{
    private static final Component MINING_SPEED = TinkersThinking.makeTranslation("modifier", "hungriness.mining_speed");
    private static final Component VELOCITY = TinkersThinking.makeTranslation("modifier", "hungriness.velocity");
    @Override
    public int getPriority() {
        return 55;
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.CONDITIONAL_STAT,ModifierHooks.BREAK_SPEED,ModifierHooks.TOOLTIP);
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.VELOCITY&&living instanceof Player) {
            return (float) (baseValue*(1+(20-(((Player)living).getFoodData().getFoodLevel()))*0.02*modifier.getLevel()));
        }
        return baseValue;
    }
    @Override
    public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, PlayerEvent.@NotNull BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (!isEffective||event.getEntity()==null) {
            return;
        }
        event.setNewSpeed((float) (event.getNewSpeed()*(1+(20-(event.getEntity().getFoodData().getFoodLevel()))*0.02*modifier.getLevel())));
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        float boost;
        if ((harvest || tool.hasTag(TinkerTags.Items.RANGED))&&player!=null) {
            Component prefix = harvest ? MINING_SPEED : VELOCITY;
            boost = (float) ((20 - (player.getFoodData().getFoodLevel())) * 0.02 * modifier.getLevel());
            TooltipModifierHook.addPercentBoost(this, prefix, boost, tooltip);
        }
    }
}
