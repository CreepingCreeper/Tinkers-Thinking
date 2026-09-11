package com.creeping_creeper.tinkers_thinking.data.provider.tinkering;

import com.creeping_creeper.tinkers_thinking.data.ModMaterialIds;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class ModMaterialProvider extends AbstractMaterialDataProvider {
    public ModMaterialProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addMaterials() {
        material(ModMaterialIds.electrical_steel).tier(3).sort(ORDER_WEAPON);
        material(ModMaterialIds.bacium).tier(3).sort(ORDER_WEAPON + ORDER_NETHER).craftable();

    }

    @Override
    public @NotNull String getName() {
        return "TiT Materials";
    }

}