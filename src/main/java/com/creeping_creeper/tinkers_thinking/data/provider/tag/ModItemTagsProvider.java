package com.creeping_creeper.tinkers_thinking.data.provider.tag;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.common.register.ModToolItems;
import com.creeping_creeper.tinkers_thinking.data.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;

import java.util.concurrent.CompletableFuture;

import static slimeknights.mantle.Mantle.commonResource;

public class ModItemTagsProvider extends ItemTagsProvider {
    private static final TagKey<Item> COOKED_EGGS = ItemTags.create(commonResource("cooked_eggs"));

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider, TinkersThinking.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(@NotNull HolderLookup.Provider lookupProvider) {
        //vanilla
        tag(ItemTags.BEACON_PAYMENT_ITEMS).addTags(ModTags.Items.SILKY_JEWEL, ModCommonItems.ardite.getIngotTag(), ModCommonItems.lightite.getIngotTag(), ModCommonItems.chlorophyte.getIngotTag(), ModCommonItems.spectre.getIngotTag(),
                ModCommonItems.shroomite.getIngotTag(), ModCommonItems.obsidian_bronze.getIngotTag(), ModCommonItems.electrical_steel.getIngotTag(), ModCommonItems.warden_steel.getIngotTag(), ModCommonItems.zith.getIngotTag(), ModCommonItems.shimmerslime.getIngotTag(),
                ModCommonItems.adamantium.getIngotTag());
        tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(ModToolItems.paxel.get());
        tag(ItemTags.PIGLIN_FOOD).add(ModCommonItems.Pork_Jerky.get());
        tag(ItemTags.PIGLIN_LOVED).add(ModCommonItems.gilded_silky_cloth.get(), ModCommonItems.silky_jewel.get(), ModCommonItems.silky_jewel_block.asItem());
        tag(ItemTags.PIGLIN_REPELLENTS).add(ModCommonItems.stone_soul_torch_item.get());
        //common

        tag(ModTags.Items.STONE_ROD).add(ModCommonItems.stone_stick.get());
        tag(Tags.Items.RODS).addTag(ModTags.Items.STONE_ROD);
        tag(ModTags.Items.SILKY_JEWEL).add(ModCommonItems.silky_jewel.get());
        tag(Tags.Items.GEMS_QUARTZ).add(ModCommonItems.quartz_crystal.get());
        tag(Tags.Items.GEMS).addTag(ModTags.Items.SILKY_JEWEL);
        tag(ModTags.Items.RAW_ARDITE).add(ModCommonItems.raw_ardite.get());
        tag(ModTags.Items.RAW_ZITH).add(ModCommonItems.raw_zith.get());
        tag(Tags.Items.RAW_MATERIALS).addTags(ModTags.Items.RAW_ARDITE, ModTags.Items.RAW_ZITH);
        copy(ModTags.Blocks.ARDITE_ORE, ModTags.Items.ARDITE_ORE);
        copy(ModTags.Blocks.CHLOROPHYLL_ORE, ModTags.Items.CHLOROPHYLL_ORE);
        copy(ModTags.Blocks.ZITH_ORE, ModTags.Items.ZITH_ORE);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.RAW_ARDITE_BLOCK, ModTags.Items.RAW_ARDITE_BLOCK);
        copy(ModTags.Blocks.RAW_ZITH_BLOCK, ModTags.Items.RAW_ZITH_BLOCK);

        tag(ModCommonItems.ardite.getIngotTag()).add(ModCommonItems.ardite.getIngot());
        tag(ModCommonItems.tinkers_bronze.getIngotTag()).add(ModCommonItems.tinkers_bronze.getIngot());
        tag(ModCommonItems.lightite.getIngotTag()).add(ModCommonItems.lightite.getIngot());
        tag(ModCommonItems.chlorophyte.getIngotTag()).add(ModCommonItems.chlorophyte.getIngot());
        tag(ModCommonItems.spectre.getIngotTag()).add(ModCommonItems.spectre.getIngot());
        tag(ModCommonItems.shroomite.getIngotTag()).add(ModCommonItems.shroomite.getIngot());
        tag(ModCommonItems.obsidian_bronze.getIngotTag()).add(ModCommonItems.obsidian_bronze.getIngot());
        tag(ModCommonItems.electrical_steel.getIngotTag()).add(ModCommonItems.electrical_steel.getIngot());
        tag(ModCommonItems.beetron.getIngotTag()).add(ModCommonItems.beetron.getIngot());
        tag(ModCommonItems.echo_bronze.getIngotTag()).add(ModCommonItems.echo_bronze.getIngot());
        tag(ModCommonItems.warden_steel.getIngotTag()).add(ModCommonItems.warden_steel.getIngot());
        tag(ModCommonItems.zith.getIngotTag()).add(ModCommonItems.zith.getIngot());
        tag(ModCommonItems.shimmerslime.getIngotTag()).add(ModCommonItems.shimmerslime.getIngot());
        tag(ModCommonItems.adamantium.getIngotTag()).add(ModCommonItems.adamantium.getIngot());

        tag(Tags.Items.INGOTS).addTags(ModCommonItems.ardite.getIngotTag(), ModCommonItems.tinkers_bronze.getIngotTag(), ModCommonItems.lightite.getIngotTag(), ModCommonItems.chlorophyte.getIngotTag(), ModCommonItems.spectre.getIngotTag(),
                ModCommonItems.shroomite.getIngotTag(), ModCommonItems.obsidian_bronze.getIngotTag(), ModCommonItems.electrical_steel.getIngotTag(), ModCommonItems.beetron.getIngotTag(), ModCommonItems.echo_bronze.getIngotTag(), ModCommonItems.warden_steel.getIngotTag(),
                ModCommonItems.zith.getIngotTag(), ModCommonItems.shimmerslime.getIngotTag(), ModCommonItems.adamantium.getIngotTag()
        );

        tag(ModCommonItems.ardite.getNuggetTag()).add(ModCommonItems.ardite.getNugget());
        tag(ModCommonItems.tinkers_bronze.getNuggetTag()).add(ModCommonItems.tinkers_bronze.getNugget());
        tag(ModCommonItems.lightite.getNuggetTag()).add(ModCommonItems.lightite.getNugget());
        tag(ModCommonItems.chlorophyte.getNuggetTag()).add(ModCommonItems.chlorophyte.getNugget());
        tag(ModCommonItems.spectre.getNuggetTag()).add(ModCommonItems.spectre.getNugget());
        tag(ModCommonItems.shroomite.getNuggetTag()).add(ModCommonItems.shroomite.getNugget());
        tag(ModCommonItems.obsidian_bronze.getNuggetTag()).add(ModCommonItems.obsidian_bronze.getNugget());
        tag(ModCommonItems.electrical_steel.getNuggetTag()).add(ModCommonItems.electrical_steel.getNugget());
        tag(ModCommonItems.beetron.getNuggetTag()).add(ModCommonItems.beetron.getNugget());
        tag(ModCommonItems.echo_bronze.getNuggetTag()).add(ModCommonItems.echo_bronze.getNugget());
        tag(ModCommonItems.warden_steel.getNuggetTag()).add(ModCommonItems.warden_steel.getNugget());
        tag(ModCommonItems.zith.getNuggetTag()).add(ModCommonItems.zith.getNugget());
        tag(ModCommonItems.shimmerslime.getNuggetTag()).add(ModCommonItems.shimmerslime.getNugget());
        tag(ModCommonItems.adamantium.getNuggetTag()).add(ModCommonItems.adamantium.getNugget());

        tag(Tags.Items.INGOTS).addTags(ModCommonItems.ardite.getIngotTag(), ModCommonItems.tinkers_bronze.getIngotTag(), ModCommonItems.lightite.getIngotTag(), ModCommonItems.chlorophyte.getIngotTag(), ModCommonItems.spectre.getIngotTag(),
                ModCommonItems.shroomite.getIngotTag(), ModCommonItems.obsidian_bronze.getIngotTag(), ModCommonItems.electrical_steel.getIngotTag(), ModCommonItems.beetron.getIngotTag(), ModCommonItems.echo_bronze.getIngotTag(), ModCommonItems.warden_steel.getIngotTag(),
                ModCommonItems.zith.getIngotTag(), ModCommonItems.shimmerslime.getIngotTag(), ModCommonItems.adamantium.getIngotTag()
        );

        copy(ModTags.Blocks.SILKY_JEWEL_BLOCK, ModTags.Items.SILKY_JEWEL_BLOCK);
        copy(ModCommonItems.ardite.getBlockTag(), ModCommonItems.ardite.getBlockItemTag());
        copy(ModCommonItems.tinkers_bronze.getBlockTag(), ModCommonItems.tinkers_bronze.getBlockItemTag());
        copy(ModCommonItems.lightite.getBlockTag(), ModCommonItems.lightite.getBlockItemTag());
        copy(ModCommonItems.chlorophyte.getBlockTag(), ModCommonItems.chlorophyte.getBlockItemTag());
        copy(ModCommonItems.spectre.getBlockTag(), ModCommonItems.spectre.getBlockItemTag());
        copy(ModCommonItems.shroomite.getBlockTag(), ModCommonItems.shroomite.getBlockItemTag());
        copy(ModCommonItems.obsidian_bronze.getBlockTag(), ModCommonItems.obsidian_bronze.getBlockItemTag());
        copy(ModCommonItems.electrical_steel.getBlockTag(), ModCommonItems.electrical_steel.getBlockItemTag());
        copy(ModCommonItems.beetron.getBlockTag(), ModCommonItems.beetron.getBlockItemTag());
        copy(ModCommonItems.echo_bronze.getBlockTag(), ModCommonItems.echo_bronze.getBlockItemTag());
        copy(ModCommonItems.warden_steel.getBlockTag(), ModCommonItems.warden_steel.getBlockItemTag());
        copy(ModCommonItems.zith.getBlockTag(), ModCommonItems.zith.getBlockItemTag());
        copy(ModCommonItems.shimmerslime.getBlockTag(), ModCommonItems.shimmerslime.getBlockItemTag());
        copy(ModCommonItems.adamantium.getBlockTag(), ModCommonItems.adamantium.getBlockItemTag());

        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        tag(COOKED_EGGS).add(ModCommonItems.Fried_Egg.get());
        tag(Tags.Items.TOOLS_BOWS).add(ModToolItems.atlatl.get());
        tag(Tags.Items.TOOLS_CROSSBOWS).add(ModToolItems.repeating_crossbow.get());
        tag(ModTags.Items.TOOL_KNIVES).add(ModToolItems.knife.get());
        tag(ModTags.Items.FILLET_KNIFE).add(ModToolItems.knife.get());

        //tconstruct

        copy(TinkerTags.Blocks.ANVIL_METAL, TinkerTags.Items.ANVIL_METAL);
        //self

    }
}
