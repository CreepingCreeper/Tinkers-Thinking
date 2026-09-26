package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.modifiers.Modifier;
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
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record SculkSiphonModule(float chance) implements ModifierModule, MeleeHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter, ModifyDamageModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SculkSiphonModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT, ModifierHooks.MODIFY_DAMAGE);
        LOADER = RecordLoadable.create(FloatLoadable.FROM_ZERO.requiredField("chance", SculkSiphonModule::chance), SculkSiphonModule::new);
    }

    public RecordLoadable<SculkSiphonModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity living = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();
        if (context.isFullyCharged() && !context.isExtraAttack() && attacker.hasEffect(ModEffects.sculk_power.get()) && living != null && Modifier.RANDOM.nextFloat() < chance){
            dropItem(living,ModCommonItems.soul_shard_a.get(), modifier.getLevel());
        }
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, net.minecraft.world.entity.EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        Entity entity = source.getEntity();
        if (context.getEntity().hasEffect(ModEffects.sculk_power.get()) && entity != null && entity.isAlive() && Modifier.RANDOM.nextFloat() < chance){
           dropItem(entity,ModCommonItems.soul_shard_b.get(), modifier.getLevel());
       }
        return amount;
    }

    private void dropItem(Entity living, Item item, int x){
        ItemEntity itementity = new ItemEntity(living.level(), living.getX(), living.getY(), living.getZ(), new ItemStack(item,x));
        itementity.setPickUpDelay(10);
        itementity.lifespan = 600;
        living.level().addFreshEntity(itementity);
    }
}
