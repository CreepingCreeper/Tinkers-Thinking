package com.creeping_creeper.tinkers_thinking.common.modifer.curio;

import com.xiaoyue.tinkers_ingenuity.content.items.ModifiableCurio;
import com.xiaoyue.tinkers_ingenuity.content.shared.holder.CurioStackView;
import com.xiaoyue.tinkers_ingenuity.content.shared.hooks.specail.TinkersCurioModifierHook;
import com.xiaoyue.tinkers_ingenuity.register.TIHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.capability.TinkerDataKeys;

import java.util.List;
import java.util.Optional;

public record CurioLevelModule(TinkerDataCapability.TinkerDataKey<Integer> key) implements HookProvider, TinkersCurioModifierHook, ModifierModule {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<CurioLevelModule>defaultHooks(TIHooks.TINKERS_CURIO);
    public static final RecordLoadable<CurioLevelModule> LOADER = RecordLoadable.create(TinkerDataKeys.INTEGER_REGISTRY.requiredField("key", CurioLevelModule::key), CurioLevelModule::new);
    @Override
    public RecordLoadable<CurioLevelModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public void onChangeCurio(CurioStackView curio, int level, ItemStack exchanged, ModifiableCurio.ChangeType type) {
        addLevels(curio.context().entity(), key,type == ModifiableCurio.ChangeType.EQUIP ? level : -level);
    }
    public static void addLevels(LivingEntity living, TinkerDataCapability.TinkerDataKey<Integer> key, int amount) {
        Optional<TinkerDataCapability.Holder> dataCap = living.getCapability(TinkerDataCapability.CAPABILITY).resolve();
        dataCap.ifPresent(data -> {
            int totalLevels = data.get(key, 0) + amount;
            if (totalLevels <= 0) {
                data.remove(key);
            } else {
                data.put(key, totalLevels);
            }
        });
    }
    public static int getLevel(LivingEntity living, TinkerDataCapability.TinkerDataKey<Integer> key) {
        return getLevel(living.getCapability(TinkerDataCapability.CAPABILITY), key);
    }
    public static int getLevel(LazyOptional<TinkerDataCapability.Holder> cap, TinkerDataCapability.TinkerDataKey<Integer> key) {
        return cap.resolve().map(data -> data.get(key)).orElse(0);
    }
}

