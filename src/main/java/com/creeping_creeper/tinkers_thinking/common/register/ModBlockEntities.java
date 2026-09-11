package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.common.things.block.entity.WasteFluidCylinderBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities extends ModModule {
    public static final RegistryObject<BlockEntityType<WasteFluidCylinderBlockEntity>> Waste_Fluid_Cylinder =
            BLOCK_ENTITIES.register("waste_fluid_cylinder", WasteFluidCylinderBlockEntity::new, ModCommonItems.waste_fluid_cylinder);
}
