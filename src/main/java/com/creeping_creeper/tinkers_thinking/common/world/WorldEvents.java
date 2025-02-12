package com.creeping_creeper.tinkers_thinking.common.world;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModCommonItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldEvents {
    @SubscribeEvent
    static void blazeKilled(LivingDropsEvent event) {
        DamageSource source = event.getSource();
        LivingEntity dying = event.getEntity();
        boolean a = source.getDirectEntity() instanceof Snowball;
        boolean b = source.getDirectEntity() instanceof ThrownPotion potion&&PotionUtils.getPotion(potion.getItem())== Potions.WATER&&PotionUtils.getMobEffects(potion.getItem()).isEmpty();
        if (dying instanceof Blaze blaze&&(source.is(DamageTypes.DROWN) || source.is(DamageTypes.FREEZE) ||a||b)) {
            event.getDrops().add(blaze.spawnAtLocation(ModCommonItems.ashes));
        }
    }
}
