package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;
import java.util.List;

public class DepositionModifier extends Modifier implements ToolDamageModifierHook, TooltipModifierHook {
    @Override
    public int getPriority() {
        return 180; // after , before
    }
    private static final Component prefix = TinkersThinking.makeTranslation("modifier", "deposition.chance");
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_DAMAGE, ModifierHooks.TOOLTIP);
    }
    public int onDamageTool(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, int amount, @Nullable LivingEntity holder)  {
        if (holder != null){
            float y = (float) (holder.getY());
            if (y < 72) {
                //use 72 instead of 64 because we are good
                float chance = (float) Math.min((72 - y)/136 * modifier.getLevel() * 0.40,0.80); // up to  80% a chance at max
                int maxDamage = amount;
                // for each damage we will take, if the random number is below chance, reduce
                for (int i = 0; i < maxDamage; i++) {
                    if (RANDOM.nextFloat() < chance) {
                        amount--;
                    }
                }
            }
        }
        return amount;
    }
    public void addTooltip(IToolStackView tool, @NotNull ModifierEntry modifier, @Nullable Player player, @NotNull List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag)
    {
        if (player != null) {
            float chance = 0;
            float y = (float) player.getY();
            if (y < 72) {
                chance = (float) Math.min((72 - y) / 136 * modifier.getLevel() * 0.40, 0.80);
            }
            TooltipModifierHook.addPercentBoost(this, prefix, chance, tooltip);
        }
    }
}
