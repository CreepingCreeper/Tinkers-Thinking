package com.creeping_creeper.tinkers_thinking.common.things.item;

import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SoulShardItem extends Item {
    public SoulShardItem(Properties properties) {
        super(properties);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        int x = stack.getCount();
        if (entity instanceof LivingEntity living){
            living.heal(2*x);
            living.addEffect(new MobEffectInstance(stack.getItem()==ModCommonItems.soul_shard_a.get()?MobEffects.DIG_SPEED:MobEffects.DAMAGE_RESISTANCE,60,2));
        }
        stack.shrink(x);
    }
}
