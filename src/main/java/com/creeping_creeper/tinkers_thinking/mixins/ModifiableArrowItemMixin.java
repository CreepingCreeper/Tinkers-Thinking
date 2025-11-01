package com.creeping_creeper.tinkers_thinking.mixins;

import com.creeping_creeper.tinkers_thinking.common.register.ModModifiers;
import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.item.ModifiableArrowItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin({ModifiableArrowItem.class})
public class ModifiableArrowItemMixin {
    @Inject(method = "createArrow",at = @At(value = "HEAD"),cancellable = true)
    public void createArrow(Level level, ItemStack stack, LivingEntity shooter, CallbackInfoReturnable<AbstractArrow> cir) {
        if (ToolStack.from(stack).getModifierLevel(ModModifiers.Seeking.get())>0) {
            SeekingArrow arrow = new SeekingArrow(level, shooter);
            arrow.onCreate(stack, shooter);
            cir.setReturnValue(arrow);
        }
    }
}
