package com.creeping_creeper.tinkers_thinking.mixins;

import com.creeping_creeper.tinkers_thinking.common.register.ModModifiers;
import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingArrow;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.logic.ModifiableArrowDispenserBehavior;

@Mixin({ModifiableArrowDispenserBehavior.class})
public class ModifiableArrowDispenserBehaviorMixin {
    @Inject(method = "getProjectile",at = @At(value = "HEAD"),cancellable = true)
    protected void getProjectile(Level level, Position position, ItemStack stack, CallbackInfoReturnable<Projectile> cir) {
        if (ToolStack.from(stack).getModifierLevel(ModModifiers.Seeking.get())>0) {
            SeekingArrow arrow = new SeekingArrow(level, position.x(), position.y(), position.z());
            arrow.onCreate(stack, null);
            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
            cir.setReturnValue(arrow);
        }
    }
}
