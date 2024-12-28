package com.creeping_creeper.tinkers_thinking.common.things.block.entity;

import com.creeping_creeper.tinkers_thinking.common.things.ModItems;
import com.creeping_creeper.tinkers_thinking.common.things.ModModule;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities extends ModModule {
    public static final RegistryObject<BlockEntityType<DryingRackBlockEntity>> Drying_Rack =
            BLOCK_ENTITIES.register("drying_rack",DryingRackBlockEntity::new, ModItems.drying_rack);
    public static final RegistryObject<BlockEntityType<SpitterBlockEntity>> Spitter =
            BLOCK_ENTITIES.register("seared_spitter", SpitterBlockEntity::new, set -> set.add((ModItems.seared_spitter.get()),ModItems.scorched_spitter.get()));
}
