package com.creeping_creeper.tinkers_thinking.common.datagen;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagProvider extends ItemTagsProvider {
    public ItemTagProvider(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagProvider, TinkersThinking.MODID, existingFileHelper);
    }
    @Override
    protected void addTags() {
        this.addCommon();
    }
    private void addCommon() {

    }
    public static class Items {
        private static void init() {
        }

        public static final TagKey<Item> sling_projectiles = tag("sling_projectiles");
        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registry.ITEM_REGISTRY, TinkersThinking.getResource(name));
        }
    }
    @Override
    public String getName() {
        return "Tinkers Thinking Item Tags";
    }
}
