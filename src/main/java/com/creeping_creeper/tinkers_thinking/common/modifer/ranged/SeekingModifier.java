package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingThrownShuriken;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileShootModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.tools.entity.ThrownShuriken;

public class SeekingModifier extends Modifier implements ProjectileShootModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_SHOT);
    }

    @Override
    public void onProjectileShoot(IToolStackView tool, ModifierEntry modifier, @Nullable LivingEntity shooter, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, boolean primary) {
        Level level = projectile.level();
        CompoundTag tag = new CompoundTag();
        projectile.save(tag);
        if (projectile instanceof ThrownShuriken x) {
            SeekingThrownShuriken y = new SeekingThrownShuriken(level, x.getX(), x.getY(), x.getZ());
            y.readAdditionalSaveData(tag);
            y.setPower(x.getPower());
            Vec3 vec3 = x.getDeltaMovement();
            EntityModifierCapability.getCapability(y).addModifiers(tool.getModifiers());
            boolean isFire = x.isOnFire();
            x.discard();
            y.setDeltaMovement(vec3);
            double d0 = vec3.horizontalDistance();
            y.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
            y.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
            y.yRotO = y.getYRot();
            y.xRotO = y.getXRot();
            level.addFreshEntity(y);
            if (isFire){
                y.setRemainingFireTicks(100);
            }
        }
    }
}
