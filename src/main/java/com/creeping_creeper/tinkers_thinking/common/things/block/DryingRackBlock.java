package com.creeping_creeper.tinkers_thinking.common.things.block;

import com.creeping_creeper.tinkers_thinking.common.things.block.entity.DryingRackBlockEntity;
import com.creeping_creeper.tinkers_thinking.common.things.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DryingRackBlock extends BaseEntityBlock {
    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState p_49232_) {
        return RenderShape.MODEL;
    }
    @Override
    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos blockPos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity block = level.getBlockEntity(blockPos);
            if(block instanceof DryingRackBlockEntity){
                ((DryingRackBlockEntity) block).drops();
            }

        }
        super.onRemove(state, level, blockPos, newState, isMoving);
    }
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level,
                                          @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult res) {
        if (!level.isClientSide()){
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof DryingRackBlockEntity){
                if (((DryingRackBlockEntity) entity).itemStackHandler.getStackInSlot(0).isEmpty()&&((DryingRackBlockEntity) entity).itemStackHandler.getStackInSlot(1).isEmpty()) {
                    if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                        ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
                        CompoundTag tag = itemStack.getTag();
                        ItemStack itemStack2 = new ItemStack(itemStack.getItem(),1);
                        itemStack2.setTag(tag);
                        ((DryingRackBlockEntity) entity).itemStackHandler.setStackInSlot(0, itemStack2);
                        itemStack.shrink(1);
                        return InteractionResult.SUCCESS;
                    } else if (!player.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
                        ItemStack itemStack = player.getItemInHand(InteractionHand.OFF_HAND);
                        CompoundTag tag = itemStack.getTag();
                        ItemStack itemStack2 = new ItemStack(itemStack.getItem(),1);
                        itemStack2.setTag(tag);
                        ((DryingRackBlockEntity) entity).itemStackHandler.setStackInSlot(0, itemStack2);
                        itemStack.shrink(1);
                        return InteractionResult.SUCCESS;
                    }
                    return InteractionResult.CONSUME;
                } else if (!((DryingRackBlockEntity) entity).itemStackHandler.getStackInSlot(0).isEmpty()) {
                    ItemHandlerHelper.giveItemToPlayer(player, ((DryingRackBlockEntity) entity).itemStackHandler.getStackInSlot(0), player.getInventory().selected);
                    ((DryingRackBlockEntity) entity).itemStackHandler.extractItem(0, 1, false);
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                } else  {
                    ItemHandlerHelper.giveItemToPlayer(player, ((DryingRackBlockEntity) entity).itemStackHandler.getStackInSlot(1), player.getInventory().selected);
                    ((DryingRackBlockEntity) entity).itemStackHandler.extractItem(1, 1, false);
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.CONSUME;
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DryingRackBlockEntity(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.Drying_Rack.get(),
                DryingRackBlockEntity::tick);
    }
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;// 具有属性是水平的朝向
    public DryingRackBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    private static final VoxelShape EAST =
            Block.box(6, 12, 0, 10, 16, 16);
    private static final VoxelShape NORTH =
            Block.box(0, 12, 6, 16, 16, 10);

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        Direction direction = p_60555_.getValue(FACING);
        return switch (direction) {
            case EAST, WEST -> EAST;
            default -> NORTH;
        };
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }
    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
