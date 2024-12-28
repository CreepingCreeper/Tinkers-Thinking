package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.defense;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.UseAnim;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class R extends Modifier implements OnAttackedModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED);
    }
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        ModifierEntry activeModifier = GeneralInteractionModifierHook.getActiveModifier(tool);
        GeneralInteractionModifierHook hook = activeModifier.getHook(ModifierHooks.GENERAL_INTERACT);
        if (source.getEntity()!=null&&hook.getUseAction(tool, activeModifier) == UseAnim.BLOCK&&source.getEntity()==source.getDirectEntity()&&source.getEntity()!=context.getEntity()&&!source.isBypassArmor()&&source!=DamageSource.thorns(source.getEntity())){
            source.getEntity().hurt(DamageSource.mobAttack(context.getEntity()), tool.getDamage());
        }
    }
}
