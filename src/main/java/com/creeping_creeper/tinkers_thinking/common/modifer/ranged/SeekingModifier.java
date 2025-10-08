package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingArrow;
import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingThrownShuriken;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileShootModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ScheduledProjectileTaskModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.Schedule;
import slimeknights.tconstruct.tools.entity.ModifiableArrow;
import slimeknights.tconstruct.tools.entity.ThrownShuriken;

import java.util.ArrayList;
import java.util.Objects;

public class SeekingModifier extends Modifier implements ScheduledProjectileTaskModifierHook, ProjectileHitModifierHook {
    private static final String KEY_TASKS = "tasks";
    private static final EntityDataAccessor<Integer> ARC_TOWARDS_ENTITY_ID = SynchedEntityData.defineId(SeekingArrow.class, EntityDataSerializers.INT);

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.SCHEDULE_PROJECTILE_TASK,ModifierHooks.PROJECTILE_HIT);
    }
    @Override
    public void scheduleProjectileTask(IToolStackView tool, ModifierEntry modifier, ItemStack ammo, Projectile projectile, @org.jetbrains.annotations.Nullable AbstractArrow arrow, ModDataNBT persistentData, Schedule.Scheduler scheduler) {
        scheduler.add(0,2);
    }

    @Override
    public void onScheduledProjectileTask(IToolStackView tool, ModifierEntry modifier, ItemStack ammo, Projectile projectile, @org.jetbrains.annotations.Nullable AbstractArrow arrow, ModDataNBT persistentData, int task) {
        if (task == 0){
            CompoundTag tag = new CompoundTag();
            projectile.save(tag);
            if (projectile instanceof ModifiableArrow x) {
                ListTag list = new ListTag();
                CompoundTag tag1 = new CompoundTag();
                tag1.putInt("task", 0);
                tag1.putInt("time", 2);
                list.add(tag);
                tag.put(KEY_TASKS, list);
                x.readAdditionalSaveData(tag);
            }
            int id = projectile.getEntityData().get(ARC_TOWARDS_ENTITY_ID);
                if (id == -1) {
                    if (!projectile.level().isClientSide) {
                        LivingEntity closest = null;
                        Entity owner = projectile.getOwner();
                        float boxExpandBy = Math.min(10, 3 + (projectile.tickCount / 4));
                        //ArrayList<Entity> list = new ArrayList<>(projectile.level().getEntities(projectile, projectile.getBoundingBox().inflate(boxExpandBy), projectile::canHitEntity));
                        //for (Entity entity : list) {
                         //   if ((closest == null || entity.distanceTo(projectile) < closest.distanceTo(projectile)) && entity instanceof LivingEntity living&& !entity.getUUID().equals(Objects.requireNonNull(projectile.getOwner()).getUUID()) && (owner == null || !entity.isAlliedTo(owner))&&!living.hasEffect(MobEffects.INVISIBILITY)) {
                         //       closest = living;
                        //    }
                       // }
                       // if (closest != null) {
                      //      projectile.getEntityData().set(ARC_TOWARDS_ENTITY_ID, closest.getId());
                       // }
                    }
                } else {
                    Entity arcTowards = projectile.level().getEntity(id);
                    if (arcTowards != null) {
                        Vec3 arcVec = arcTowards.position().add(0, 0.65F * arcTowards.getBbHeight(), 0).subtract(projectile.position());
                        if(arcVec.length() > arcTowards.getBbWidth()){
                            projectile.setDeltaMovement(projectile.getDeltaMovement().scale(0.3).add(arcVec.normalize().scale(0.7)));
                        }
                    }
                }
        }
    }
}
