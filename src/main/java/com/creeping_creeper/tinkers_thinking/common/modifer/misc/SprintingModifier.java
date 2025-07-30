package com.creeping_creeper.tinkers_thinking.common.modifer.misc;

import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.SlimeBounceHandler;
import slimeknights.tconstruct.tools.modifiers.ability.sling.SlingModifier;

public class SprintingModifier extends SlingModifier {
    @Override
    public @NotNull InteractionResult onToolUse(IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull Player player, @NotNull InteractionHand hand, @NotNull InteractionSource source) {
        if (!tool.isBroken() && source == InteractionSource.RIGHT_CLICK) {
            GeneralInteractionModifierHook.startUsingWithDrawtime(tool, modifier.getId(), player, hand, 1f);
        }
        return InteractionResult.SUCCESS;
    }
    @Override
    public void onStoppedUsing(@NotNull IToolStackView tool, ModifierEntry modifier, LivingEntity entity, int timeLeft) {
        super.onStoppedUsing(tool, modifier, entity, timeLeft);
        if (entity instanceof Player player && !player.isFallFlying()) {
            player.causeFoodExhaustion(0.2F);

            float f = getForce(tool, modifier, player, timeLeft, true) * 3.5f;
            if (f > 0) {
                Vec3 look = player.getLookAngle().add(0, 1, 0).normalize();
                player.push(look.x * f,0.02,look.z * f);
                int time = (int)Math.ceil((getUseDuration(tool, modifier) - timeLeft)/3f*2* ConditionalStatModifierHook.getModifiedStat(tool, entity, ToolStats.DRAW_SPEED));
                if (time>20){
                    time=20;
                }
                player.addEffect(new MobEffectInstance(ModEffects.weightless.get(),time,1,true,false));
                SlimeBounceHandler.addBounceHandler(player);
                if (!entity.level().isClientSide) {
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.SLIME_SLING.getSound(), player.getSoundSource(), 1, 1);
                    player.causeFoodExhaustion(0.2F);
                    player.getCooldowns().addCooldown(tool.getItem(), 18);
                    ToolDamageUtil.damageAnimated(tool, 1, entity);
                }
                return;
            }
        }
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), Sounds.SLIME_SLING.getSound(), entity.getSoundSource(), 1, 0.5f);
    }
}
