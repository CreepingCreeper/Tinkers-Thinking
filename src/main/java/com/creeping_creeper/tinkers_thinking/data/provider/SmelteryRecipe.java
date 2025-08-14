package com.creeping_creeper.tinkers_thinking.data.provider;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder;
import slimeknights.tconstruct.smeltery.data.Byproduct;

import java.util.function.Consumer;

public class SmelteryRecipe extends RecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper{
    public SmelteryRecipe(PackOutput output) {
        super(output);
    }
    @Override
    public String getModId() {
        return TinkersThinking.MODID;
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addTagRecipes(consumer);
        this.addMaterialRecipes(consumer);
    }
    @Override
    public String getName() {
        return "Tinkers' Thinking Smeltery Recipes";
    }
    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, String name, TagKey<Fluid> fluid) {
        return SmelteryRecipeBuilder.fluid(consumer, location(name), fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }
    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, FluidObject<?> fluid) {
        return molten(consumer, fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }
    private void addMaterialRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "common/materials/";
        metalCrafting(consumer, ModCommonItems.ardite, folder);
        metalCrafting(consumer, ModCommonItems.tinkers_bronze, folder);
        metalCrafting(consumer, ModCommonItems.lightite, folder);
        metalCrafting(consumer, ModCommonItems.chlorophyte, folder);
        metalCrafting(consumer, ModCommonItems.spectre, folder);
        metalCrafting(consumer, ModCommonItems.shroomite, folder);
        metalCrafting(consumer, ModCommonItems.beetron, folder);
        metalCrafting(consumer, ModCommonItems.obsidian_bronze, folder);
        metalCrafting(consumer, ModCommonItems.echo_bronze, folder);
        metalCrafting(consumer, ModCommonItems.electrical_steel, folder);
        metalCrafting(consumer, ModCommonItems.warden_steel, folder);
    }
    private void addTagRecipes(Consumer<FinishedRecipe> consumer) {
        metal(consumer, ModCommonItems.molten_ardite).metal().ore(Byproduct.GOLD);
        metal(consumer, ModCommonItems.molten_tinkers_bronze).metal();
        metal(consumer, ModCommonItems.molten_lightite).metal();
        metal(consumer, ModCommonItems.molten_chlorophyte).metal();
        metal(consumer, ModCommonItems.molten_spectre).metal();
        metal(consumer, ModCommonItems.molten_shroomite).metal();
        metal(consumer, ModCommonItems.molten_beetron).metal();
        metal(consumer, ModCommonItems.molten_obsidian_bronze).metal();
        metal(consumer, ModCommonItems.molten_echo_bronze).metal();
        metal(consumer, ModCommonItems.molten_electrical_steel).metal();
        metal(consumer, ModCommonItems.molten_warden_steel).metal();
    }

}
