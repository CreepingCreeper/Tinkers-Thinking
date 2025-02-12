package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.melee;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.Util;

import java.util.List;

public class LightlyAttackModifier extends Modifier implements MeleeHitModifierHook,TooltipModifierHook, ConditionalStatModifierHook {
    private static final Component Boost = TinkersThinking.makeTranslation("modifier", "lightly_attack.draw_speed");
    @Override
    public int getPriority() {
        // run this last as we boost original speed, adds to existing boosts
        return 75;
    }
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this,ModifierHooks.MELEE_HIT, ModifierHooks.CONDITIONAL_STAT,ModifierHooks.TOOLTIP);
    }
    private boolean isEmpty(LivingEntity living, int stack){
        return living.getSlot(stack).get().isEmpty();
    }
    private boolean isAllEmpty(LivingEntity living){
        int x = 0;
        for (int i=0;i<9;i++){
            if (!isEmpty(living,i)) {
                x++;
            }
        }
        return x < 2;
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack()) {
            if (isAllEmpty(context.getAttacker())) {
                context.getAttacker().addEffect(new MobEffectInstance(ModEffects.lightly_attack.get(),60,modifier.getLevel()));
            }
        }
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
          if (isAllEmpty(living)) {
              if (stat == ToolStats.DRAW_SPEED) {
                  return (float) (baseValue * (1 + (0.30 * modifier.getLevel())));
              }
          }
        return baseValue;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if ((tool.hasTag(TinkerTags.Items.RANGED)&&player!=null)) {
            float bonus = 0;
            if (isAllEmpty(player)){
                bonus = (float) (0.30 * modifier.getLevel());
            }
            tooltip.add(applyStyle(Component.literal(Util.PERCENT_BOOST_FORMAT.format(bonus) + " ").append(Boost)));
        }
    }
}
