package com.creeping_creeper.tinkers_thinking.common.recipes;

import com.creeping_creeper.tinkers_thinking.common.things.ModModule;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes extends ModModule {
    public static final RegistryObject<RecipeSerializer<DryingRackRecipes>> Drying_Rack =
            RECIPE_SERIALIZERS.register("drying_rack", () -> DryingRackRecipes.Serializer.INSTANCE);

}
