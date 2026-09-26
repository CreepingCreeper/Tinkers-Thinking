package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import javax.annotation.Nullable;
import java.util.List;

public record BurningOutModule(LevelingValue rate) implements ModifierModule, MeleeHitModifierHook, ProjectileHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<BurningOutModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT, ModifierHooks.PROJECTILE_HIT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(BurningOutModule::rate), BurningOutModule::new);
    }

    public RecordLoadable<BurningOutModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }


    public Integer getPriority() {
        return 85;
    }
    
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (!context.isExtraAttack() && context.isFullyCharged() && target!=null) {
            burn(context.getAttacker(),target,modifier.getLevel());
        }
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target, boolean notBlocked) {
        if (target != null && attacker != null) {
            burn(attacker,target,modifier.getLevel());
        }
        return false;
    }
    private void burn(LivingEntity attacker, LivingEntity target, int level) {
        if (target.isAlive() && !target.fireImmune() && !target.hasEffect(MobEffects.FIRE_RESISTANCE)) {
            int fire = target.getRemainingFireTicks() / 20;
            if (fire > 0) {
                DamageSource source = TinkerDamageTypes.source(target.level().registryAccess(), DamageTypes.ON_FIRE, attacker);
                ToolAttackUtil.attackEntitySecondary(source, fire * rate.compute(level), target, target, true);
                target.invulnerableTime = 0;
                target.clearFire();
                ModifierUtils.particles(attacker.level(), target, ParticleTypes.SMOKE, 4);
            }
        }
    }
}
