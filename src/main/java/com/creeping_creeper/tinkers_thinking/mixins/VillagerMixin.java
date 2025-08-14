package com.creeping_creeper.tinkers_thinking.mixins;

import com.creeping_creeper.tinkers_thinking.common.register.ModModifiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import static slimeknights.tconstruct.library.tools.helper.ModifierUtil.getModifierLevel;

@Mixin(Villager.class)
public class VillagerMixin {
    @Inject(at = @At("RETURN"), method = "updateSpecialPrices")
    private void updateSpecialPrices(Player player, CallbackInfo info) {
        Object forgottenObject = this;
        Villager villager = (Villager) forgottenObject;
        if (getModifierLevel(player.getItemBySlot(EquipmentSlot.HEAD), ModModifiers.Tyranny.getId())>0 && ToolStack.from(player.getItemBySlot(EquipmentSlot.MAINHAND)).getStats().get(ToolStats.ATTACK_DAMAGE) > villager.getMaxHealth()-1) {
            for (MerchantOffer trade : villager.getOffers()) {
                double discountValue = 0.35;
                int discount = (int) Math.floor(discountValue * trade.getBaseCostA().getCount());
                trade.addToSpecialPriceDiff(-Math.max(discount, 1));
            }
        }
    }
}
