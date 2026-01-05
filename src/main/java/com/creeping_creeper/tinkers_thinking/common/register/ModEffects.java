package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.common.things.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class ModEffects extends ModModule {
    public static final RegistryObject<MobEffect> overweight = MOB_EFFECTS.register("overweight",() -> new NoMilkEffect(MobEffectCategory.HARMFUL, 0x8f2e91,true).addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "2307DE5E-7CE8-4030-940E-514C1F160001", 2, AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<MobEffect> weightless = MOB_EFFECTS.register("weightless",() -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0x16b944,true).addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "2307DE5E-7CE8-4030-940E-514C1F160002", -0.5, AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<MobEffect> sculk_power = MOB_EFFECTS.register("sculk_power",() -> new SculkPowerEffect(MobEffectCategory.BENEFICIAL, 0x009295,true));
    public static final RegistryObject<MobEffect> quick_attack = MOB_EFFECTS.register("quick_attack", () -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0x009295,true).addAttributeModifier(Attributes.ATTACK_SPEED,"2307DE5E-7CE8-4030-940E-514C1F160003",0.1, AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<MobEffect> modifier_immune = MOB_EFFECTS.register("modifier_immune",() -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0xff7f27,true));
    public static final RegistryObject<MobEffect> antibrute_cooldown = MOB_EFFECTS.register("antibrute_cooldown",() -> new NoMilkEffect(MobEffectCategory.HARMFUL, 0xff7d86,true));
    public static final RegistryObject<MobEffect> armor = MOB_EFFECTS.register("armor",() -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0xff7d86,true).addAttributeModifier(Attributes.ARMOR,"2307DE5E-7CE8-4030-940E-514C1F160004",1,AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> cataclysm = MOB_EFFECTS.register("cataclysm",() -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0x727272,true));
    public static final RegistryObject<MobEffect> freezing_cold = MOB_EFFECTS.register("freezing_cold",() -> new FreezingColdEffect(MobEffectCategory.HARMFUL, 0x7cf6fc,true));
    public static final RegistryObject<MobEffect> disintegration = MOB_EFFECTS.register("disintegration",() -> new DisintegrationEffect(MobEffectCategory.HARMFUL, 0xA52548,true));
    public static final RegistryObject<MobEffect> disarm = MOB_EFFECTS.register("disarm",() -> new NoMilkEffect(MobEffectCategory.HARMFUL, 0xc1bc4e,true).addAttributeModifier(Attributes.ATTACK_DAMAGE,"2307DE5E-7CE8-4030-940E-514C1F160005",-1,AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<MobEffect> reminiscence = MOB_EFFECTS.register("reminiscence",() -> new ReminiscenceEffect(MobEffectCategory.BENEFICIAL, true));
    public static final RegistryObject<MobEffect> jumpless = MOB_EFFECTS.register("jumpless",() -> new NoMilkEffect(MobEffectCategory.HARMFUL, 0x8f2e91,true).addAttributeModifier(Attributes.JUMP_STRENGTH,"2307DE5E-7CE8-4030-940E-514C1F160006",-1,AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<MobEffect> last_effort = MOB_EFFECTS.register("last_effort",() -> new LastEffortEffect(MobEffectCategory.BENEFICIAL,  0xa2af86,true));
    /**attack cooldown reset */
    public static final RegistryObject<MobEffect> strength_reset = MOB_EFFECTS.register("strength_reset",() -> new StrengthResetEffect(MobEffectCategory.BENEFICIAL, 0xbf9c81,true));
}
