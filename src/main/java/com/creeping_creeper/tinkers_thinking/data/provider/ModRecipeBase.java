package com.creeping_creeper.tinkers_thinking.data.provider;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.recipe.data.IRecipeHelper;

import java.util.function.Consumer;

public abstract class ModRecipeBase extends net.minecraft.data.recipes.RecipeProvider implements IConditionBuilder, IRecipeHelper {
    public ModRecipeBase(PackOutput output) {
        super(output);
    }
    @Override
    protected abstract void buildRecipes(Consumer<FinishedRecipe> consumer);

    @Override
    public abstract String getName();

    @Override
    public String getModId() {
        return TinkersThinking.MODID;
    }
}
