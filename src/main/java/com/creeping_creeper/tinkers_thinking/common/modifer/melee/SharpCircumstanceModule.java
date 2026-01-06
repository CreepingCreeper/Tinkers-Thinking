package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.entity.ProjectileWithPower;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import java.util.List;

import static slimeknights.tconstruct.TConstruct.RANDOM;

public record SharpCircumstanceModule(LevelingValue amount) implements ModifierModule, MeleeDamageModifierHook, MonsterMeleeHitModifierHook.RedirectAfter, ProjectileHitModifierHook, ModifyDamageModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SharpCircumstanceModule> LOADER;

    public @NotNull RecordLoadable<SharpCircumstanceModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();
        LivingEntity target = context.getLivingTarget();
        if (!context.isExtraAttack() && target != null && damage > 0) {
            dealDamage(attacker, target, damage * modifier.getLevel() * amount().eachLevel());
        }
        return damage;
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target, boolean notBlocked) {
        if (attacker != null && attacker.isAlive() && target!=null){
            float damage = 0;
            if (projectile instanceof AbstractArrow arrow){
                damage = Mth.ceil(Mth.clamp(arrow.getDeltaMovement().length() * arrow.getBaseDamage(), 0.0f, Float.MAX_VALUE));
                if (arrow.isCritArrow()) {
                    damage = Math.min(RANDOM.nextFloat(damage / 2 + 2) + (long) damage, Float.MAX_VALUE);
                }
            }else if (projectile instanceof ProjectileWithPower withPower){
                damage = Mth.ceil(Mth.clamp(projectile.getDeltaMovement().length() * withPower.getDamage() * 1.5f, 0.0f, Float.MAX_VALUE));
            }
            dealDamage(attacker, target, damage * modifier.getLevel() * amount().eachLevel());
            return target.isDeadOrDying();
        }
        return false;
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float damage, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        LivingEntity attacker = (LivingEntity)source.getEntity();
        if (attacker != null && attacker.isAlive() && !source.is(DamageTypeTags.BYPASSES_ARMOR)) {
            dealDamage(living, attacker, damage * modifier.getLevel() * (amount().eachLevel() - 0.045f));
            if (living.isDeadOrDying()){
                damage = 0;
            }
        }
        return damage;
    }
    private float who(LivingEntity living){
        return living.getHealth()/living.getMaxHealth();
    }
    private void dealDamage(LivingEntity a, LivingEntity b, float damage){
        if (Float.compare(who(a), who(b)) >= 0){
            DamageSource source = TinkerDamageTypes.source(a.level().registryAccess(), TinkerDamageTypes.BLEEDING, a);
            ToolAttackUtil.attackEntitySecondary(source, damage, b, b, true);
            b.invulnerableTime = 0;
        }else {
            DamageSource source = TinkerDamageTypes.source(b.level().registryAccess(), TinkerDamageTypes.BLEEDING, b);
            ToolAttackUtil.attackEntitySecondary(source, damage, a, a, true);
            a.invulnerableTime = 0;
        }
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_DAMAGE, ModifierHooks.MONSTER_MELEE_DAMAGE, ModifierHooks.PROJECTILE_HIT, ModifierHooks.MODIFY_DAMAGE);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SharpCircumstanceModule::amount), SharpCircumstanceModule::new);
    }
}
