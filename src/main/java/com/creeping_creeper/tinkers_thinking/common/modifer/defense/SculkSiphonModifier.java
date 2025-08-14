package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModModifiers;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
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
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class SculkSiphonModifier extends Modifier implements MeleeHitModifierHook,ModifyDamageModifierHook, ModifierUtils {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT ,ModifierHooks.MODIFY_DAMAGE);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity living = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();
        if (context.isFullyCharged()&&!context.isExtraAttack()&&attacker.hasEffect(ModEffects.sculk_power.get())&&living != null&&!living.hasEffect(ModEffects.modifier_immune.get())){
            dropItem(living,ModCommonItems.soul_shard_a.get(),getArmorModifierLevel(attacker, ModModifiers.SculkSiphon.getId()));
            living.addEffect(new MobEffectInstance(ModEffects.modifier_immune.get(),200));
        }
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, net.minecraft.world.entity.EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
       if (context.getEntity().hasEffect(ModEffects.sculk_power.get())&&source.getEntity() instanceof LivingEntity living&& living.isAlive()&&!living.hasEffect(ModEffects.modifier_immune.get())){
           dropItem(living,ModCommonItems.soul_shard_b.get(),getArmorModifierLevel(context.getEntity(), ModModifiers.SculkSiphon.getId()));
           living.addEffect(new MobEffectInstance(ModEffects.modifier_immune.get(),200));
       }
        return amount;
    }
    private static void dropItem(LivingEntity dying, Item item, int x){
        ItemEntity itementity = new ItemEntity(dying.level(), dying.getX(), dying.getY(), dying.getZ(), new ItemStack(item,x));
        itementity.setPickUpDelay(10);
        itementity.lifespan = 600;
        dying.level().addFreshEntity(itementity);
    }
}
