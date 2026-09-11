package com.creeping_creeper.tinkers_thinking.common.modifer.misc;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.predicate.IJsonPredicate;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierManager;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.special.sling.SlingAngleModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.special.sling.SlingForceModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.special.sling.SlingLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.util.ModifierCondition;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.SlimeBounceHandler;
import slimeknights.tconstruct.tools.TinkerToolActions;
import slimeknights.tconstruct.tools.modules.interaction.sling.SlingModule;

public record SlingSprintingModule(LevelingValue forceMultiplier, float drawtimeMultiplier, IJsonPredicate<LivingEntity> target, ModifierCondition<IToolStackView> condition) implements SlingModule {
    public static final RecordLoadable<SlingSprintingModule> LOADER = RecordLoadable.create(FORCE_FIELD, DRAWTIME_FIELD, TARGET_FIELD, ModifierCondition.TOOL_FIELD, SlingSprintingModule::new);

    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public void sling(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, int chargeTime, ModifierEntry activeModifier) {
        Level level = entity.level();
        // must be sufficiently charged, not have too much knockback resistance, and not have any modifier zeroing its force
        float charge = GeneralInteractionModifierHook.getToolCharge(tool, chargeTime);
        if (charge > 0 && target.matches(entity)) {
            float forceMultiplier = this.forceMultiplier.compute(modifier);
            float multiplier = charge * Math.abs(forceMultiplier);
            float force = SlingForceModifierHook.modifySlingForce(tool, entity, entity, modifier, SlingModule.getPower(tool, entity) * multiplier, multiplier);
            if (force > 0) {
                Vec3 look = entity.getLookAngle().normalize();

                Vec3 angle = SlingAngleModifierHook.modifySlingAngle(tool, entity, entity, modifier, force, multiplier, new Vec3((look.x ), 0, (look.z )));

                // fling in look direction, unless force is negative in which case reverse it
                if (forceMultiplier < 0) {
                    angle = angle.multiply(-1, -1, -1);
                }
                angle = SlingAngleModifierHook.modifySlingAngle(tool, entity, entity, modifier, force, multiplier, angle);
                entity.push(force * angle.x, force * angle.y, force * angle.z);
                int time = (int) (charge * 20);
                ModifierUtils.addEffect(entity, ModEffects.jumpless.get(), time, 0, false);
                ModifierUtils.addEffect(entity, ModEffects.weightless.get(),time, 1, false);
                // if on the ground, get off the ground so jumping is not required before springing
                if (entity.onGround()) {
                    entity.move(MoverType.SELF, new Vec3(0, 1.3f, 0));
                }

                // after sling callback
                SlimeBounceHandler.addBounceHandler(entity);
                SlingLaunchModifierHook.afterSlingLaunch(tool, entity, entity, modifier, force, multiplier, angle);

                if (!level.isClientSide) {
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), Sounds.SLIME_SLING.getSound(), entity.getSoundSource(), 1, 1);
                    ToolDamageUtil.damageAnimated(tool, 1, entity, entity.getUsedItemHand(), modifier.getId());
                }
                // only need player for exhaustion, cooldowns, and drill attack
                if (entity instanceof Player player) {
                    if (!level.isClientSide) {
                        player.causeFoodExhaustion(0.2F);
                        player.getCooldowns().addCooldown(tool.getItem(), 3);
                    }
                    // if supported, perform drill attack if the modifier is available
                    if (ModifierManager.isInTag(modifier.getId(), TinkerTags.Modifiers.DRILL_ATTACKS) && ModifierUtil.canPerformAction(tool, TinkerToolActions.DRILL_ATTACK)) {
                        player.startAutoSpinAttack(20);
                    }
                }
                return;
            }
        }
        // play failure sound
        if (!level.isClientSide && ModifierUtil.isActiveModifier(tool, modifier, activeModifier)) {
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), Sounds.SLIME_SLING.getSound(), entity.getSoundSource(), 1, 0.5f);
        }
    }
}
