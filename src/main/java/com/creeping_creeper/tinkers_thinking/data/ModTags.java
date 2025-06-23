package com.creeping_creeper.tinkers_thinking.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.creeping_creeper.tinkers_thinking.TinkersThinking.getResource;
import static slimeknights.mantle.Mantle.commonResource;


public class ModTags {
    public static class Items {
        /** Any modifiable bows or crossbows that has a loading_animation like vanilla crossbow */
        public static final TagKey<Item> LOADING_ANIMATION = local("loading_animation");
        private static TagKey<Item> local(String name) {
            return TagKey.create(Registries.ITEM, getResource(name));
        }
        private static TagKey<Item> common(String name) {
            return TagKey.create(Registries.ITEM, commonResource(name));
        }
    }
    public static class Blocks {
        /** Any Blocks that can speed up cooling */
        public static final TagKey<Block> cooling_fast = local("cooling_fast");
        private static TagKey<Block> local(String name) {
            return TagKey.create(Registries.BLOCK, getResource(name));
        }
        private static TagKey<Block> common(String name) {return TagKey.create(Registries.BLOCK, commonResource(name));}
    }
}
