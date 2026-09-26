package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceLocation;
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
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.common.network.TinkerNetwork;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolActionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.hook.interaction.UsingToolModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.modules.armor.CounterModule;

import java.util.List;

public record CounterAttackModule(float rate, LevelingValue boost) implements ModifierModule, GeneralInteractionModifierHook, OnAttackedModifierHook, MeleeDamageModifierHook, ToolActionModifierHook, UsingToolModifierHook {
   public static final ResourceLocation IS_BONKING = TinkersThinking.getResource("is_blocking");


    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<CounterAttackModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.GENERAL_INTERACT, ModifierHooks.ON_ATTACKED, ModifierHooks.MELEE_DAMAGE, ModifierHooks.TOOL_ACTION, ModifierHooks.TOOL_USING);
        LOADER = RecordLoadable.create(FloatLoadable.FROM_ZERO.requiredField("rate", CounterAttackModule::rate),
                LevelingValue.LOADABLE.directField(CounterAttackModule::boost), CounterAttackModule::new);
    }

    public RecordLoadable<CounterAttackModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }


    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        if (tool.getPersistentData().getBoolean(IS_BONKING)) {
            damage *= rate + boost.compute(tool.getModifierLevel(ModModifierIds.CounterAdvanced));
        }
        return damage;
    }
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        Entity attacker = source.getEntity();
        if (isDirectDamage && !source.is(DamageTypeTags.BYPASSES_SHIELD) && living instanceof Player player && CounterModule.isBlocking(tool, slotType, player) && ToolAttackUtil.isAttackable(living, attacker)) {
            ModDataNBT data = tool.getPersistentData();
            data.putBoolean(IS_BONKING, true);
            InteractionHand hand = living.getUsedItemHand();
            ToolAttackContext.Builder builder = ToolAttackContext.attacker(living).target(attacker).hand(hand).cooldown(1);
            if (hand == InteractionHand.MAIN_HAND) {
                builder.applyAttributes();
                builder.baseKnockback(0.8f);
            } else {
                builder.toolAttributes(tool);
            }
            ToolAttackUtil.performAttack(tool, builder.build());
            data.remove(IS_BONKING);
            ModifierUtils.addEffect(player, MobEffects.MOVEMENT_SPEED, 80, 3);
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
