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
        public static final TagKey<Item> STONE_ROD = common("rods/stone");
        public static final TagKey<Item> ARDITE_ORE = common("ores/ardite");
        public static final TagKey<Item> RAW_ARDITE = common("raw_ardite");
        public static final TagKey<Item> RAW_ARDITE_BLOCK = common("storage_blocks/raw_ardite");
        public static final TagKey<Item> CHLOROPHYLL_ORE = common("ores/chlorophyll");
        public static final TagKey<Item> ZITH_ORE = common("ores/ith");
        public static final TagKey<Item> RAW_ZITH = common("raw_zith");
        public static final TagKey<Item> RAW_ZITH_BLOCK = common("storage_blocks/raw_zith");
        public static final TagKey<Item> SILKY_JEWEL = common("gems/silky_jewel");
        public static final TagKey<Item> SILKY_JEWEL_BLOCK = common("storage_blocks/silky_jewel");

        public static final TagKey<Item> TOOL_KNIVES = common("tools/knives");
        public static final TagKey<Item> FILLET_KNIFE = common("fillet_knife");

        private static TagKey<Item> local(String name) {
            return TagKey.create(Registries.ITEM, getResource(name));
        }
        private static TagKey<Item> common(String name) {
            return TagKey.create(Registries.ITEM, commonResource(name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> ARDITE_ORE = common("ores/ardite");
        public static final TagKey<Block> RAW_ARDITE_BLOCK = common("storage_blocks/raw_ardite");
        public static final TagKey<Block> CUTTABLE = local("cuttable_blocks");
        public static final TagKey<Block> CHLOROPHYLL_ORE = common("ores/chlorophyll");
        public static final TagKey<Block> ZITH_ORE = common("ores/ith");
        public static final TagKey<Block> RAW_ZITH_BLOCK = common("storage_blocks/raw_zith");
        public static final TagKey<Block> SILKY_JEWEL_BLOCK = common("storage_blocks/silky_jewel");


        /** Any Blocks that can speed up cooling */
        public static final TagKey<Block> COOLING_FAST = local("cooling_fast");
        public static final TagKey<Block> ZITH = local("zith");
        private static TagKey<Block> local(String name) {
            return TagKey.create(Registries.BLOCK, getResource(name));
        }
        private static TagKey<Block> common(String name) {return TagKey.create(Registries.BLOCK, commonResource(name));}
    }

    public static class Fluids {
        public static final TagKey<Fluid> GLOWSTONE = common("glowstone");
        private static TagKey<Fluid> local(String name) {
            return TagKey.create(Registries.FLUID, getResource(name));
        }
        private static TagKey<Fluid> common(String name) {return TagKey.create(Registries.FLUID, commonResource(name));}
    }

    public static class DamageTypes {
        /** Any DamageTypes that make blaze drop ashes */
        public static final TagKey<DamageType> DROP_ASHES = local("drop_ashes");
        private static TagKey<DamageType> local(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, getResource(name));
        }
        private static TagKey<DamageType> common(String name) {return TagKey.create(Registries.DAMAGE_TYPE, commonResource(name));}
    }

    public static class EntityTypes {
        public static final TagKey<EntityType<?>> COLLECTABLES = local("collectables");
        public static final TagKey<EntityType<?>> PIG_LIKE = local("pig_like");
        public static final TagKey<EntityType<?>> RESISTING = local("resisting");
        private static TagKey<EntityType<?>> local(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, getResource(name));
        }
        private static TagKey<EntityType<?>> common(String name) {return TagKey.create(Registries.ENTITY_TYPE, commonResource(name));}
    }

}
