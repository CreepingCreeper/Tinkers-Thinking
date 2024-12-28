package com.creeping_creeper.tinkers_thinking.common.things.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpitterBlockEntity extends BlockEntity{
    public SpitterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.Spitter.get(),pos,state);
    }
    private final FluidTank FLUID_TANK = new FluidTank(4000){
    };
    public void setFluid(FluidStack stack){
        this.FLUID_TANK.setFluid(stack);
    }
    public FluidStack getFluidStack(FluidStack stack){
        return this.FLUID_TANK.getFluid();
    }
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.FLUID_HANDLER){
            return lazyFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyFluidHandler = LazyOptional.of(()->FLUID_TANK);
    }
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        nbt = FLUID_TANK.writeToNBT(nbt);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        FLUID_TANK.readFromNBT(nbt);
    }
}
