package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
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
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import javax.annotation.Nullable;

public class CrimsonModifier extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook,ModifyDamageModifierHook  {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT ,ModifierHooks.MODIFY_DAMAGE);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity living = context.getPlayerAttacker();
        int x = 0;
        if (living != null&&!context.isExtraAttack() && context.isFullyCharged()) {
            if (living.hasEffect(ModEffects.armor.get())){
                x = living.getEffect(ModEffects.armor.get()).getAmplifier();
            }
            if (x <= modifier.getLevel() * 2 + 2) {
                living.addEffect(new MobEffectInstance(ModEffects.armor.get(), 400, x+1));
            }
        }
    }
    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, ModDataNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null&&attacker != null) {
            int x = 0;
            if (attacker.hasEffect(ModEffects.armor.get())){
                x =attacker.getEffect(ModEffects.armor.get()).getAmplifier();
            }
            if (x <= modifier.getLevel() * 2 + 2) {
                attacker.addEffect(new MobEffectInstance(ModEffects.armor.get(), 400, x+1));
            }
        }
        return false;
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, net.minecraft.world.entity.EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        int x = 0;
        if (living.hasEffect(ModEffects.armor.get())){
            x = living.getEffect(ModEffects.armor.get()).getAmplifier();
        }
        if (x<=modifier.getLevel()*2+4){
            living.addEffect(new MobEffectInstance(ModEffects.armor.get(), 400,x+1));
        }
        return amount;
    }
}