package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.mantle.item.TooltipItem;
import slimeknights.mantle.registration.deferred.*;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;

import slimeknights.tconstruct.common.registration.FluidDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModModule {
    protected static final BlockDeferredRegisterExtension BLOCKS = new BlockDeferredRegisterExtension(TinkersThinking.MODID);
    protected static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(TinkersThinking.MODID);
    protected static final FluidDeferredRegisterExtension FLUIDS = new FluidDeferredRegisterExtension(TinkersThinking.MODID);
    protected static final SynchronizedDeferredRegister<CreativeModeTab> CREATIVE_TABS = SynchronizedDeferredRegister.create(Registries.CREATIVE_MODE_TAB, TinkersThinking.MODID);
    protected static final EnumDeferredRegister<MobEffect> MOB_EFFECTS = new EnumDeferredRegister<>(Registries.MOB_EFFECT, TinkersThinking.MODID);
    protected static final EntityTypeDeferredRegister ENTITIES = new EntityTypeDeferredRegister(TinkersThinking.MODID);
    protected static final BlockEntityTypeDeferredRegister BLOCK_ENTITIES = new BlockEntityTypeDeferredRegister(TinkersThinking.MODID);
    protected static final Item.Properties GENERAL_PROPS = new Item.Properties();
    protected static final Item.Properties FIRE_PROPS = new Item.Properties().fireResistant();

    protected static final Supplier<Item> TOOLTIP_ITEM = () -> new TooltipItem(GENERAL_PROPS);
    protected static final Function<Block,? extends BlockItem> GENERAL_BLOCK_ITEM = (b) -> new BlockItem(b, GENERAL_PROPS);
    protected static final Function<Block,? extends BlockItem> FIRE_BLOCK_ITEM = (b) -> new BlockItem(b,FIRE_PROPS);
    protected static final Item.Properties Stack1Item = new Item.Properties().stacksTo(1);
    protected static final Function<Block,? extends BlockItem> GENERAL_TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, GENERAL_PROPS);
    protected static final Function<Block,? extends BlockItem> FIRE_TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, FIRE_PROPS);
    public static void initRegisters() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        FLUIDS.register(bus);
        CREATIVE_TABS.register(bus);
        MOB_EFFECTS.register(bus);
        BLOCK_ENTITIES.register(bus);
        ENTITIES.register(bus);
    }
}
