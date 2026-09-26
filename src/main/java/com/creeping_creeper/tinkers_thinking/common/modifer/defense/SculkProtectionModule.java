package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ProtectionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.Util;

import java.util.List;

public record SculkProtectionModule(LevelingValue amount) implements ModifierModule, ProtectionModifierHook, TooltipModifierHook {
    private static final Component Boost = TinkersThinking.makeTranslation("modifier", "sculk_protection.boost");

    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SculkProtectionModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.PROTECTION, ModifierHooks.TOOLTIP);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(SculkProtectionModule::amount), SculkProtectionModule::new);
    }

    public RecordLoadable<SculkProtectionModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    private float getBono(LivingEntity living, ModifierEntry modifier){
        if (living.hasEffect(ModEffects.sculk_power.get())){
            return Math.min(1.5f, living.getEffect(ModEffects.sculk_power.get()).duration / 1600f) * modifier.getLevel();
        }
        return 0;
    }
    @Override
    public float getProtectionModifier(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float modifierValue) {
        return modifierValue + getBono(context.getEntity(), modifier);
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player!=null) {
            tooltip.add(Component.literal(Util.PERCENT_BOOST_FORMAT.format(getBono(player, modifier)/25f) + " ").append(Boost));
        }
    }
}
