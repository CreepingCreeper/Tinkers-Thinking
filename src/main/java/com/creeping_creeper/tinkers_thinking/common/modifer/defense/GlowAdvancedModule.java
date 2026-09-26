package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.tools.modules.armor.CounterModule;

import java.util.List;

public record GlowAdvancedModule(LevelingValue rate) implements ModifierModule, OnAttackedModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<GlowAdvancedModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.ON_ATTACKED);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(GlowAdvancedModule::rate), GlowAdvancedModule::new);
    }

    public RecordLoadable<GlowAdvancedModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        Level level = living.level();
        level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, living.getRandomX(0.6), living.getRandomY() + 1, living.getRandomZ(0.6), 0.0F, 0.0F, 0.0F);
        if (living instanceof Player player && level.getBlockState(player.blockPosition()).getBlock() == TinkerCommons.glowBlock.get()) {
            player.causeFoodExhaustion(0.1F);
            ModifierUtils.heal(player, amount * (CounterModule.isBlocking(tool, slotType, player) ? rate.compute(modifier) * 2 : rate.compute(modifier)));
            level.setBlockAndUpdate(player.getOnPos().above(), Blocks.AIR.defaultBlockState());
            ToolDamageUtil.damageAnimated(tool, modifier.getLevel(), player);
        }
    }
}
