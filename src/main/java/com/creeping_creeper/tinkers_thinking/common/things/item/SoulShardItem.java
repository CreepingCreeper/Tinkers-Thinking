package com.creeping_creeper.tinkers_thinking.common.things.item;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SoulShardItem extends Item implements ModifierUtils {
    public SoulShardItem(Properties properties) {
        super(properties);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        int x = stack.getCount();
        if (entity instanceof LivingEntity living){
            heal(living, 2*x);
            addEffect(living,stack.getItem()==ModCommonItems.soul_shard_a.get()?MobEffects.DIG_SPEED:MobEffects.DAMAGE_RESISTANCE,60,2);
        }
        stack.shrink(x);
    }
}
