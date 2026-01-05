package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities extends ModModule {
    public static final RegistryObject<EntityType<SeekingArrow>> Seeking_Arrow = ENTITIES.register("arrow", () -> EntityType.Builder.<SeekingArrow>of(SeekingArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(2));
}
