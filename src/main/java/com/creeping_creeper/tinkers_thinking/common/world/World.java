package com.creeping_creeper.tinkers_thinking.common.world;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.config.Config;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class World {
    private static void injectInto(LootTableLoadEvent event, String poolName, LootPoolEntryContainer... entries) {
        LootPool pool = event.getTable().getPool(poolName);
        //noinspection ConstantConditions method is annotated wrongly
        if (pool != null) {
            int oldLength = pool.entries.length;
            pool.entries = Arrays.copyOf(pool.entries, oldLength + entries.length);
            System.arraycopy(entries, 0, pool.entries, oldLength, entries.length);
        }
    }
    @SubscribeEvent
    static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if ("minecraft".equals(name.getNamespace())) {
            switch (name.getPath()) {
                case "chests/nether_bridge":
                    if (Config.COMMON.slimyLootChests.get()) {
                        injectInto(event, "main", LootItem.lootTableItem(ModItems.raw_ardite.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))).build());
                    }
                    break;
                case "chests/bastion_bridge":
                    if (Config.COMMON.slimyLootChests.get()) {
                        injectInto(event, "main", LootItem.lootTableItem(ModItems.raw_ardite.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 5))).build());
                    }
                    break;
            }
        }
    }
    @SubscribeEvent
    static void blazeKilled(LivingDropsEvent event) {
        DamageSource source = event.getSource();
        LivingEntity dying = event.getEntity();
        boolean a = source.getDirectEntity() instanceof Snowball;
        boolean b = source.getDirectEntity() instanceof ThrownPotion potion&&PotionUtils.getPotion(potion.getItem())== Potions.WATER&&PotionUtils.getMobEffects(potion.getItem()).isEmpty();
        if (dying instanceof Blaze blaze&&(source == DamageSource.DROWN || source == DamageSource.FREEZE||a||b)) {
            event.getDrops().add(blaze.spawnAtLocation(ModItems.ashes.get()));
        }
    }
}
