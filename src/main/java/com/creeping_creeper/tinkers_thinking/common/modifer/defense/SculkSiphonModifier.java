package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModModifiers;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class SculkSiphonModifier extends Modifier implements MeleeHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter, ModifyDamageModifierHook, ModifierUtils {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT, ModifierHooks.MODIFY_DAMAGE);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity living = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();
        if (context.isFullyCharged() && !context.isExtraAttack() && attacker.hasEffect(ModEffects.sculk_power.get()) && living != null && RANDOM.nextFloat() < 0.2){
            dropItem(living,ModCommonItems.soul_shard_a.get(), modifier.getLevel());
        }
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, net.minecraft.world.entity.EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        Entity entity = source.getEntity();
        if (context.getEntity().hasEffect(ModEffects.sculk_power.get()) && entity != null && entity.isAlive() && RANDOM.nextFloat() < 0.2){
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
