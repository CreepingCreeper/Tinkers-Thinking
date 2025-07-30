package com.creeping_creeper.tinkers_thinking.common.library;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.Objects;

import static slimeknights.tconstruct.library.tools.helper.ModifierUtil.getModifierLevel;

public interface ModUtils {
    default int getItemModifierLevel(LivingEntity living, ModifierId id){
        int x = 0;
        for (int i=98;i<104;i++){
            ItemStack stack = living.getSlot(i).get();
            if (stack.getItem() instanceof IModifiable&& !ToolStack.from(stack).isBroken()) {
                x += getModifierLevel(stack, id);
            }
        }
        return x;
    }
    default int getArmorModifierLevel(LivingEntity living, ModifierId id){
        int x = 0;
        for (int i=99;i<104;i++){
            ItemStack stack = living.getSlot(i).get();
            if (stack.hasTag()&&!stack.getTag().getBoolean("tic_broken")) {
                x += getModifierLevel(stack, id);
            }
        }
        return x;
    }
    ResourceLocation reverse_key = new ResourceLocation(TinkersThinking.MODID, "reverse");
    default boolean reverse(IToolStackView tool){
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        return !persistentData.contains(reverse_key);
    }
}
