package com.creeping_creeper.tinkers_thinking.common.things.item;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.recipes.ModBrewingRecipe;
import com.creeping_creeper.tinkers_thinking.common.things.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions{
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, TinkersThinking.MODID);
    public static final RegistryObject<Potion> overweight_potion = POTIONS.register("overweight_potion", () -> new Potion(new MobEffectInstance(ModEffects.overweight.get(), 1200, 0)));
    public static final RegistryObject<Potion> overweight_potion_strong = POTIONS.register("overweight_potion_strong", () -> new Potion(new MobEffectInstance(ModEffects.overweight.get(), 400, 1)));
    public static final RegistryObject<Potion> overweight_potion_long = POTIONS.register("overweight_potion_long", () -> new Potion(new MobEffectInstance(ModEffects.overweight.get(), 2400, 0)));
    public static final RegistryObject<Potion> weightless_potion = POTIONS.register("weightless_potion", () -> new Potion(new MobEffectInstance(ModEffects.weightless.get(), 1200, 0)));
    public static final RegistryObject<Potion> weightless_potion_strong = POTIONS.register("weightless_potion_strong", () -> new Potion(new MobEffectInstance(ModEffects.weightless.get(), 400, 1)));
    public static final RegistryObject<Potion> weightless_potion_long = POTIONS.register("weightless_potion_long", () -> new Potion(new MobEffectInstance(ModEffects.weightless.get(), 2400, 0)));
    public static void setup() {
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD),ModPotions.overweight_potion.get(), ModCommonItems.clay_crystal.get());
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.overweight_potion.get()),ModPotions.overweight_potion_strong.get(), Items.GLOWSTONE_DUST);
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.overweight_potion.get()),ModPotions.overweight_potion_long.get(), Items.REDSTONE);
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.overweight_potion.get()),ModPotions.weightless_potion.get(), Items.FERMENTED_SPIDER_EYE);
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.overweight_potion_strong.get()),ModPotions.weightless_potion_strong.get(), Items.FERMENTED_SPIDER_EYE);
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.overweight_potion_long.get()),ModPotions.weightless_potion_long.get(), Items.FERMENTED_SPIDER_EYE);
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.weightless_potion.get()),ModPotions.weightless_potion_strong.get(), Items.GLOWSTONE_DUST);
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.weightless_potion.get()),ModPotions.weightless_potion_long.get(), Items.REDSTONE);
    }
    private static void potionBrewing(ItemStack inputPot, Potion pot, Item item) {
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(inputPot, Ingredient.of(item), PotionUtils.setPotion(new ItemStack(Items.POTION), pot)));
    }
    public static void registers(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
