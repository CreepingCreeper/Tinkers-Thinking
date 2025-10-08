package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import java.util.List;

public record RederangementModule(LevelingValue amount) implements ModifierModule, MeleeHitModifierHook, ProjectileHitModifierHook,ModifierUtils {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<RederangementModule> LOADER;

    public @NotNull RecordLoadable<RederangementModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    private void derangement(ServerLevel level, LivingEntity target, LivingEntity attacker, boolean a, int l){
        if (a) {
            LightningBolt entity = EntityType.LIGHTNING_BOLT.spawn(level, target.getOnPos(), MobSpawnType.MOB_SUMMONED);
            if (entity != null) {
                entity.setDamage(l*3);
            }
        }
        if (!a) {
            AreaEffectCloud entity = new AreaEffectCloud(level, target.getX(), target.getY(), target.getZ());
            entity.setRadius(3);
            entity.setRadiusOnUse(-0.25F);
            entity.setOwner(attacker);
            entity.setWaitTime(10);
            entity.setRadiusPerTick(-entity.getRadius() / (float)entity.getDuration());
            entity.addEffect(new MobEffectInstance(MobEffects.HARM,1,l-1));
            level.addFreshEntity(entity);
        }
        target.setLastHurtByMob(attacker);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (!context.isExtraAttack() && context.getLevel() instanceof ServerLevel level&& target!=null) {
            derangement(level,target,context.getAttacker(),reverse(tool),modifier.getLevel());
        }
    }
    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, ModDataNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        if (target != null && attacker != null  && target.level() instanceof ServerLevel level) {
                derangement(level,target,attacker, reverseProjectile(projectile),modifier.getLevel());
        }
        return false;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT,ModifierHooks.PROJECTILE_HIT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(RederangementModule::amount), RederangementModule::new);
    }
}

