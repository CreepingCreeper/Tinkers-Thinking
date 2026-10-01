package com.creeping_creeper.tinkers_thinking.data.provider.assets;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
public class ModBlockStateProvider extends BlockStateProvider {
    private final ModelFile.UncheckedModelFile GENERATED = new ModelFile.UncheckedModelFile("item/generated");

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TinkersThinking.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        cubeColumn(ModCommonItems.ardite.get(), TinkersThinking.getResource("block/ardite"), TinkersThinking.getResource("block/ardite_top"));
        basicBlock(ModCommonItems.tinkers_bronze.get());
        basicBlock(ModCommonItems.ardite_ore.get());
        basicBlock(ModCommonItems.raw_ardite_block.get());
        //basicBlock(ModCommonItems.ardite_platform.get());
        basicBlock(ModCommonItems.silky_jewel_block.get());
        basicBlock(ModCommonItems.lightite.get());
        basicBlock(ModCommonItems.chlorophyte.get());
        basicBlock(ModCommonItems.chlorophyll_ore.get());
        basicBlock(ModCommonItems.deepslate_chlorophyll_ore.get());
        basicBlock(ModCommonItems.mud_chlorophyll_ore.get());
        basicBlock(ModCommonItems.spectre.get());
        basicBlock(ModCommonItems.shroomite.get());
        basicBlock(ModCommonItems.obsidian_bronze.get());
        basicBlock(ModCommonItems.electrical_steel.get());
        basicBlock(ModCommonItems.beetron.get());
        //basicBlock(ModCommonItems.stone_ladder.get());
        //basicBlock(ModCommonItems.ground_stone_torch.get());
        //basicBlock(ModCommonItems.wall_stone_torch.get());
        //basicBlock(ModCommonItems.ground_stone_soul_torch.get());
        //basicBlock(ModCommonItems.wall_stone_soul_torch.get());
        basicBlock(ModCommonItems.echo_bronze.get());
        horizontalBlock(ModCommonItems.waste_fluid_cylinder.get(), TinkersThinking.getResource("block/waste_fluid_cylinder/side"), TinkersThinking.getResource("block/waste_fluid_cylinder/front"), TinkersThinking.getResource("block/waste_fluid_cylinder/top"));
        basicBlock(ModCommonItems.warden_steel.get());
        //basicBlock(ModCommonItems.soul_vine.get());
        //basicBlock(ModCommonItems.bound_chain.get());
        //basicBlock(ModCommonItems.tempered_glass.get());
        //basicBlock(ModCommonItems.tempered_glass_pane.get());
        basicBlock(ModCommonItems.ancient_ceramic_block.get());
        basicBlock(ModCommonItems.zith.get());
        basicBlock(ModCommonItems.zith_ore.get());
        basicBlock(ModCommonItems.raw_zith_block.get());
        //basicBlock(ModCommonItems.zith_platform.get());
        //basicBlock(ModCommonItems.shimmerslime.get());
        basicBlock(ModCommonItems.adamantium.get());


    }

    @SuppressWarnings("deprecation")
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    /** Gets the resource path for a block */
    private String name(Block block) {
        return key(block).getPath();
    }

    public ModelFile basicBlock(Block block, ModelFile model) {
        simpleBlock(block, model);
        simpleBlockItem(block, model);
        return model;
    }

    private ModelFile customBlock(Block block, String location, ResourceLocation texture) {
        return basicBlock(block, models().cubeAll(location, texture));
    }

    private ModelFile basicBlock(Block block) {
        return basicBlock(block, models().cubeAll(name(block), ResourceLocation.fromNamespaceAndPath(key(block).getNamespace(),  "block/" + name(block))));
    }

    private ModelFile basicBlock(Block block, String location, ResourceLocation texture) {
        return basicBlock(block, models().cubeAll(location, texture));
    }

    public ModelFile cubeColumn(Block block, ResourceLocation side, ResourceLocation top) {
        return basicBlock(block, models().cubeColumn(name(block), side, top));
    }

    private ModelFile pathBlock(Block block, String path) {
        return basicBlock(block, models().cubeAll( "block/" + path + name(block), ResourceLocation.fromNamespaceAndPath(key(block).getNamespace(), "block/" + path + name(block))));
    }

}
