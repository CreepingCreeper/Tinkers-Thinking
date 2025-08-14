package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities extends ModModule {
    public static final RegistryObject<EntityType<SeekingArrowEntity>> Seeking_Arrow  = ENTITIES.register("seeking_arrow", () ->
            EntityType.Builder.<SeekingArrowEntity>of(SeekingArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20).setShouldReceiveVelocityUpdates(true));

}
