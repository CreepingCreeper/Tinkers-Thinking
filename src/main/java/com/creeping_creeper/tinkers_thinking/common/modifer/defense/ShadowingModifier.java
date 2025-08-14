package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class ShadowingModifier extends Modifier implements TooltipModifierHook, ModifyDamageModifierHook {
    private static final Component Resistance = TinkersThinking.makeTranslation("modifier", "shadowing.resistance");

    @Override
    public int getPriority() {
        // run this last as we boost original speed, adds to existing boosts
        return 75;
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,  ModifierHooks.TOOLTIP,ModifierHooks.MODIFY_DAMAGE);
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        Level world =context.getEntity().getCommandSenderWorld();
        amount+=((float) (15-world.getBrightness(LightLayer.SKY, context.getEntity().blockPosition()) - world.getSkyDarken()) /-15)*modifier.getLevel();
        return amount;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        Level world;
        if(player!=null){
            world = player.getCommandSenderWorld();
            float boost = (float) ((world.getBrightness(LightLayer.SKY, player.blockPosition()) - world.getSkyDarken())/15 * modifier.getLevel());
            TooltipModifierHook.addFlatBoost(this,Resistance , boost, tooltip);;
        }
    }

}
