package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.fluid.InvertedFluid;
import slimeknights.mantle.registration.object.FlowingFluidObject;

import static net.minecraft.world.level.material.MapColor.*;
import static slimeknights.tconstruct.fluids.block.BurningLiquidBlock.createBurning;
import static slimeknights.tconstruct.fluids.block.MobEffectLiquidBlock.createEffect;

public class ModFluids extends ModModule{
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_ardite   = FLUIDS.registerMetal("molten_ardite").type(hot("molten_ardite").temperature(1296).lightLevel(15)).block(createBurning(MapColor.COLOR_ORANGE,15, 10, 9f)).bucket().flowing();
    public static final FlowingFluidObject<InvertedFluid> molten_zith   = FLUIDS.registerMetal("molten_zith").type(gas("molten_zith").temperature(1800).lightLevel(15)).burningBlock(COLOR_PINK,15, 10, 12f).bucket().invertedFlowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_tinkers_bronze   = FLUIDS.registerMetal("molten_tinkers_bronze").type(hot("molten_tinkers_bronze").temperature(550).lightLevel(15)).block(createBurning(MapColor.COLOR_YELLOW,15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_lightite   = FLUIDS.registerMetal("molten_lightite").type(hot("molten_lightite").temperature(450).lightLevel(15)).block(createBurning(MapColor.COLOR_LIGHT_GRAY,15, 10, 4f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_chlorophyte   = FLUIDS.registerMetal("molten_chlorophyte").type(hot("molten_chlorophyte").temperature(600).lightLevel(15)).block(createBurning(MapColor.COLOR_GREEN,15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_spectre   = FLUIDS.registerMetal("molten_spectre").type(hot("molten_spectre").temperature(600).lightLevel(15)).block(createBurning(MapColor.COLOR_LIGHT_BLUE,15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_shroomite  = FLUIDS.registerMetal("molten_shroomite").type(hot("molten_shroomite").temperature(600).lightLevel(15)).block(createBurning(MapColor.COLOR_BLUE,15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_obsidian_bronze  = FLUIDS.registerMetal("molten_obsidian_bronze").type(hot("molten_obsidian_bronze").temperature(950).lightLevel(15)).block(createBurning(MapColor.COLOR_BROWN,15, 10, 6f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_electrical_steel   = FLUIDS.registerMetal("molten_electrical_steel").type(hot("molten_electrical_steel").temperature(1296).lightLevel(15)).block(createBurning(MapColor.COLOR_GRAY,15, 10, 6f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_beetron   = FLUIDS.registerMetal("molten_beetron").type(hot("molten_beetron").temperature(400).lightLevel(15)).block(createBurning(MapColor.COLOR_RED,15, 10, 4f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_echo_bronze   = FLUIDS.registerMetal("molten_echo_bronze").type(hot("molten_echo_bronze").temperature(150).lightLevel(3)).block(createBurning(MapColor.COLOR_BLACK,3, 10, 3f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_warden_steel   = FLUIDS.registerMetal("molten_warden_steel").type(hot("molten_warden_steel").temperature(750).lightLevel(12)).block(createBurning(MapColor.COLOR_BLACK,12, 10, 3f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_tempered_glass   = FLUIDS.registerGlass("molten_tempered_glass").type(hot("molten_tempered_glass").temperature(950).lightLevel(14)).block(createBurning(MapColor.COLOR_LIGHT_GRAY,14, 10, 4f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> scarletslime   = FLUIDS.registerSlime("scarletslime").type(hot("scarletslime").temperature(1100).lightLevel(12)).block(createBurning(COLOR_RED,12, 10, 4f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_ancient_ceramic   = FLUIDS.registerStone("molten_ancient_ceramic").type(hot("molten_ancient_ceramic").temperature(1200).lightLevel(12)).block(createBurning(COLOR_LIGHT_BLUE,12, 10, 4f)).bucket().flowing();

    public static final FlowingFluidObject<ForgeFlowingFluid> reburn_ashes   = FLUIDS.register("reburn_ashes").type(hot("reburn_ashes").temperature(1500).lightLevel(15)).block(createBurning(MapColor.COLOR_RED,15, 10, 9f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_echo  = FLUIDS.registerGem("molten_echo").type(common("molten_echo").temperature(50).lightLevel(3)).block(createEffect(MapColor.COLOR_BLACK,3, () -> new MobEffectInstance(MobEffects.DARKNESS, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> liquid_sculk_power  = FLUIDS.register("liquid_sculk_power").type(common("liquid_sculk_power").temperature(50).lightLevel(3)).block(createEffect(MapColor.COLOR_BLACK,3, () -> new MobEffectInstance(ModEffects.sculk_power.get(), 80))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> chillslime   = FLUIDS.registerSlime("chillslime").type(common("chillslime").temperature(10).lightLevel(9)).block(createEffect(MapColor.COLOR_LIGHT_BLUE,3, () -> new MobEffectInstance(ModEffects.freezing_cold.get(), 40))).bucket().flowing();

    public static final FlowingFluidObject<ForgeFlowingFluid> syrup  = FLUIDS.register("syrup").type(common("syrup").temperature(75).lightLevel(0)).block(createEffect(MapColor.COLOR_ORANGE,0, () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> pulp  = FLUIDS.register("pulp").type(common("pulp").temperature(25).lightLevel(0)).block(createEffect(MapColor.COLOR_LIGHT_GRAY,0, () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100))).bucket().flowing();
    public static final FlowingFluidObject<InvertedFluid> emptiness  = FLUIDS.register("emptiness").invertedType(gas("emptiness").temperature(35).lightLevel(10)).mobEffectBlock(COLOR_CYAN, 10, () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100)).bucket().invertedFlowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_cocoa  = FLUIDS.register("molten_cocoa").type(hot("molten_cocoa").temperature(100).lightLevel(7)).block(createEffect(MapColor.COLOR_ORANGE, 7,() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_black_chocolate  = FLUIDS.register("molten_black_chocolate").type(hot("molten_black_chocolate").temperature(90).lightLevel(7)).block(createEffect(MapColor.COLOR_BLACK,7, () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_white_chocolate  = FLUIDS.register("molten_white_chocolate").type(hot("molten_white_chocolate").temperature(80).lightLevel(7)).block(createEffect(MapColor.COLOR_LIGHT_GRAY,7, () -> new MobEffectInstance(MobEffects.DIG_SPEED, 100))).bucket().flowing();

    private static FluidType.Properties gas(String name) {
        return base(name).density(-2000)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);
    }
    private static FluidType.Properties common(String name) {
        return base(name).density(2000)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .canExtinguish(true);
    }
    private static FluidType.Properties hot(String name) {
        return base(name).density(2000)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                .canSwim(false).canDrown(false)
                .pathType(BlockPathTypes.LAVA).adjacentPathType(null);
    }
    private static FluidType.Properties base(String name) {
        return FluidType.Properties.create().viscosity(10000)
                .descriptionId(TinkersThinking.makeDescriptionId("fluid", name))
                .motionScale(0.0023333333333333335D);
    }
}
