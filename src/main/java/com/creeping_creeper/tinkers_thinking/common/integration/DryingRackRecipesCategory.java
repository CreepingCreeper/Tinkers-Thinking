package com.creeping_creeper.tinkers_thinking.common.integration;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.recipes.DryingRackRecipes;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class DryingRackRecipesCategory implements IRecipeCategory<DryingRackRecipes> {
    public static final ResourceLocation UID = new ResourceLocation(TinkersThinking.MODID,
            "drying_rack");
    public static final ResourceLocation TEXTURE = new ResourceLocation(TinkersThinking.MODID,
            "textures/gui/drying_rack_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;
    // 构造方法
    public DryingRackRecipesCategory(IGuiHelper helper){
        this.background  = helper.createDrawable(TEXTURE,0,0,90,42);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(ModCommonItems.drying_rack));
        this.arrow = helper.drawableBuilder(TEXTURE,90,0,22,16).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }
    @Override
    public @NotNull RecipeType<DryingRackRecipes> getRecipeType() {
        return JEIPlugin.DryingRackRecipes_TYPE;
    }
    @Override
    public @NotNull Component getTitle() {
        return TinkersThinking.makeTranslation("jei", "drying_rack");
    }
    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }
    @Override
    public int getWidth() {
        return background.getWidth();
    }
    @Override
    public int getHeight() {
        return background.getHeight();
    }
    @Override
    public void draw(DryingRackRecipes recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics, 0, 0);
        this.arrow.draw(guiGraphics, 34, 13);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DryingRackRecipes recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,9,13).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 65, 13).addItemStack(recipe.getResultItem(null));
    }
}