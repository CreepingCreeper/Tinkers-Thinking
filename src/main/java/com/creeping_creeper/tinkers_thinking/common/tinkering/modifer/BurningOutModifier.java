package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import java.util.Objects;

public class BurningOutModifier extends Modifier implements MeleeDamageModifierHook, ProjectileHitModifierHook {
    public int getPriority() {
        return 85;
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,  ModifierHooks.PROJECTILE_HIT,  ModifierHooks.MELEE_DAMAGE);
    }
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        int fire = Objects.requireNonNull(context.getLivingTarget()).getRemainingFireTicks()/20;
        if (context.isFullyCharged()&&fire > 0) {
            damage += fire*0.5*modifier.getLevel();
            context.getLivingTarget().setRemainingFireTicks(0);
        }
        return damage;
    }
    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, @NotNull NamespacedNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        int fire = 0;
        if (target != null && target.isAlive() &&projectile instanceof AbstractArrow) {
            fire = target.getRemainingFireTicks()/20;
        }
        if  (fire > 0) {
            AbstractArrow arrow = (AbstractArrow) projectile;
            arrow.setBaseDamage(arrow.getBaseDamage() + (fire*0.5*modifier.getLevel()));
            target.setRemainingFireTicks(0);
        }
        return false;
    }
}
