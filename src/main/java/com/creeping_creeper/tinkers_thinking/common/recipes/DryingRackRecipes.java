package com.creeping_creeper.tinkers_thinking.common.recipes;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DryingRackRecipes implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private static String category = "misc";
    public DryingRackRecipes(ResourceLocation id, ItemStack output,
                             NonNullList<Ingredient> recipeItems, String category){
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        DryingRackRecipes.category = category;
    }
    @Override
    public boolean matches(@NotNull SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()){
            return false;
        }
        return recipeItems.get(0).test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_, RegistryAccess p_267165_) {
        return output;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public @NotNull String getGroup() {
        return category ;
    }
    public boolean isSpecial() {
        return true;
    }
    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return false;
    }
    @Override
    public ItemStack getResultItem(@Nullable RegistryAccess p_267052_) {
        return output.copy();
    }
    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    // 返回type
    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<DryingRackRecipes>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        // 标识了合成的类型，和json文件中的type一致
        public static final String ID = "drying_rack";
    }
    public static class Serializer implements RecipeSerializer<DryingRackRecipes> {
        public static final Serializer INSTANCE = new Serializer();
        public static final  ResourceLocation ID =
                new ResourceLocation(TinkersThinking.MODID,"drying_rack");
        // 将JSON解码为recipe子类型
        @Override
        public @NotNull DryingRackRecipes fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe,"output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe,"ingredient");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1,Ingredient.EMPTY);
            String category ="misc";
            for(int i =0;i<inputs.size();i++){
                inputs.set(i,Ingredient.fromJson(ingredients.get(i)));
            }
            return new DryingRackRecipes(pRecipeId,output,inputs, category);
        }
        @Override
        public @Nullable DryingRackRecipes fromNetwork(@NotNull ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(),Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromNetwork(pBuffer));
            ItemStack output = pBuffer.readItem();
            String category ="misc";
            return new DryingRackRecipes(pRecipeId,output,inputs,category);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, DryingRackRecipes pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());
            for (Ingredient ing : pRecipe.getIngredients()){
                ing.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null),false);
        }
    }
}
