package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import javax.annotation.Nullable;
import java.util.List;

public record NonsenseModule(LevelingValue chance) implements ModifierModule, ProjectileHitModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<NonsenseModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.PROJECTILE_HIT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(NonsenseModule::chance), NonsenseModule::new);
    }

    public RecordLoadable<NonsenseModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public boolean onProjectileHitsBlock(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, BlockHitResult hit, @Nullable LivingEntity owner) {
        if (Modifier.RANDOM.nextFloat() > (chance.compute(modifier))) {
            projectile.level().playSound(null, projectile.getX(), projectile.getY(), projectile.getZ(), SoundEvents.GRASS_BREAK, SoundSource.MASTER, 1.0f, 1.0f);
            projectile.discard();
        }
        return false;
    }
}
