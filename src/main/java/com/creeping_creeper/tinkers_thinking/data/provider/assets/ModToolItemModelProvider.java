package com.creeping_creeper.tinkers_thinking.data.provider.assets;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModToolItems;
import com.google.gson.JsonObject;
import net.minecraft.data.PackOutput;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.data.AbstractToolItemModelProvider;

import java.io.IOException;

public class ModToolItemModelProvider extends AbstractToolItemModelProvider {
    public ModToolItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, existingFileHelper, TinkersThinking.MODID);
    }

    @Override
    protected void addModels() throws IOException {
        JsonObject toolBlocking = readJson(TConstruct.getResource("base/tool_blocking"));

        tool(ModToolItems.atlatl, toolBlocking, "bowstring");
        tool(ModToolItems.cutlass, toolBlocking, "blade");
        tool(ModToolItems.paxel, toolBlocking, "head", "axe");
        tool(ModToolItems.knife, toolBlocking, "blade_left", "blade_right");
        tool(ModToolItems.mace, toolBlocking, "handle");

        bow(ModToolItems.repeating_crossbow, toolBlocking, new CrossbowAmmo(new Vec2(-1, -1), true, false), "bowstring");

        staff(ModToolItems.clay_staff, toolBlocking);
        staff(ModToolItems.magma_staff, toolBlocking);
        //staff(ModToolItems.quartz_staff, toolBlocking);
    }

    @Override
    public String getName() {
        return "TiT Tool Item Model Provider";
    }
}
