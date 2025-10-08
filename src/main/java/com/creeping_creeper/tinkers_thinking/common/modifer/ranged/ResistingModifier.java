package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import com.creeping_creeper.tinkers_thinking.data.ModTags;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import javax.annotation.Nullable;
import java.util.List;

public class ResistingModifier extends Modifier implements ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_HIT);
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target, boolean notBlocked) {
        if (target != null)
            target.invulnerableTime=0;
            // wither are hardcoded to not take arrow damage, so disagree by reimplementing arrow damage right here
        if (target.getType().is(ModTags.EntityTypes.resisting) && projectile instanceof AbstractArrow arrow && attacker != null) {
                // first, give up if we reached pierce capacity, and ensure list are created
                if (arrow.getPierceLevel() > 0) {
                    if (arrow.piercingIgnoreEntityIds == null) {
                        arrow.piercingIgnoreEntityIds = new IntOpenHashSet(5);
                    }
                    if (arrow.piercedAndKilledEntities == null) {
                        arrow.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
                    }
                    if (arrow.piercingIgnoreEntityIds.size() >= arrow.getPierceLevel() + 1) {
                        arrow.discard();
                        return true;
                    }
                    arrow.piercingIgnoreEntityIds.add(target.getId());
                }

                // calculate damage, bonus on crit
                int damage = Mth.ceil(Mth.clamp(arrow.getDeltaMovement().length() * arrow.getBaseDamage(), 0.0D, Integer.MAX_VALUE));
                if (arrow.isCritArrow()) {
                    damage = (int) Math.min(RANDOM.nextInt(damage / 2 + 2) + (long) damage, Integer.MAX_VALUE);
                }

                // create damage source, don't use arrow as directEntity because that makes wither ignore it
                Entity owner = arrow.getOwner();
                DamageSource damageSource = TinkerDamageTypes.source(attacker.level().registryAccess(), DamageTypes.ARROW, attacker, attacker);
                attacker.setLastHurtMob(target);

                // handle fire
                int remainingFire = target.getRemainingFireTicks();
                if (arrow.isOnFire()) {
                    target.setSecondsOnFire(5);
                }

                Level level = arrow.level();
                if (target.hurt(damageSource, (float) damage)) {
                    if (!level.isClientSide && arrow.getPierceLevel() <= 0) {
                        target.setArrowCount(target.getArrowCount() + 1);
                    }

                    // knockback from punch
                    int knockback = arrow.getKnockback();
                    if (knockback > 0) {
                        Vec3 knockbackVec = arrow.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale(knockback * 0.6D);
                        if (knockbackVec.lengthSqr() > 0.0D) {
                            target.push(knockbackVec.x, 0.1D, knockbackVec.z);
                        }
                    }

                    if (!level.isClientSide) {
                        EnchantmentHelper.doPostHurtEffects(target, attacker);
                        EnchantmentHelper.doPostDamageEffects(attacker, target);
                    }

                    arrow.doPostHurtEffects(target);

                    if (!target.isAlive() && arrow.piercedAndKilledEntities != null) {
                        arrow.piercedAndKilledEntities.add(target);
                    }
                }

                 if (!level.isClientSide && arrow.shotFromCrossbow() && owner instanceof ServerPlayer player) {
                     if (arrow.piercedAndKilledEntities != null) {
                         CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(player, arrow.piercedAndKilledEntities);
                     } else if (!target.isAlive()) {
                         CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(player, List.of(target));
                     }
                 }
                    arrow.playSound(arrow.soundEvent, 1.0F, 1.2F / (RANDOM.nextFloat() * 0.2F + 0.9F));
                    if (arrow.getPierceLevel() <= 0) {
                        arrow.discard();
                    }
                    else {
                    // reset fire and drop the arrow
                    target.setRemainingFireTicks(remainingFire);
                    arrow.setDeltaMovement(arrow.getDeltaMovement().scale(-0.1D));
                    arrow.setYRot(arrow.getYRot() + 180.0F);
                    arrow.yRotO += 180.0F;
                    if (!level.isClientSide && arrow.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                        if (arrow.pickup == AbstractArrow.Pickup.ALLOWED) {
                            arrow.spawnAtLocation(arrow.getPickupItem(), 0.1F);
                        }

                        arrow.discard();
                    }
                }
            }
        return false;
    }
}