package com.creeping_creeper.tinkers_thinking.mixins;

import com.creeping_creeper.tinkers_thinking.data.ModTags;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ItemInHandRenderer.class})
public class ItemInHandRendererMixin {
    //Add the animation of the crossbow in the first-person view.
    @Redirect( method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;"))
    private Item redirectGetItemForIfCondition(ItemStack stack) {
        if (stack.is(ModTags.Items.LOADING_ANIMATION)) {
            return Items.CROSSBOW;
        }else return stack.getItem();
    }
}
