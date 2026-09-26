package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record MagicTransformModule(LevelingValue rate1, float rate2) implements ModifierModule, MeleeDamageModifierHook, ModifyDamageModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<MagicTransformModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_DAMAGE, ModifierHooks.MODIFY_HURT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(MagicTransformModule::rate1),
                FloatLoadable.FROM_ZERO.requiredField("magic_rate", MagicTransformModule::rate2),MagicTransformModule::new);
    }

    public RecordLoadable<MagicTransformModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }


    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity target = context.getLivingTarget();
        if (target != null && target.isAlive()) {
            damage = transform(context.getAttacker(), target, damage, modifier.getLevel());
        }
        return damage;
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
         Entity attacker = source.getEntity();
         LivingEntity living = context.getEntity();
        if (living.isAlive() && attacker != null && !source.is(DamageTypeTags.WITCH_RESISTANT_TO)) {
            amount = transform(source.getEntity(), living, amount, modifier.getLevel());
        }
        return amount;
    }
    private float transform(Entity attacker, LivingEntity target, float value, int level){
        float x = value;
        value *= Math.max(1 - rate1.compute(level), 0);
        float y = (x-value) * rate2;
        int z = target.invulnerableTime;
        target.invulnerableTime=0;
        DamageSource damageSource = TinkerDamageTypes.source(target.level().registryAccess(), DamageTypes.INDIRECT_MAGIC, attacker);
        target.hurt(damageSource,y);
        target.invulnerableTime = z;
        return value;
    }
}
