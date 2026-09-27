package com.creeping_creeper.tinkers_thinking.data.provider.loot;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    @SuppressWarnings("deprecation")  // the vanilla registry is perfectly fine for our uses, will make migration away from forge registries easier
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> TinkersThinking.MODID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace()))
                .collect(Collectors.toList());
    }

    protected ModBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModCommonItems.ardite.get());
        dropSelf(ModCommonItems.tinkers_bronze.get());
        dropSelf(ModCommonItems.lightite.get());
        dropSelf(ModCommonItems.chlorophyte.get());
        dropSelf(ModCommonItems.spectre.get());
        dropSelf(ModCommonItems.shroomite.get());
        dropSelf(ModCommonItems.obsidian_bronze.get());
        dropSelf(ModCommonItems.electrical_steel.get());
        dropSelf(ModCommonItems.beetron.get());
        dropSelf(ModCommonItems.echo_bronze.get());
        dropSelf(ModCommonItems.warden_steel.get());
        dropSelf(ModCommonItems.zith.get());
        dropSelf(ModCommonItems.shimmerslime.get());
        dropSelf(ModCommonItems.adamantium.get());

        add(ModCommonItems.ardite_ore.get(), block -> createOreDrop(block, ModCommonItems.raw_ardite.get()));
        dropSelf(ModCommonItems.ardite_platform.get());

        dropSelf(ModCommonItems.bound_chain.get());

        add(ModCommonItems.chlorophyll_ore.get(), block -> createSpecialOreDrop(block, ModCommonItems.chlorophyll_a.get(), ModCommonItems.chlorophyll_b.get()));
        add(ModCommonItems.deepslate_chlorophyll_ore.get(), block -> createSpecialOreDrop(block, ModCommonItems.chlorophyll_a.get(), ModCommonItems.chlorophyll_b.get()));
        add(ModCommonItems.mud_chlorophyll_ore.get(), block -> createSpecialOreDrop(block, ModCommonItems.chlorophyll_a.get(), ModCommonItems.chlorophyll_b.get()));

        dropSelf(ModCommonItems.raw_ardite_block.get());
        dropSelf(ModCommonItems.raw_zith_block.get());
        dropSelf(ModCommonItems.ancient_ceramic_block.get());
        dropSelf(ModCommonItems.silky_jewel_block.get());
        dropSelf(ModCommonItems.soul_vine.get());
        dropSelf(ModCommonItems.stone_ladder.get());

        dropSelf(ModCommonItems.ground_stone_torch.get());
        dropSelf(ModCommonItems.ground_stone_soul_torch.get());
        dropSelf(ModCommonItems.wall_stone_torch.get());
        dropSelf(ModCommonItems.wall_stone_soul_torch.get());
        dropSelf(ModCommonItems.tempered_glass.get());
        dropSelf(ModCommonItems.tempered_glass_pane.get());
        dropSelf(ModCommonItems.waste_fluid_cylinder.get());

        add(ModCommonItems.zith_ore.get(), block -> createOreDrop(block, ModCommonItems.raw_zith.get()));
        dropSelf(ModCommonItems.zith_platform.get());

    }

    private LootTable.Builder createSpecialOreDrop(Block a, ItemLike b, ItemLike c) {
        return LootTable.lootTable()
                .withPool(this.applyExplosionCondition(a, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(a).when(HAS_SILK_TOUCH))))
                .withPool(this.applyExplosionCondition(b, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(b).when(HAS_SILK_TOUCH.invert()).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))))
                .withPool(this.applyExplosionCondition(c, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(c).when(HAS_SILK_TOUCH.invert()).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
    }
}
