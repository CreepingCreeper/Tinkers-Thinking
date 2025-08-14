package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.common.register.ModModule;
import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import com.creeping_creeper.tinkers_thinking.common.things.block.entity.DryingRackBlockEntity;
import com.creeping_creeper.tinkers_thinking.common.things.block.entity.WasteFluidCylinderBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities extends ModModule {
    public static final RegistryObject<BlockEntityType<DryingRackBlockEntity>> Drying_Rack =
            BLOCK_ENTITIES.register("drying_rack",DryingRackBlockEntity::new, ModCommonItems.drying_rack);
    public static final RegistryObject<BlockEntityType<WasteFluidCylinderBlockEntity>> New =
            BLOCK_ENTITIES.register("waste_fluid_cylinder", WasteFluidCylinderBlockEntity::new, ModCommonItems.waste_fluid_cylinder);
}
