package com.creeping_creeper.tinkers_thinking.common.recipes;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
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
    // 为了能够通过管理器获得配方，match必须返回true
    // 此方法用于管理容器是否输入有效。
    // 通过代用test检测
    // 检查容器内的物品和配方是否匹配。
    @Override
    public boolean matches(@NotNull SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()){
            return false;
        }
        // 如果recipeItems的第0个和container中的第一个匹配那么返回true
        return recipeItems.get(0).test(pContainer.getItem(0));
    }
    // 获得合成表所需要的item stacks
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
    // 返回了合成表的结果output
    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return false;
    }

    // 这个方法用于判断合成表是否可以在指定的dimensions合成。
    // 获得合成表物品的copy()
    @Override
    public @NotNull ItemStack getResultItem() {
        return output.copy();
    }

    //
    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }
    // 返回Serializer 必须返回
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    // 返回type
    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    // 注册新的合成的type
    public static class Type implements RecipeType<DryingRackRecipes>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        // 标识了合成的类型，和json文件中的type一致
        public static final String ID = "drying_rack";
    }

    // 负责解码JSON并通过网络通信
    // 需要注册
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
        // 从服务器中发送的数据中解码recipe，配方标识符不需要解码。
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
            pBuffer.writeItemStack(pRecipe.getResultItem(),false);
        }
    }
}
