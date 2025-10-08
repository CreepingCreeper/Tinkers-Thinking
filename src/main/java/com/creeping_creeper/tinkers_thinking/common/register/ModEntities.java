package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingArrow;
import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingThrownShuriken;
import com.creeping_creeper.tinkers_thinking.common.things.entity.ThrownBoomerang;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.tools.entity.ThrownTool;

public class ModEntities extends ModModule {
    public static final RegistryObject<EntityType<SeekingArrow>> Seeking_Arrow = ENTITIES.register("arrow", () -> EntityType.Builder.<SeekingArrow>of(SeekingArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));
    public static final RegistryObject<EntityType<SeekingThrownShuriken>> Seeking_Shuriken = ENTITIES.register("thrown_shuriken", () -> EntityType.Builder.<SeekingThrownShuriken>of(SeekingThrownShuriken::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));
    public static final RegistryObject<EntityType<ThrownBoomerang>> thrownBoomerang = ENTITIES.register("thrown_boomerang", () -> EntityType.Builder.<ThrownBoomerang>of(ThrownBoomerang::new, MobCategory.MISC).sized(0.5f, 0.5f).clientTrackingRange(4).updateInterval(10));

}
