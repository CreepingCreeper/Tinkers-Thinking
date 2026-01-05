package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class MagicTransformModifier extends Modifier implements MeleeDamageModifierHook, ModifyDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE,ModifierHooks.MODIFY_HURT);
    }
    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity target = context.getLivingTarget();
        if (target != null && target.isAlive()) {
            damage = transform(context.getAttacker(), target, damage, modifier.getLevel());
        }
        return damage;
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
         Entity attacker = source.getEntity();
         LivingEntity living = context.getEntity();
        if (living.isAlive() && attacker!=null && !source.is(DamageTypeTags.WITCH_RESISTANT_TO)) {
            amount = transform(source.getEntity(),living,amount,modifier.getLevel());
        }
        return amount;
    }
    private float transform(Entity attacker,LivingEntity target,float value,int level){
        float x = value;
        value *= (float) Math.max(1-level*0.2,0);
        float y = (float) ((x-value)*0.75);
        int z = target.invulnerableTime;
        target.invulnerableTime=0;
        DamageSource damageSource = TinkerDamageTypes.source(target.level().registryAccess(), DamageTypes.INDIRECT_MAGIC, attacker);
        target.hurt(damageSource,y);
        target.invulnerableTime = z;
        return value;
    }
}
