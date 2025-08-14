package com.creeping_creeper.tinkers_thinking.common.world;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TinkersThinking.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldEvents{
    @SubscribeEvent
    static void mobKilled(LivingDropsEvent event) {
        DamageSource source = event.getSource();
        LivingEntity dying = event.getEntity();
        boolean a = source.getDirectEntity() instanceof Snowball;
        boolean b = source.getDirectEntity() instanceof ThrownPotion potion&&PotionUtils.getPotion(potion.getItem())== Potions.WATER&&PotionUtils.getMobEffects(potion.getItem()).isEmpty();
        if (dying instanceof Blaze &&(source.is(DamageTypes.DROWN) || source.is(DamageTypes.FREEZE) ||a||b)) {
            dropItem(dying,ModCommonItems.ashes.get());
        }
        if (source.is(DamageTypes.SONIC_BOOM)&&dying.hasEffect(ModEffects.sculk_power.get())){
            if (dying instanceof Creeper){
                dropItem(dying,ModCommonItems.soul_vine.get().asItem());
            }
            if (dying instanceof IronGolem){
                dropItem(dying,ModCommonItems.bound_chain.get().asItem());
            }
        }
    }
    private static void dropItem(LivingEntity dying, Item item){
        ItemEntity itementity = new ItemEntity(dying.level(), dying.getX(), dying.getY(), dying.getZ(), new ItemStack(item));
        dying.level().addFreshEntity(itementity);
    }
}
