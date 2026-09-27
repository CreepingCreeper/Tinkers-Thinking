package com.creeping_creeper.tinkers_thinking.data.provider.tag;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.data.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.world.TinkerWorld;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TinkersThinking.MODID, existingFileHelper);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTags(@NotNull HolderLookup.Provider lookupProvider) {
        //vanilla
        tag(BlockTags.MINEABLE_WITH_PICKAXE);
        tag(BlockTags.MINEABLE_WITH_SHOVEL);
        tag(BlockTags.NEEDS_IRON_TOOL);
        tag(BlockTags.NEEDS_STONE_TOOL);
        tag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(BlockTags.WITHER_IMMUNE).add(ModCommonItems.tempered_glass.get(), ModCommonItems.tempered_glass.get());
        tag(BlockTags.IMPERMEABLE).add(ModCommonItems.tempered_glass.get());

        //common
        tag(ModTags.Blocks.ARDITE_ORE).add(ModCommonItems.ardite_ore.get());
        tag(ModTags.Blocks.CHLOROPHYLL_ORE).add(ModCommonItems.chlorophyll_ore.get(), ModCommonItems.deepslate_chlorophyll_ore.get(), ModCommonItems.mud_chlorophyll_ore.get());
        tag(ModTags.Blocks.ZITH_ORE).add(ModCommonItems.zith_ore.get());
        tag(ModTags.Blocks.RAW_ARDITE_BLOCK).add(ModCommonItems.raw_ardite_block.get());
        tag(ModTags.Blocks.RAW_ZITH_BLOCK).add(ModCommonItems.raw_zith_block.get());
        tag(Tags.Blocks.ORES).addTags(ModTags.Blocks.ARDITE_ORE, ModTags.Blocks.CHLOROPHYLL_ORE, ModTags.Blocks.ZITH_ORE);

        tag(ModCommonItems.ardite.getBlockTag()).add(ModCommonItems.ardite.get());
        tag(ModCommonItems.tinkers_bronze.getBlockTag()).add(ModCommonItems.tinkers_bronze.get());
        tag(ModCommonItems.lightite.getBlockTag()).add(ModCommonItems.lightite.get());
        tag(ModCommonItems.chlorophyte.getBlockTag()).add(ModCommonItems.chlorophyte.get());
        tag(ModCommonItems.spectre.getBlockTag()).add(ModCommonItems.spectre.get());
        tag(ModCommonItems.shroomite.getBlockTag()).add(ModCommonItems.shroomite.get());
        tag(ModCommonItems.obsidian_bronze.getBlockTag()).add(ModCommonItems.obsidian_bronze.get());
        tag(ModCommonItems.electrical_steel.getBlockTag()).add(ModCommonItems.electrical_steel.get());
        tag(ModCommonItems.beetron.getBlockTag()).add(ModCommonItems.beetron.get());
        tag(ModCommonItems.echo_bronze.getBlockTag()).add(ModCommonItems.echo_bronze.get());
        tag(ModCommonItems.warden_steel.getBlockTag()).add(ModCommonItems.warden_steel.get());
        tag(ModCommonItems.zith.getBlockTag()).add(ModCommonItems.zith.get());
        tag(ModCommonItems.shimmerslime.getBlockTag()).add(ModCommonItems.shimmerslime.get());
        tag(ModCommonItems.adamantium.getBlockTag()).add(ModCommonItems.adamantium.get());

        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(ModTags.Blocks.RAW_ARDITE_BLOCK, ModCommonItems.ardite.getBlockTag(), ModCommonItems.tinkers_bronze.getBlockTag(), ModCommonItems.lightite.getBlockTag(), ModCommonItems.chlorophyte.getBlockTag(), ModCommonItems.spectre.getBlockTag(),
                ModCommonItems.shroomite.getBlockTag(), ModCommonItems.obsidian_bronze.getBlockTag(), ModCommonItems.electrical_steel.getBlockTag(), ModCommonItems.beetron.getBlockTag(), ModCommonItems.echo_bronze.getBlockTag(), ModCommonItems.warden_steel.getBlockTag(),
                ModTags.Blocks.RAW_ZITH_BLOCK, ModCommonItems.zith.getBlockTag(), ModCommonItems.shimmerslime.getBlockTag(), ModCommonItems.adamantium.getBlockTag()
        );

        //tconstruct
        tag(TinkerTags.Blocks.ANVIL_METAL).addTags(ModCommonItems.ardite.getBlockTag(), ModCommonItems.tinkers_bronze.getBlockTag(), ModCommonItems.lightite.getBlockTag(), ModCommonItems.chlorophyte.getBlockTag(), ModCommonItems.spectre.getBlockTag(),
                ModCommonItems.shroomite.getBlockTag(), ModCommonItems.obsidian_bronze.getBlockTag(), ModCommonItems.electrical_steel.getBlockTag(), ModCommonItems.beetron.getBlockTag(), ModCommonItems.echo_bronze.getBlockTag(), ModCommonItems.warden_steel.getBlockTag(),
                ModCommonItems.zith.getBlockTag(), ModCommonItems.shimmerslime.getBlockTag(), ModCommonItems.adamantium.getBlockTag()
        );
        tag(TinkerTags.Blocks.PLATFORM_CONNECTIONS).add(ModCommonItems.stone_ladder.get(), ModCommonItems.wall_stone_torch.get(), ModCommonItems.wall_stone_soul_torch.get());
        //self
        tag(ModTags.Blocks.COOLING_FAST).add(Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW, TinkerWorld.slime.get(SlimeType.SKY), TinkerWorld.congealedSlime.get(SlimeType.SKY),
                TinkerWorld.skyGeode.getBlock(), TinkerWorld.skyGeode.getBudding()).addTag(BlockTags.SNOW);
        tag(ModTags.Blocks.CUTTABLE).add(Blocks.QUARTZ_BLOCK, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_STONE, Blocks.BRICKS, Blocks.PURPUR_BLOCK,
                Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS, Blocks.COPPER_BLOCK, Blocks.WAXED_COPPER_BLOCK, Blocks.CUT_COPPER, Blocks.WAXED_CUT_COPPER,
                        Blocks.BLACKSTONE)
                .addTags(TinkerTags.Blocks.SEARED_BLOCKS, TinkerTags.Blocks.SCORCHED_BLOCKS, Tags.Blocks.SANDSTONE);
        tag(ModTags.Blocks.ZITH).add(ModCommonItems.zith.get(), ModCommonItems.zith_ore.get(), ModCommonItems.raw_zith_block.get(), ModCommonItems.zith_platform.get());
    }
}
