package com.creeping_creeper.tinkers_thinking.common.modifer.harvest;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import com.creeping_creeper.tinkers_thinking.data.ModDataKeys;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public enum SculkBoostModule implements ModifierModule, TooltipModifierHook , ConditionalStatModifierHook, BreakSpeedModifierHook, EquipmentChangeModifierHook{
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<SculkBoostModule>defaultHooks(ModifierHooks.TOOLTIP, ModifierHooks.BREAK_SPEED, ModifierHooks.CONDITIONAL_STAT, ModifierHooks.EQUIPMENT_CHANGE);
    public static final RecordLoadable<SculkBoostModule> LOADER = new SingletonLoader<>(INSTANCE);
    public @NotNull RecordLoadable<SculkBoostModule> getLoader() {
        return LOADER;
    }
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    private static final Component Boost = TinkersThinking.makeTranslation("modifier", "sculk_boost");
    public static final UUID ATTRIBUTE_BONUS = UUID.fromString("2307DE5E-7CE8-4030-940E-514C1F170002");
    @Override
    public void onBreakSpeed(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, PlayerEvent.@NotNull BreakSpeed event, @NotNull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (isEffective && event.getEntity().hasEffect(ModEffects.sculk_power.get())){
            event.setNewSpeed(event.getNewSpeed() * (1 + modifier.getLevel() * 0.2f));
        }
    }
    @Override
    public float modifyStat(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, LivingEntity living, @NotNull FloatToolStat stat, float baseValue, float multiplier) {
        if (living.hasEffect(ModEffects.sculk_power.get())) {
            if (tool.hasTag(TinkerTags.Items.ARMOR) && stat == ToolStats.ARMOR_TOUGHNESS) {
                AtomicInteger x = new AtomicInteger();
                Optional<TinkerDataCapability.Holder> dataCap = living.getCapability(TinkerDataCapability.CAPABILITY).resolve();
                dataCap.ifPresent(data -> x.set(data.get(ModDataKeys.SculkBoost, 1)));
                return baseValue * (1 + x.get() * 0.2f);
            }else if (stat == ToolStats.DRAW_SPEED) {
                return baseValue * (1 + (0.2f * modifier.getLevel()));
            }
        }
        return baseValue;
    }
    @Override
    public void onEquip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        // remove boost when boots are removed
        LivingEntity living = context.getEntity();
        IToolStackView oldTool = context.getOriginalTool();
        if (living.hasEffect(ModEffects.sculk_power.get()) && !tool.isBroken() && (oldTool == null || oldTool.getModifier(modifier.getModifier()).getLevel() != modifier.getLevel())) {
            reset(living);
        }
    }
    @Override
    public void onUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        // remove boost when boots are removed
        IToolStackView newTool = context.getReplacementTool();
        if (newTool == null || newTool.isBroken() || newTool.getModifier(modifier.getModifier()).getLevel() == 0) {
           reset(context.getEntity());
        }
    }
    private void reset(LivingEntity living){
        AttributeInstance attribute = living.getAttribute(Attributes.ARMOR_TOUGHNESS);
        if (attribute.getModifier(ATTRIBUTE_BONUS) != null) {
            attribute.removeModifier(ATTRIBUTE_BONUS);
        }
        Optional<TinkerDataCapability.Holder> dataCap = living.getCapability(TinkerDataCapability.CAPABILITY).resolve();
        dataCap.ifPresent(data -> {
            int x = data.get(ModDataKeys.SculkBoost, 0);
            if (x > 0) living.getAttribute(Attributes.ARMOR_TOUGHNESS).addPermanentModifier(new AttributeModifier(ATTRIBUTE_BONUS, "tinkers_thinking.modifier.sculk_boost", x * 0.2f,
                    AttributeModifier.Operation.MULTIPLY_BASE));
        }
        );
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player!=null) {
            float bonus = 0;
            if (player.hasEffect(ModEffects.sculk_power.get())) {
                bonus = 0.2f * modifier.getLevel();
            }
            TooltipModifierHook.addPercentBoost(modifier.getModifier(), Boost, bonus, tooltip);
        }
    }
}
