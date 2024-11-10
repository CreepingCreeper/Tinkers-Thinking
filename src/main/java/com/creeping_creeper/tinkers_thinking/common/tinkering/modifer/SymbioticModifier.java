package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import javax.annotation.Nullable;

public class SymbioticModifier extends Modifier implements  MeleeHitModifierHook, ProjectileLaunchModifierHook, ModifyDamageModifierHook{
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,ModifierHooks.MELEE_HIT,ModifierHooks.PROJECTILE_LAUNCH, ModifierHooks.MODIFY_DAMAGE);
    }
    private void eat(IToolStackView tool, ModifierEntry modifier, LivingEntity entity) {
        if (entity instanceof Player player) {
            // eat the food
            int level = modifier.getLevel();
            player.heal(Math.max(level, player.getMaxHealth() * 0.2f));
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.NECROTIC_HEAL.getSound(), SoundSource.PLAYERS, 1.0f, 1.0f);
            // take a bit of extra damage to heal
            // 8 damage for a bite per level, does not process reinforced/overslime, your teeth are tough
            if (ToolDamageUtil.directDamage(tool, 8* level, player, player.getUseItem())) {
                player.broadcastBreakEvent(player.getUsedItemHand());
            }
        }
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity entity = context.getPlayerAttacker();
        float level = modifier.getEffectiveLevel();
        if (RANDOM.nextFloat() < (level * 0.15f) && entity!=null&&entity.getHealth()<entity.getMaxHealth()&& !tool.isBroken()) {
            eat(tool, modifier, entity);
        }
    }
    @Override
    public void onProjectileLaunch(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, LivingEntity entity, @NotNull Projectile projectile, @Nullable AbstractArrow arrow, @NotNull NamespacedNBT persistentData, boolean primary) {
        float level = modifier.getEffectiveLevel();
        if (RANDOM.nextFloat() < (level * 0.15f) && entity.getHealth()<entity.getMaxHealth()&& !tool.isBroken()) {
            eat(tool, modifier, entity);
        }
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        float level = modifier.getEffectiveLevel();
        if (RANDOM.nextFloat() < (level * 0.15f) && living.getHealth() < living.getMaxHealth() && !tool.isBroken()) {
            eat(tool, modifier, living);
        }
        return amount;
    }
}
