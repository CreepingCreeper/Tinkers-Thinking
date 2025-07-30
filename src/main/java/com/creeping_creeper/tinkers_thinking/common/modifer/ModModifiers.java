package com.creeping_creeper.tinkers_thinking.common.modifer;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.modifer.defense.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.durability.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.harvest.HungrinessModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.harvest.InspiredModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.harvest.SculkBoostModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.harvest.ShadyModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.melee.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.HurriedModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.durability.ReverseModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.SprintingModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.ranged.SinistralModifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.creeping_creeper.tinkers_thinking.TinkersThinking.getResource;

public class ModModifiers{
    public ModModifiers(){
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static void initRegisters(){
        IEventBus bus =FMLJavaModLoadingContext.get().getModEventBus();
        RECIPE_SERIALIZERS.register(bus);
    }
    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersThinking.MODID);
    protected static final SynchronizedDeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = SynchronizedDeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TinkersThinking.MODID);

    public static final StaticModifier<DepositionModifier> Deposition = MODIFIERS.register("deposition", DepositionModifier::new);
    public static final StaticModifier<StimulationModifier> Stimulation = MODIFIERS.register("stimulation", StimulationModifier::new);
    public static final StaticModifier<InspiredModifier> Inspired = MODIFIERS.register("inspired", InspiredModifier::new);
    public static final StaticModifier<MockModifier> Mock = MODIFIERS.register("mock", MockModifier::new);
    public static final StaticModifier<ConcealingModifier> Concealing = MODIFIERS.register("concealing", ConcealingModifier::new);
    public static final StaticModifier<ShadyModifier> Shady = MODIFIERS.register("shady", ShadyModifier::new);
    public static final StaticModifier<ShadowingModifier> Shadowing = MODIFIERS.register("shadowing", ShadowingModifier::new);
    public static final StaticModifier<RepulsiveModifier> Repulsive = MODIFIERS.register("repulsive", RepulsiveModifier::new);
    public static final StaticModifier<PricklyModifier> Prickly = MODIFIERS.register("prickly", PricklyModifier::new);
    public static final StaticModifier<SprintingModifier> Sprinting = MODIFIERS.register("sprinting", SprintingModifier::new);
    public static final StaticModifier<DuritaeModifier> Duritae = MODIFIERS.register("duritae", DuritaeModifier::new);
    public static final StaticModifier<OvereatModifier> Overeat = MODIFIERS.register("overeat", OvereatModifier::new);
    public static final StaticModifier<DisarmModifier> Disarm = MODIFIERS.register("disarm", DisarmModifier::new);
    public static final StaticModifier<SculkCatalyseModifier> SculkCatalyse = MODIFIERS.register("sculk_catalyse", SculkCatalyseModifier::new);
    public static final StaticModifier<SculkBoostModifier> SculkBoost = MODIFIERS.register("sculk_boost", SculkBoostModifier::new);
    public static final StaticModifier<SculkTeleportModifier> SculkTeleport = MODIFIERS.register("sculk_teleport", SculkTeleportModifier::new);
    public static final StaticModifier<FallingAttackModifier> FallingAttack = MODIFIERS.register("falling_attack", FallingAttackModifier::new);
    public static final StaticModifier<Modifier> DensityAdvanced = MODIFIERS.register("density_advanced",Modifier::new);
    public static final StaticModifier<SpikyModifier> Spiky = MODIFIERS.register("spiky", SpikyModifier::new);
    public static final StaticModifier<HungrinessModifier> Hungriness = MODIFIERS.register("hungriness", HungrinessModifier::new);
    public static final StaticModifier<BurningOutModifier> BurningOut = MODIFIERS.register("burning_out", BurningOutModifier::new);
    public static final StaticModifier<ReburningModifier> Reburning = MODIFIERS.register("reburning", ReburningModifier::new);
    public static final StaticModifier<SinistralModifier> Sinistral = MODIFIERS.register("sinistral", SinistralModifier::new);
    public static final StaticModifier<Modifier> RepeatingAdvanced = MODIFIERS.register("repeating_advanced", Modifier::new);
    public static final StaticModifier<AntiBruteModifier> AntiBrute = MODIFIERS.register("antibrute", AntiBruteModifier::new);
    public static final StaticModifier<SculkBreedModifier> SculkBreed = MODIFIERS.register("sculk_breed", SculkBreedModifier::new);
    public static final StaticModifier<SculkDashModifier> SculkDash = MODIFIERS.register("sculk_dash", SculkDashModifier::new);
    public static final StaticModifier<SculkStruggleModifier> SculkStruggle = MODIFIERS.register("sculk_struggle", SculkStruggleModifier::new);
    public static final StaticModifier<CrimsonModifier> Crimson = MODIFIERS.register("crimson", CrimsonModifier::new);
    public static final StaticModifier<CataclysmModifier> Cataclysm = MODIFIERS.register("cataclysm", CataclysmModifier::new);
    public static final StaticModifier<MagicTransformModifier> MagicTransform = MODIFIERS.register("magic_transform", MagicTransformModifier::new);
    public static final StaticModifier<OverFreezeModifier> FreezingCold = MODIFIERS.register("overfreeze", OverFreezeModifier::new);
    public static final StaticModifier<DurableModifier> Durable = MODIFIERS.register("durable", DurableModifier::new);
    public static final StaticModifier<OverdisintegrateModifier> Overdisintegrate = MODIFIERS.register("overdisintegrate", OverdisintegrateModifier::new);
    public static final StaticModifier<OverbearModifier> Overbear = MODIFIERS.register("overbear", OverbearModifier::new);
    public static final StaticModifier<Modifier> Tyranny = MODIFIERS.register("tyranny", Modifier::new);
    public static final StaticModifier<ReverseModifier> Reverse = MODIFIERS.register("reverse", ReverseModifier::new);
    public static final StaticModifier<RecalamityModifier> Recalamity = MODIFIERS.register("recalamity", RecalamityModifier::new);
    @SubscribeEvent
    void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.RECIPE_SERIALIZER) {
            ModifierModule.LOADER.register(getResource("netherite"), NetheriteModule.LOADER);
            ModifierModule.LOADER.register(getResource("symbiotic"), SymbioticModule.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_levitate"), SculkLevitateModule.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_gravity"), SculkGravityModule.LOADER);
            ModifierModule.LOADER.register(getResource("lightly_attack"), LightlyAttackModule.LOADER);
            ModifierModule.LOADER.register(getResource("hurried"), HurriedModule.LOADER);
        }
    }
}
