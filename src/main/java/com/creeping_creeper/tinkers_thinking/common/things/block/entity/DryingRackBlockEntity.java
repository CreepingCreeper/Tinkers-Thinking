package com.creeping_creeper.tinkers_thinking.common.things.block.entity;

import com.creeping_creeper.tinkers_thinking.common.networking.ModMessages;
import com.creeping_creeper.tinkers_thinking.common.networking.packet.packet.ItemStackSyncS2CPacket;
import com.creeping_creeper.tinkers_thinking.common.recipes.DryingRackRecipes;
import com.creeping_creeper.tinkers_thinking.common.register.ModBlockEntities;
import com.creeping_creeper.tinkers_thinking.common.things.block.DryingRackBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.block.entity.MantleBlockEntity;

import java.util.Map;
import java.util.Optional;

public class DryingRackBlockEntity extends MantleBlockEntity {
    public final ItemStackHandler itemStackHandler = new ItemStackHandler(2) {
        @Override
        public int getSlotLimit(int slot) {return 1;}
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> getStackInSlot(1).isEmpty();
                case 1 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
                Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemStackHandler, (i) -> i == 1,
                                (i, s) -> false)),
                        Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemStackHandler, (i) -> i == 1,
                                (index, stack) -> itemStackHandler.isItemValid(0, stack))),
                        Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemStackHandler, (i) -> i == 1,
                                (index, stack) -> itemStackHandler.isItemValid(0, stack))),
                        Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemStackHandler, (i) -> i == 1,
                                (index, stack) -> itemStackHandler.isItemValid(0, stack))),
                        Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemStackHandler, (i) -> i == 1,
                                (index, stack) -> itemStackHandler.isItemValid(0, stack))),
                        Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemStackHandler, (i) -> i == 1,
                                (index, stack) -> itemStackHandler.isItemValid(0, stack))));
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 2998;
    private static boolean canInsertItemToOutputSlot(SimpleContainer inventory) {
        return  inventory.getItem(1).isEmpty();
    }
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public DryingRackBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.Drying_Rack.get(), blockPos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0-> DryingRackBlockEntity.this.progress;
                    case 1->DryingRackBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0->DryingRackBlockEntity.this.progress = value;
                    case 1->DryingRackBlockEntity.this.maxProgress = value;
                }
            }
            @Override
            public int getCount() {
                return 2;
            }
        };
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) {
                return lazyItemHandler.cast();
            }
            if (directionWrappedHandlerMap.containsKey(side)) {
                if (itemStackHandler.getStackInSlot(0).isEmpty()&& itemStackHandler.getStackInSlot(1).isEmpty()) {
                    Direction LocalDir = this.getBlockState().getValue(DryingRackBlock.FACING);
                    if (side == Direction.UP || side == Direction.DOWN) {
                        return directionWrappedHandlerMap.get(side).cast();
                    }
                    return switch (LocalDir) {
                        default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                        case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                        case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                        case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                    };
                }else return directionWrappedHandlerMap.get(Direction.DOWN).cast();
            }
        }
        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(()->itemStackHandler);
    }
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory",itemStackHandler.serializeNBT());
        nbt.putInt("drying_rack.progress",this.progress);
        super.saveAdditional(nbt);

    }
    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.progress = nbt.getInt("drying_rack.progress");
    }
    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for(int i = 0;i<itemStackHandler.getSlots();i++){
            inventory.setItem(i,itemStackHandler.getStackInSlot(i));
        }
        if (this.level != null) {
            Containers.dropContents(this.level,this.worldPosition,inventory);
        }
    }
    public static void tick(Level level, BlockPos blockPos, BlockState state, DryingRackBlockEntity entity) {
        if (!level.isClientSide) {
            ModMessages.sendToClients(new ItemStackSyncS2CPacket(entity.itemStackHandler, blockPos));
        }
        if(hasRecipe(entity)){
            entity.progress ++ ;
            setChanged(level,blockPos,state);
            if(entity.progress >= entity.maxProgress){
                craftItem(entity);
            }
        }else{
            entity.resetProgress();
            setChanged(level,blockPos,state);
        }
    }
    private void resetProgress() {
        this.progress = 0;
    }
    private static void craftItem(DryingRackBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
        for(int i=0;i < entity.itemStackHandler.getSlots() ; i++){
            inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
        }
        Optional<DryingRackRecipes> recipe = Optional.empty();
        if (level != null) {
            recipe = level.getRecipeManager().getRecipeFor(DryingRackRecipes.Type.INSTANCE,inventory,level);
        }

        if(hasRecipe(entity)){
            entity.itemStackHandler.extractItem(0,1,false);
            entity.itemStackHandler.setStackInSlot(1,new ItemStack(recipe.get().getResultItem(level.registryAccess()).getItem(), 1));
            entity.resetProgress();
        }
    }
    private static boolean hasRecipe(DryingRackBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
        for(int i = 0; i< entity.itemStackHandler.getSlots(); i++){
            inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
        }
        Optional<DryingRackRecipes> recipe = Optional.empty();
        if (level != null) {
            recipe = level.getRecipeManager().getRecipeFor(DryingRackRecipes.Type
                    .INSTANCE,inventory,level);
        }

        return recipe.isPresent()&& canInsertItemToOutputSlot(inventory);
    }
    public ItemStack getRenderStack(){
        ItemStack itemStack;
        if(itemStackHandler.getStackInSlot(1).isEmpty()){
            itemStack = itemStackHandler.getStackInSlot(0);
        }else{
            itemStack = itemStackHandler.getStackInSlot(1);
        }
        return itemStack;
    }
    public void setHandler(ItemStackHandler itemStackHandler){
        for(int i=0;i<itemStackHandler.getSlots();i++){
            this.itemStackHandler.setStackInSlot(i,itemStackHandler.getStackInSlot(i));
        }
    }

}
