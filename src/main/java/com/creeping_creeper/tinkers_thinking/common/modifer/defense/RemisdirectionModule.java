package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.events.teleport.SlingModifierTeleportEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.List;
import java.util.Optional;

public enum RemisdirectionModule implements ModifierModule, DamageBlockModifierHook, ModifierUtils {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<RemisdirectionModule>defaultHooks(ModifierHooks.DAMAGE_BLOCK);
    public static final RecordLoadable<RemisdirectionModule> LOADER = new SingletonLoader<>(INSTANCE);
    public @NotNull RecordLoadable<RemisdirectionModule> getLoader() {
        return LOADER;
    }
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        LivingEntity living = context.getEntity();
        if (source.getEntity() != null && !living.hasEffect(TinkerModifiers.teleportCooldownEffect.get()) && reverse(tool)){
            Vec3 look = living.getLookAngle();
            Level level = context.getLevel();
            double offX = look.x * 5;
            double offZ = look.y * 5;
            BlockPos furthestPos = null;
            // find teleport target
            while (Math.abs(offX) > .5 || Math.abs(offZ) > .5) { // while not too close to player
                BlockPos posAttempt = BlockPos.containing(living.getX() + offX, living.getY(), living.getZ() + offZ);
                // if we do not have a position yet, see if this one is valid
                if (furthestPos == null) {
                    if (level.getWorldBorder().isWithinBounds(posAttempt) && !level.getBlockState(posAttempt).isSuffocating(level, posAttempt)) {
                        furthestPos = posAttempt;
                    }
                } else {
                    // if we already have a position, clear if the new one is unbreakable
                    if (level.getBlockState(posAttempt).getDestroySpeed(level, posAttempt) == -1) {
                        furthestPos = null;
                    }
                }
                // update for next iteration
                offX -= (Math.abs(offX) > .25 ? (offX >= 0 ? 1 : -1) * .25 : 0);
                offZ -= (Math.abs(offZ) > .25 ? (offZ >= 0 ? 1 : -1) * .25 : 0);
            }
            if (furthestPos != null) {
                SlingModifierTeleportEvent event = new SlingModifierTeleportEvent(living, furthestPos.getX() + 0.5f, furthestPos.getY(), furthestPos.getZ() + 0.5f, tool, modifier);
                MinecraftForge.EVENT_BUS.post(event);
                if (!event.isCanceled()) {
                    block(living);
                    Optional<TinkerDataCapability.Holder> dataCap = living.getCapability(TinkerDataCapability.CAPABILITY).resolve();
                    dataCap.ifPresent(data -> addEffect(living, TinkerModifiers.teleportCooldownEffect.get(), Mth.clamp((int) amount / data.get(ModDataKeys.Remisdirection, 1), 10, 60) * 20));
                    living.teleportTo(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                    return true;
                }
            }
        }
        return false;
    }
}
