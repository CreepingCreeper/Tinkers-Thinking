package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.shared.TinkerEffects;

import java.util.List;

public record RetransitModule(LevelingValue amount) implements ModifierModule, ModifyDamageModifierHook, ModifierUtils {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<RetransitModule> LOADER;

    public @NotNull RecordLoadable<RetransitModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, net.minecraft.world.entity.EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        int x = (int)amount().eachLevel();
        if (reverse(tool)&&!living.hasEffect(TinkerEffects.returning.get())) {
            living.addEffect(new MobEffectInstance(TinkerEffects.returning.get(),x-modifier.getLevel()*20));
        }
        if (!reverse(tool)&&!living.hasEffect(ModEffects.reminiscence.get())) {
            living.addEffect(new MobEffectInstance(ModEffects.reminiscence.get(),x-modifier.getLevel()*20));
        }
        return amount;
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MODIFY_DAMAGE);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(RetransitModule::amount), RetransitModule::new);
    }
}
