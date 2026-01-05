package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import java.util.List;

public enum RederangementModule implements ModifierModule, MeleeHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter, ProjectileHitModifierHook, ModifierUtils {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<RederangementModule>defaultHooks(ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT ,ModifierHooks.PROJECTILE_HIT);
    public static final RecordLoadable<RederangementModule> LOADER = new SingletonLoader<>(INSTANCE);
    public @NotNull RecordLoadable<RederangementModule> getLoader() {
        return LOADER;
    }
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    private void derangement(Level level, LivingEntity target, LivingEntity attacker, boolean a, int l) {
        if (level instanceof ServerLevel) {
            if (a) {
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                if (lightning != null) {
                    lightning.moveTo(Vec3.atBottomCenterOf(target.getOnPos()));
                    if (attacker instanceof ServerPlayer player) {
                        lightning.setCause(player);
                    }
                    lightning.setDamage(l * 3);
                    level.addFreshEntity(lightning);
                    level.playSound(null, target, SoundEvents.TRIDENT_THUNDER, SoundSource.NEUTRAL, 5, 1);
                }
            } else {
                AreaEffectCloud entity = new AreaEffectCloud(level, target.getX(), target.getY(), target.getZ());
                entity.setRadius(3);
                entity.setRadiusOnUse(-0.25F);
                entity.setOwner(attacker);
                entity.setWaitTime(10);
                entity.setRadiusPerTick(-entity.getRadius() / (float) entity.getDuration());
                entity.addEffect(new MobEffectInstance(MobEffects.HARM, 1, l - 1));
                level.addFreshEntity(entity);
            }
            target.setLastHurtByMob(attacker);
        }
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (!context.isExtraAttack() && target!=null) {
            derangement(context.getLevel(), target, context.getPlayerAttacker(), reverse(tool), modifier.getLevel());
        }
    }
    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, ModDataNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target, boolean notBlocked) {
        if (target != null && attacker != null) {
                derangement(projectile.level(), target, attacker, reverseProjectile(projectile), modifier.getLevel());
        }
        return false;
    }
}

