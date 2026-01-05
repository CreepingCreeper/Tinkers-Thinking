package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record A(LevelingValue amount) implements ModifierModule, MeleeHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter, ModifyDamageModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<A> LOADER;

    public @NotNull RecordLoadable<A> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity attacker = context.getAttacker();
        LivingEntity target = context.getLivingTarget();
        if (!context.isExtraAttack() && target != null && damageDealt > 0) {
            dealDamage(attacker, target, damageDealt * modifier.getLevel() * amount().eachLevel());
        }
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float damage, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        LivingEntity attacker = (LivingEntity)source.getEntity();
        if (attacker != null && attacker.isAlive() && !source.is(DamageTypeTags.BYPASSES_ARMOR)) {
            dealDamage(living, attacker, damage * modifier.getLevel() * (amount().eachLevel() - 0.045f));
        }
        return damage;
    }
    private float who(LivingEntity living){
        return living.getHealth()/living.getMaxHealth();
    }
    private void dealDamage(LivingEntity a, LivingEntity b, float damage){
        if (who(a) < who(b)){
            int time = a.invulnerableTime;
            a.invulnerableTime = 0;
            DamageSource source = TinkerDamageTypes.source(b.level().registryAccess(), TinkerDamageTypes.BLEEDING, b);
            ToolAttackUtil.attackEntitySecondary(source, damage, a, a, true);
            a.invulnerableTime = time;
        }else {
            int time = b.invulnerableTime;
            b.invulnerableTime=0;
            DamageSource source = TinkerDamageTypes.source(a.level().registryAccess(), TinkerDamageTypes.BLEEDING, a);
            ToolAttackUtil.attackEntitySecondary(source, damage, b, b, true);
            b.invulnerableTime = time;
        }
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT, ModifierHooks.MODIFY_DAMAGE);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(A::amount), A::new);
    }
}
