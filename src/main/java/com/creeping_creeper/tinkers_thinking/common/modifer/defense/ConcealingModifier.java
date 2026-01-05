package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.predicate.damage.DamageSourcePredicate;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ProtectionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.armor.ProtectionModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class ConcealingModifier extends Modifier implements TooltipModifierHook, ModifyDamageModifierHook, ProtectionModifierHook, ModifierUtils {
    public int getPriority() {
        return 90;
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOLTIP,ModifierHooks.MODIFY_DAMAGE, ModifierHooks.PROTECTION);
    }
    @Override
    public float modifyDamageTaken(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull EquipmentContext context, @NotNull EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (source.getEntity() != null) {
            addEffect(context.getEntity(),MobEffects.INVISIBILITY, 200, 0);
        }
        return amount;
    }

    @Override
    public float getProtectionModifier(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull EquipmentContext context, @NotNull EquipmentSlot slotType, @NotNull DamageSource source, float modifierValue) {
        if (context.getEntity().hasEffect(MobEffects.INVISIBILITY)&&DamageSourcePredicate.CAN_PROTECT.matches(source)) {
            modifierValue += (float) (modifier.getLevel()*1.5);
        }
        return modifierValue;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, @NotNull List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
            ProtectionModule.addResistanceTooltip(tool, this,  modifier.getLevel(), player, tooltip);
    }
}

