package com.creeping_creeper.tinkers_thinking.common.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class ModBrewingRecipe extends BrewingRecipe {

    private final ItemStack inputStack;
    public ModBrewingRecipe(ItemStack inputStack, Ingredient ingredient, ItemStack output) {
        super(Ingredient.of(inputStack), ingredient, output);
        this.inputStack = inputStack;
    }
    @Override
    public boolean isInput(ItemStack stack) {
        return super.isInput(stack) && PotionUtils.getPotion(stack) == PotionUtils.getPotion(inputStack);
    }
}

