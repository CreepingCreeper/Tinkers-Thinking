package com.creeping_creeper.tinkers_thinking.data.provider;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.common.register.ModFluids;
import com.creeping_creeper.tinkers_thinking.data.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.shared.TinkerMaterials;
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
        this.addAlloy(consumer);
        this.addMaterialRecipes(consumer);
    }

    @Override
    public String getName() {
        return "TiT Smeltery Recipes";
    }

    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, String name, TagKey<Fluid> fluid) {
        return SmelteryRecipeBuilder.fluid(consumer, location(name), fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }
    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, FluidObject<?> fluid) {
        return molten(consumer, fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }
    private void addMaterialRecipes(Consumer<FinishedRecipe> consumer) {
        String metal = "common/materials/metal/";
        metalCrafting(consumer, ModCommonItems.ardite, metal);
        metalCrafting(consumer, ModCommonItems.tinkers_bronze, metal);
        metalCrafting(consumer, ModCommonItems.lightite, metal);
        metalCrafting(consumer, ModCommonItems.chlorophyte, metal);
        metalCrafting(consumer, ModCommonItems.spectre, metal);
        metalCrafting(consumer, ModCommonItems.shroomite, metal);
        metalCrafting(consumer, ModCommonItems.beetron, metal);
        metalCrafting(consumer, ModCommonItems.obsidian_bronze, metal);
        metalCrafting(consumer, ModCommonItems.echo_bronze, metal);
        metalCrafting(consumer, ModCommonItems.electrical_steel, metal);
        metalCrafting(consumer, ModCommonItems.warden_steel, metal);
        metalCrafting(consumer, ModCommonItems.shimmerslime, metal);
        metalCrafting(consumer, ModCommonItems.adamantium, metal);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModCommonItems.raw_ardite, ModCommonItems.ardite_ore), RecipeCategory.MISC, TinkerMaterials.cobalt.getIngot(), 1.5f, 200)
                .unlockedBy("has_item", has(ModCommonItems.raw_ardite))
                .save(consumer, location(metal + "ardite_ingot_blasting"));
        packingRecipe(consumer, RecipeCategory.MISC, "raw_block", ModCommonItems.raw_ardite_block, "raw", ModCommonItems.raw_ardite, ModTags.Items.raw_ardite, metal);

    }

    private void addAlloy(Consumer<FinishedRecipe> consumer){
        String folder = "smeltery/alloy/";
        AlloyRecipeBuilder.alloy(TinkerFluids.moltenManyullyn, FluidValues.INGOT)
                .addInput(TinkerFluids.moltenCobalt.getTag(), FluidValues.INGOT)
                .addInput(ModFluids.moltenArdite.getTag(), FluidValues.INGOT)
                .save(consumer, prefix(TinkerFluids.moltenManyullyn, folder));
        AlloyRecipeBuilder.alloy(ModFluids.moltenTinkersBronze, FluidValues.INGOT * 2)
                .addInput(TinkerFluids.moltenCopper.getTag(), FluidValues.INGOT * 2)
                .addInput(TinkerFluids.moltenGlass.getTag(), FluidValues.GLASS_BLOCK)
                .save(consumer, prefix(ModFluids.moltenTinkersBronze, folder));
        AlloyRecipeBuilder.alloy(ModFluids.moltenBeetron, FluidValues.INGOT)
                .addInput(TinkerFluids.moltenIron.getTag(), FluidValues.INGOT)
                .addInput(TinkerFluids.beetrootSoup.getTag(), FluidValues.BOWL * 2)
                .save(consumer, prefix(ModFluids.moltenBeetron, folder));
        AlloyRecipeBuilder.alloy(ModFluids.moltenObsidianBronze, FluidValues.INGOT * 2)
                .addInput(TinkerFluids.moltenObsidian.getTag(), FluidValues.GLASS_BLOCK)
                .addInput(TinkerFluids.moltenCopper.getTag(), FluidValues.INGOT * 2)
                .save(consumer, prefix(ModFluids.moltenObsidianBronze, folder));
        AlloyRecipeBuilder.alloy(ModFluids.moltenEchoBronze, FluidValues.INGOT )
                .addInput(ModFluids.moltenEcho.getTag(), FluidValues.GEM)
                .addInput(TinkerFluids.moltenCopper.getTag(), FluidValues.INGOT)
                .save(consumer, prefix(ModFluids.moltenEchoBronze, folder));
        AlloyRecipeBuilder.alloy(ModFluids.moltenElectricalSteel, FluidValues.INGOT )
                .addInput(TinkerFluids.moltenQuartz.getTag(), FluidValues.BRICK)
                .addInput(TinkerFluids.moltenSteel.getTag(), FluidValues.INGOT)
                .save(consumer, prefix(ModFluids.moltenElectricalSteel, folder));
        AlloyRecipeBuilder.alloy(ModFluids.moltenWardenSteel, FluidValues.INGOT * 2)
                .addInput(TinkerFluids.moltenSteel.getTag(), FluidValues.INGOT)
                .addInput(ModFluids.moltenArdite.getTag(), FluidValues.INGOT)
                .addInput(ModFluids.moltenEcho.getTag(), FluidValues.GEM * 2)
                .save(consumer, prefix(ModFluids.moltenWardenSteel, folder));
        Consumer<FinishedRecipe> wrapped;
        wrapped = withCondition(consumer, tagCondition("glowstone"));
        AlloyRecipeBuilder.alloy(ModFluids.moltenShimmerslime, FluidValues.INGOT * 2)
                .addInput(TinkerFluids.enderSlime.getTag(), FluidValues.INGOT)
                .addInput(ModTags.Fluids.glowstone, FluidValues.INGOT)
                .addInput(ModFluids.moltenZith.getTag(), FluidValues.GEM * 2)
                .save(wrapped, prefix(ModFluids.moltenShimmerslime, folder));
        wrapped = withCondition(consumer, new NotCondition(tagCondition("glowstone")));
        AlloyRecipeBuilder.alloy(ModFluids.moltenShimmerslime, FluidValues.INGOT * 2)
                .addInput(TinkerFluids.enderSlime.getTag(), FluidValues.INGOT)
                .addInput(TinkerFluids.blazingBlood.getTag(), FluidValues.INGOT)
                .addInput(ModFluids.moltenZith.getTag(), FluidValues.GEM * 2)
                .save(wrapped, prefix(ModFluids.moltenShimmerslime, folder).withSuffix("_2"));
        AlloyRecipeBuilder.alloy(ModFluids.moltenAdamantium, FluidValues.INGOT * 2)
                .addInput(TinkerFluids.moltenDebris.getTag(), FluidValues.INGOT * 2)
                .addInput(ModFluids.moltenZith.getTag(), FluidValues.INGOT)
                .save(consumer, prefix(ModFluids.moltenAdamantium, folder));
    }

    private void addTagRecipes(Consumer<FinishedRecipe> consumer) {
        metal(consumer, ModFluids.moltenArdite).metal().ore(Byproduct.GOLD).rawOre(Byproduct.GOLD);
        metal(consumer, ModFluids.moltenTinkersBronze).metal();
        metal(consumer, ModFluids.moltenLightite).metal();
        metal(consumer, ModFluids.moltenChlorophyte).metal();
        metal(consumer, ModFluids.moltenSpectre).metal();
        metal(consumer, ModFluids.moltenShroomite).metal();
        metal(consumer, ModFluids.moltenBeetron).metal();
        metal(consumer, ModFluids.moltenObsidianBronze).metal();
        metal(consumer, ModFluids.moltenEchoBronze).metal();
        metal(consumer, ModFluids.moltenElectricalSteel).metal();
        metal(consumer, ModFluids.moltenWardenSteel).metal();
        metal(consumer, ModFluids.moltenShimmerslime).metal();
        metal(consumer, ModFluids.moltenAdamantium).metal();
    }

}
