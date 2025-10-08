package com.creeping_creeper.tinkers_thinking.common.library;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.entity.ProjectileWithPower;
import slimeknights.tconstruct.library.tools.capability.PersistentDataCapability;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.Objects;

import static slimeknights.tconstruct.library.tools.helper.ModifierUtil.getModifierLevel;

public interface ModifierUtils {
    default int getItemModifierLevel(LivingEntity living, ModifierId id){
        int x = 0;
        for (int i=98;i<104;i++){
            ItemStack stack = living.getSlot(i).get();
            if (isToolItem(stack)) {
                x += getModifierLevel(stack, id);
            }
        }
        return x;
    }
    default int getArmorModifierLevel(LivingEntity living, ModifierId id){
        int x = 0;
        for (int i=99;i<104;i++){
            ItemStack stack = living.getSlot(i).get();
            if (isToolItem(stack)) {
                x += getModifierLevel(stack, id);
            }
        }
        return x;
    }
    default boolean isToolItem(ItemStack stack){
        return stack.getItem() instanceof IModifiable&& !ToolStack.from(stack).isBroken();
    }
    ResourceLocation reverse_key = new ResourceLocation(TinkersThinking.MODID, "reverse");
    default boolean reverse(IToolStackView tool){
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        return !persistentData.contains(reverse_key);
    }
    default boolean reverseProjectile(Projectile projectile){
        ModDataNBT data = PersistentDataCapability.getOrWarn(projectile);
        CompoundTag tag = data.getCompound(reverse_key);
        return !tag.getBoolean("reverse");
    }
    default void setPower(Projectile projectile,float multiple){
        float x= 1+multiple;
        if (projectile instanceof AbstractArrow arrow){
            arrow.setBaseDamage(arrow.getBaseDamage()*x);
            return;
        }
        if (projectile instanceof ProjectileWithPower){
            ((ProjectileWithPower) projectile).setPower(((ProjectileWithPower) projectile).getPower()*x);
        }
    }
    default void addPower(Projectile projectile,float addition){
        if (projectile instanceof AbstractArrow arrow){
            arrow.setBaseDamage(arrow.getBaseDamage()+addition);
            return;
        }
        if (projectile instanceof ProjectileWithPower projectile1){
            projectile1.setPower(projectile1.getPower()+addition);
        }
    }
}
