package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;
import java.util.Optional;

public record ReburningModule(float rate) implements ModifierModule, OnAttackedModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<ReburningModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MODIFY_DAMAGE);
        LOADER = RecordLoadable.create(FloatLoadable.FROM_ZERO.requiredField("rate", ReburningModule::rate), ReburningModule::new);
    }

    public RecordLoadable<ReburningModule> getLoader() {
        return LOADER;
    }
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        int fire = living.getRemainingFireTicks() / 20;
        if (!living.hasEffect(MobEffects.FIRE_RESISTANCE)) {
            Optional<TinkerDataCapability.Holder> dataCap = living.getCapability(TinkerDataCapability.CAPABILITY).resolve();
            dataCap.ifPresent(data -> living.heal(fire * data.get(ModDataKeys.Reburning, 1) * amount));
            living.clearFire();
            ModifierUtils.particles(living.level(), living, ParticleTypes.SMOKE, 4);
            ModifierUtils.addEffect(living,MobEffects.FIRE_RESISTANCE,80);
        }
    }
}
