package com.creeping_creeper.tinkers_thinking.mixins;

import com.creeping_creeper.tinkers_thinking.data.ModTags;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableCrossbowItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

@Mixin({CrossbowItem.class})
public class CrossbowItemMixin {
    @Inject(method = "getChargeDuration",at = @At(value = "RETURN"), cancellable = true)
    private static void getChargeDuration(ItemStack p_40940_, CallbackInfoReturnable<Integer> cir) {
        if (p_40940_.is(ModTags.Items.LOADING_ANIMATION)){
            cir.setReturnValue((int) (25/ ToolStack.from(p_40940_).getStats().get(ToolStats.DRAW_SPEED)));
        }
    }
    @Inject(method = "isCharged",at = @At(value = "RETURN"), cancellable = true)
    private static void isCharged(ItemStack p_40933_, CallbackInfoReturnable<Boolean> cir) {
        if (p_40933_.is(ModTags.Items.LOADING_ANIMATION)&&!ToolStack.from(p_40933_).getPersistentData().getCompound(ModifiableCrossbowItem.KEY_CROSSBOW_AMMO).isEmpty()){
            cir.setReturnValue(true);
        }
    }
}
