package com.creeping_creeper.tinkers_thinking.common.things.block;

import com.creeping_creeper.tinkers_thinking.common.things.block.entity.SpitterBlockEntity;
import com.creeping_creeper.tinkers_thinking.common.things.entity.ModFluidEffectProjectile;
import net.minecraft.core.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.fluid.FluidTransferHelper;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffectManager;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffects;

import java.util.Objects;

public class SpitterBlock extends BaseEntityBlock{
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
    public SpitterBlock(Properties p_49224_) {
        super(p_49224_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TRIGGERED, Boolean.FALSE));
    }
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (FluidTransferHelper.interactWithTank(world, pos, player, hand, hit)) {
            return InteractionResult.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, hit);
    }
    protected void dispenseFrom(ServerLevel p_52665_, BlockPos p_52666_) {
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(p_52665_, p_52666_);
        if (Objects.requireNonNull(p_52665_.getBlockEntity(p_52666_)) instanceof SpitterBlockEntity entity) {
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(entity.serializeNBT());
            Position pos = SpitterBlock.getDispensePosition(blocksourceimpl);
            FluidEffects effects = FluidEffectManager.INSTANCE.find(fluid.getFluid());
            int cost = effects.getAmount(fluid.getFluid());
            int amount = fluid.getAmount();
            if (!fluid.isEmpty() && effects.hasEffects() && Math.min(fluid.getAmount(), amount) > 0&&amount>=cost) {
                ModFluidEffectProjectile spit = new ModFluidEffectProjectile( blocksourceimpl.getLevel(), pos, new FluidStack(fluid, amount), 2);
                spit.shoot(pos.x() - p_52666_.getX() - 0.5, pos.y() - p_52666_.getY() - 0.5, pos.z() - p_52666_.getZ() - 0.5, 5, 0.1f);
                entity.setFluid(new FluidStack(fluid,amount-cost));
                p_52665_.addFreshEntity(spit);
                p_52665_.playSound(null, pos.x(), pos.y(), pos.z(), SoundEvents.LLAMA_SPIT, SoundSource.PLAYERS, 1.0F, 1.0F / (p_52665_.getRandom().nextFloat() * 0.4F + 1.2F));
            } else p_52665_.playSound(null, pos.x(), pos.y(), pos.z(), SoundEvents.LLAMA_ANGRY, SoundSource.PLAYERS, 1.0F, 1.0F / (p_52665_.getRandom().nextFloat() * 0.4F + 1.2F));
        }
    }
    public void neighborChanged(BlockState p_52700_, Level p_52701_, BlockPos p_52702_, Block p_52703_, BlockPos p_52704_, boolean p_52705_)
        {
            boolean flag = p_52701_.hasNeighborSignal(p_52702_) || p_52701_.hasNeighborSignal(p_52702_.above());
            boolean flag1 = p_52700_.getValue(TRIGGERED);
            if (flag && !flag1) {
                p_52701_.scheduleTick(p_52702_, this, 4);
                p_52701_.setBlock(p_52702_, p_52700_.setValue(TRIGGERED, Boolean.valueOf(true)), 4);
            } else if (!flag && flag1) {
                p_52701_.setBlock(p_52702_, p_52700_.setValue(TRIGGERED, Boolean.valueOf(false)), 4);
            }
    }


    public void tick(BlockState p_221075_, ServerLevel p_221076_, BlockPos p_221077_, RandomSource p_221078_) {
        this.dispenseFrom(p_221076_, p_221077_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new SpitterBlockEntity(pos, blockState);
    }
    public static Position getDispensePosition(BlockSource p_52721_) {
        Direction direction = p_52721_.getBlockState().getValue(FACING);
        double d0 = p_52721_.x() + 0.5d*(double) direction.getStepX();
        double d1 = p_52721_.y() + 0.5d*(double) direction.getStepY();
        double d2 = p_52721_.z() + 0.5d*(double) direction.getStepZ();
        return new PositionImpl(d0, d1, d2);
    }
    public boolean hasAnalogOutputSignal(BlockState p_52682_) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState p_52689_, Level p_52690_, BlockPos p_52691_) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_52690_.getBlockEntity(p_52691_));
    }

    public RenderShape getRenderShape(BlockState p_52725_) {
        return RenderShape.MODEL;
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getNearestLookingDirection().getOpposite());
    }
    public BlockState rotate(BlockState p_52716_, Rotation p_52717_) {
        return p_52716_.setValue(FACING, p_52717_.rotate(p_52716_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_52713_, Mirror p_52714_) {
        return p_52713_.rotate(p_52714_.getRotation(p_52713_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_52719_) {
        p_52719_.add(FACING, TRIGGERED);
    }
}
