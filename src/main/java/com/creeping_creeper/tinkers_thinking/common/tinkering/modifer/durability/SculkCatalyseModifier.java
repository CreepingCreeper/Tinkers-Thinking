package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.durability;

import com.creeping_creeper.tinkers_thinking.common.things.ModEffects;
import com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.ModModifiers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DurabilityDisplayModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Objects;

import static slimeknights.tconstruct.library.tools.helper.ModifierUtil.getModifierLevel;

public class SculkCatalyseModifier extends NoLevelsModifier implements ToolDamageModifierHook, DurabilityDisplayModifierHook, InventoryTickModifierHook, ModifierRemovalHook {
    private static final ResourceLocation KEY = new ResourceLocation("tinkers_thinking", "sculk_catalyse");
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
        hookBuilder.addHook(this, ModifierHooks.TOOL_DAMAGE,ModifierHooks.DURABILITY_DISPLAY,ModifierHooks.INVENTORY_TICK,ModifierHooks.REMOVE);
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

    @Nullable
    @Override
    public Boolean showDurabilityBar(IToolStackView tool, ModifierEntry modifier) {
        return  tool.getPersistentData().contains(KEY,1) ? true : null;
    }

    @Override
    public int getDurabilityWidth(IToolStackView tool, ModifierEntry modifier) {
        return 0;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, ModifierEntry modifier) {
        return tool.getPersistentData().contains(KEY,1) ? 0x009295 : -1;
    }
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
      if (holder instanceof Player ) {
          if (holder.hasEffect(ModEffects.sculk_power.get()) && !tool.getPersistentData().contains(KEY, 1)) {
              tool.getPersistentData().putBoolean(KEY, true);
          }
          if (!holder.hasEffect(ModEffects.sculk_power.get()) && tool.getPersistentData().contains(KEY, 1)) {
              tool.getPersistentData().remove(KEY);
          }
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
}
