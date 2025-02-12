package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

public class SculkGravityModifier extends NoLevelsModifier implements ProjectileHitModifierHook {
     @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_HIT);
    }
    @Override
    public ProjectileImpactEvent.ImpactResult onProjectileHitEntity(@NotNull ModifierNBT modifiers, ModDataNBT persistentData, @NotNull ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        if (target!=null&&!target.hasEffect(ModEffects.modifier_immune.get())&&projectile instanceof AbstractArrow &&attacker!=null&&attacker.hasEffect(ModEffects.sculk_power.get())) {
            target.addEffect(new MobEffectInstance(ModEffects.overweight.get(), 120, 5,true,true));
            target.addEffect(new MobEffectInstance(ModEffects.modifier_immune.get(), 360/modifier.getLevel(), 1));
        }
        return ProjectileImpactEvent.ImpactResult.DEFAULT;
    }
}
