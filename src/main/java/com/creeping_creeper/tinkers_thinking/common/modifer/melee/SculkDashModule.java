package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.utils.Util;

import java.util.List;
import java.util.Objects;

public record SculkDashModule(float rate) implements ModifierModule, MeleeHitModifierHook, MeleeDamageModifierHook, MonsterMeleeHitModifierHook.RedirectAfter, ModifierRemovalHook, TooltipModifierHook {
    private static final Component Times = TinkersThinking.makeTranslation("modifier", "sculk_dash.times");
    private static final ResourceLocation KEY = TinkersThinking.getResource("sculk_dash");

    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<SculkDashModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_HIT, ModifierHooks.MELEE_DAMAGE, ModifierHooks.MONSTER_MELEE_HIT, ModifierHooks.MONSTER_MELEE_DAMAGE, ModifierHooks.REMOVE, ModifierHooks.TOOLTIP);
        LOADER = RecordLoadable.create(FloatLoadable.FROM_ZERO.requiredField("rate", SculkDashModule::rate), SculkDashModule::new);
    }

    public RecordLoadable<SculkDashModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        Player attacker = context.getPlayerAttacker();
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        if (attacker == null) {
            return;
        }
        if (attacker.hasEffect(ModEffects.sculk_power.get()) && (context.getLivingTarget()==null||context.getLivingTarget().isDeadOrDying())) {
            persistentData.putInt(KEY,persistentData.getInt(KEY) + modifier.getLevel());
            attacker.addEffect(new MobEffectInstance(ModEffects.strength_reset.get(),5,0,true,false));
        }
    }
    @Nullable
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        if (tool.getModifierLevel(modifier.getId()) == 0) {
            tool.getPersistentData().remove(KEY);
        }
        return null;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        if (context.isFullyCharged() && !context.isExtraAttack()&&persistentData.contains(KEY,3) && persistentData.getInt(KEY) > 0){
            persistentData.putInt(KEY,persistentData.getInt(KEY) - 1);
            damage *= 1 + rate;
        }
        return damage;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
        if (player!=null) {
            int x = persistentData.getInt(KEY);
            tooltip.add(Component.literal(Util.COMMA_FORMAT.format(x) + " ").append(Times));
        }
    }
}
