package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.EdibleEffectHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record EdibleHealModule(LevelingValue rate) implements ModifierModule, EdibleEffectHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<EdibleHealModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.EDIBLE_EFFECT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(EdibleHealModule::rate), EdibleHealModule::new);
    }

    @Override
    public void onToolEaten(IToolStackView tool, ModifierEntry modifier, Player player, EquipmentSlot eatenSlot, int hunger, float saturation, List<ItemStack> representativeItems) {
        ModifierUtils.heal(player, (player.getMaxHealth() - player.getHealth()) * rate.compute(modifier));
    }

    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
}
