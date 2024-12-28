package com.creeping_creeper.tinkers_thinking.common.things;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.object.FlowingFluidObject;

import static slimeknights.tconstruct.fluids.block.BurningLiquidBlock.createBurning;
import static slimeknights.tconstruct.fluids.block.MobEffectLiquidBlock.createEffect;

public class ModFluids extends ModModule {
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_ardite   = FLUIDS.register("molten_ardite").type(hot().temperature(1296).lightLevel(15)).block(createBurning(15, 10, 9f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_tinkers_bronze   = FLUIDS.register("molten_tinkers_bronze").type(hot().temperature(550).lightLevel(15)).block(createBurning(15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_lightite   = FLUIDS.register("molten_lightite").type(hot().temperature(450).lightLevel(15)).block(createBurning(15, 10, 4f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_chlorophyte   = FLUIDS.register("molten_chlorophyte").type(hot().temperature(600).lightLevel(15)).block(createBurning(15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_spectre   = FLUIDS.register("molten_spectre").type(hot().temperature(600).lightLevel(15)).block(createBurning(15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_shroomite  = FLUIDS.register("molten_shroomite").type(hot().temperature(600).lightLevel(15)).block(createBurning(15, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_obsidian_bronze  = FLUIDS.register("molten_obsidian_bronze").type(hot().temperature(950).lightLevel(15)).block(createBurning(15, 10, 6f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_electrical_steel   = FLUIDS.register("molten_electrical_steel").type(hot().temperature(1296).lightLevel(15)).block(createBurning(15, 10, 6f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_beetron   = FLUIDS.register("molten_beetron").type(hot().temperature(400).lightLevel(15)).block(createBurning(15, 10, 4f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_echo_bronze   = FLUIDS.register("molten_echo_bronze").type(hot().temperature(150).lightLevel(3)).block(createBurning(3, 10, 3f)).bucket().flowing();
    //
    public static final FlowingFluidObject<ForgeFlowingFluid> reburn_ashes   = FLUIDS.register("reburn_ashes").type(hot().temperature(1500).lightLevel(15)).block(createBurning(15, 10, 9f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_echo  = FLUIDS.register("molten_echo").type(common().temperature(50).lightLevel(3)).block(createEffect(3, () -> new MobEffectInstance(MobEffects.DARKNESS, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> syrup  = FLUIDS.register("syrup").type(common().temperature(75).lightLevel(15)).block(createEffect(7, () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_cocoa  = FLUIDS.register("molten_cocoa").type(hot().temperature(100).lightLevel(7)).block(createEffect(7, () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_black_chocolate  = FLUIDS.register("molten_black_chocolate").type(hot().temperature(90).lightLevel(7)).block(createEffect(7, () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100))).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> molten_white_chocolate  = FLUIDS.register("molten_white_chocolate").type(hot().temperature(80).lightLevel(7)).block(createEffect(7, () -> new MobEffectInstance(MobEffects.DIG_SPEED, 100))).bucket().flowing();
    private static FluidType.Properties common() {
       return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .motionScale(0.0023333333333333335D)
                .canExtinguish(true);
    }
    private static FluidType.Properties hot() {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                .motionScale(0.0023333333333333335D)
                .canSwim(false).canDrown(false)
                .pathType(BlockPathTypes.LAVA).adjacentPathType(null);
    }
}
