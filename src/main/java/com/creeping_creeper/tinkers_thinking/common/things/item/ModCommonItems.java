package com.creeping_creeper.tinkers_thinking.common.things.item;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.ModModule;
import com.creeping_creeper.tinkers_thinking.common.things.block.DryingRackBlock;
import com.creeping_creeper.tinkers_thinking.common.things.block.WasteFluidCylinderBlock;
import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.mantle.registration.object.MetalItemObject;
import slimeknights.tconstruct.shared.block.PlatformBlock;
import slimeknights.tconstruct.world.TinkerWorld;

import static net.minecraft.world.level.block.SoundType.*;
import static net.minecraft.world.level.material.MapColor.COLOR_ORANGE;
import static net.minecraft.world.level.material.MapColor.COLOR_YELLOW;
import static slimeknights.tconstruct.fluids.block.BurningLiquidBlock.createBurning;
import static slimeknights.tconstruct.fluids.block.MobEffectLiquidBlock.createEffect;

public class ModCommonItems extends ModModule {

    public static final RegistryObject<CreativeModeTab> tab = CREATIVE_TABS.register(
            "", () -> CreativeModeTab.builder().title(TinkersThinking.makeTranslation("itemGroup", "common"))
                    .icon(() -> ModCommonItems.chromatic_crystal.get().getDefaultInstance())
                    .displayItems(ModCommonItems::addTabItems)
                    .withTabsBefore(TinkerWorld.tabWorld.getId())
                    .build());
    /** Called during construction to initialize the registers for this mod */
    protected static BlockBehaviour.Properties builder(SoundType soundType) {
        return Block.Properties.of().sound(soundType);
    }

    /** Same as above, but with a color */
    protected static BlockBehaviour.Properties builder(MapColor color, SoundType soundType) {
        return builder(soundType).mapColor(color);
    }

    /** Builder that pre-supplies metal properties */
    protected static BlockBehaviour.Properties metalBuilder(MapColor color) {
        return builder(color, SoundType.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f);
    }
    public static final ItemObject<ModBookItem> book = ITEMS.register("fantastic_gadgetry", ()-> new ModBookItem(GENERAL_PROPS, ModBookItem.BookType.FANTASTIC_GADGETRY ));

    //Metal Materials
    public static final MetalItemObject ardite = BLOCKS.registerMetal("ardite", builder( MapColor.COLOR_ORANGE, SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops().strength(30.0f).explosionResistance(1200), FIRE_TOOLTIP_BLOCK_ITEM, FIRE_PROPS);
    public static final MetalItemObject tinkers_bronze = BLOCKS.registerMetal("tinkers_bronze", metalBuilder(MapColor.COLOR_YELLOW), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject lightite = BLOCKS.registerMetal("lightite", metalBuilder(MapColor.COLOR_LIGHT_GRAY), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject chlorophyte = BLOCKS.registerMetal("chlorophyte", metalBuilder(MapColor.COLOR_GREEN), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject spectre = BLOCKS.registerMetal("spectre", metalBuilder(MapColor.COLOR_LIGHT_BLUE), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject shroomite = BLOCKS.registerMetal("shroomite", metalBuilder(MapColor.COLOR_BLUE), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject obsidian_bronze = BLOCKS.registerMetal("obsidian_bronze", metalBuilder(MapColor.COLOR_BROWN), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject electrical_steel = BLOCKS.registerMetal("electrical_steel", metalBuilder(MapColor.COLOR_GRAY), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject beetron = BLOCKS.registerMetal("beetron", metalBuilder(MapColor.COLOR_RED), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject echo_bronze = BLOCKS.registerMetal("echo_bronze", builder(MapColor.COLOR_BLACK, SoundType.SCULK).requiresCorrectToolForDrops().strength(5f).explosionResistance(200), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject warden_steel = BLOCKS.registerMetal("warden_steel", builder(MapColor.COLOR_BLACK, SoundType.SCULK).requiresCorrectToolForDrops().strength(10f).explosionResistance(500), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);

    //Other Materials
    public static final ItemObject<Block> ardite_ore = BLOCKS.register("ardite_ore", () -> new Block(builder(MapColor.NETHER, SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops().strength(30f).explosionResistance(1200)), FIRE_BLOCK_ITEM);
    public static final ItemObject<Block> raw_ardite_block = BLOCKS.register("raw_ardite_block", () -> new Block(builder(MapColor.COLOR_ORANGE, SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops().strength(30f).explosionResistance(1200)), FIRE_BLOCK_ITEM);
    public static final ItemObject<Item> raw_ardite = ITEMS.register("raw_ardite",() -> new Item(new Item.Properties().fireResistant()));
    public static final ItemObject<Item>  lightite_compound= ITEMS.register("lightite_compound", GENERAL_PROPS);
    public static final ItemObject<Block> chlorophyll_ore = BLOCKS.register("chlorophyll_ore", () -> new Block(builder(MapColor.STONE, STONE).requiresCorrectToolForDrops().strength(8F).explosionResistance(400)), GENERAL_TOOLTIP_BLOCK_ITEM);
    public static final ItemObject<Block> deepslate_chlorophyll_ore = BLOCKS.register("deepslate_chlorophyll_ore", () -> new Block(builder(MapColor.DEEPSLATE, SoundType.DEEPSLATE).requiresCorrectToolForDrops().strength(8f).explosionResistance(400)), GENERAL_TOOLTIP_BLOCK_ITEM);
    public static final ItemObject<Item>  chlorophyll_a= ITEMS.register("chlorophyll_a", TOOLTIP_ITEM);
    public static final ItemObject<Item>  chlorophyll_b= ITEMS.register("chlorophyll_b", TOOLTIP_ITEM);
    public static final ItemObject<Item>  chlorophyte_compound= ITEMS.register("chlorophyte_compound", GENERAL_PROPS);
    public static final ItemObject<Item>  spectre_compound= ITEMS.register("spectre_compound", GENERAL_PROPS);
    public static final ItemObject<Item>  shroomite_compound= ITEMS.register("shroomite_compound", GENERAL_PROPS);//Other Materials
    public static final ItemObject<Item>  magma_crystal= ITEMS.register("magma_crystal", GENERAL_PROPS);
    public static final ItemObject<Item>  quartz_crystal= ITEMS.register("quartz_crystal", GENERAL_PROPS);
    public static final ItemObject<Item>  clay_crystal= ITEMS.register("clay_crystal", GENERAL_PROPS);
    public static final ItemObject<Item> chromatic_crystal = ITEMS.register("chromatic_crystal", GENERAL_PROPS);
    public static final ItemObject<Item> surging_wellspring = ITEMS.register("surging_wellspring", GENERAL_PROPS);
    public static final ItemObject<Item>  lightite_reinforcement = ITEMS.register("lightite_reinforcement", GENERAL_PROPS);
    public static final ItemObject<Item>  gilded_silky_cloth= ITEMS.register("gilded_silky_cloth", GENERAL_PROPS);
    public static final ItemObject<Item>  silky_jewel= ITEMS.register("silky_jewel", GENERAL_PROPS);
    public static final ItemObject<Item>  stone_stick= ITEMS.register("stone_stick", GENERAL_PROPS);
    public static final ItemObject<Item>  ashes = ITEMS.register("ashes", TOOLTIP_ITEM);
    public static final ItemObject<Item>  verdant_frogcroaking= ITEMS.register("verdant_frogcroaking", GENERAL_PROPS);
    public static final ItemObject<Item>  ochre_frogcroaking= ITEMS.register("ochre_frogcroaking", GENERAL_PROPS);
    public static final ItemObject<Item>  pearlescent_frogcroaking= ITEMS.register("pearlescent_frogcroaking", GENERAL_PROPS);
    public static final ItemObject<Item>  bacium= ITEMS.register("bacium", GENERAL_PROPS);
    public static final ItemObject<Item>  ancient_compound= ITEMS.register("ancient_compound", GENERAL_PROPS);
    //Block&Block Items
    public static final ItemObject<Block> silky_jewel_block = BLOCKS.register("silky_jewel_block", () -> new Block(metalBuilder(COLOR_YELLOW)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<ChainBlock> soul_vine = BLOCKS.register("soul_vine", () ->new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN).sound(VINE)),GENERAL_TOOLTIP_BLOCK_ITEM);
    public static final ItemObject<ChainBlock> bound_chain = BLOCKS.register("bound_chain", () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN)),GENERAL_TOOLTIP_BLOCK_ITEM);

    public static final RegistryObject<TorchBlock> ground_stone_torch = BLOCKS.registerNoItem("stone_torch",
            () -> new TorchBlock(BlockBehaviour.Properties.of().noCollission().strength(0.6f).lightLevel((p_50886_) -> 14).sound(STONE), ParticleTypes.FLAME));
    public static final RegistryObject<WallTorchBlock> wall_stone_torch = BLOCKS.registerNoItem("wall_stone_torch",
            () -> new WallTorchBlock(BlockBehaviour.Properties.of().noCollission().strength(0.6f).lightLevel((p_50886_) -> 14).sound(STONE), ParticleTypes.FLAME));
    public static final RegistryObject<TorchBlock> ground_stone_soul_torch = BLOCKS.registerNoItem("stone_soul_torch",
            () -> new TorchBlock(BlockBehaviour.Properties.of().noCollission().strength(0.6f).lightLevel((p_50886_) -> 10).sound(STONE), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<WallTorchBlock> wall_stone_soul_torch = BLOCKS.registerNoItem("wall_stone_soul_torch",
            () -> new WallTorchBlock(BlockBehaviour.Properties.of().noCollission().strength(0.6f).lightLevel((p_50886_) -> 10).sound(STONE), ParticleTypes.SOUL_FIRE_FLAME));
    public static final ItemObject<Item> stone_torch_item = ITEMS.register("stone_torch", () -> new  StandingAndWallBlockItem(ModCommonItems.ground_stone_torch.get(), ModCommonItems.wall_stone_torch.get(),GENERAL_PROPS, Direction.DOWN));
    public static final ItemObject<Item> stone_soul_torch_item = ITEMS.register("stone_soul_torch", () -> new  StandingAndWallBlockItem(ModCommonItems.ground_stone_soul_torch.get(), ModCommonItems.wall_stone_soul_torch.get(),GENERAL_PROPS, Direction.DOWN));
    public static final ItemObject<LadderBlock> stone_ladder = BLOCKS.register("stone_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER).sound(LADDER).strength(0.8f)), GENERAL_BLOCK_ITEM);

    public static final ItemObject<Block> ardite_platform = BLOCKS.register("ardite_platform", () -> new PlatformBlock(BlockBehaviour.Properties.of().mapColor(COLOR_ORANGE).strength(30f).explosionResistance(1200).requiresCorrectToolForDrops().sound(ANCIENT_DEBRIS)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<DryingRackBlock> drying_rack = BLOCKS.register("drying_rack", () -> new DryingRackBlock(BlockBehaviour.Properties.of().strength(0.6f).sound(WOOD)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<WasteFluidCylinderBlock> waste_fluid_cylinder = BLOCKS.register("waste_fluid_cylinder", () -> new WasteFluidCylinderBlock(BlockBehaviour.Properties.of().strength(1.2f).sound(BASALT)), GENERAL_TOOLTIP_BLOCK_ITEM);
    //Foods
    public static final ItemObject<Item>  Beef_Jerky= ITEMS.register("beef_jerky", () -> new Item(new Item.Properties().food(ModFoods.Beef_Jerky)));
    public static final ItemObject<Item>  Pork_Jerky= ITEMS.register("pork_jerky", () -> new Item(new Item.Properties().food(ModFoods.Beef_Jerky)));
    public static final ItemObject<Item>  Mutton_Jerky= ITEMS.register("mutton_jerky", () -> new Item(new Item.Properties().food(ModFoods.Mutton_Jerky)));
    public static final ItemObject<Item>  Rabbit_Jerky= ITEMS.register("rabbit_jerky", () -> new Item(new Item.Properties().food(ModFoods.Rabbit_Jerky)));
    public static final ItemObject<Item>  Chicken_Jerky= ITEMS.register("chicken_jerky", () -> new Item(new Item.Properties().food(ModFoods.Rabbit_Jerky)));
    public static final ItemObject<Item>  Cod_Jerky= ITEMS.register("cod_jerky", () -> new Item(new Item.Properties().food(ModFoods.Rabbit_Jerky)));
    public static final ItemObject<Item>  Salmon_Jerky= ITEMS.register("salmon_jerky", () -> new Item(new Item.Properties().food(ModFoods.Mutton_Jerky)));
    public static final ItemObject<Item>  Tropical_Fish_Jerky= ITEMS.register("tropical_fish_jerky", () -> new Item(new Item.Properties().food(ModFoods.Fish_Jerky)));
    public static final ItemObject<Item>  Pufferfish_Jerky= ITEMS.register("pufferfish_jerky", () -> new Item(new Item.Properties().food(ModFoods.Fish_Jerky)));
    public static final ItemObject<Item>  Rotten_Flesh_Jerky= ITEMS.register("rotten_flesh_jerky", () -> new Item(new Item.Properties().food(ModFoods.Rotten_Flesh_Jerky)));
    public static final ItemObject<Item>  Fried_Egg= ITEMS.register("fried_egg", () -> new Item(new Item.Properties().food(ModFoods.Fried_Egg)));
    public static final ItemObject<Item>  Earth_Slime_Drop= ITEMS.register("earth_slime_drop", () -> new Item(new Item.Properties().food(ModFoods.Earth_Slime_Drop)));
    public static final ItemObject<Item>  Sky_Slime_Drop= ITEMS.register("sky_slime_drop", () -> new Item(new Item.Properties().food(ModFoods.Sky_Slime_Drop)));
    public static final ItemObject<Item>  Magma_Slime_Drop= ITEMS.register("magma_slime_drop", () -> new Item(new Item.Properties().food(ModFoods.Magma_Slime_Drop)));
    public static final ItemObject<Item>  Ichor_Slime_Drop= ITEMS.register("ichor_slime_drop", () -> new Item(new Item.Properties().food(ModFoods.Ichor_Slime_Drop)));
    public static final ItemObject<Item>  Ender_Slime_Drop= ITEMS.register("ender_slime_drop", () -> new Item(new Item.Properties().food(ModFoods.Ender_Slime_Drop)));
    public static final ItemObject<Item>  Black_Chocolate= ITEMS.register("black_chocolate", () -> new Item(new Item.Properties().food(ModFoods.Black_Chocolate)));
    public static final ItemObject<Item>  White_Chocolate= ITEMS.register("white_chocolate", () -> new Item(new Item.Properties().food(ModFoods.White_Chocolate)));
    //fluids
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_ardite   = FLUIDS.register("molten_ardite").type(hot().temperature(1296).lightLevel(15)).block(createBurning(MapColor.COLOR_ORANGE,15, 10, 9f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_tinkers_bronze   = FLUIDS.register("molten_tinkers_bronze").type(hot().temperature(550).lightLevel(15)).block(createBurning(MapColor.COLOR_YELLOW,15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_lightite   = FLUIDS.register("molten_lightite").type(hot().temperature(450).lightLevel(15)).block(createBurning(MapColor.COLOR_LIGHT_GRAY,15, 10, 4f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_chlorophyte   = FLUIDS.register("molten_chlorophyte").type(hot().temperature(600).lightLevel(15)).block(createBurning(MapColor.COLOR_GREEN,15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_spectre   = FLUIDS.register("molten_spectre").type(hot().temperature(600).lightLevel(15)).block(createBurning(MapColor.COLOR_LIGHT_BLUE,15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_shroomite  = FLUIDS.register("molten_shroomite").type(hot().temperature(600).lightLevel(15)).block(createBurning(MapColor.COLOR_BLUE,15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_obsidian_bronze  = FLUIDS.register("molten_obsidian_bronze").type(hot().temperature(950).lightLevel(15)).block(createBurning(MapColor.COLOR_BROWN,15, 10, 6f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_electrical_steel   = FLUIDS.register("molten_electrical_steel").type(hot().temperature(1296).lightLevel(15)).block(createBurning(MapColor.COLOR_GRAY,15, 10, 6f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_beetron   = FLUIDS.register("molten_beetron").type(hot().temperature(400).lightLevel(15)).block(createBurning(MapColor.COLOR_RED,15, 10, 4f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_echo_bronze   = FLUIDS.register("molten_echo_bronze").type(hot().temperature(150).lightLevel(3)).block(createBurning(MapColor.COLOR_BLACK,3, 10, 3f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_warden_steel   = FLUIDS.register("molten_warden_steel").type(hot().temperature(750).lightLevel(12)).block(createBurning(MapColor.COLOR_BLACK,12, 10, 3f)).bucket().flowing();

    //
    public static final FlowingFluidObject<ForgeFlowingFluid> reburn_ashes   = FLUIDS.register("reburn_ashes").type(hot().temperature(1500).lightLevel(15)).block(createBurning(MapColor.COLOR_RED,15, 10, 9f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_echo  = FLUIDS.register("molten_echo").type(common().temperature(50).lightLevel(3)).block(createEffect(MapColor.COLOR_BLACK,3, () -> new MobEffectInstance(MobEffects.DARKNESS, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> liquid_sculk_power  = FLUIDS.register("liquid_sculk_power").type(common().temperature(50).lightLevel(3)).block(createEffect(MapColor.COLOR_BLACK,3, () -> new MobEffectInstance(ModEffects.sculk_power.get(), 80))).bucket().flowing();

    public static final FlowingFluidObject<ForgeFlowingFluid> syrup  = FLUIDS.register("syrup").type(common().temperature(75).lightLevel(0)).block(createEffect(MapColor.COLOR_ORANGE,0, () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> pulp  = FLUIDS.register("pulp").type(common().temperature(25).lightLevel(0)).block(createEffect(MapColor.COLOR_LIGHT_GRAY,0, () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100))).bucket().flowing();

    public static final FlowingFluidObject<ForgeFlowingFluid> molten_cocoa  = FLUIDS.register("molten_cocoa").type(hot().temperature(100).lightLevel(7)).block(createEffect(MapColor.COLOR_ORANGE, 7,() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_black_chocolate  = FLUIDS.register("molten_black_chocolate").type(hot().temperature(90).lightLevel(7)).block(createEffect(MapColor.COLOR_BLACK,7, () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_white_chocolate  = FLUIDS.register("molten_white_chocolate").type(hot().temperature(80).lightLevel(7)).block(createEffect(MapColor.COLOR_LIGHT_GRAY,7, () -> new MobEffectInstance(MobEffects.DIG_SPEED, 100))).bucket().flowing();
    private static FluidType.Properties common() {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .motionScale(0.0023333333333333335D)
                .canExtinguish(true);
    }
    private static FluidType.Properties hot() {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                .motionScale(0.0023333333333333335D)
                .canSwim(false).canDrown(false)
                .pathType(BlockPathTypes.LAVA).adjacentPathType(null);

    }
    private static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(book);
        accept(output, ardite);
        accept(output, tinkers_bronze);
        accept(output, lightite);
        accept(output, chlorophyte);
        accept(output, spectre);
        accept(output, shroomite);
        accept(output, obsidian_bronze);
        accept(output, electrical_steel);
        accept(output, beetron);
        accept(output, echo_bronze);
        accept(output, warden_steel);
        output.accept(ardite_ore);
        output.accept(raw_ardite);
        output.accept(raw_ardite_block);
        output.accept(lightite_compound);
        output.accept(lightite_reinforcement);
        output.accept(chlorophyll_ore);
        output.accept(deepslate_chlorophyll_ore);
        output.accept(chlorophyll_a);
        output.accept(chlorophyll_b);
        output.accept(chlorophyte_compound);
        output.accept(spectre_compound);
        output.accept(shroomite_compound);
        output.accept(clay_crystal);
        output.accept(quartz_crystal);
        output.accept(magma_crystal);
        output.accept(chromatic_crystal);
        output.accept(stone_stick);
        output.accept(gilded_silky_cloth);
        output.accept(silky_jewel);
        output.accept(silky_jewel_block);
        output.accept(surging_wellspring);
        output.accept(ashes);
        output.accept(ochre_frogcroaking);
        output.accept(pearlescent_frogcroaking);
        output.accept(verdant_frogcroaking);
        output.accept(bacium);
        output.accept(ancient_compound);

        output.accept(soul_vine);
        output.accept(bound_chain);
        output.accept(stone_torch_item);
        output.accept(stone_soul_torch_item);
        output.accept(stone_ladder);
        output.accept(ardite_platform);
        output.accept(drying_rack);
        output.accept(waste_fluid_cylinder);
        output.accept(Fried_Egg);
        output.accept(Beef_Jerky);
        output.accept(Chicken_Jerky);
        output.accept(Pork_Jerky);
        output.accept(Mutton_Jerky);
        output.accept(Rabbit_Jerky);
        output.accept(Rotten_Flesh_Jerky);
        output.accept(Salmon_Jerky);
        output.accept(Cod_Jerky);
        output.accept(Tropical_Fish_Jerky);
        output.accept(Pufferfish_Jerky);
        output.accept(Earth_Slime_Drop);
        output.accept(Sky_Slime_Drop);
        output.accept(Ichor_Slime_Drop);
        output.accept(Magma_Slime_Drop);
        output.accept(Ender_Slime_Drop);
        output.accept(Black_Chocolate);
        output.accept(White_Chocolate);

        output.accept(molten_ardite);
        output.accept(molten_tinkers_bronze);
        output.accept(molten_lightite);
        output.accept(molten_chlorophyte);
        output.accept(molten_spectre);
        output.accept(molten_shroomite);
        output.accept(molten_obsidian_bronze);
        output.accept(molten_electrical_steel);
        output.accept(molten_beetron);
        output.accept(molten_echo_bronze);
        output.accept(molten_warden_steel);

        output.accept(reburn_ashes);
        output.accept(molten_echo);
        output.accept(liquid_sculk_power);
        output.accept(syrup);
        output.accept(pulp);

        output.accept(molten_cocoa);
        output.accept(molten_black_chocolate);
        output.accept(molten_white_chocolate);
    }
    private static void accept(CreativeModeTab.Output output, MetalItemObject metal) {
        output.accept(metal.getIngot());
        output.accept(metal.getNugget());
        output.accept(metal.get());
    }
}
