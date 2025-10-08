package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.List;

import static slimeknights.tconstruct.library.modifiers.Modifier.RANDOM;

public record SymbioticModule(LevelingValue amount) implements ModifierModule,  MeleeHitModifierHook, ProjectileLaunchModifierHook, ModifyDamageModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SymbioticModule> LOADER;

    public @NotNull RecordLoadable<SymbioticModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity entity = context.getPlayerAttacker();
        float level = modifier.getEffectiveLevel();
        if (!context.isExtraAttack() && context.isFullyCharged()&&RANDOM.nextFloat() < (level * amount.eachLevel()) && entity!=null&&entity.getHealth()<entity.getMaxHealth()&& !tool.isBroken()) {
            eat(tool, modifier, entity);
        }
    }
    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, boolean primary) {
        float level = modifier.getEffectiveLevel();
        if (primary&&RANDOM.nextFloat() < (level * amount.eachLevel()) && shooter.getHealth()<shooter.getMaxHealth()&& !tool.isBroken()) {
            eat(tool, modifier, shooter);
        }
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, net.minecraft.world.entity.EquipmentSlot slotType, DamageSource source, float damage, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        float level = modifier.getEffectiveLevel();
        if (RANDOM.nextFloat() < (level * amount.eachLevel()) && living.getHealth() < living.getMaxHealth() && !tool.isBroken()) {
            eat(tool, modifier, living);
        }
        return damage;
    }
    private void eat(IToolStackView tool, ModifierEntry modifier, LivingEntity entity) {
        if (entity instanceof Player player) {
            // eat the food
            int level = modifier.getLevel();
            player.heal(Math.max(level, (player.getMaxHealth()-player.getHealth()) * 0.3f));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.NECROTIC_HEAL.getSound(), SoundSource.PLAYERS, 1.0f, 1.0f);
            // take a bit of extra damage to heal
            // 8 damage for a bite per level, does not process reinforced/overslime, your teeth are tough
            if (ToolDamageUtil.directDamage(tool, 8* level, player, player.getUseItem())) {
                player.broadcastBreakEvent(player.getUsedItemHand());
            }
        }
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_LAUNCH, ModifierHooks.MODIFY_DAMAGE);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SymbioticModule::amount), SymbioticModule::new);
    }
}
