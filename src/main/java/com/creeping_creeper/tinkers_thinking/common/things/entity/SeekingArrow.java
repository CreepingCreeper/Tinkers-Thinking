package com.creeping_creeper.tinkers_thinking.common.things.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import slimeknights.tconstruct.tools.entity.ModifiableArrow;

import java.util.ArrayList;

public class SeekingArrow extends ModifiableArrow {
    //The code in AlexModGuy's AlexsCaves mod was used and modified.
    private static final EntityDataAccessor<Integer> ARC_TOWARDS_ENTITY_ID = SynchedEntityData.defineId(SeekingArrow.class, EntityDataSerializers.INT);
    private boolean stopSeeking;

    public SeekingArrow(EntityType<SeekingArrow> entityType, Level level) {
        super(entityType, level);
    }
    public SeekingArrow(Level level, double x, double y, double z) {
        super(level,x, y, z);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ARC_TOWARDS_ENTITY_ID, -1);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    public void tick() {
        super.tick();
        int id = this.entityData.get(ARC_TOWARDS_ENTITY_ID);
        if (!inGround && !stopSeeking) {
            if (id == -1) {
                if (!level().isClientSide) {
                    LivingEntity closest = null;
                    Entity owner = this.getOwner();
                    float boxExpandBy = Math.min(10, 3 + (this.tickCount / 4));
                    ArrayList<Entity> list = new ArrayList<>(this.level().getEntities(this, this.getBoundingBox().inflate(boxExpandBy), this::canHitEntity));
                    for (Entity entity : list) {
                        if ((closest == null || entity.distanceTo(this) < closest.distanceTo(this)) && entity instanceof LivingEntity living&& !ownedBy(entity) && (owner == null || !entity.isAlliedTo(owner))&&!living.hasEffect(MobEffects.INVISIBILITY)) {
                            closest = living;
                        }
                    }
                    if (closest != null) {
                        this.entityData.set(ARC_TOWARDS_ENTITY_ID, closest.getId());
                    }
                }
            } else {
                Entity arcTowards = level().getEntity(id);
                if (arcTowards != null) {
                    Vec3 arcVec = arcTowards.position().add(0, 0.65F * arcTowards.getBbHeight(), 0).subtract(this.position());
                    if(arcVec.length() > arcTowards.getBbWidth()){
                        this.setDeltaMovement(this.getDeltaMovement().scale(0.3).add(arcVec.normalize().scale(0.7)));
                    }
                }
            }
        }
        // if (this.level().isClientSide && !this.inGround) {
        //   Vec3 center = this.position().add(this.getDeltaMovement());
        //  Vec3 vec3 = center.add(new Vec3(random.nextFloat() - 0.5F, random.nextFloat() - 0.5F, random.nextFloat() - 0.5F));
        //   this.level().addParticle(ACParticleRegistry.SCARLET_SHIELD_LIGHTNING.get(), center.x, center.y, center.z, vec3.x, vec3.y, vec3.z);
        //}
    }
    @Override
    public void doPostHurtEffects(LivingEntity entity) {stopSeeking = true; }
}
