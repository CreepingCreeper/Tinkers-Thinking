package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ScheduledProjectileTaskModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.utils.Schedule;

import javax.annotation.Nullable;
import java.util.List;

public enum BackModule implements ModifierModule, ScheduledProjectileTaskModifierHook, ProjectileHitModifierHook {
    INSTANCE;
    private static final ResourceLocation KEY = TinkersThinking.getResource("back");
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.SCHEDULE_PROJECTILE_TASK, ModifierHooks.PROJECTILE_HIT);
    public static final RecordLoadable<BackModule> LOADER = new SingletonLoader<>(INSTANCE);

    public RecordLoadable<BackModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    
    @Override
    public void scheduleProjectileTask(IToolStackView tool, ModifierEntry modifier, ItemStack ammo, Projectile projectile, @org.jetbrains.annotations.Nullable AbstractArrow arrow, ModDataNBT persistentData, Schedule.Scheduler scheduler) {
        scheduler.add(0,40);
    }

    @Override
    public void onScheduledProjectileTask(IToolStackView tool, ModifierEntry modifier, ItemStack ammo, Projectile projectile, @org.jetbrains.annotations.Nullable AbstractArrow arrow, ModDataNBT persistentData, int task) {
        if (task == 0 && !persistentData.getBoolean(KEY)){
            back(projectile);
        }
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target, boolean notBlocked) {
        back(projectile);
        persistentData.putBoolean(KEY, true);
        return false;
    }

    @Override
    public boolean onProjectileHitsBlock(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, BlockHitResult hit, @Nullable LivingEntity owner) {
        back(projectile);
        persistentData.putBoolean(KEY, true);
        return false;
    }

    private static void back(Projectile projectile){
        Vec3 vec3 = projectile.getDeltaMovement();
        Vec3 newVec = new Vec3(-vec3.x, -vec3.y, -vec3.z);
        projectile.setDeltaMovement(newVec);
    }
}
