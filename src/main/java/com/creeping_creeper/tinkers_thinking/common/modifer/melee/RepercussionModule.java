package com.creeping_creeper.tinkers_thinking.common.modifer.melee;

import com.creeping_creeper.tinkers_thinking.common.library.ModifierUtils;
import com.creeping_creeper.tinkers_thinking.common.register.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.List;
import java.util.Objects;

public record RepercussionModule(LevelingValue even_time) implements ModifierModule, MeleeDamageModifierHook, MeleeHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter, ProjectileHitModifierHook {
    private static boolean repercussion = true;

    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<RepercussionModule> LOADER;

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.MELEE_DAMAGE, ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_DAMAGE, ModifierHooks.MONSTER_MELEE_HIT, ModifierHooks.PROJECTILE_HIT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(RepercussionModule::even_time), RepercussionModule::new);
    }

    public RecordLoadable<RepercussionModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public Integer getPriority() {
        return 230;
    }
    //Before Reverse
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity target = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();
        if (!context.isExtraAttack() && context.isFullyCharged() && target != null) {
            if (ModifierUtils.reverse(tool, modifier) && repercussion){
                ToolAttackContext.Builder builder = ToolAttackContext.attacker(attacker).target(target).hand(context.getHand()).cooldown(1);
                if (context.getHand() == InteractionHand.MAIN_HAND) {
                    builder.applyAttributes();
                    builder.baseKnockback(0.4f);
                } else {
                    builder.toolAttributes(tool);
                }
                ModDataNBT persistentData = Objects.requireNonNull(tool.getPersistentData());
                int x =target.invulnerableTime;
                target.invulnerableTime = 0;
                repercussion = false;
                ToolAttackUtil.performAttack(tool, builder.build());
                repercussion = true;
                target.invulnerableTime = x;
                persistentData.remove(ModifierUtils.reverse_key);
                if (attacker instanceof Player player) player.causeFoodExhaustion(0.1F);
                ToolDamageUtil.damageAnimated(tool, 1, attacker);
            }
        }
        return damage;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (!context.isExtraAttack() && !ModifierUtils.reverse(tool, modifier) && context.getTarget().isAlive()) {
            ModifierUtils.addEffect(context.getAttacker(), ModEffects.disintegration.get(), (int) even_time.compute(modifier), 2);
        }
    }
}
