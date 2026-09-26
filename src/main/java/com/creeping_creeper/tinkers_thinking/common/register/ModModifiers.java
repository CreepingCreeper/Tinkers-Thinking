package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.library.ModPredicate;
import com.creeping_creeper.tinkers_thinking.common.library.variable.SkyLightVariable;
import com.creeping_creeper.tinkers_thinking.common.modifer.OverbearModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.curio.CurioLevelModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.curio.SculkHealModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.defense.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.durability.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.harvest.HungrinessModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.melee.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.HurriedModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.SlingSprintingModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.ranged.*;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.creeping_creeper.tinkers_thinking.TinkersThinking.getResource;

@SuppressWarnings("removal")
public class ModModifiers{
    public static void init(){
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersThinking.MODID);
    public static final StaticModifier<ConcealingModifier> Concealing = MODIFIERS.register("concealing", ConcealingModifier::new);
    public static final StaticModifier<ShadowingModifier> Shadowing = MODIFIERS.register("shadowing", ShadowingModifier::new);
    public static final StaticModifier<SculkProtectionModifier> SculkProtection = MODIFIERS.register("sculk_protection", SculkProtectionModifier::new);
    public static final StaticModifier<SpikyModifier> Spiky = MODIFIERS.register("spiky", SpikyModifier::new);
    public static final StaticModifier<SculkBreedModifier> SculkBreed = MODIFIERS.register("sculk_breed", SculkBreedModifier::new);
    public static final StaticModifier<CrimsonModifier> Crimson = MODIFIERS.register("crimson", CrimsonModifier::new);
    public static final StaticModifier<MagicTransformModifier> MagicTransform = MODIFIERS.register("magic_transform", MagicTransformModifier::new);
    public static final StaticModifier<OverbearModifier> Overbear = MODIFIERS.register("overbear", OverbearModifier::new);
    public static final StaticModifier<SculkSiphonModifier> SculkSiphon = MODIFIERS.register("sculk_siphon", SculkSiphonModifier::new);
    public static final StaticModifier<CounterAttackModifier> CounterAttack = MODIFIERS.register("counter_attack", CounterAttackModifier::new);
    public static final StaticModifier<GlowAdvancedModifier> GlowAdvanced = MODIFIERS.register("glow_advanced", GlowAdvancedModifier::new);
    public static final StaticModifier<TeleportAdvancedModifier> TeleportAdvanced = MODIFIERS.register("teleport_advanced", TeleportAdvancedModifier::new);

    @SubscribeEvent
    void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.RECIPE_SERIALIZER) {
            // predicate

            LivingEntityPredicate.LOADER.register(getResource("is_day"), ModPredicate.Entity.IS_DAY.getLoader());
            LivingEntityPredicate.LOADER.register(getResource("is_riding"), ModPredicate.Entity.IS_RIDING.getLoader());
            LivingEntityPredicate.LOADER.register(getResource("is_hotbar_empty"), ModPredicate.Entity.IS_HOTBAR_EMPTY.getLoader());

            // variable
            ToolVariable.register(getResource("sky_light"), SkyLightVariable.LOADER);

            // module-melee
            ModifierModule.LOADER.register(getResource("battle_advanced"), BattleAdvancedModule.LOADER);
            ModifierModule.LOADER.register(getResource("burning_out"), BurningOutModule.LOADER);
            ModifierModule.LOADER.register(getResource("falling_attack"), FallingAttackModule.LOADER);
            ModifierModule.LOADER.register(getResource("prickly"), PricklyModule.LOADER);
            ModifierModule.LOADER.register(getResource("overdisintegrate"), OverdisintegrateModule.LOADER);
            ModifierModule.LOADER.register(getResource("overfreeze"), OverfreezeModule.LOADER);
            ModifierModule.LOADER.register(getResource("recalamity"), RecalamityModule.LOADER);
            ModifierModule.LOADER.register(getResource("rederangement"), RederangementModule.LOADER);
            ModifierModule.LOADER.register(getResource("redye"), RedyeModule.LOADER);
            ModifierModule.LOADER.register(getResource("repercussion"), RepercussionModule.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_dash"), SculkDashModule.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_teleport"), SculkTeleportModule.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_struggle"), SculkStruggleModule.LOADER);
            ModifierModule.LOADER.register(getResource("sharp_circumstance"), SharpCircumstanceModule.LOADER);

            // module-havest
            ModifierModule.LOADER.register(getResource("hungriness"), HungrinessModule.LOADER);

            // module-defense
            ModifierModule.LOADER.register(getResource("antibrute"), AntibruteModule.LOADER);
            ModifierModule.LOADER.register(getResource("edible_heal"), EdibleHealModule.LOADER);
            ModifierModule.LOADER.register(getResource("reburning"), ReburningModule.LOADER);
            ModifierModule.LOADER.register(getResource("remisdirection"), RemisdirectionModule.LOADER);
            ModifierModule.LOADER.register(getResource("retransit"), RetransitModule.LOADER);

            // module-ranged
            ModifierModule.LOADER.register(getResource("atlatl"), AtlatlModule.LOADER);
            ModifierModule.LOADER.register(getResource("coercion"), CoercionModule.LOADER);
            ModifierModule.LOADER.register(getResource("nonsense"), NonsenseModule.LOADER);
            ModifierModule.LOADER.register(getResource("bide_time"), BideTimeModule.LOADER);
            ModifierModule.LOADER.register(getResource("riding_shoot"), RidingShootModule.LOADER);
            ModifierModule.LOADER.register(getResource("resisting"), ResistingModule.LOADER);
            ModifierModule.LOADER.register(getResource("sinistral"), SinistralModule.LOADER);
            ModifierModule.LOADER.register(getResource("swash_advanced"), SwashAdvancedModule.LOADER);
            // module-durability
            ModifierModule.LOADER.register(getResource("sculk_catalyse"), SculkCatalyseModule.LOADER);
            ModifierModule.LOADER.register(getResource("reverse"), ReverseModule.LOADER);
            ModifierModule.LOADER.register(getResource("deposition"), DepositionModule.LOADER);
            ModifierModule.LOADER.register(getResource("duritae"), DuritaeModule.LOADER);
            ModifierModule.LOADER.register(getResource("overeat"), OvereatModule.LOADER);
            ModifierModule.LOADER.register(getResource("durable"), DurableModule.LOADER);
            ModifierModule.LOADER.register(getResource("overcharge"), OverchargeModule.LOADER);
            ModifierModule.LOADER.register(getResource("netherite"), NetheriteModule.LOADER);
            // module-misc
            ModifierModule.LOADER.register(getResource("sling_sprinting"), SlingSprintingModule.LOADER);
            ModifierModule.LOADER.register(getResource("hurried"), HurriedModule.LOADER);
            // module-compat
            if (ModList.get().isLoaded("tinkers_ingenuity")) {
                ModifierModule.LOADER.register(getResource("curio_level"), CurioLevelModule.LOADER);
                ModifierModule.LOADER.register(getResource("sculk_heal"), SculkHealModule.LOADER);
            }
        }
    }

}
