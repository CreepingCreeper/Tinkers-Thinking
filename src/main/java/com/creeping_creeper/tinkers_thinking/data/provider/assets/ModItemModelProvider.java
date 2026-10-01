package com.creeping_creeper.tinkers_thinking.data.provider.assets;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

@SuppressWarnings("UnusedReturnValue")
public class ModItemModelProvider extends ItemModelProvider {
    private final ModelFile.UncheckedModelFile GENERATED = new ModelFile.UncheckedModelFile("item/generated");

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TinkersThinking.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        pathItem(ModCommonItems.ardite.getIngot(), "ardite/");
        pathItem(ModCommonItems.tinkers_bronze.getIngot(), "tinkers_bronze/");
        pathItem(ModCommonItems.lightite.getIngot(), "lightite/");
        pathItem(ModCommonItems.chlorophyte.getIngot(), "chlorophyte/");
        pathItem(ModCommonItems.spectre.getIngot(), "spectre/");
        pathItem(ModCommonItems.shroomite.getIngot(), "shroomite/");
        pathItem(ModCommonItems.obsidian_bronze.getIngot(), "obsidian_bronze/");
        pathItem(ModCommonItems.electrical_steel.getIngot(), "electrical_steel/");
        pathItem(ModCommonItems.beetron.getIngot(), "beetron/");
        pathItem(ModCommonItems.echo_bronze.getIngot(), "echo_bronze/");
        pathItem(ModCommonItems.warden_steel.getIngot(), "warden_steel/");
        pathItem(ModCommonItems.zith.getIngot(), "zith/");
        pathItem(ModCommonItems.shimmerslime.getIngot(), "shimmerslime/");

        pathItem(ModCommonItems.adamantium.getIngot(), "adamantium/");
        pathItem(ModCommonItems.ardite.getNugget(), "ardite/");
        pathItem(ModCommonItems.tinkers_bronze.getNugget(), "tinkers_bronze/");
        pathItem(ModCommonItems.lightite.getNugget(), "lightite/");
        pathItem(ModCommonItems.chlorophyte.getNugget(), "chlorophyte/");
        pathItem(ModCommonItems.spectre.getNugget(), "spectre/");
        pathItem(ModCommonItems.shroomite.getNugget(), "shroomite/");
        pathItem(ModCommonItems.obsidian_bronze.getNugget(), "obsidian_bronze/");
        pathItem(ModCommonItems.electrical_steel.getNugget(), "electrical_steel/");
        pathItem(ModCommonItems.beetron.getNugget(), "beetron/");
        pathItem(ModCommonItems.echo_bronze.getNugget(), "echo_bronze/");
        pathItem(ModCommonItems.warden_steel.getNugget(), "warden_steel/");
        pathItem(ModCommonItems.zith.getNugget(), "zith/");
        pathItem(ModCommonItems.shimmerslime.getNugget(), "shimmerslime/");
        pathItem(ModCommonItems.adamantium.getNugget(), "adamantium/");

        pathItem(ModCommonItems.raw_ardite, "ardite/");
        pathItem(ModCommonItems.raw_zith, "zith/");

        pathItem(ModCommonItems.lightite_compound.get(), "lightite/");
        pathItem(ModCommonItems.chlorophyll_a.get(), "chlorophyte/");
        pathItem(ModCommonItems.chlorophyll_b.get(), "chlorophyte/");
        pathItem(ModCommonItems.chlorophyte_compound.get(), "chlorophyte/");
        pathItem(ModCommonItems.Life_Fruit.get(), "chlorophyte/");
        pathItem(ModCommonItems.spectre_compound.get(), "spectre/");
        pathItem(ModCommonItems.shroomite_compound.get(), "shroomite/");
        pathItem(ModCommonItems.clay_crystal.get(), "crystal/");
        pathItem(ModCommonItems.chromatic_crystal.get(), "crystal/");
        pathItem(ModCommonItems.magma_crystal.get(), "crystal/");
        pathItem(ModCommonItems.quartz_crystal.get(), "crystal/");

        basicItem(ModCommonItems.ancient_ceramic.get());
        basicItem(ModCommonItems.ashes.get());
        basicItem(ModCommonItems.bacium.get());
        basicItem(ModCommonItems.chillslime_cryogel.get());
        basicItem(ModCommonItems.dusk_chunk.get());
        basicItem(ModCommonItems.gilded_silky_cloth.get());
        basicItem(ModCommonItems.silky_jewel.get());
        basicItem(ModCommonItems.soul_shard_a.get());
        basicItem(ModCommonItems.soul_shard_b.get());
        basicItem(ModCommonItems.stone_ladder.asItem());
        basicItem(ModCommonItems.stone_stick.get());
        basicItem(ModCommonItems.stone_soul_torch_item.get());
        basicItem(ModCommonItems.stone_torch_item.get());
        basicItem(ModCommonItems.stabilized_gunpowder.get());
        basicItem(ModCommonItems.surging_wellspring.get());

    }

    @SuppressWarnings("deprecation") // no its not
    private ResourceLocation id(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }

    private ItemModelBuilder generated(ResourceLocation item, ResourceLocation texture) {
        return getBuilder(item.toString()).parent(GENERATED).texture("layer0", texture);
    }

    @SuppressWarnings("removal")
    private ItemModelBuilder generated(ResourceLocation item, String texture) {
        return generated(item, new ResourceLocation(item.getNamespace(), texture));
    }

    private ItemModelBuilder otherItem(ResourceLocation item, String texture) {
        return generated(item, "item/" + texture);
    }

    public ItemModelBuilder customItem(ItemLike item, String texture) {
        return otherItem(id(item), texture);
    }

    protected ItemModelBuilder pathItem(ItemLike item, String path) {
        return customItem(item, path + id(item).getPath());
    }
}
