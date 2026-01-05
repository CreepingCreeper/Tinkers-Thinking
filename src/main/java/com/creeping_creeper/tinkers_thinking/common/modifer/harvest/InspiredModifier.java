package com.creeping_creeper.tinkers_thinking.common.modifer.harvest;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class InspiredModifier extends Modifier implements BlockBreakModifierHook, ModifierUtils {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.BLOCK_BREAK);
    }
    @Override
    public void afterBlockBreak(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolHarvestContext context) {
        if (context.canHarvest() && context.isEffective() && !context.isAOE() && RANDOM.nextFloat() <  0.25) {
            LivingEntity living = context.getLiving();
            addEffect(living,MobEffects.DIG_SPEED,20,1);
            ToolDamageUtil.directDamage(tool, modifier.getLevel() * 5, living, living.getUseItem());
        }
    }
}
