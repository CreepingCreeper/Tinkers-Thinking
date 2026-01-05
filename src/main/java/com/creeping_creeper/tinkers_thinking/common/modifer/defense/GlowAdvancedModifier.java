package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap.Builder;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.tools.modules.armor.CounterModule;

public class GlowAdvancedModifier extends NoLevelsModifier implements OnAttackedModifierHook, ModifierUtils {
    @Override
    protected void registerHooks(Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED);
    }
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity living = context.getEntity();
        Level level = living.level();
        level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, living.getRandomX(0.6), living.getRandomY() + 1, living.getRandomZ(0.6), 0.0F, 0.0F, 0.0F);
        if (living instanceof Player player && level.getBlockState(player.blockPosition()).getBlock() == TinkerCommons.glow.get()) {
            player.causeFoodExhaustion(0.1F);
            heal(player, amount * (CounterModule.isBlocking(tool, slotType, player) ? 0.2f : 0.1f));
            level.setBlockAndUpdate(player.getOnPos().above(), Blocks.AIR.defaultBlockState());
            ToolDamageUtil.damageAnimated(tool, modifier.getLevel(), player);
        }
    }
}
