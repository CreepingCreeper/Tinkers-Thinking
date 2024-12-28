package com.creeping_creeper.tinkers_thinking.common.things;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.mantle.item.TooltipItem;
import slimeknights.mantle.registration.deferred.BlockEntityTypeDeferredRegister;
import slimeknights.mantle.registration.deferred.EntityTypeDeferredRegister;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.EnumDeferredRegister;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ModModule {
    protected static final BlockDeferredRegisterExtension BLOCKS = new BlockDeferredRegisterExtension(TinkersThinking.MODID);
    protected static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(TinkersThinking.MODID);
    protected static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(TinkersThinking.MODID);
    protected static final EnumDeferredRegister<MobEffect> MOB_EFFECTS = new EnumDeferredRegister<>(Registry.MOB_EFFECT_REGISTRY, TinkersThinking.MODID);
    // gameplay instances
    protected static final BlockEntityTypeDeferredRegister BLOCK_ENTITIES = new BlockEntityTypeDeferredRegister(TinkersThinking.MODID);
    protected static final EntityTypeDeferredRegister ENTITIES = new EntityTypeDeferredRegister(TinkersThinking.MODID);
    // datapacks
    protected static final SynchronizedDeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = SynchronizedDeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TinkersThinking.MODID);
    public static final CreativeModeTab Tinkers_Thinking_Tab = new CreativeModeTab("tinkers_thinking") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.chromatic_crystal);
        }
    };
    protected static final Item.Properties GENERAL_PROPS = new Item.Properties().tab(ModModule.Tinkers_Thinking_Tab);
    protected static final Item.Properties FIRE_PROPS = new Item.Properties().tab(ModModule.Tinkers_Thinking_Tab).fireResistant();
    protected static final Item.Properties Stack1Item = new Item.Properties().stacksTo(1).tab(Tinkers_Thinking_Tab);
    protected static final Supplier<Item> TOOLTIP_ITEM = () -> new TooltipItem(GENERAL_PROPS);
    protected static final Function<Block,? extends BlockItem> GENERAL_BLOCK_ITEM = (b) -> new BlockItem(b, GENERAL_PROPS);
    protected static final Function<Block,? extends BlockItem> FIRE_BLOCK_ITEM = (b) -> new BlockItem(b,FIRE_PROPS);
    protected static final Function<Block,? extends BlockItem> GENERAL_TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, GENERAL_PROPS);
    protected static final Function<Block,? extends BlockItem> FIRE_TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, FIRE_PROPS);
    /* Creative tab for items that do not fit in another tab */
    // base item properties

    /** Called during construction to initialize the registers for this mod */
    public static void initRegisters() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // gameplay singleton
        BLOCKS.register(bus);
        ITEMS.register(bus);
        FLUIDS.register(bus);
        MOB_EFFECTS.register(bus);
        // gameplay instance
        BLOCK_ENTITIES.register(bus);
        ENTITIES.register(bus);
        // datapacks
        RECIPE_SERIALIZERS.register(bus);
    }
    protected static BlockBehaviour.Properties builder(Material material, SoundType soundType) {
        return Block.Properties.of(material).sound(soundType);
    }

    /** Same as above, but with a color */
    protected static BlockBehaviour.Properties builder(Material material, MaterialColor color, SoundType soundType) {
        return Block.Properties.of(material, color).sound(soundType);
    }

    /** Builder that pre-supplies metal properties */
    protected static BlockBehaviour.Properties metalBuilder(MaterialColor color,float strength,int resistance) {
        return builder(Material.METAL, color, SoundType.METAL).requiresCorrectToolForDrops().strength(strength).explosionResistance(resistance);
    }
    protected static ResourceLocation resource(String path) {
        return TinkersThinking.getResource(path);
    }
}

