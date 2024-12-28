package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.things.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class SpikyModifier extends Modifier implements OnAttackedModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED);
    }
    @Override
    public void onAttacked(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull EquipmentContext context, @NotNull EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (source.getEntity() instanceof LivingEntity attacker&&!attacker.hasEffect(ModEffects.modifier_immune.get())&&attacker!=context.getEntity()&&(!(source.getDirectEntity() instanceof AbstractArrow arrow) || arrow.getPierceLevel() == 0)) {
            attacker.hurt(DamageSource.thorns(context.getEntity()), amount);
            context.getEntity().getLevel().playSound(null, context.getEntity().getX(), context.getEntity().getY(), context.getEntity().getZ(), SoundEvents.THORNS_HIT, SoundSource.PLAYERS, 1.0F, 1.0F);
            ((LivingEntity) source.getEntity()).addEffect(new MobEffectInstance(ModEffects.modifier_immune.get(), 60/modifier.getLevel(), 1));
        }
    }
}
