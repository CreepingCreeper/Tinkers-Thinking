package com.creeping_creeper.tinkers_thinking.common.library;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import static slimeknights.tconstruct.library.tools.helper.ModifierUtil.getModifierLevel;

public interface FindBySlot {
    default int getItemModifierLevel(LivingEntity living, ModifierId id){
        int x = 0;
        for (int i=98;i<104;i++){
            ItemStack stack = living.getSlot(i).get();
            if (stack.hasTag()&&!stack.getTag().getBoolean("tic_broken")) {
                x += getModifierLevel(stack, id);
            }
        }
        return x;
    }
}
