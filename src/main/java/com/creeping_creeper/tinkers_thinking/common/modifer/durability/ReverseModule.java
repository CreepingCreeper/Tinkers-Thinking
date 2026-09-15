package com.creeping_creeper.tinkers_thinking.common.modifer.durability;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.data.ModModifierIds;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DisplayNameModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DurabilityDisplayModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.LauncherHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.capacity.CapacitySourceModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.List;

public enum ReverseModule implements ModifierModule, ToolDamageModifierHook, DurabilityDisplayModifierHook, CapacitySourceModule, MeleeHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter, BlockBreakModifierHook, LauncherHitModifierHook, ProjectileLaunchModifierHook, ModifyDamageModifierHook, DisplayNameModifierHook {
    INSTANCE;
    private static final Component Odd = TinkersThinking.makeTranslation("modifier", "reverse.1");
    private static final Component Even = TinkersThinking.makeTranslation("modifier", "reverse.2");
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.TOOL_DAMAGE, ModifierHooks.DURABILITY_DISPLAY, ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT, ModifierHooks.BLOCK_BREAK, ModifierHooks.LAUNCHER_HIT, ModifierHooks.PROJECTILE_LAUNCH, ModifierHooks.MODIFY_DAMAGE, ModifierHooks.DISPLAY_NAME);
    public static final RecordLoadable<ReverseModule> LOADER = new SingletonLoader<>(INSTANCE);

    public RecordLoadable<ReverseModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public Integer getPriority() {
        return 225;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack()){
            ModifierUtils.change(tool, modifier);
        }
    }

    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        if (context.isEffective()) {
            ModifierUtils.change(tool, modifier);
        }
    }

    @Override
    public void onLauncherHitEntity(IToolStackView tool, ModifierEntry modifier, Projectile projectile, LivingEntity attacker, Entity target, @Nullable LivingEntity livingTarget, float damageDealt) {
        ModifierUtils.change(tool, modifier);
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, boolean primary) {
        if (primary) {
            ModifierUtils.change(tool, modifier);
            persistentData.putBoolean(ModifierUtils.reverse_key, ModifierUtils.reverse(tool, modifier));

        }
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, net.minecraft.world.entity.EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
       if(tool.hasTag((TinkerTags.Items.ARMOR))){
           ModifierUtils.change(tool, modifier);
       }
        return amount;
    }

    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if (ModifierUtils.reverse(tool, modifier)){
            amount *= 2 ;
        }else amount = 0;
        return amount;
    }

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
        return ModifierUtils.reverse(tool, modifier) ? 0xFF5555 : 0x5555FF;
    }

    @Override
    public ModifierId owner() {
        return ModModifierIds.Reverse;
    }

    @Override
    public Component getDisplayName(IToolStackView tool, ModifierEntry modifier, Component name, @Nullable RegistryAccess access) {
        Component n = ModifierUtils.reverse(tool, modifier) ? Even : Odd;
        return modifier.getDisplayName().copy().append(n);
    }
}
