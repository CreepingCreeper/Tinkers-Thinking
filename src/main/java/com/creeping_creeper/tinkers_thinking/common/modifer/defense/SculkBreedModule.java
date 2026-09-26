package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record SculkBreedModule(LevelingValue amount) implements ModifierModule, ModifyDamageModifierHook, EquipmentChangeModifierHook, TooltipModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SculkBreedModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MODIFY_DAMAGE, ModifierHooks.EQUIPMENT_CHANGE, ModifierHooks.TOOLTIP);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SculkBreedModule::amount), SculkBreedModule::new);
    }

    public RecordLoadable<SculkBreedModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }


    public static final UUID ATTRIBUTE_BONUS = UUID.fromString("2307DE5E-7CE8-4030-940E-514C1F170001");
    private static final Component Boost = TinkersThinking.makeTranslation("modifier", "sculk_breed.boost");

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        AttributeInstance attribute = living.getAttribute(Attributes.MAX_HEALTH);
        float x = (float)(amount * modifier.getLevel() * 0.5);
        if (living.hasEffect(ModEffects.sculk_power.get()) && !tool.isBroken()) {
            if (attribute.getModifier(ATTRIBUTE_BONUS) == null) {
            attribute.addTransientModifier(new AttributeModifier(ATTRIBUTE_BONUS, "tinkers_thinking.modifier.sculk_breed", x,
                    AttributeModifier.Operation.ADDITION));
            }
            if (attribute.getModifier(ATTRIBUTE_BONUS) != null && x > Objects.requireNonNull(attribute.getModifier(ATTRIBUTE_BONUS)).getAmount()) {
                attribute.removeModifier(ATTRIBUTE_BONUS);
                attribute.addTransientModifier(new AttributeModifier(ATTRIBUTE_BONUS, "tinkers_thinking.modifier.sculk_breed", x,
                    AttributeModifier.Operation.ADDITION));
            }
        }
        return amount;
    }

    @Override
    public void onUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        // remove boost when boots are removed
        LivingEntity living = context.getEntity();
        IToolStackView newTool = context.getReplacementTool();
        if (newTool == null || newTool.isBroken() || newTool.getModifier(modifier.getModifier()).getLevel() < modifier.getLevel()) {
            AttributeInstance attribute = living.getAttribute(Attributes.MAX_HEALTH);
            if (attribute.getModifier(ATTRIBUTE_BONUS) != null) {
                attribute.removeModifier(ATTRIBUTE_BONUS);
                if (living.getHealth()>living.getMaxHealth()){
                    living.setHealth(living.getMaxHealth());
                }
            }
        }
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        float x = 0;
        if (player != null && key == TooltipKey.SHIFT) {
            AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
            if (attribute!= null && attribute.getModifier(ATTRIBUTE_BONUS) != null) {
                x = (float) Objects.requireNonNull(attribute.getModifier(ATTRIBUTE_BONUS)).getAmount();
            }
            TooltipModifierHook.addFlatBoost(modifier.getModifier(), Boost, x, tooltip);
        }
    }
}
