package com.creeping_creeper.tinkers_thinking.common.things.item;

import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingArrowEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SeekingArrowItem extends ArrowItem {
    public SeekingArrowItem(Properties properties) {
        super(properties);
    }
    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new SeekingArrowEntity(pLevel, pShooter);
    }
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        return false;
    }
}
