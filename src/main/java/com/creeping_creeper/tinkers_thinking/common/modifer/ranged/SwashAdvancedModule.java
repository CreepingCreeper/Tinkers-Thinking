package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffectContext;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffectManager;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffects;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.tools.entity.FluidEffectProjectile;

import javax.annotation.Nullable;
import java.util.List;

public record SwashAdvancedModule(LevelingValue radius) implements ModifierModule, ProjectileHitModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SwashAdvancedModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.PROJECTILE_HIT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SwashAdvancedModule::radius), SwashAdvancedModule::new);
    }

    public RecordLoadable<SwashAdvancedModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target, boolean notBlocked) {
        if (attacker != null && target != null && projectile instanceof FluidEffectProjectile spit) {
            Level level = target.level();
            float range = radius.compute(modifier);
            List<Entity> list = level.getEntitiesOfClass(Entity.class, target.getBoundingBox().inflate(range, 0.25, range));
            list.remove(target);
            FluidStack fluid = spit.getFluid();
            FluidEffects recipe = FluidEffectManager.INSTANCE.find(fluid.getFluid());fluid.getFluid().getFluidType();
            if (recipe.hasEntityEffects()) {
                for (Entity entity : list) {
                    if (!(entity instanceof ArmorStand stand && stand.isMarker())) {
                        recipe.applyToEntity(fluid, spit.getPower(), FluidEffectContext.builder(level).user(attacker).target(entity), IFluidHandler.FluidAction.EXECUTE);
                    }
                }
            }
        }
        return false;
    }
}