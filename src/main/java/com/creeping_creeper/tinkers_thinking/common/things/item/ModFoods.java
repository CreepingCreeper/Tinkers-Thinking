package com.creeping_creeper.tinkers_thinking.common.things.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties Beef_Jerky = new FoodProperties.Builder().alwaysEat().fast().nutrition(8).saturationMod(1.0F).meat().build();
    public static final FoodProperties Mutton_Jerky = new FoodProperties.Builder().alwaysEat().fast().nutrition(6).saturationMod(1.0F).meat().build();
    public static final FoodProperties Rabbit_Jerky = new FoodProperties.Builder().alwaysEat().fast().nutrition(5).saturationMod(0.8F).meat().build();
    public static final FoodProperties Fish_Jerky = new FoodProperties.Builder().alwaysEat().fast().nutrition(3).saturationMod(0.8F).build();
    public static final FoodProperties Rotten_Flesh_Jerky = new FoodProperties.Builder().alwaysEat().fast().nutrition(4).saturationMod(0.4F).meat().build();
    public static final FoodProperties Fried_Egg = new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties Earth_Slime_Drop = new FoodProperties.Builder().alwaysEat().fast().nutrition(1).saturationMod(1.0F)
            .effect(() ->new MobEffectInstance(MobEffects.UNLUCK, 100, 0), 0.8F)
            .effect(() ->new MobEffectInstance(MobEffects.LUCK, 700, 1), 1.0F).build();
    public static final FoodProperties Sky_Slime_Drop = new FoodProperties.Builder().alwaysEat().fast().nutrition(3).saturationMod(1.0F)
            .effect(() ->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0), 0.8F)
            .effect(() ->new MobEffectInstance(MobEffects.JUMP, 700, 1), 1.0F).build();
    public static final FoodProperties Ichor_Slime_Drop = new FoodProperties.Builder().alwaysEat().fast().nutrition(6).saturationMod(1.0F)
            .effect(() ->new MobEffectInstance(MobEffects.WITHER, 100, 0), 0.8F)
            .effect(() ->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 700, 1), 1.0F).build();
    public static final FoodProperties Magma_Slime_Drop = new FoodProperties.Builder().alwaysEat().fast().nutrition(3).saturationMod(1.0F)
            .effect(() ->new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0), 0.8F)
            .effect(() ->new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 700, 1), 1.0F).build();
    public static final FoodProperties Ender_Slime_Drop = new FoodProperties.Builder().alwaysEat().fast().nutrition(3).saturationMod(1.0F)
            .effect(() ->new MobEffectInstance(MobEffects.LEVITATION, 100, 0), 0.8F)
            .effect(() ->new MobEffectInstance(MobEffects.SLOW_FALLING, 700, 1), 1.0F).build();
    public static final FoodProperties Black_Chocolate = new FoodProperties.Builder().alwaysEat().fast().nutrition(2).saturationMod(0.4F)
            .effect(() ->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 500, 2), 1.0F).build();
    public static final FoodProperties White_Chocolate = new FoodProperties.Builder().alwaysEat().fast().nutrition(2).saturationMod(0.4F)
            .effect(() ->new MobEffectInstance(MobEffects.DIG_SPEED, 500, 2), 1.0F).build();
}
