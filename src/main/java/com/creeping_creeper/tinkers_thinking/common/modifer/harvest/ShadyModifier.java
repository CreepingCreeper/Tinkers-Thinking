package com.creeping_creeper.tinkers_thinking.common.modifer.harvest;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
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
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public class ShadyModifier extends Modifier implements ConditionalStatModifierHook, BreakSpeedModifierHook, TooltipModifierHook {
    private static final Component MINING_SPEED = TinkersThinking.makeTranslation("modifier", "shady.mining_speed");
    private static final Component VELOCITY = TinkersThinking.makeTranslation("modifier", "shady.velocity");

    @Override
    public int getPriority() {
        // run this last as we boost original speed, adds to existing boosts
        return 75;
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.CONDITIONAL_STAT, ModifierHooks.BREAK_SPEED, ModifierHooks.TOOLTIP);
    }

    @Override
    public void onBreakSpeed(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, PlayerEvent.@NotNull BreakSpeed event, @NotNull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (!isEffective) {
            return;
        }
        Level world =event.getEntity().getCommandSenderWorld();
        event.setNewSpeed((float) (event.getNewSpeed()*( 1+(world.getBrightness(LightLayer.SKY, event.getEntity().blockPosition()) - world.getSkyDarken())*0.015)*modifier.getLevel()));
    }
    @Override
    public float modifyStat(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull LivingEntity living, @NotNull FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.VELOCITY) {
            Level world =living.getCommandSenderWorld();
            return (float) (baseValue*( 1+(world.getBrightness(LightLayer.SKY, living.blockPosition()) - world.getSkyDarken())*0.015)*modifier.getLevel());
        }
        return baseValue;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        Level world;
        if ((harvest || tool.hasTag(TinkerTags.Items.RANGED))&&player!=null) {
            Component prefix = harvest ? MINING_SPEED : VELOCITY;
            world = player.getCommandSenderWorld();
            float boost = (float) ((world.getBrightness(LightLayer.SKY, player.blockPosition()) - world.getSkyDarken()) * 0.015 * modifier.getLevel());
            TooltipModifierHook.addPercentBoost(this, prefix, boost, tooltip);
        }
    }
}
