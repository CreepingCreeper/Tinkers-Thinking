package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.shared.TinkerEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecalamityModifier extends Modifier implements MeleeDamageModifierHook, ProjectileHitModifierHook, ModifierUtils {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.PROJECTILE_HIT);
    }
    private static final List<MobEffect> EFFECTS = new ArrayList<>(List.of(
            MobEffects.POISON, MobEffects.WITHER, TinkerEffects.bleeding.get(), ModEffects.disintegration.get()
    ));
    private float calamity(LivingEntity target,LivingEntity attacker,boolean a,float damage,int l){
        MobEffect effect = ModEffects.disintegration.get();
        int x = 0;
        if (a) {
            switch (RANDOM.nextInt(4)) {
                case 1 -> effect = MobEffects.POISON;
                case 2 -> effect = MobEffects.WITHER;
                case 3 -> effect = TinkerEffects.bleeding.get();
            }
            target.addEffect(new MobEffectInstance(effect, target.hasEffect(effect) ? Objects.requireNonNull(target.getEffect(effect)).getDuration() + 80*l : 80*l));
            target.setLastHurtMob(attacker);
        }else for (MobEffect i : EFFECTS) {
            x += target.hasEffect(i) ? 1 : 0;
        }
        return (float) (damage * (1 + 0.1 * x));
    }
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity target = context.getLivingTarget();
        if (!context.isExtraAttack() && context.isFullyCharged()&&target!=null&&target.isAlive()) {
            calamity(target,context.getAttacker(),reverse(tool),damage,modifier.getLevel());
        }
        return damage;
    }
    @Override
    public boolean onProjectileHitEntity(@NotNull ModifierNBT modifiers, ModDataNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        if (target != null) {
            float damage=1;
            setPower(projectile,calamity(target,attacker, reverseProjectile(projectile),damage,modifier.getLevel()));
        }
        return false;
    }
}