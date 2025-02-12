package com.creeping_creeper.tinkers_thinking.common.things.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffectContext;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffectManager;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffects;

public class ModFluidEffectProjectile extends LlamaSpit {
    private static final EntityDataAccessor<FluidStack> FLUID;
    private float power;
    private int knockback;

    public ModFluidEffectProjectile(Level level, Position pos, FluidStack fluid, float power) {
        this( ModEntityTypes.fluidSpitEntity.get(), level);
        this.setPos(pos.x(), pos.y(), pos.z());
        this.setFluid(fluid);
        this.setPower(power);
    }
    public ModFluidEffectProjectile(EntityType<ModFluidEffectProjectile> type, Level level) {
        super(type,level);
        this.power = 1.0F;
        this.knockback = 1;
    }

    public FluidStack getFluid() {
        return this.entityData.get(FLUID);
    }

    public void setFluid(FluidStack fluid) {
        this.entityData.set(FLUID, fluid);
    }

    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();
        if (this.knockback > 0) {
            Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale((double)this.knockback * 0.6);
            if (vec3.lengthSqr() > 0.0) {
                target.push(vec3.x, 0.1, vec3.z);
            }
        }

        FluidStack fluid = this.getFluid();
        if (!this.level().isClientSide && !fluid.isEmpty()) {
            FluidEffects recipe = FluidEffectManager.INSTANCE.find(fluid.getFluid());
            if (recipe.hasEntityEffects()) {
                int consumed = recipe.applyToEntity(fluid, this.power, new FluidEffectContext.Entity(this.level(), null, this, target), FluidAction.EXECUTE);
                fluid.shrink(consumed);
                if (fluid.isEmpty()) {
                    this.discard();
                } else {
                    this.setFluid(fluid);
                }
            }
        }

    }

    protected void onHitBlock(BlockHitResult hitResult) {
        BlockPos hit = hitResult.getBlockPos();
        BlockState state = this.level().getBlockState(hit);
        state.onProjectileHit(this.level(), state, hitResult, this);
        FluidStack fluid = this.getFluid();
        if (!this.level().isClientSide) {
            if (!fluid.isEmpty()) {
                FluidEffects recipe = FluidEffectManager.INSTANCE.find(fluid.getFluid());
                if (recipe.hasEntityEffects()) {
                    FluidEffectContext.Block context = new FluidEffectContext.Block(this.level(), null, this, hitResult);
                    int consumed;
                    do {
                        consumed = recipe.applyToBlock(fluid, this.power, context, FluidAction.EXECUTE);
                        fluid.shrink(consumed);
                    } while(consumed > 0 && !fluid.isEmpty());
                    if (!fluid.isEmpty() && (state.isAir())) {
                        return;
                    }
                }
            }
            this.discard();
        }

    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLUID, FluidStack.EMPTY);
    }

    protected void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putFloat("power", this.power);
        nbt.putInt("knockback", this.knockback);
        FluidStack fluid = this.getFluid();
        if (!fluid.isEmpty()) {
            nbt.put("fluid", fluid.writeToNBT(new CompoundTag()));
        }

    }

    protected void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.power = nbt.getFloat("power");
        this.knockback = nbt.getInt("knockback");
        this.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompound("fluid")));
    }

    public void setPower(float power) {
        this.power = power;
    }

    static {
        FLUID = SynchedEntityData.defineId(ModFluidEffectProjectile.class, TinkerFluids.FLUID_DATA_SERIALIZER);
    }
}
