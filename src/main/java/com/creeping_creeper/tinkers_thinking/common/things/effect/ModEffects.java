package com.creeping_creeper.tinkers_thinking.common.things.effect;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.effect.TestEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.registration.EnumDeferredRegister;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class ModEffects {
    protected static final EnumDeferredRegister<MobEffect> MOB_EFFECTS = new EnumDeferredRegister<>(Registries.MOB_EFFECT, TinkersThinking.MODID);
    public static final RegistryObject<MobEffect> overweight = MOB_EFFECTS.register("overweight",() -> new NoMilkEffect(MobEffectCategory.HARMFUL, 0x8f2e91,true).addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "2307DE5E-7CE8-4030-940E-514C1F160001", 2, AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<MobEffect> weightless = MOB_EFFECTS.register("weightless",() -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0x16b944,true).addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "2307DE5E-7CE8-4030-940E-514C1F160002", -0.5, AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<MobEffect> sculk_power = MOB_EFFECTS.register("sculk_power",() -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0x009295,true));
    public static final RegistryObject<MobEffect> lightly_attack = MOB_EFFECTS.register("lightly_attack", () -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0x009295,true).addAttributeModifier(Attributes.ATTACK_SPEED,"2307DE5E-7CE8-4030-940E-514C1F160003",0.30,AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<MobEffect> modifier_immune = MOB_EFFECTS.register("modifier_immune",() -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0xff7f27,true));
    public static final RegistryObject<TestEffect> test = MOB_EFFECTS.register("test",() -> new TestEffect(MobEffectCategory.BENEFICIAL, 0xff7f27,true));
    public static void registers(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
