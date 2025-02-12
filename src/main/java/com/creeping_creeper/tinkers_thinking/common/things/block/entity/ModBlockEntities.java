package com.creeping_creeper.tinkers_thinking.common.things.block.entity;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModCommonItems;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.BlockEntityTypeDeferredRegister;

public class ModBlockEntities{
    protected static final BlockEntityTypeDeferredRegister BLOCK_ENTITIES = new BlockEntityTypeDeferredRegister(TinkersThinking.MODID);
    public static final RegistryObject<BlockEntityType<DryingRackBlockEntity>> Drying_Rack =
            BLOCK_ENTITIES.register("drying_rack",DryingRackBlockEntity::new, ModCommonItems.drying_rack);
    public static final RegistryObject<BlockEntityType<SpitterBlockEntity>> Spitter =
            BLOCK_ENTITIES.register("seared_spitter", SpitterBlockEntity::new, set -> set.add((ModCommonItems.seared_spitter.get()), ModCommonItems.scorched_spitter.get()));
    public static void registers(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
