package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import javax.annotation.Nullable;

public class BurningOutModifier extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter, ModifierUtils {
    public int getPriority() {
        return 85;
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,  ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT, ModifierHooks.PROJECTILE_HIT);
    }
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (!context.isExtraAttack() && context.isFullyCharged() && target!=null) {
            burn(context.getAttacker(),target,modifier.getLevel());
        }
    }
    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, ModDataNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target, boolean notBlocked) {
        if (target != null && attacker != null) {
            burn(attacker,target,modifier.getLevel());
        }
        return false;
    }
    private void burn(LivingEntity attacker,LivingEntity target,int level) {
        if (target.isAlive()&&!target.fireImmune()&&!target.hasEffect(MobEffects.FIRE_RESISTANCE)) {
            int fire = target.getRemainingFireTicks() / 20;
            if (fire > 0) {
                DamageSource source = TinkerDamageTypes.source(target.level().registryAccess(), DamageTypes.ON_FIRE, attacker);
                ToolAttackUtil.attackEntitySecondary(source, (float) (fire * 0.3 * level), target, target, true);
                target.invulnerableTime = 0;
                target.clearFire();
                particles(attacker.level(), target, ParticleTypes.SMOKE, 4);
                addEffect(target,MobEffects.FIRE_RESISTANCE, 80);
            }
        }
    }
}
