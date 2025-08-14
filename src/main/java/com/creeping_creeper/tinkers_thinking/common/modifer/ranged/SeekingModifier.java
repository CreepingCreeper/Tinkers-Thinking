package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import cn.com.tinker_transplant.entities.TinkerArrowEntity;
import cn.com.tinker_transplant.library.ProjectileInterface.TinkerTransplantModifierHooks;
import cn.com.tinker_transplant.library.ProjectileInterface.hooks.ProjectileOnHitEntityModifierHook;
import cn.com.tinker_transplant.library.ProjectileInterface.hooks.ProjectileTickModifierHook;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.PersistentDataCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.ArrayList;
import java.util.Objects;

public class SeekingModifier extends Modifier implements ProjectileTickModifierHook,ProjectileOnHitEntityModifierHook {
    private static final ResourceLocation KEY = new ResourceLocation("tinkers_thinking:target");
    private static final ResourceLocation KEY2 = new ResourceLocation("tinkers_thinking:cooling");
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this,TinkerTransplantModifierHooks.PROJECTILE_TICK,TinkerTransplantModifierHooks.ON_HIT_ENTITY);
    }
    @Override
    public void onTick(IToolStackView var1, ModifierEntry var2, Projectile var3, int var4, Entity var5, Level var6) {
        ModDataNBT data = PersistentDataCapability.getOrWarn(var3);
        int x = data.getInt(KEY2);
        if (var3 instanceof TinkerArrowEntity arrow && !arrow.dealtDamage) {
            if (x>0){
                data.putInt(KEY,x-1);
                return;
            }
            if (!data.contains(KEY,3)) {
                if (!var6.isClientSide) {
                    LivingEntity closest = null;
                    Entity owner = var3.getOwner();
                    float boxExpandBy = Math.min(10, 3 + (var3.tickCount / 4));
                    ArrayList<Entity> list = new ArrayList<>(var6.getEntities(var3, var3.getBoundingBox().inflate(boxExpandBy), arrow::canHitEntity));
                    for (Entity entity : list) {
                        if ((closest == null || entity.distanceTo(var3) < closest.distanceTo(var3)) && entity instanceof LivingEntity living&& !living.getUUID().equals(Objects.requireNonNull(var3.getOwner()).getUUID()) && (owner == null || !entity.isAlliedTo(owner))&&!living.hasEffect(MobEffects.INVISIBILITY)) {
                            closest = living;
                        }
                    }
                    if (closest != null) {
                        data.putInt(KEY,closest.getId());
                    }
                }
            } else {
                Entity arcTowards = var6.getEntity(data.getInt(KEY));
                if (arcTowards != null) {
                    Vec3 arcVec = arcTowards.position().add(0, 0.65F * arcTowards.getBbHeight(), 0).subtract(var3.position());
                    if(arcVec.length() > arcTowards.getBbWidth()){
                        var3.setDeltaMovement(var3.getDeltaMovement().scale(0.31).add(arcVec.normalize().scale(0.7)));
                    }
                }
            }
        }
    }

    @Override
    public void onHitEntity(IToolStackView iToolStackView, ModifierEntry modifierEntry, Projectile projectile, Entity entity, Entity entity1, EntityHitResult entityHitResult, Level level) {
        if (projectile instanceof TinkerArrowEntity arrow){
            ModDataNBT data = PersistentDataCapability.getOrWarn(arrow);
            data.putInt(KEY,10);
        }
    }
}
