package com.creeping_creeper.tinkers_thinking.common.things.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableBowItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.modifiers.ability.interaction.BlockingModifier;

public class ModifiableAtlatlItem extends ModifiableBowItem {
    public ModifiableAtlatlItem(Properties properties, ToolDefinition toolDefinition, boolean storeDrawingItem) {
        super(properties, toolDefinition, storeDrawingItem);
    }
   @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return BlockingModifier.blockWhileCharging(ToolStack.from(stack), UseAnim.SPEAR);
    }
}
