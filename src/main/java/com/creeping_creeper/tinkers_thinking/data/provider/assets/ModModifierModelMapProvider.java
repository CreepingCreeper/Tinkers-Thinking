package com.creeping_creeper.tinkers_thinking.data.provider.assets;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.register.ModToolItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.data.AbstractModifierModelMapProvider;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;

public class ModModifierModelMapProvider extends AbstractModifierModelMapProvider {
    public ModModifierModelMapProvider(PackOutput output) {
        super(output, TinkersThinking.MODID);
    }

    @Override
    protected void addModels() {
        // small
        ResourceLocation axeModifier = TConstruct.getResource("hand_axe/modifiers/");
        tool(ModToolItems.paxel)
                // shared with axe
                .smallFolder(axeModifier)
                .basic(
                        ModifierIds.diamond, ModifierIds.emerald, ModifierIds.netherite,
                        ModifierIds.reinforced, ModifierIds.overforced, ModifierIds.magnetic, ModifierIds.soulbound,
                        ModifierIds.experienced, ModifierIds.luck, TinkerModifiers.severing.getId(), ModifierIds.silky,
                        ModifierIds.sharpness, ModifierIds.smite, ModifierIds.antiaquatic, ModifierIds.baneOfSssss, ModifierIds.cooling,
                        ModifierIds.knockback, ModifierIds.necrotic,
                        ModifierIds.blasting, ModifierIds.hydraulic
                )
                .fluid(ModifierIds.bucketing).tank()
                .luminosity(15, ModifierIds.lightspeed, ModifierIds.glowing)
                .luminosity(10, ModifierIds.fiery)
                .luminosity( 7, ModifierIds.haste)
                .luminosity( 2, ModifierIds.unbreakable);

        tool(ModToolItems.knife)
                .basic(
                        ModifierIds.diamond, ModifierIds.emerald, ModifierIds.netherite,
                        ModifierIds.reinforced, ModifierIds.overforced, ModifierIds.magnetic, ModifierIds.soulbound,
                        ModifierIds.experienced, TinkerModifiers.severing.getId(), ModifierIds.silky,
                        ModifierIds.sharpness, ModifierIds.antiaquatic, ModifierIds.baneOfSssss, ModifierIds.cooling,
                        ModifierIds.knockback, ModifierIds.necrotic, ModifierIds.hydraulic, ModifierIds.smite, ModifierIds.luck
                )
                .fluid(ModifierIds.bucketing).tank()
                .luminosity(15, ModifierIds.lightspeed, ModifierIds.glowing)
                .luminosity(10, ModifierIds.fiery)
                .luminosity( 7, ModifierIds.haste)
                .luminosity( 2, ModifierIds.unbreakable);

        // ranged weapon
        ResourceLocation crossbowSmall = TConstruct.getResource("crossbow/modifiers/");
        ResourceLocation longbowSmall = TConstruct.getResource("longbow/modifiers/");
        tool(ModToolItems.repeating_crossbow)
                .smallFolder(crossbowSmall)
                .basic(
                        ModifierIds.diamond, ModifierIds.netherite,
                        ModifierIds.reinforced, ModifierIds.overforced, ModifierIds.experienced, ModifierIds.freezing,
                        ModifierIds.arrowPierce, ModifierIds.pierce, ModifierIds.trueshot
                )
                .luminosity(10, ModifierIds.fiery)
                .luminosity( 7, ModifierIds.quickCharge)
                .luminosity( 2, ModifierIds.unbreakable)
                // shared with longbow
                .smallFolder(longbowSmall)
                .basic(
                       ModifierIds.emerald
                );
        for (int i = 1; i < 4; i++){
            tool(ModToolItems.repeating_crossbow, "/" + i).smallFolder(crossbowSmall).basic("_" + i, ModifierIds.quickCharge);
        }
        tool(ModToolItems.repeating_crossbow, "/broken").smallFolder(crossbowSmall).basic("_broken", ModifierIds.quickCharge);
    }

    @Override
    public String getName() {
        return "TiT Modifier Model Map Provider";
    }
}
