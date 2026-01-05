package com.creeping_creeper.tinkers_thinking.common.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slimeknights.mantle.client.model.NBTKeyModel;
import slimeknights.tconstruct.library.tools.SlotType;

public class ModSlots {
    public static SlotType ANCIENT = SlotType.getOrCreate("ancient");

    public ModSlots(){
    }
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        NBTKeyModel.registerExtraTexture(new ResourceLocation("tconstruct:creative_slot")
                ,ANCIENT.getName(),new ResourceLocation("tinkers_thinking:item/ancient_ceramic"));
    }
}
