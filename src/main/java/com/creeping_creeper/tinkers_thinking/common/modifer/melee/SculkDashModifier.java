package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.modifer.ModModifiers;
import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.utils.Util;

import java.util.List;
import java.util.Objects;

public class SculkDashModifier extends Modifier implements MeleeHitModifierHook, MeleeDamageModifierHook, ModifierRemovalHook, TooltipModifierHook {
    private static final Component Times = TinkersThinking.makeTranslation("modifier", "sculk_dash.times");
    private final ResourceLocation KEY = new ResourceLocation(TinkersThinking.MODID, "sculk_dash");
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT,ModifierHooks.MELEE_DAMAGE, ModifierHooks.REMOVE,ModifierHooks.TOOLTIP);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        Player attacker = context.getPlayerAttacker();
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        if (attacker == null) {
            return;
        }
        if (attacker.hasEffect(ModEffects.sculk_power.get()) && (context.getLivingTarget()==null||context.getLivingTarget().isDeadOrDying())) {
            persistentData.putInt(this.KEY,persistentData.getInt(KEY)+modifier.getLevel());
            attacker.addEffect(new MobEffectInstance(ModEffects.strength_reset.get(),5,0,true,false));
        }
    }
    @Nullable
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        if (tool.getModifierLevel(this.getId()) == 0) {
            tool.getPersistentData().remove(KEY);
        }
        return null;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        if (context.isFullyCharged()&&!context.isExtraAttack()&&persistentData.contains(KEY,3)&&persistentData.getInt(KEY)>0){
            persistentData.putInt(KEY,persistentData.getInt(KEY)-1);
            damage *= 1.25;
        }
        return damage;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        if (player!=null) {
            int x = persistentData.getInt(KEY);
            tooltip.add(applyStyle(Component.literal(Util.COMMA_FORMAT.format(x) + " ").append(Times)));
        }
    }
}
