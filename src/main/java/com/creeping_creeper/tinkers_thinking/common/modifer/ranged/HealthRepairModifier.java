package com.creeping_creeper.tinkers_thinking.common.modifer.ranged;

import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class HealthRepairModifier extends Modifier implements ToolDamageModifierHook {
    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if (tool.getDamage()<=amount&&holder!=null){
            holder.hurt(TinkerDamageTypes.source(holder.level().registryAccess(), TinkerDamageTypes.ENTANGLED), amount);
            ToolDamageUtil.repair(tool, amount);
            amount = 0;
        }
        return amount;
    }
}
