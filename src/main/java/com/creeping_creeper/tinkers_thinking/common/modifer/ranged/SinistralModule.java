package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import com.creeping_creeper.tinkers_thinking.common.things.item.ModifiableRepeatingCrossbowItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.EntityInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public enum SinistralModule implements ModifierModule, GeneralInteractionModifierHook, EntityInteractionModifierHook {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.GENERAL_INTERACT, ModifierHooks.ENTITY_INTERACT);
    public static final RecordLoadable<SinistralModule> LOADER = new SingletonLoader<>(INSTANCE);


    public RecordLoadable<SinistralModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public InteractionResult afterEntityUse(IToolStackView tool, ModifierEntry modifier, Player player, LivingEntity target, InteractionHand hand, InteractionSource source) {
        return onToolUse(tool, modifier, player, hand, source);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.LEFT_CLICK && hand == InteractionHand.MAIN_HAND && !tool.isBroken()) {
            CompoundTag heldAmmo = tool.getPersistentData().getCompound(ModifiableRepeatingCrossbowItem.KEY_CROSSBOW_AMMO);
            if (!heldAmmo.isEmpty()) {
                ModifiableRepeatingCrossbowItem.fireCrossbow(tool, player, hand, heldAmmo);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }
}
