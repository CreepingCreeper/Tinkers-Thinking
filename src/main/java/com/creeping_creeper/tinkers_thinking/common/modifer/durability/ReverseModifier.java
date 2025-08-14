package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DurabilityDisplayModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.PersistentDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.List;
import java.util.Objects;

public class ReverseModifier extends NoLevelsModifier implements ToolDamageModifierHook, DurabilityDisplayModifierHook, ModifierRemovalHook, TooltipModifierHook, MeleeHitModifierHook, BlockBreakModifierHook, ProjectileLaunchModifierHook, ModifyDamageModifierHook, ModifierUtils {
    private static final Component n1 = TinkersThinking.makeTranslation("modifier", "reverse.1");
    private static final Component n2 = TinkersThinking.makeTranslation("modifier", "reverse.2");
    @Override
    public int getPriority() {
        return 230;
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks .TOOL_DAMAGE,ModifierHooks.DURABILITY_DISPLAY,ModifierHooks.TOOLTIP,ModifierHooks.MELEE_HIT,ModifierHooks.BLOCK_BREAK,ModifierHooks.PROJECTILE_LAUNCH,ModifierHooks.MODIFY_DAMAGE);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack()){
            change(tool);
        }
    }
    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        if (context.isEffective()) {
            change(tool);
        }
    }
    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, boolean primary) {
        change(tool);
        ModDataNBT data = PersistentDataCapability.getOrWarn(projectile);
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("reverse",reverse(tool));
        data.put(reverse_key,tag);
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, net.minecraft.world.entity.EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        change(tool);
        return amount;
    }
    private void change(IToolStackView tool){
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        if (reverse(tool)){
            persistentData.putBoolean(reverse_key, true);
        } else {
            persistentData.remove(reverse_key);
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        if (tool.getModifierLevel(this.getId()) == 0) {
            tool.getPersistentData().remove(reverse_key);
        }
        return null;
    }
    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if (reverse(tool)){
            amount *= 2 ;
        }else amount = 0;
        return amount;
    }

    @Nullable
    @Override
    public Boolean showDurabilityBar(IToolStackView tool, ModifierEntry modifier) {
        return true;
    }

    @Override
    public int getDurabilityWidth(IToolStackView tool, ModifierEntry modifier) {
        return 0;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, ModifierEntry modifier) {
        return tool.getPersistentData().getBoolean(reverse_key) ? 0x5555FF : 0xFF5555;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        tooltip.add(applyStyle((MutableComponent) (persistentData.getBoolean(reverse_key)?n2:n1)));
    }
}
