package com.creeping_creeper.tinkers_thinking.common.modifer.misc;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;
import java.util.UUID;

public record HurriedModule(LevelingValue amount) implements ModifierModule, EquipmentChangeModifierHook , TooltipModifierHook, InventoryTickModifierHook {
    private static final UUID ATTRIBUTE_BONUS = UUID.fromString("2307DE5E-7CE8-4030-940E-514C1F170002");
    private static final Component Boost = TinkersThinking.makeTranslation("modifier", "hurried.boost");
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<HurriedModule> LOADER;

    public @NotNull RecordLoadable<HurriedModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public void onUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        LivingEntity living = context.getEntity();
        IToolStackView newTool = context.getReplacementTool();
        AttributeInstance attribute = living.getAttribute(Attributes.MOVEMENT_SPEED);
            if (attribute!=null){
                if (newTool == null || newTool.isBroken() || newTool.getModifier(modifier.getModifier()).getLevel() != modifier.getLevel()) {
                            attribute.removeModifier(ATTRIBUTE_BONUS);
                }
            }
    }
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        AttributeInstance attribute = holder.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attribute!=null&&attribute.getModifier(ATTRIBUTE_BONUS) == null){
            attribute.addTransientModifier(new AttributeModifier(ATTRIBUTE_BONUS, "tinkers_thinking.modifier.hurried",getBonus(holder,modifier),
                    AttributeModifier.Operation.ADDITION));
        }
    }
    private boolean isEmpty(LivingEntity living, int stack){
        return living.getSlot(stack).get().isEmpty();
    }
    private float getBonus(LivingEntity living,ModifierEntry modifier){
        int x = 0;
        for (int i=0;i<9;i++){
            if (isEmpty(living,i)) {
                x++;
            }
        }
        return amount.eachLevel() *x*modifier.getLevel();
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        float x = 0;
        if (player != null && key == TooltipKey.SHIFT) {
           x = 10*getBonus(player,modifier);
        }
        if (x > 0) {
            TooltipModifierHook.addPercentBoost(modifier.getModifier(), Boost, x, tooltip);
        }
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.EQUIPMENT_CHANGE, ModifierHooks.TOOLTIP, ModifierHooks.INVENTORY_TICK);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(HurriedModule::amount), HurriedModule::new);
    }
}
