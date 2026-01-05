package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.shared.TinkerEffects;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class TeleportAdvancedModifier extends NoLevelsModifier implements GeneralInteractionModifierHook, MeleeHitModifierHook, ModifierUtils {
    private final ResourceLocation X = new ResourceLocation("tinkersinnovation", "teleport_x");
    private final ResourceLocation Y = new ResourceLocation("tinkersinnovation", "teleport_y");
    private final ResourceLocation Z = new ResourceLocation("tinkersinnovation", "teleport_z");
    private final ResourceLocation WORLD = new ResourceLocation("tinkersinnovation", "teleport_dimension");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.GENERAL_INTERACT, ModifierHooks.MELEE_HIT);
    }

    @Override
    public int getPriority() {
        return 5;
    }

    private void applyEffect(LivingEntity living, int level){
        addEffect(living, MobEffects.MOVEMENT_SPEED, level * 300, 1);
        addEffect(living, ModEffects.sculk_power.get(), level * 300);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.RIGHT_CLICK && !tool.isBroken() && player.isCrouching() && !player.hasEffect(TinkerModifiers.teleportCooldownEffect.get()) && !player.hasEffect(TinkerEffects.enderference.get())) {
            Level world = player.level();
            ModDataNBT data = tool.getPersistentData();
            if (data.contains(X, Tag.TAG_FLOAT) && data.contains(Y, Tag.TAG_FLOAT) && data.contains(Z, Tag.TAG_FLOAT) && data.contains(WORLD, Tag.TAG_STRING)) {
                if (data.getString(WORLD).equals(world.dimension().location().getPath())) {
                    applyEffect(player, modifier.getLevel());
                }
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (!tool.isBroken()) {
            if (target != null && !target.hasEffect(TinkerEffects.enderference.get())) {
                Level world = target.level();
                ModDataNBT data = tool.getPersistentData();
                if (data.contains(X, Tag.TAG_FLOAT) && data.contains(Y, Tag.TAG_FLOAT) && data.contains(Z, Tag.TAG_FLOAT) && data.contains(WORLD, Tag.TAG_STRING)) {
                    if (data.getString(WORLD).equals(world.dimension().location().getPath())) {
                        applyEffect(context.getAttacker(), modifier.getLevel());
                    }
                }
            }
        }
    }
}
