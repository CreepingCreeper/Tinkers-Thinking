package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.modifer.curio.CurioLevelModule;
import com.creeping_creeper.tinkers_thinking.common.modifer.OverbearModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.curio.SculkHealModifier;
import com.creeping_creeper.tinkers_thinking.common.modifer.defense.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.durability.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.harvest.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.melee.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.misc.*;
import com.creeping_creeper.tinkers_thinking.common.modifer.ranged.*;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.creeping_creeper.tinkers_thinking.TinkersThinking.getResource;

public class ModModifiers{
    public static void init(){
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        if (ModList.get().isLoaded("tinkers_ingenuity")){
            TIModifiers.init();
        }
    }
    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersThinking.MODID);
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
    public static final StaticModifier<SculkProtectionModifier> SculkProtection = MODIFIERS.register("sculk_protection", SculkProtectionModifier::new);
    public static final StaticModifier<SculkTeleportModifier> SculkTeleport = MODIFIERS.register("sculk_teleport", SculkTeleportModifier::new);
    public static final StaticModifier<FallingAttackModifier> FallingAttack = MODIFIERS.register("falling_attack", FallingAttackModifier::new);
    public static final StaticModifier<SpikyModifier> Spiky = MODIFIERS.register("spiky", SpikyModifier::new);
    public static final StaticModifier<HungrinessModifier> Hungriness = MODIFIERS.register("hungriness", HungrinessModifier::new);
    public static final StaticModifier<BurningOutModifier> BurningOut = MODIFIERS.register("burning_out", BurningOutModifier::new);
    public static final StaticModifier<SinistralModifier> Sinistral = MODIFIERS.register("sinistral", SinistralModifier::new);
    public static final StaticModifier<SculkBreedModifier> SculkBreed = MODIFIERS.register("sculk_breed", SculkBreedModifier::new);
    public static final StaticModifier<SculkDashModifier> SculkDash = MODIFIERS.register("sculk_dash", SculkDashModifier::new);
    public static final StaticModifier<CrimsonModifier> Crimson = MODIFIERS.register("crimson", CrimsonModifier::new);
    public static final StaticModifier<CataclysmModifier> Cataclysm = MODIFIERS.register("cataclysm", CataclysmModifier::new);
    public static final StaticModifier<MagicTransformModifier> MagicTransform = MODIFIERS.register("magic_transform", MagicTransformModifier::new);
    public static final StaticModifier<OverFreezeModifier> FreezingCold = MODIFIERS.register("overfreeze", OverFreezeModifier::new);
    public static final StaticModifier<DurableModifier> Durable = MODIFIERS.register("durable", DurableModifier::new);
    public static final StaticModifier<OverdisintegrateModifier> Overdisintegrate = MODIFIERS.register("overdisintegrate", OverdisintegrateModifier::new);
    public static final StaticModifier<OverbearModifier> Overbear = MODIFIERS.register("overbear", OverbearModifier::new);
    public static final StaticModifier<ReverseModifier> Reverse = MODIFIERS.register("reverse", ReverseModifier::new);
    public static final StaticModifier<RecalamityModifier> Recalamity = MODIFIERS.register("recalamity", RecalamityModifier::new);
    public static final StaticModifier<SculkSiphonModifier> SculkSiphon = MODIFIERS.register("sculk_siphon", SculkSiphonModifier::new);
    public static final StaticModifier<ResistingModifier> Resisting = MODIFIERS.register("resisting", ResistingModifier::new);
    public static final StaticModifier<BoomModifier> Boom = MODIFIERS.register("boom", BoomModifier::new);
    public static final StaticModifier<RidingShootModifier> RidingShoot = MODIFIERS.register("riding_shoot", RidingShootModifier::new);
    public static final StaticModifier<FrozenModifier> Frozen = MODIFIERS.register("frozen", FrozenModifier::new);
    public static final StaticModifier<RedyeModifier> Redye = MODIFIERS.register("redye", RedyeModifier::new);
    public static final StaticModifier<RechargeModifier> Recharge = MODIFIERS.register("recharge", RechargeModifier::new);
    public static final StaticModifier<RepercussionModifier> Repercussion = MODIFIERS.register("repercussion", RepercussionModifier::new);
    public static final StaticModifier<CounterAttackModifier> CounterAttack = MODIFIERS.register("counter_attack", CounterAttackModifier::new);
    public static final StaticModifier<SwashAdvancedModifier> SwashAdvanced = MODIFIERS.register("swash_advanced", SwashAdvancedModifier::new);
    public static final StaticModifier<BattleAdvancedModifier> BattleAdvanced = MODIFIERS.register("battle_advanced", BattleAdvancedModifier::new);
    public static final StaticModifier<GlowAdvancedModifier> GlowAdvanced = MODIFIERS.register("glow_advanced", GlowAdvancedModifier::new);
    public static final StaticModifier<TeleportAdvancedModifier> TeleportAdvanced = MODIFIERS.register("teleport_advanced", TeleportAdvancedModifier::new);
    public static final StaticModifier<OverchargeModifier> Overcharge = MODIFIERS.register("overcharge", OverchargeModifier::new);
    @SubscribeEvent
    void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.RECIPE_SERIALIZER) {
            ModifierModule.LOADER.register(getResource("atlatl"), AtlatlModule.LOADER);
            ModifierModule.LOADER.register(getResource("netherite"), NetheriteModule.LOADER);
            ModifierModule.LOADER.register(getResource("symbiotic"), SymbioticModule.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_boost"), SculkBoostModule.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_catalyse"), SculkCatalyseModule.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_levitate"), SculkLevitateModule.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_gravity"), SculkGravityModule.LOADER);
            ModifierModule.LOADER.register(getResource("lightly_attack"), LightlyAttackModule.LOADER);
            ModifierModule.LOADER.register(getResource("hurried"), HurriedModule.LOADER);
            ModifierModule.LOADER.register(getResource("antibrute"), AntibruteModule.LOADER);
            ModifierModule.LOADER.register(getResource("reburning"), ReburningModifier.LOADER);
            ModifierModule.LOADER.register(getResource("sculk_struggle"), SculkStruggleModule.LOADER);
            ModifierModule.LOADER.register(getResource("rederangement"), RederangementModule.LOADER);
            ModifierModule.LOADER.register(getResource("retransit"), RetransitModule.LOADER);
            ModifierModule.LOADER.register(getResource("nonsense"), NonsenseModule.LOADER);
            ModifierModule.LOADER.register(getResource("coercion"), CoercionModule.LOADER);
            ModifierModule.LOADER.register(getResource("nocturnal"), NocturnalModule.LOADER);
            ModifierModule.LOADER.register(getResource("bide_time"), BideTimeModule.LOADER);
            ModifierModule.LOADER.register(getResource("sharp_circumstance"), SharpCircumstanceModule.LOADER);
            ModifierModule.LOADER.register(getResource("remisdirection"), RemisdirectionModule.LOADER);
            if (ModList.get().isLoaded("tinkers_ingenuity")) {
                ModifierModule.LOADER.register(getResource("curio_level"), CurioLevelModule.LOADER);
            }
        }
    }
    public static class TIModifiers{
        public static void init(){
            TI_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
        private static final ModifierDeferredRegister TI_MODIFIERS = ModifierDeferredRegister.create(TinkersThinking.MODID);
        public static final StaticModifier<SculkHealModifier> SculkHeal = TI_MODIFIERS.register("sculk_heal", SculkHealModifier::new);
    }
}
