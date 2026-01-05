package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.entity.ProjectileWithPower;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import java.util.Objects;
import java.util.UUID;

public class RedyeModifier extends Modifier implements MeleeDamageModifierHook, MonsterMeleeHitModifierHook.RedirectAfter,ProjectileHitModifierHook, ModifierRemovalHook, ModifierUtils {
    private final ResourceLocation KEY = new ResourceLocation(TinkersThinking.MODID, "a");
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.MONSTER_MELEE_DAMAGE, ModifierHooks.PROJECTILE_HIT, ModifierHooks.REMOVE);
    }
    private float dye(ServerLevel level, LivingEntity target, LivingEntity attacker, ModDataNBT persistentData, float damage, boolean a, int l){
        Entity entity = persistentData.contains(KEY) ? level.getEntity((UUID.fromString(persistentData.getString(KEY)))) : null;
        persistentData.putString(this.KEY, target.getStringUUID());
        if (entity instanceof LivingEntity living && living.isAlive() && attacker!=null){
            if (a && entity == target) {
              return 1+l*0.06f;
            }
            if (!a && entity != target) {
                DamageSource source = TinkerDamageTypes.source(level.registryAccess(), DamageTypes.PLAYER_ATTACK, attacker);
                ToolAttackUtil.attackEntitySecondary(source, damage, living, living, true);
            }
        }
        return 0;
    }
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity target = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();
        if (!context.isExtraAttack() && context.isFullyCharged() && target!=null && target.isAlive()) {
            damage *= (1 + dye((ServerLevel) context.getLevel(),target,attacker,Objects.requireNonNull(tool.getPersistentData()),damage,reverse(tool),modifier.getLevel()));
        }
        return damage;
    }
    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, ModDataNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target, boolean notBlocked) {
        if (target != null) {
            float power = 0f;
            if (projectile instanceof AbstractArrow arrow) power = (float) arrow.getBaseDamage();
            if (projectile instanceof ProjectileWithPower withPower ) power = withPower.getDamage();
            setPower(projectile, dye((ServerLevel) target.level(),target,attacker,Objects.requireNonNull(persistentData),power,reverseProjectile(projectile),modifier.getLevel()));
        }
        return false;
    }
    @Nullable
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        if (tool.getModifierLevel(this.getId()) == 0) {
            tool.getPersistentData().remove(KEY);
        }
        return null;
    }
}
