package com.creeping_creeper.tinkers_thinking.common.client;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slimeknights.mantle.client.model.NBTKeyModel;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.tools.SlotType;

public class ModSlots {
    public static SlotType ANCIENT = SlotType.getOrCreate("ancient");

    public ModSlots(){
    }
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        NBTKeyModel.registerExtraTexture(TConstruct.getResource("creative_slot")
                ,ANCIENT.getName(), TinkersThinking.getResource("item/ancient_ceramic"));
    }
}
