package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import slimeknights.tconstruct.common.network.TinkerNetwork;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolActionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.hook.interaction.UsingToolModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap.Builder;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.modules.armor.CounterModule;

public class CounterAttackModifier extends NoLevelsModifier implements GeneralInteractionModifierHook, OnAttackedModifierHook, MeleeDamageModifierHook, ToolActionModifierHook, UsingToolModifierHook, ModifierUtils {
    private static boolean isblocking = false;
    @Override
    protected void registerHooks(Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.GENERAL_INTERACT, ModifierHooks.ON_ATTACKED, ModifierHooks.MELEE_DAMAGE, ModifierHooks.TOOL_ACTION, ModifierHooks.TOOL_USING);
    }
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        if (isblocking) {
            damage *= 2+0.35f*tool.getModifierLevel(ModModifierIds.CounterAdvanced);
        }
        return damage;
    }
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        Entity attacker = source.getEntity();
        if (isDirectDamage && !source.is(DamageTypeTags.BYPASSES_SHIELD) && living instanceof Player player && CounterModule.isBlocking(tool, slotType, player) && attacker != null && ToolAttackUtil.isAttackable(living, attacker)) {
            isblocking = true;
            InteractionHand hand = living.getUsedItemHand();
            ToolAttackContext.Builder builder = ToolAttackContext.attacker(living).target(attacker).hand(hand).cooldown(1);
            if (hand == InteractionHand.MAIN_HAND) {
                builder.applyAttributes();
                builder.baseKnockback(0.8f);
            } else {
                builder.toolAttributes(tool);
            }
            ToolAttackUtil.performAttack(tool, builder.build());
            isblocking = false;
            addEffect(player, MobEffects.MOVEMENT_SPEED, 80, 3);
            ToolAttackUtil.spawnAttackParticle(TinkerTools.hammerAttackParticle.get(), living, 0.6d);
            if (player instanceof ServerPlayer playerMP) {
                TinkerNetwork.getInstance().sendVanillaPacket(new ClientboundSetEntityMotionPacket(player), playerMP);
            }
            // cooldowns and stuff
            context.getLevel().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, living.getSoundSource(), 1, 0.5f);
            player.causeFoodExhaustion(0.1F);
            ToolDamageUtil.damageAnimated(tool, 1, player);
        }
    }
    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.RIGHT_CLICK && !tool.isBroken()) {
            GeneralInteractionModifierHook.startUsing(tool, modifier.getId(), player, hand);
            //blocking require using the item at last 5 ticks
            player.useItemRemaining-=5;
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
    @Override
    public void onFinishUsing(IToolStackView tool, ModifierEntry modifier, LivingEntity entity) {
        if (entity instanceof Player player) {
            player.getCooldowns().addCooldown(tool.getItem(), (int)(20 / tool.getStats().get(ToolStats.ATTACK_SPEED)));
        }
    }

    @Override
    public UseAnim getUseAction(IToolStackView tool, ModifierEntry modifier) {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(IToolStackView tool, ModifierEntry modifier) {
        return 15;
    }

    @Override
    public boolean canPerformAction(IToolStackView tool, ModifierEntry modifier, ToolAction toolAction) {
        return toolAction == ToolActions.SHIELD_BLOCK;
    }
}
