package com.creeping_creeper.tinkers_thinking.common.modifer.defense;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingInt;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.shared.TinkerEffects;

import java.util.List;

public record TeleportAdvancedModule(LevelingInt time) implements ModifierModule, GeneralInteractionModifierHook, MeleeHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter {
    private static final ResourceLocation X = ResourceLocation.fromNamespaceAndPath ("tinkersinnovation", "teleport_x");
    private static final ResourceLocation Y = ResourceLocation.fromNamespaceAndPath ("tinkersinnovation", "teleport_y");
    private static final ResourceLocation Z = ResourceLocation.fromNamespaceAndPath ("tinkersinnovation", "teleport_z");
    private static final ResourceLocation WORLD = ResourceLocation.fromNamespaceAndPath ("tinkersinnovation", "teleport_dimension");

    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<TeleportAdvancedModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.GENERAL_INTERACT, ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT);
        LOADER = RecordLoadable.create(LevelingInt.LOADABLE.directField(TeleportAdvancedModule::time), TeleportAdvancedModule::new);
    }

    public RecordLoadable<TeleportAdvancedModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public Integer getPriority() {
        return 5;
    }

    private void applyEffect(LivingEntity living, int level){
        ModifierUtils.addEffect(living, MobEffects.MOVEMENT_SPEED, time.compute(level), 1);
        ModifierUtils.addEffect(living, ModEffects.sculk_power.get(), time.compute(level));
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.RIGHT_CLICK && !tool.isBroken() && player.isCrouching() && !player.hasEffect(TinkerEffects.enderference.get()) && !player.hasEffect(TinkerEffects.enderference.get())) {
            Level world = player.level();
            ModDataNBT data = tool.getPersistentData();
            if (data.contains(X, Tag.TAG_FLOAT) && data.contains(Y, Tag.TAG_FLOAT) && data.contains(Z, Tag.TAG_FLOAT) && data.contains(WORLD, Tag.TAG_STRING)) {
                if (data.getString(WORLD).equals(world.dimension().location().getPath())) {
                    applyEffect(player, modifier.getLevel());
                }
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (!tool.isBroken()) {
            if (target != null && !target.hasEffect(TinkerEffects.enderference.get())) {
                Level world = target.level();
                ModDataNBT data = tool.getPersistentData();
                if (data.contains(X, Tag.TAG_FLOAT) && data.contains(Y, Tag.TAG_FLOAT) && data.contains(Z, Tag.TAG_FLOAT) && data.contains(WORLD, Tag.TAG_STRING)) {
                    if (data.getString(WORLD).equals(world.dimension().location().getPath())) {
                        applyEffect(context.getAttacker(), modifier.getLevel());
                    }
                }
            }
        }
    }
}
