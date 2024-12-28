package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.List;
import java.util.Objects;

public class Test2Modifier extends Modifier implements ModifierRemovalHook,TooltipModifierHook, MeleeHitModifierHook {
    private static final Component Boost = TConstruct.makeTranslation("modifier", "test.boost");
    private final ResourceLocation KEY = new ResourceLocation("tinkers_thinking", "test_a");
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOLTIP,ModifierHooks.MELEE_HIT);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        boolean a;
        a = persistentData.getBoolean(this.KEY);
        if (!context.isExtraAttack()&&context.isFullyCharged()&!a){
            persistentData.remove(this.KEY);
            persistentData.putBoolean(this.KEY, true);
        }
        if (!context.isExtraAttack()&&context.isFullyCharged()&a){
            context.getAttacker().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,600,1));
            persistentData.remove(this.KEY);
            persistentData.putBoolean(this.KEY, false);
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        if (tool.getModifierLevel(this.getId()) == 0) {
            tool.getPersistentData().remove(this.KEY);
        }
        return null;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        float x = 0;
        if (player != null && key == TooltipKey.SHIFT) {
            x = (float) player.getPosition(x).x;
        }
        TooltipModifierHook.addFlatBoost(this,Boost,x,tooltip);
    }
}
