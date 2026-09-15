package com.creeping_creeper.tinkers_thinking.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import static com.creeping_creeper.tinkers_thinking.TinkersThinking.getResource;
import static slimeknights.mantle.Mantle.commonResource;


public class ModTags {
    public static class Items {
        /** Any modifiable bows or crossbows that has a loading_animation like vanilla crossbow */
        public static final TagKey<Item> raw_ardite = common("raw_ardite");
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
        public static final TagKey<Block> zith = local("zith");
        private static TagKey<Block> local(String name) {
            return TagKey.create(Registries.BLOCK, getResource(name));
        }
        private static TagKey<Block> common(String name) {return TagKey.create(Registries.BLOCK, commonResource(name));}
    }
    public static class Fluids {
        public static final TagKey<Fluid> glowstone = common("glowstone");
        private static TagKey<Fluid> local(String name) {
            return TagKey.create(Registries.FLUID, getResource(name));
        }
        private static TagKey<Fluid> common(String name) {return TagKey.create(Registries.FLUID, commonResource(name));}
    }
    public static class DamageTypes {
        /** Any DamageTypes that make blaze drop ashes */
        public static final TagKey<DamageType> drop_ashes = local("drop_ashes");
        private static TagKey<DamageType> local(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, getResource(name));
        }
        private static TagKey<DamageType> common(String name) {return TagKey.create(Registries.DAMAGE_TYPE, commonResource(name));}
    }
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> resisting = local("resisting");
        private static TagKey<EntityType<?>> local(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, getResource(name));
        }
        private static TagKey<EntityType<?>> common(String name) {return TagKey.create(Registries.ENTITY_TYPE, commonResource(name));}
    }
}
