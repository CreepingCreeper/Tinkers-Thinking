package com.creeping_creeper.tinkers_thinking.data.provider.assets;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

public class ModPartSpriteProvider extends AbstractPartSpriteProvider {

    public ModPartSpriteProvider() {
        super(TinkersThinking.MODID);
    }

    @Override
    protected void addAllSpites() {
        addHead("narrow_blade");
        buildTool("arrow_thrower").withLarge()
                .addLimb("limb").addGrip("grip_top").addGrip("grip_bottom").addBreakableBowstring("bowstring");
        buildTool("cutlass").addBreakableHead("blade").addPart("binding", StatlessMaterialStats.SHIELD_CORE).addHandle("handle");
        buildTool("paxel").addBreakableHead("head").addBreakableHead("axe");
        buildTool("knife").addBreakableHead("blade_left").addBreakableHead("blade_right").addHandle("handle");
        buildTool("mace").addHead("plate").addBreakablePart("handle", HandleMaterialStats.ID);
        buildTool("repeating_crossbow").addLimb("limb").addGrip("body")
                .addBreakableBowstring("bowstring").addBowstring("bowstring_1").addBowstring("bowstring_2").addBowstring("bowstring_3");
        buildTool("quartz_staff").withLarge()
                .addHead("grip").addHandle("low").addHandle("high");
    }

    @Override
    public String getName() {
        return "TiT Parts";
    }
}
