package com.creeping_creeper.tinkers_thinking.common.recipes;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.recipe.partbuilder.IPartBuilderRecipe;

public class ModRecipes{
   private static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, TinkersThinking.MODID);
   protected static final SynchronizedDeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = SynchronizedDeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TinkersThinking.MODID);
   public static final RegistryObject<RecipeSerializer<DryingRackRecipes>> Drying_Rack =
            RECIPE_SERIALIZERS.register("drying_rack", () -> DryingRackRecipes.Serializer.INSTANCE);
   public static void registers(IEventBus eventBus) {
      RECIPE_SERIALIZERS.register(eventBus);
   }
}
