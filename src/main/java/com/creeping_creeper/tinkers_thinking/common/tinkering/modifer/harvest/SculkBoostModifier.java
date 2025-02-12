package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.harvest;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.predicate.damage.DamageSourcePredicate;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ProtectionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.Util;

import java.util.List;

public class SculkBoostModifier extends Modifier implements TooltipModifierHook,ConditionalStatModifierHook , BreakSpeedModifierHook , ProtectionModifierHook {
    private static final Component Boost = TinkersThinking.makeTranslation("modifier", "sculk_boost.boost");

    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOLTIP,ModifierHooks.BREAK_SPEED,ModifierHooks.CONDITIONAL_STAT,ModifierHooks.PROTECTION);
    }
    @Override
    public void onBreakSpeed(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, PlayerEvent.@NotNull BreakSpeed event, @NotNull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (!isEffective) {
            return;
        }
        if (event.getEntity().hasEffect(ModEffects.sculk_power.get())){
            event.setNewSpeed((float) (event.getNewSpeed() * (1 + modifier.getLevel() * 0.20)));
        }
    }
    @Override
    public float modifyStat(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, LivingEntity living, @NotNull FloatToolStat stat, float baseValue, float multiplier) {
        if (living.hasEffect(ModEffects.sculk_power.get())) {
            if (stat ==ToolStats.PROJECTILE_DAMAGE) {
                return (float) (baseValue*(1+(0.20*modifier.getLevel())));
            }
        }
        return baseValue;
    }
    @Override
    public float getProtectionModifier(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float modifierValue) {
        if (context.getEntity().hasEffect(ModEffects.sculk_power.get())&&DamageSourcePredicate.CAN_PROTECT.matches(source) && tool.hasTag(TinkerTags.Items.ARMOR)){
            modifierValue*=1+(0.2*modifier.getLevel());
        }
   return modifierValue;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if ((tool.hasTag(TinkerTags.Items.RANGED) || tool.hasTag(TinkerTags.Items.MELEE))&&player!=null) {
            float bonus = 0;
            if (player.hasEffect(ModEffects.sculk_power.get())) {
                bonus = (float) (0.20 * modifier.getLevel());
            }
            tooltip.add(applyStyle(Component.literal(Util.PERCENT_BOOST_FORMAT.format(bonus) + " ").append(Boost)));
        }
        }
}
