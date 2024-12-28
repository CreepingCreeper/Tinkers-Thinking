package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.melee;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class RepulsiveModifier extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook , ModifyDamageModifierHook{
     @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT,ModifierHooks.PROJECTILE_HIT,ModifierHooks.MODIFY_DAMAGE);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (context.getLivingTarget()!=null&&!context.isExtraAttack()) {
            TinkerModifiers.repulsiveEffect.get().apply(context.getLivingTarget(), 15, modifier.getLevel()*2  - 1);
        }
    }
    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, @NotNull NamespacedNBT persistentData, @NotNull ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        if (target != null  && projectile instanceof AbstractArrow) {
            TinkerModifiers.repulsiveEffect.get().apply(target, 15, modifier.getLevel()*2 - 1);
        }
        return false;
    }

    @Override
    public float modifyDamageTaken(@NotNull IToolStackView tool, ModifierEntry modifier, EquipmentContext context, @NotNull EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
            TinkerModifiers.repulsiveEffect.get().apply(context.getEntity(), 15, modifier.getLevel()*2 - 1);
         return amount;
    }
}
