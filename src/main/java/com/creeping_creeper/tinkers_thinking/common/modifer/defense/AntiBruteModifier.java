package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class AntiBruteModifier extends Modifier implements ModifyDamageModifierHook {
    @Override
    public int getPriority() {
        return 0; // after , before
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,  ModifierHooks.MODIFY_HURT);
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
       if (!source.is(DamageTypeTags.BYPASSES_RESISTANCE)&&amount>=context.getEntity().getHealth()-1&&!context.getEntity().hasEffect(ModEffects.antibrute_cooldown.get()))
       {
           context.getEntity().addEffect(new MobEffectInstance(ModEffects.antibrute_cooldown.get(), 240 /modifier.getLevel(),0));
           return 0;
       }
        else return amount;
    }
}
