package com.creeping_creeper.tinkers_thinking.common.things;

import com.creeping_creeper.tinkers_thinking.common.things.block.DryingRackBlock;
import com.creeping_creeper.tinkers_thinking.common.things.block.HeavyCoreBlock;
import com.creeping_creeper.tinkers_thinking.common.things.block.SpitterBlock;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModFoods;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModifiableRepeatingCrossbowItem;
import com.creeping_creeper.tinkers_thinking.common.tinkering.ToolDefinitions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.mantle.registration.object.MetalItemObject;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableBowItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.shared.block.PlatformBlock;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static net.minecraft.world.level.block.SoundType.STONE;
import static net.minecraft.world.level.block.SoundType.WOOD;
import static net.minecraft.world.level.block.SoundType.*;
import static net.minecraft.world.level.material.MaterialColor.*;

public class ModItems extends ModModule{
    //Tools
    public static final ItemObject<ModifiableItem> paxel = ITEMS.register( "paxel", () -> new ModifiableItem(Stack1Item, ToolDefinitions.PAXEL));
    public static final ItemObject<ModifiableItem>  knife = ITEMS.register( "knife", () -> new ModifiableItem(Stack1Item, ToolDefinitions.KNIFE));
    public static final ItemObject<ModifiableItem>  mace = ITEMS.register( "mace", () -> new ModifiableItem(Stack1Item, ToolDefinitions.MACE));
    public static final ItemObject<ModifiableBowItem> arrow_thrower = ITEMS.register("arrow_thrower", () -> new ModifiableBowItem(Stack1Item,  ToolDefinitions.ARROW_THROWER));
    public static final ItemObject<ModifiableRepeatingCrossbowItem>  repeating_crossbow = ITEMS.register( "repeating_crossbow", () -> new ModifiableRepeatingCrossbowItem(Stack1Item,ToolDefinitions.REPEATING_CROSSBOW));
    public static final ItemObject<ModifiableItem> magma_staff = ITEMS.register("magma_staff", () -> new ModifiableItem(Stack1Item, ToolDefinitions.MAGMA_STAFF));
    public static final ItemObject<ModifiableItem> clay_staff = ITEMS.register("clay_staff", () -> new ModifiableItem(Stack1Item, ToolDefinitions.CLAY_STAFF));
    public static final ItemObject<ModifiableItem> quartz_staff = ITEMS.register("quartz_staff", () -> new ModifiableItem(Stack1Item, ToolDefinitions.QUARTZ_STAFF));
    public static final ItemObject<ModifiableItem> seared_bucket = ITEMS.register("seared_bucket", () -> new ModifiableItem(Stack1Item, ToolDefinitions.SEARED_BUCKET));
    public static final ItemObject<ModifiableItem> tinkers_bronze_bucket = ITEMS.register("tinkers_bronze_bucket", () -> new ModifiableItem(Stack1Item, ToolDefinitions.TINKERS_BRONZE_BUCKET));
    public static final ItemObject<ModifiableItem> battle_bucket = ITEMS.register("battle_bucket", () -> new ModifiableItem(Stack1Item.fireResistant(), ToolDefinitions.BATTLE_BUCKET));
    public static final ItemObject<ToolPartItem> narrow_blade = ITEMS.register("narrow_blade", () -> new ToolPartItem(GENERAL_PROPS, HeadMaterialStats.ID));
    //Metal Materials
    public static final MetalItemObject ardite = BLOCKS.registerMetal("ardite", builder(Material.METAL, MaterialColor.COLOR_ORANGE, SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops().strength(30.0f).explosionResistance(1200), FIRE_TOOLTIP_BLOCK_ITEM, FIRE_PROPS);
    public static final MetalItemObject tinkers_bronze = BLOCKS.registerMetal("tinkers_bronze", metalBuilder(MaterialColor.COLOR_YELLOW,10f,800), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject lightite = BLOCKS.registerMetal("lightite", metalBuilder(MaterialColor.COLOR_LIGHT_GRAY,5f,200), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject chlorophyte = BLOCKS.registerMetal("chlorophyte", metalBuilder(MaterialColor.COLOR_GREEN,8f,400), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject spectre = BLOCKS.registerMetal("spectre", metalBuilder(MaterialColor.COLOR_LIGHT_BLUE,8f,400), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject shroomite = BLOCKS.registerMetal("shroomite", metalBuilder(MaterialColor.COLOR_BLUE,8f,400), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject obsidian_bronze = BLOCKS.registerMetal("obsidian_bronze", metalBuilder(MaterialColor.COLOR_BROWN,15f,1000), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject electrical_steel = BLOCKS.registerMetal("electrical_steel", metalBuilder(MaterialColor.COLOR_GRAY,10f,800), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject beetron = BLOCKS.registerMetal("beetron", metalBuilder(MaterialColor.COLOR_RED,8f,400), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject echo_bronze = BLOCKS.registerMetal("echo_bronze", builder(Material.METAL, MaterialColor.COLOR_BLACK, SoundType.SCULK).requiresCorrectToolForDrops().strength(5f).explosionResistance(200), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    //Other Materials
    public static final ItemObject<Block> ardite_ore = BLOCKS.register("ardite_ore", () -> new Block(builder(Material.STONE, MaterialColor.NETHER, SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops().strength(30f).explosionResistance(1200)), FIRE_BLOCK_ITEM);
    public static final ItemObject<Block> raw_ardite_block = BLOCKS.register("raw_ardite_block", () -> new Block(builder(Material.METAL, MaterialColor.COLOR_ORANGE, SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops().strength(30f).explosionResistance(1200)), FIRE_BLOCK_ITEM);
    public static final ItemObject<Item> raw_ardite = ITEMS.register("raw_ardite",() -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).fireResistant()));
    public static final ItemObject<Item>  lightite_compound= ITEMS.register("lightite_compound", GENERAL_PROPS);
    public static final ItemObject<Block> chlorophyll_ore = BLOCKS.register("chlorophyll_ore", () -> new Block(builder(Material.STONE, MaterialColor.STONE, STONE).requiresCorrectToolForDrops().strength(8F).explosionResistance(400)), GENERAL_TOOLTIP_BLOCK_ITEM);
    public static final ItemObject<Block> deepslate_chlorophyll_ore = BLOCKS.register("deepslate_chlorophyll_ore", () -> new Block(builder(Material.STONE, MaterialColor.DEEPSLATE, SoundType.DEEPSLATE).requiresCorrectToolForDrops().strength(8f).explosionResistance(400)), GENERAL_TOOLTIP_BLOCK_ITEM);
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
    //BlockItems
    public static final ItemObject<Block> silky_jewel_block = BLOCKS.register("silky_jewel_block", () -> new Block(metalBuilder(COLOR_YELLOW,8f,400)), GENERAL_BLOCK_ITEM);

    public static final ItemObject<Block> ardite_platform = BLOCKS.register("ardite_platform", () -> new PlatformBlock(BlockBehaviour.Properties.of(Material.METAL).strength(30f).explosionResistance(1200).requiresCorrectToolForDrops().sound(ANCIENT_DEBRIS).color(COLOR_ORANGE)), GENERAL_BLOCK_ITEM);
    //Block&Block Items
    public static final ItemObject<HeavyCoreBlock> heavy_core = BLOCKS.register("heavy_core", () -> new HeavyCoreBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f).explosionResistance(1000).requiresCorrectToolForDrops().sound(ANCIENT_DEBRIS).color(COLOR_GRAY)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LadderBlock> stone_ladder = BLOCKS.register("stone_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER).sound(LADDER).strength(0.8f)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<DryingRackBlock> drying_rack = BLOCKS.register("drying_rack", () -> new DryingRackBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.6f).sound(WOOD)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<SpitterBlock> seared_spitter = BLOCKS.register("seared_spitter", () -> new SpitterBlock(BlockBehaviour.Properties.of(Material.STONE).strength(8f).sound(BASALT).color(COLOR_GRAY)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<SpitterBlock> scorched_spitter = BLOCKS.register("scorched_spitter", () -> new SpitterBlock(BlockBehaviour.Properties.of(Material.STONE).strength(8f).sound(BASALT).color(COLOR_BROWN)), GENERAL_BLOCK_ITEM);
    public static final RegistryObject<TorchBlock> ground_stone_torch = BLOCKS.registerNoItem("stone_torch",
            () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.6f).lightLevel((p_50886_) -> 14).sound(STONE), ParticleTypes.FLAME));
    public static final RegistryObject<WallTorchBlock> wall_stone_torch = BLOCKS.registerNoItem("wall_stone_torch",
            () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.6f).lightLevel((p_50886_) -> 14).sound(STONE), ParticleTypes.FLAME));
    public static final RegistryObject<TorchBlock> ground_stone_soul_torch = BLOCKS.registerNoItem("stone_soul_torch",
            () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.6f).lightLevel((p_50886_) -> 10).sound(STONE), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<WallTorchBlock> wall_stone_soul_torch = BLOCKS.registerNoItem("wall_stone_soul_torch",
            () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.6f).lightLevel((p_50886_) -> 10).sound(STONE), ParticleTypes.SOUL_FIRE_FLAME));
    public static final ItemObject<Item> stone_torch_item =ITEMS.register("stone_torch", () -> new  StandingAndWallBlockItem(ModItems.ground_stone_torch.get(), ModItems.wall_stone_torch.get(),GENERAL_PROPS));
    public static final ItemObject<Item> stone_soul_torch_item =ITEMS.register("stone_soul_torch", () -> new  StandingAndWallBlockItem(ModItems.ground_stone_soul_torch.get(), ModItems.wall_stone_soul_torch.get(),GENERAL_PROPS));
    //Foods
    public static final ItemObject<Item>  Beef_Jerky= ITEMS.register("beef_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Beef_Jerky)));
    public static final ItemObject<Item>  Pork_Jerky= ITEMS.register("pork_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Beef_Jerky)));
    public static final ItemObject<Item>  Mutton_Jerky= ITEMS.register("mutton_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Mutton_Jerky)));
    public static final ItemObject<Item>  Rabbit_Jerky= ITEMS.register("rabbit_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Rabbit_Jerky)));
    public static final ItemObject<Item>  Chicken_Jerky= ITEMS.register("chicken_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Rabbit_Jerky)));
    public static final ItemObject<Item>  Cod_Jerky= ITEMS.register("cod_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Rabbit_Jerky)));
    public static final ItemObject<Item>  Salmon_Jerky= ITEMS.register("salmon_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Mutton_Jerky)));
    public static final ItemObject<Item>  Tropical_Fish_Jerky= ITEMS.register("tropical_fish_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Fish_Jerky)));
    public static final ItemObject<Item>  Pufferfish_Jerky= ITEMS.register("pufferfish_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Fish_Jerky)));
    public static final ItemObject<Item>  Rotten_Flesh_Jerky= ITEMS.register("rotten_flesh_jerky", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Rotten_Flesh_Jerky)));
    public static final ItemObject<Item>  Fried_Egg= ITEMS.register("fried_egg", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Fried_Egg)));
    public static final ItemObject<Item>  Earth_Slime_Drop= ITEMS.register("earth_slime_drop", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Earth_Slime_Drop)));
    public static final ItemObject<Item>  Sky_Slime_Drop= ITEMS.register("sky_slime_drop", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Sky_Slime_Drop)));
    public static final ItemObject<Item>  Magma_Slime_Drop= ITEMS.register("magma_slime_drop", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Magma_Slime_Drop)));
    public static final ItemObject<Item>  Ichor_Slime_Drop= ITEMS.register("ichor_slime_drop", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Ichor_Slime_Drop)));
    public static final ItemObject<Item>  Ender_Slime_Drop= ITEMS.register("ender_slime_drop", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Ender_Slime_Drop)));
    public static final ItemObject<Item>  Black_Chocolate= ITEMS.register("black_chocolate", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.Black_Chocolate)));
    public static final ItemObject<Item>  White_Chocolate= ITEMS.register("white_chocolate", () -> new Item(new Item.Properties().tab(Tinkers_Thinking_Tab).food(ModFoods.White_Chocolate)));
    //Misc
    public static final ItemObject<Item>  narrow_blade_sand_cast= ITEMS.register("narrow_blade_sand_cast", GENERAL_PROPS);
    public static final ItemObject<Item>  narrow_blade_red_sand_cast= ITEMS.register("narrow_blade_red_sand_cast", GENERAL_PROPS);
    public static final ItemObject<Item>  narrow_blade_gold_cast= ITEMS.register("narrow_blade_gold_cast", GENERAL_PROPS);
}
