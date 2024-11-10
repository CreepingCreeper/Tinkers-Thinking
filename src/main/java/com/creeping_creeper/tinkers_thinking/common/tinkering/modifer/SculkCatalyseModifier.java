package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer;

import com.creeping_creeper.tinkers_thinking.common.things.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Objects;

import static slimeknights.tconstruct.library.tools.helper.ModifierUtil.getModifierLevel;

public class SculkCatalyseModifier extends NoLevelsModifier implements ToolDamageModifierHook  {
    @SubscribeEvent
    public void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
        var orb = event.getOrb().getValue();
        var player = event.getEntity();
        var id = ModModifiers.SculkCatalyse.getId();
        boolean sculk = getModifierLevel(player.getItemInHand(InteractionHand.MAIN_HAND), id) +
                getModifierLevel(player.getItemInHand(InteractionHand.OFF_HAND), id) +
                getModifierLevel(player.getItemBySlot(EquipmentSlot.HEAD), id)+
                getModifierLevel(player.getItemBySlot(EquipmentSlot.CHEST), id)+
                getModifierLevel(player.getItemBySlot(EquipmentSlot.LEGS), id)+
                getModifierLevel(player.getItemBySlot(EquipmentSlot.FEET), id)
                > 0;
            if (sculk) {
                int time = orb * 60;
                if (player.hasEffect(ModEffects.sculk_power.get())) {
                   time = time + Objects.requireNonNull(player.getEffect(ModEffects.sculk_power.get())).getDuration() ;
                }
                player.addEffect(new MobEffectInstance(ModEffects.sculk_power.get(), time));
                player.giveExperiencePoints(-orb);
            }
    }
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_DAMAGE);
    }
    @Override
    public int getPriority() {
        return 200;
    }
    @Override
    public int onDamageTool(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if (holder!= null&&holder.hasEffect(ModEffects.sculk_power.get())){
            amount = 0;
        }
        return amount;
    }
}
