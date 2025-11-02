package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileShootModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public record NocturnalModule(LevelingValue amount) implements ModifierModule, ConditionalStatModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<NocturnalModule> LOADER;

    public @NotNull RecordLoadable<NocturnalModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.VELOCITY) {
            return baseValue*( 1 + (living.level().isDay() ? -amount.eachLevel() : amount.eachLevel() * modifier.getLevel()));
        }
        return baseValue;
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.CONDITIONAL_STAT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(NocturnalModule::amount), NocturnalModule::new);
    }
}
