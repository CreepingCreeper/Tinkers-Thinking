package com.creeping_creeper.tinkers_thinking.common.things.item;

import com.creeping_creeper.tinkers_thinking.common.modifer.ModModifiers;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.items.ItemHandlerHelper;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.BowAmmoModifierHook;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.capability.PersistentDataCapability;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableLauncherItem;
import slimeknights.tconstruct.library.tools.nbt.*;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.ability.interaction.BlockingModifier;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook.KEY_DRAWTIME;

public class ModifiableRepeatingCrossbowItem extends ModifiableLauncherItem {
  /** Key containing the stored crossbow ammo */
  public static final ResourceLocation KEY_CROSSBOW_AMMO = TConstruct.getResource("crossbow_ammo");
  private static final String PROJECTILE_KEY = "item.minecraft.crossbow.projectile";
  @Getter
  private final Predicate<ItemStack> supportedHeldProjectiles;
  private final boolean storeDrawingItem;
  public ModifiableRepeatingCrossbowItem(Properties properties, ToolDefinition toolDefinition,Predicate<ItemStack> supportedHeldProjectiles,boolean storeDrawingItem) {
    super(properties, toolDefinition);
    this.supportedHeldProjectiles = supportedHeldProjectiles;
    this.storeDrawingItem = storeDrawingItem;
  }
  public ModifiableRepeatingCrossbowItem(Item.Properties properties, ToolDefinition toolDefinition) {
    this(properties, toolDefinition, ARROW_OR_FIREWORK, false);
  }

  @Override
  public Predicate<ItemStack> getAllSupportedProjectiles() {
    return ARROW_ONLY;
  }

  @Override
  public int getDefaultProjectileRange() {
    return 8;
  }

  @Override
  public UseAnim getUseAnimation(ItemStack stack) {
    // crossbow is superhardcoded to crossbows, so use none and rely on the model
    return BlockingModifier.blockWhileCharging(ToolStack.from(stack), UseAnim.NONE);
  }

  @Override
  public boolean useOnRelease(ItemStack stack) {
    return true;
  }


  /* Arrow launching */

  /** Gets the arrow pitch */
  private static float getRandomShotPitch(float angle, RandomSource pRandom) {
    if (angle == 0) {
      return 1.0f;
    }
    return 1.0F / (pRandom.nextFloat() * 0.5F + 1.8F) + 0.53f + (angle / 10f);
  }

  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack bow = player.getItemInHand(hand);
    ToolStack tool = ToolStack.from(bow);
    if (tool.isBroken()) {
      return InteractionResultHolder.fail(bow);
    } else {
      boolean sinistral = hand == InteractionHand.MAIN_HAND && tool.getModifierLevel(TinkerModifiers.sinistral.getId()) > 0;
      ModDataNBT persistentData = tool.getPersistentData();
      CompoundTag heldAmmo = persistentData.getCompound(KEY_CROSSBOW_AMMO);
      ItemStack ammo;
      if (heldAmmo.isEmpty()) {
        if (sinistral && !player.getOffhandItem().isEmpty() && player.isCrouching()) {
          return InteractionResultHolder.pass(bow);
        } else {
          ammo = BowAmmoModifierHook.getAmmo(tool, bow, player, ARROW_OR_FIREWORK);
          if (ammo.isEmpty() && !tool.getModifiers().has(TinkerTags.Modifiers.CHARGE_EMPTY_BOW_WITH_DRAWTIME)) {
            if (tool.getModifiers().has(TinkerTags.Modifiers.CHARGE_EMPTY_BOW_WITHOUT_DRAWTIME)) {
              player.startUsingItem(hand);
              return InteractionResultHolder.consume(bow);
            } else {
              return InteractionResultHolder.fail(bow);
            }
          } else {
            GeneralInteractionModifierHook.startDrawtime(tool, player, 1.0F);
            if (!ammo.isEmpty()) {
              if (this.storeDrawingItem) {
                persistentData.put(KEY_DRAWBACK_AMMO, ammo.save(new CompoundTag()));
              } else {
                persistentData.putBoolean(KEY_DRAWBACK_AMMO, true);
              }
            }

            player.startUsingItem(hand);
            if (!level.isClientSide) {
              level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_QUICK_CHARGE_1, SoundSource.PLAYERS, 0.75F, 1.0F);
            }

            return InteractionResultHolder.consume(bow);
          }
        }
      } else {
        if (sinistral) {
          ammo = player.getOffhandItem();
          if (!ammo.isEmpty() && !ammo.is(Items.FIREWORK_ROCKET)) {
            return InteractionResultHolder.pass(bow);
          }

          if (ModifierUtil.canPerformAction(tool, ToolActions.SHIELD_BLOCK)) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(bow);
          }
        }

        fireCrossbow(tool, player, hand, heldAmmo);
        return InteractionResultHolder.consume(bow);
      }
    }
  }
  public static void fireCrossbow(IToolStackView tool, Player player, InteractionHand hand, CompoundTag heldAmmo) {
    fireCrossbow(tool, player, player.getAbilities().instabuild, hand, heldAmmo);
  }
  public static void fireCrossbow(IToolStackView tool, LivingEntity living, boolean creative, InteractionHand hand, CompoundTag heldAmmo) {
    Level level = living.level();
    if (!level.isClientSide) {
      int damage = 0;
      float velocity = ConditionalStatModifierHook.getModifiedStat(tool, living, ToolStats.VELOCITY);
      float inaccuracy = ModifierUtil.getInaccuracy(tool, living);
      ItemStack ammo = ItemStack.of(heldAmmo);
      int ammoCount = Math.min(1+2*tool.getModifierLevel(TinkerModifiers.multishot.getId()),ammo.getCount());
      float startAngle = getAngleStart(ammoCount);
      int primaryIndex = ammoCount / 2;

      for(int arrowIndex = 0; arrowIndex < ammoCount; ++arrowIndex) {
        AbstractArrow arrow = null;
        Projectile projectile;
        float speed;
        float angle;
        if (ammo.is(Items.FIREWORK_ROCKET)) {
          projectile = new FireworkRocketEntity(level, ammo, living, living.getX(), living.getEyeY() - 0.15000000596046448, living.getZ(), true);
          speed = 1.5F;
          damage += 3;
        } else {
          Item var18 = ammo.getItem();
          ArrowItem var10000;
          if (var18 instanceof ArrowItem) {
            var10000 = (ArrowItem)var18;
          } else {
            var10000 = (ArrowItem)Items.ARROW;
          }

          ArrowItem arrowItem = var10000;
          arrow = arrowItem.createArrow(level, ammo, living);
          projectile = arrow;
          arrow.setCritArrow(true);
          arrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
          arrow.setShotFromCrossbow(true);
          speed = 3.0F;
          ++damage;
          angle = (float)(arrow.getBaseDamage() - 2.0 + (double) tool.getStats().get(ToolStats.PROJECTILE_DAMAGE));
          arrow.setBaseDamage(ConditionalStatModifierHook.getModifiedStat(tool, living, ToolStats.PROJECTILE_DAMAGE, angle));
          if (creative) {
            arrow.pickup = Pickup.CREATIVE_ONLY;
          }
        }

        Vec3 upVector = living.getUpVector(1.0F);
        angle = startAngle + (float)(10 * arrowIndex);
        Vector3f targetVector = living.getViewVector(1.0F).toVector3f().rotate((new Quaternionf()).setAngleAxis((double)angle * Math.PI / 180.0, upVector.x, upVector.y, upVector.z));
        projectile.shoot(targetVector.x(), targetVector.y(), targetVector.z(), velocity * speed, inaccuracy);
        ModifierNBT modifiers = tool.getModifiers();
        projectile.getCapability(EntityModifierCapability.CAPABILITY).ifPresent((cap) -> {
          cap.setModifiers(modifiers);
        });
        ModDataNBT projectileData = PersistentDataCapability.getOrWarn(projectile);

        for (ModifierEntry entry : modifiers.getModifiers()) {
          entry.getHook(ModifierHooks.PROJECTILE_LAUNCH).onProjectileLaunch(tool, entry, living, ammo, projectile, arrow, projectileData, arrowIndex == primaryIndex);
        }

        level.addFreshEntity(projectile);
        level.playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, getRandomShotPitch(angle, living.getRandom()));
      }
      tool.getPersistentData().remove(KEY_CROSSBOW_AMMO);
      if (ammo.getCount()>ammoCount){
        ammo.shrink(ammoCount);
        CompoundTag ammoNBT = ammo.save(new CompoundTag());
        tool.getPersistentData().put(KEY_CROSSBOW_AMMO, ammoNBT);
      }
      ToolDamageUtil.damageAnimated(tool, damage, living, hand);
      if (living instanceof ServerPlayer serverPlayer) {
        CriteriaTriggers.SHOT_CROSSBOW.trigger(serverPlayer, living.getItemInHand(hand));
        serverPlayer.awardStat(Stats.ITEM_USED.get(tool.getItem()));
      }
    }

  }
  public void releaseUsing(ItemStack bow, Level level, LivingEntity living, int chargeRemaining) {
    ToolStack tool = ToolStack.from(bow);

    // call the stop using modifier hook
    int duration = getUseDuration(bow);
    for (ModifierEntry entry : tool.getModifiers()) {
      entry.getHook(ModifierHooks.TOOL_USING).beforeReleaseUsing(tool, entry, living, duration, chargeRemaining, ModifierEntry.EMPTY);
    }

    // any reason we shouldn't load?
    // specifically: broken, not fully charged, already have ammo
    ModDataNBT persistentData = tool.getPersistentData();
    if (tool.isBroken() || getUseDuration(bow) - chargeRemaining < persistentData.getInt(KEY_DRAWTIME) || persistentData.contains(KEY_CROSSBOW_AMMO, Tag.TAG_COMPOUND)) {
      return;
    }

    // find ammo and store it on the bow
    Player player = living instanceof Player p ? p : null;
    ItemStack ammo = consumeAmmo(tool, bow, living, player, ARROW_OR_FIREWORK);
    if (!ammo.isEmpty()) {
      level.playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
      if (!level.isClientSide) {
        CompoundTag ammoNBT = ammo.save(new CompoundTag());
        persistentData.put(KEY_CROSSBOW_AMMO, ammoNBT);
        // if the crossbow broke during loading, fire immediately
        if (tool.isBroken()) {
          fireCrossbow(tool, living, player != null && player.getAbilities().instabuild, living.getUsedItemHand(), ammoNBT);
        }
      }
    }
  }
  private static ItemStack findMatchingAmmo(ItemStack bow, LivingEntity living, Predicate<ItemStack> predicate) {
    // start with hands, find one that matches but is not the bow
    for (InteractionHand hand : InteractionHand.values()) {
      ItemStack stack = living.getItemInHand(hand);
      if (stack != bow && predicate.test(stack)) {
        return ForgeHooks.getProjectile(living, bow, stack);
      }
    }
    // was not in hand, search the rest of the inventory
    if (living instanceof Player player) {
      Inventory inventory = player.getInventory();
      for (int i = 0; i < inventory.getContainerSize(); i++) {
        ItemStack stack = inventory.getItem(i);
        if (!stack.isEmpty() && predicate.test(stack)) {
          return ForgeHooks.getProjectile(player, bow, stack);
        }
      }
    }
    return ItemStack.EMPTY;
  }
  static ItemStack consumeAmmo(IToolStackView tool, ItemStack bow, LivingEntity living, @Nullable Player player, Predicate<ItemStack> predicate) {
    int projectilesDesired = (3+tool.getModifierLevel(ModModifiers.RepeatingAdvanced.get()))*(1+(2*tool.getModifierLevel(TinkerModifiers.multishot.getId())));
    ResourceLocation SKIP_INVENTORY_AMMO = TConstruct.getResource("skip_inventory_ammo");
    // treat client side as creative, no need to shrink the stacks clientside
    Level level = living.level();
    boolean creative = (player != null && player.getAbilities().instabuild) || level.isClientSide;

    // first search, find what ammo type we want
    boolean skipInventoryAmmo = tool.getVolatileData().getBoolean(SKIP_INVENTORY_AMMO);
    ItemStack standardAmmo = skipInventoryAmmo ? ItemStack.EMPTY : living.getProjectile(bow);
    ItemStack resultStack = ItemStack.EMPTY;
    for (ModifierEntry entry : tool.getModifierList()) {
      BowAmmoModifierHook hook = entry.getHook(ModifierHooks.BOW_AMMO);
      ItemStack ammo = hook.findAmmo(tool, entry, living, standardAmmo, predicate);
      if (!ammo.isEmpty()) {
        // if creative, we are done, just return the ammo with the given size
        if (creative) {
          return ItemHandlerHelper.copyStackWithSize(ammo, projectilesDesired);
        }

        // not creative, split out the desired amount. We may have to do more work if it is too small
        resultStack = ItemHandlerHelper.copyStackWithSize(ammo, Math.min(projectilesDesired, ammo.getCount()));
        hook.shrinkAmmo(tool, entry, living, ammo, resultStack.getCount());
        break;
      }
    }

    // result stack being empty means no modifier found it, so we use standard ammo
    if (resultStack.isEmpty()) {
      // if standard ammo is empty as well, nothing else to do but give up
      if (standardAmmo.isEmpty()) {
        return ItemStack.EMPTY;
      }
      // with standard ammo, in creative we can just return that
      if (creative) {
        return ItemHandlerHelper.copyStackWithSize(standardAmmo, projectilesDesired);
      }
      // make a copy of the result, up to the desired size
      resultStack = standardAmmo.split(projectilesDesired);
      if (standardAmmo.isEmpty() && player != null) {
        player.getInventory().removeItem(standardAmmo);
      }
    }
    // if we made it this far, we found ammo and are not in creative
    // we may be done already, saves making a predicate
    // can also return if on client side, they don't need the full stack
    if (resultStack.getCount() >= projectilesDesired || level.isClientSide) {
      return resultStack;
    }
    // not enough? keep searching until we fill the stack
    ItemStack match = resultStack;
    predicate = stack -> ItemStack.isSameItemSameTags(stack, match);
    hasEnough:
    do {
      // if standard ammo is empty, try finding a matching stack again
      if (!skipInventoryAmmo && standardAmmo.isEmpty()) {
        standardAmmo = findMatchingAmmo(bow, living, predicate);
      }
      // next, try asking modifiers if they have anything new again
      int needed = projectilesDesired - resultStack.getCount();
      for (ModifierEntry entry : tool.getModifierList()) {
        BowAmmoModifierHook hook = entry.getHook(ModifierHooks.BOW_AMMO);
        ItemStack ammo = hook.findAmmo(tool, entry, living, standardAmmo, predicate);
        if (!ammo.isEmpty()) {
          // consume as much of the stack as we need then continue, loop condition will stop if we are now done
          int gained = Math.min(needed, ammo.getCount());
          hook.shrinkAmmo(tool, entry, living, ammo, gained);
          resultStack.grow(gained);
          continue hasEnough;
        }
      }
      // no standard and no modifier found means we give up
      if (standardAmmo.isEmpty()) {
        break;
      }
      // if we have standard ammo, take what we can then loop again
      if (needed > standardAmmo.getCount()) {
        // consume the whole stack
        resultStack.grow(standardAmmo.getCount());
        if (player != null) {
          player.getInventory().removeItem(standardAmmo);
        }
        standardAmmo = ItemStack.EMPTY;
      } else {
        // found what we need, we are done
        standardAmmo.shrink(needed);
        resultStack.grow(needed);
        break;
      }
    } while (resultStack.getCount() < projectilesDesired);
    return resultStack;
  }
  @Override
  public List<Component> getStatInformation(IToolStackView tool, @Nullable Player player, List<Component> tooltips, TooltipKey key, TooltipFlag tooltipFlag) {
    tooltips = super.getStatInformation(tool, player, tooltips, key, tooltipFlag);

    // if we have ammo, render that in the tooltip
    CompoundTag heldAmmo = tool.getPersistentData().getCompound(KEY_CROSSBOW_AMMO);
    if (!heldAmmo.isEmpty()) {
      ItemStack heldStack = ItemStack.of(heldAmmo);
      if (!heldStack.isEmpty()) {
        // basic info: item and count
        MutableComponent component = Component.translatable(PROJECTILE_KEY);
        int count = heldStack.getCount();
        if (count > 1) {
          component.append(" " + count + " ");
        } else {
          component.append(" ");
        }
        tooltips.add(component.append(heldStack.getDisplayName()));
        // copy the stack's tooltip if advanced
        if (tooltipFlag.isAdvanced() && player != null) {
          List<Component> nestedTooltip = new ArrayList<>();
          heldStack.getItem().appendHoverText(heldStack, player.level(), nestedTooltip, tooltipFlag);
          for (Component nested : nestedTooltip) {
            tooltips.add(Component.literal("  ").append(nested).withStyle(ChatFormatting.GRAY));
          }
        }
      }
    }
    return tooltips;
  }
}
