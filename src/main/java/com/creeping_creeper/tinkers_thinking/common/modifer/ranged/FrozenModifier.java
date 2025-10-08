package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.ForgeMod;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import javax.annotation.Nullable;

import static com.creeping_creeper.tinkers_thinking.common.things.effect.FreezingColdEffect.FREEZINGCOLDBONUS;

public class FrozenModifier extends Modifier implements ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_HIT);
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target, boolean notBlocked) {
        if (target!=null){
            target.addEffect(new MobEffectInstance(ModEffects.freezing_cold.get(),30*modifier.getLevel(),99));
            Freeze(target.getAttribute(Attributes.MOVEMENT_SPEED));
            Freeze(target.getAttribute(Attributes.FLYING_SPEED));
            Freeze(target.getAttribute(ForgeMod.SWIM_SPEED.get()));
        }
        return false;
    }
    private void Freeze(@org.jetbrains.annotations.Nullable AttributeInstance attribute){
        if (attribute != null && attribute.getValue() > 0) {
            if (attribute.getModifier(FREEZINGCOLDBONUS) != null) {
                attribute.removeModifier(FREEZINGCOLDBONUS);
            }
            attribute.addTransientModifier(new AttributeModifier(FREEZINGCOLDBONUS, "tinkers_thinking.effect.freezing_cold", -1, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }
}
