package com.creeping_creeper.tinkers_thinking.common.things.block.entity;

import com.creeping_creeper.tinkers_thinking.common.register.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class WasteFluidCylinderBlockEntity extends BlockEntity {
    //The code in Da-Technomancer's CrossRoads mod was used.
    public WasteFluidCylinderBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.New.get(), p_155229_, p_155230_);
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing){
        if(capability == ForgeCapabilities.FLUID_HANDLER){
            return (LazyOptional<T>) mainOpt;
        }

        return super.getCapability(capability, facing);
    }

    @Override
    public void setRemoved(){
        super.setRemoved();
        mainOpt.invalidate();
    }

    private final LazyOptional<IFluidHandler> mainOpt = LazyOptional.of(VoidHandler::new);

    private static class VoidHandler implements IFluidHandler{

        @Override
        public int getTanks(){
            return 1;
        }

        @Nonnull
        @Override
        public FluidStack getFluidInTank(int tank){
            return FluidStack.EMPTY;
        }

        @Override
        public int getTankCapacity(int tank){
            return 10000;
        }

        @Override
        public boolean isFluidValid(int tank, @Nonnull FluidStack stack){
            return true;
        }

        @Override
        public int fill(FluidStack resource, FluidAction action){
            return resource.getAmount();
        }

        @Nonnull
        @Override
        public FluidStack drain(FluidStack resource, FluidAction action){
            return FluidStack.EMPTY;
        }

        @Nonnull
        @Override
        public FluidStack drain(int maxDrain, FluidAction action){
            return FluidStack.EMPTY;
        }
    }
}
