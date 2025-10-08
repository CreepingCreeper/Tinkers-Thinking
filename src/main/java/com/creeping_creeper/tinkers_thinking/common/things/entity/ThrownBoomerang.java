package com.creeping_creeper.tinkers_thinking.common.things.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.entity.ThrownTool;
import slimeknights.tconstruct.tools.entity.ToolProjectile;

import javax.annotation.Nullable;

public class ThrownBoomerang extends ThrownTool implements ToolProjectile {
    @Nullable
    private IToolStackView tool = null;
    private float charge = 1;
    private float multiplier = 1;
    private int max_impaling = 0;
    private int impaling = 0;
    // 飞行时间计时器（单位：游戏刻，1秒=20刻，10秒=200刻）
    private int flightTick = 0;
    // 存储初始飞行速度（用于反向返回）
    private boolean back = false;
    // NBT存储键新增
    // NBT存储键
    private static final String TICK = "tick";
    private static final String BACK = "back";
    private static final String MAX_IMPALING = "max_impaling";
    private static final String IMPALING = "impaling";
    public ThrownBoomerang(EntityType<? extends ThrownTool> type, Level level) {
        super(type, level);
    }

    public ThrownBoomerang(Level level, LivingEntity shooter, ItemStack stack, float charge, float multiplier, float waterInertia, int max_impaling) {
        super(level, shooter, stack, charge, multiplier, waterInertia);
        this.charge = charge;
        this.multiplier = multiplier;
        this.max_impaling = max_impaling;
    }

    @Override
    public void tick() {
        Vec3 vec = this.getDeltaMovement();
        if (!this.back) {
            if (this.flightTick < 60) {
                this.flightTick++;
            }
            // 满足反向条件：飞行满3秒
            if (this.flightTick >= 60) {
                this.setDeltaMovement(new Vec3(-vec.x, vec.y, -vec.z));
                this.back = true;
                this.impaling=0;
            }
        }
        super.tick();
    }
    @Override
    protected void onHitEntity(EntityHitResult result) {
        // need a living entity to run our attack hooks, just do nothing if we lack an owner
        if (!tridentItem.isEmpty() && this.getOwner() instanceof LivingEntity owner) {
            Entity target = result.getEntity();
            IToolStackView tool = getTool();
            if (ToolAttackUtil.canPerformAttack(tool) && ToolAttackUtil.isAttackable(owner, target)) {
                this.impaling++;
                // hack: swap the offhand for the tool so any relevant modifier hooks (notably looting) see the right thing
                // does not actually matter which slot we use, just need the tool there to ensure hooks are properly run
                ItemStack offhand = owner.getOffhandItem();
                owner.setItemInHand(InteractionHand.OFF_HAND, tridentItem);
                // TODO: consider whether redundant sound is fine
                if (ToolAttackUtil.performAttack(tool, ToolAttackContext.attacker(owner).target(target).hand(InteractionHand.OFF_HAND).baseDamage(tool.getStats().get(ToolStats.ATTACK_DAMAGE) * multiplier).cooldown(charge).projectile(this).build())) {
                    if (target.getType() == EntityType.ENDERMAN && tool.getModifiers().getLevel(TinkerModifiers.enderference.getId()) == 0) {
                        // restore held item
                        owner.setItemInHand(InteractionHand.OFF_HAND, offhand);
                        return;
                    }
                    if (target instanceof LivingEntity living) {
                        this.doPostHurtEffects(living);
                    }
                }

                // restore held item
                owner.setItemInHand(InteractionHand.OFF_HAND, offhand);
            }
            // play sound
            if (!level().isClientSide && tool.getModifiers().getLevel(ModifierIds.channeling) == 0) {
                this.playSound(tool.isBroken() ? SoundEvents.ITEM_BREAK : SoundEvents.PLAYER_ATTACK_SWEEP, 1.0f, 1.0f);
            }
            if (this.impaling>=this.max_impaling){
                if (!this.back){
                    this.flightTick=60;
                }else this.dealtDamage = true;
            }
        }
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.dealtDamage = true;
        super.onHitBlock(result);
    }
    private IToolStackView getTool() {
        if (tool == null) {
            tool = ToolStack.from(tridentItem);
        }
        return tool;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        // 保存自定义变量到NBT
        tag.putInt(TICK, this.flightTick);
        tag.putBoolean(BACK,this.back);
        tag.putInt(BACK,this.max_impaling);
        tag.putInt(BACK,this.impaling);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        // 从NBT读取自定义变量
        this.flightTick = tag.getInt(TICK);
        this.back=tag.getBoolean(BACK);
        this.max_impaling=tag.getInt(MAX_IMPALING);
        this.impaling=tag.getInt(IMPALING);
    }
}
