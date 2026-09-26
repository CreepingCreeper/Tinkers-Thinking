package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record ShadowingModule(LevelingValue amount) implements ModifierModule, TooltipModifierHook, ModifyDamageModifierHook {
    private static final Component Resistance = TinkersThinking.makeTranslation("modifier", "shadowing.resistance");

    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<ShadowingModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.TOOLTIP, ModifierHooks.MODIFY_DAMAGE);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(ShadowingModule::amount), ShadowingModule::new);
    }

    public RecordLoadable<ShadowingModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public Integer getPriority() {
        return 75;
    }
    
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        Level world =context.getEntity().getCommandSenderWorld();
        amount-= (float) (((float) (15-world.getBrightness(LightLayer.SKY, context.getEntity().blockPosition())+world.getSkyDarken())/7.5)*modifier.getLevel());
        return amount>0?amount:0;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        Level world;
        if(player!=null){
            world = player.getCommandSenderWorld();
            float boost = (float) (((float) (15-world.getBrightness(LightLayer.SKY, player.blockPosition())+world.getSkyDarken())/7.5)*modifier.getLevel());
            TooltipModifierHook.addFlatBoost(modifier.getModifier(), Resistance , boost, tooltip);
        }
    }

}
