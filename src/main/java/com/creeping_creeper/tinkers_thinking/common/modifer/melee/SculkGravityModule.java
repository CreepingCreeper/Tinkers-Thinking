package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import javax.annotation.Nullable;
import java.util.List;

public record SculkGravityModule(LevelingValue amount) implements ModifierModule, ProjectileHitModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SculkGravityModule> LOADER;

    public @NotNull RecordLoadable<SculkGravityModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, ModDataNBT persistentData, @NotNull ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target!=null&&!target.hasEffect(ModEffects.modifier_immune.get())&&projectile instanceof AbstractArrow &&attacker!=null&&attacker.hasEffect(ModEffects.sculk_power.get())) {
            target.addEffect(new MobEffectInstance(ModEffects.overweight.get(), 120, 5,true,true));
            target.addEffect(new MobEffectInstance(ModEffects.modifier_immune.get(), (int) (amount.eachLevel()/modifier.getLevel()), 1));
        }
        return false;
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.PROJECTILE_HIT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SculkGravityModule::amount), SculkGravityModule::new);
    }
}
