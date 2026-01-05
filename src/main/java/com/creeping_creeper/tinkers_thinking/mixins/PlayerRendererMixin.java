package com.creeping_creeper.tinkers_thinking.mixins;

import com.creeping_creeper.tinkers_thinking.data.ModTags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PlayerRenderer.class})
public abstract class PlayerRendererMixin {
    //Add the animation of the crossbow in the third-person view.
    @Inject(method = "getArmPose",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/UseAnim;",shift =At.Shift.AFTER), cancellable = true)
   private static void getArmPose(AbstractClientPlayer p_117795_, InteractionHand p_117796_, CallbackInfoReturnable<HumanoidModel.ArmPose> cir){
        ItemStack itemstack = p_117795_.getItemInHand(p_117796_);
        if (itemstack.is(ModTags.Items.LOADING_ANIMATION)&&p_117796_ == p_117795_.getUsedItemHand()) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_CHARGE);
        }
    }
    @Redirect( method = "getArmPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;"))
    private static net.minecraft.world.item.Item getItem(ItemStack stack) {
        if (stack.is(ModTags.Items.LOADING_ANIMATION)) {
            return Items.CROSSBOW;
        }else return stack.getItem();
    }
}
