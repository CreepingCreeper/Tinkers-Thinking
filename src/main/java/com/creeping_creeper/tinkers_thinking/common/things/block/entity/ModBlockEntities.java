package com.creeping_creeper.tinkers_thinking.common.things.block.entity;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.ModModule;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModCommonItems;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.BlockEntityTypeDeferredRegister;

public class ModBlockEntities extends ModModule {
    public static final RegistryObject<BlockEntityType<DryingRackBlockEntity>> Drying_Rack =
            BLOCK_ENTITIES.register("drying_rack",DryingRackBlockEntity::new, ModCommonItems.drying_rack);
    public static final RegistryObject<BlockEntityType<WasteFluidCylinderBlockEntity>> New =
            BLOCK_ENTITIES.register("waste_fluid_cylinder", WasteFluidCylinderBlockEntity::new, ModCommonItems.waste_fluid_cylinder);
}
