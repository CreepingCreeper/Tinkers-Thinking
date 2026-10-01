package com.creeping_creeper.tinkers_thinking.data.provider.assets;

import com.creeping_creeper.tinkers_thinking.data.ModMaterialIds;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

public class ModMaterialSpriteProvider extends AbstractMaterialSpriteProvider {

    @Override
    protected void addAllMaterials() {
        // tier 1
        buildMaterial(ModMaterialIds.sugar_cane)
                .arrowShaft()
                .fallbacks("cloth")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF2A4C0D)
                        .addARGB(102,0xFFB4183A)
                        .addARGB(140,0xFFB4183A)
                        .addARGB(178,0xFF60A028)
                        .addARGB(216,0xFF7CCC35)
                        .addARGB(255,0xFF97F544)
                        .build());

        buildMaterial(ModMaterialIds.sponge)
                .meleeHarvest()
                .fallbacks("wood")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF8B7D2B)
                        .addARGB(102,0xFF9D8F39)
                        .addARGB(140,0xFF949621)
                        .addARGB(178,0xFFB5B63A)
                        .addARGB(216,0xFFC1BC4E)
                        .addARGB(255,0xFFE6E289)
                        .build());

        buildMaterial(ModMaterialIds.netherrack)
                .meleeHarvest()
                .fallbacks("rock")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF380A0A)
                        .addARGB(102,0xFF370A0A)
                        .addARGB(140,0xFF3F0F0F)
                        .addARGB(178,0xFF481515)
                        .addARGB(216,0xFF562121)
                        .addARGB(255,0xFF7D3B3B)
                        .build());

        // tier 2
        buildMaterial(ModMaterialIds.dusk)
                .meleeHarvest().shieldCore()
                .fallbacks("slime_wood")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF1C007F)
                        .addARGB(102,0xFF1F008A)
                        .addARGB(140,0xFF2A05A8)
                        .addARGB(178,0xFF3B13C6)
                        .addARGB(216,0xFF4118CF)
                        .addARGB(255,0xFF5329E2)
                        .build());

        buildMaterial(ModMaterialIds.stabilized_gunpowder)
                .meleeHarvest().ranged()
                .fallbacks("crystal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF1B1B1B)
                        .addARGB(102,0xFF2D2D2D)
                        .addARGB(140,0xFF545454)
                        .addARGB(216,0xFF727272)
                        .addARGB(255,0xFF8A8A8A)
                        .build());

        // tier3
        buildMaterial(ModMaterialIds.obsidian_bronze)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF5C4A18)
                        .addARGB(102,0xFF67541E)
                        .addARGB(140,0xFF79662E)
                        .addARGB(178,0xFF88743B)
                        .addARGB(216,0xFFA28B48)
                        .addARGB(255,0xFFB09852)
                        .build());

        buildMaterial(ModMaterialIds.beetron)
                .meleeHarvest().ranged()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF5B1D17)
                        .addARGB(102,0xFF71160D)
                        .addARGB(140,0xFF8F2D2F)
                        .addARGB(178,0xFFA4272C)
                        .addARGB(216,0xFFB6484C)
                        .addARGB(255,0xFFD3686C)
                        .build());

        buildMaterial(ModMaterialIds.echo_bronze)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF111B21)
                        .addARGB(102,0xFF052A32)
                        .addARGB(140,0xFF034150)
                        .addARGB(178,0xFF0A5060)
                        .addARGB(216,0xFF009295)
                        .addARGB(255,0xFF29DFEB)
                        .build());

        buildMaterial(ModMaterialIds.shroomite)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF37307F)
                        .addARGB(102,0xFF2215A4)
                        .addARGB(140,0xFF2C1AE9)
                        .addARGB(178,0xFF464AFF)
                        .addARGB(216,0xFF008CF4)
                        .addARGB(255,0xFF2DC3FF)
                        .build());

        buildMaterial(ModMaterialIds.shroomite_compound)
                .fletching()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF37307F)
                        .addARGB(102,0xFF2215A4)
                        .addARGB(140,0xFF2C1AE9)
                        .addARGB(178,0xFF464AFF)
                        .addARGB(216,0xFF008CF4)
                        .addARGB(255,0xFF2DC3FF)
                        .build());

        buildMaterial(ModMaterialIds.tinkers_bronze)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFFA07A26)
                        .addARGB(102,0xFFAE862E)
                        .addARGB(140,0xFFC69C3F)
                        .addARGB(178,0xFFDCB254)
                        .addARGB(216,0xFFF9CF72)
                        .addARGB(255,0xFFFFDB8A)
                        .build());

        buildMaterial(ModMaterialIds.white_chocolate)
                .meleeHarvest().ranged()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF9F692C)
                        .addARGB(102,0xFFAE7739)
                        .addARGB(140,0xFFBC8548)
                        .addARGB(178,0xFFC99256)
                        .addARGB(216,0xFFDBA76D)
                        .addARGB(255,0xFFEEBB82)
                        .build());

        buildMaterial(ModMaterialIds.black_chocolate)
                .meleeHarvest().ranged()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF230E04)
                        .addARGB(102,0xFF331708)
                        .addARGB(140,0xFF3C1B0B)
                        .addARGB(178,0xFF49230F)
                        .addARGB(216,0xFF4F2B19)
                        .addARGB(255,0xFF6C4029)
                        .build());

        buildMaterial(ModMaterialIds.chillslime_cryogel)
                .meleeHarvest().ranged().armor().arrowHead()
                .fallbacks("slime_wood","crystal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF2CA6AC)
                        .addARGB(102,0xFF3CB8BE)
                        .addARGB(140,0xFF54D3D9)
                        .addARGB(216,0xFF7CF6FC)
                        .addARGB(255,0xFFB8FCFF)
                        .build());

        buildMaterial(ModMaterialIds.lightite)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFFA3A3A3)
                        .addARGB(102,0xFFB4B4B4)
                        .addARGB(140,0xFFC4C4C4)
                        .addARGB(178,0xFFCECECE)
                        .addARGB(216,0xFFDDD8D8)
                        .addARGB(255,0xFFEBEBEB)
                        .build());

        buildMaterial(ModMaterialIds.lightite_compound)
                .fletching()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFFA3A3A3)
                        .addARGB(102,0xFFB4B4B4)
                        .addARGB(140,0xFFC4C4C4)
                        .addARGB(178,0xFFCECECE)
                        .addARGB(216,0xFFDDD8D8)
                        .addARGB(255,0xFFEBEBEB)
                        .build());

        buildMaterial(ModMaterialIds.metherite)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF271C1D)
                        .addARGB(102,0xFF3C3232)
                        .addARGB(140,0xFF4C4143)
                        .addARGB(178,0xFF4D494D)
                        .addARGB(216,0xFF737173)
                        .addARGB(255,0xFF918F91)
                        .build());

        buildMaterial(ModMaterialIds.spectre)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF066AFF)
                        .addARGB(102,0xFF3392D9)
                        .addARGB(140,0xFF44A9F5)
                        .addARGB(178,0xFF17B3FB)
                        .addARGB(216,0xFF58DBFF)
                        .addARGB(255,0xFF95F7FF)
                        .build());

        buildMaterial(ModMaterialIds.spectre_compound)
                .fletching()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF066AFF)
                        .addARGB(102,0xFF3392D9)
                        .addARGB(140,0xFF44A9F5)
                        .addARGB(178,0xFF17B3FB)
                        .addARGB(216,0xFF58DBFF)
                        .addARGB(255,0xFF95F7FF)
                        .build());

        buildMaterial(ModMaterialIds.chlorophyte)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF23532E)
                        .addARGB(102,0xFF246133)
                        .addARGB(140,0xFF248900)
                        .addARGB(178,0xFF5DBE08)
                        .addARGB(216,0xFF75D813)
                        .addARGB(255,0xFFAFEB48)
                        .build());

        buildMaterial(ModMaterialIds.chlorophyte_compound)
                .fletching()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF2A4C0D)
                        .addARGB(102,0xFF47821E)
                        .addARGB(140,0xFF248900)
                        .addARGB(178,0xFF5DBE08)
                        .addARGB(216,0xFF75D813)
                        .addARGB(255,0xFFAFEB48)
                        .build());

        buildMaterial(ModMaterialIds.gilded_silky_cloth)
                .maille().cuirass().laces()
                .fallbacks("cloth")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF752802)
                        .addARGB(102,0xFFB26411)
                        .addARGB(140,0xFFE9B115)
                        .addARGB(178,0xFFFAD64A)
                        .addARGB(216,0xFFFDF55F)
                        .addARGB(255,0xFFFFFDE0)
                        .build());

        buildMaterial(ModMaterialIds.ardite)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF89260C)
                        .addARGB(102,0xFF932810)
                        .addARGB(140,0xFF9D3012)
                        .addARGB(178,0xFFC04A16)
                        .addARGB(216,0xFFCD4418)
                        .addARGB(255,0xFFE35F35)
                        .build());

        buildMaterial(ModMaterialIds.bacium)
                .meleeHarvest().armor().arrowShaft().shell()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFFAF591E)
                        .addARGB(102,0xFFC0601D)
                        .addARGB(140,0xFFC96521)
                        .addARGB(178,0xFFD0681F)
                        .addARGB(216,0xFFE17022)
                        .addARGB(255,0xFFFC7F28)
                        .build());

        buildMaterial(ModMaterialIds.tempered_glass)
                .meleeHarvest().ranged().armor()
                .fallbacks("crystal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFFB0B1B1)
                        .addARGB(102,0xFFC2C2C2)
                        .addARGB(140,0xFFC9CACA)
                        .addARGB(216,0xFFD2D4D4)
                        .addARGB(255,0xFFEBEEEE)
                        .build());

        buildMaterial(ModMaterialIds.ochre_frogcroaking)
                .ranged()
                .fallbacks("wood")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFFD9AC46)
                        .addARGB(102,0xFFD9B86E)
                        .addARGB(140,0xFFF5DC98)
                        .addARGB(178,0xFFE9CE86)
                        .addARGB(216,0xFFF7DF92)
                        .addARGB(255,0xFFFAEFB2)
                        .build());

        buildMaterial(ModMaterialIds.verdant_frogcroaking)
                .meleeHarvest()
                .fallbacks("wood")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF84BB73)
                        .addARGB(102,0xFF93C584)
                        .addARGB(140,0xFFB3E0AE)
                        .addARGB(178,0xFFA0D39B)
                        .addARGB(216,0xFFBDE1B7)
                        .addARGB(255,0xFFD9EED7)
                        .build());

        buildMaterial(ModMaterialIds.pearlescent_frogcroaking)
                .statType(StatlessMaterialStats.BINDING, StatlessMaterialStats.BOWSTRING)
                .fallbacks("wood")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFFA37FA4)
                        .addARGB(102,0xFFC2A4C3)
                        .addARGB(140,0xFFC4A8B4)
                        .addARGB(178,0xFFD4BFC8)
                        .addARGB(216,0xFFE2D3D3)
                        .addARGB(255,0xFFEFE6E1)
                        .build());

        // tier4
        buildMaterial(ModMaterialIds.ancient_ceramic)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF0D435A)
                        .addARGB(102,0xFF1A6482)
                        .addARGB(140,0xFF236F8F)
                        .addARGB(178,0xFF2D7C9D)
                        .addARGB(216,0xFF3C8EB0)
                        .addARGB(255,0xFF4B9FC1)
                        .build());

        buildMaterial(ModMaterialIds.scarlet_slimestone)
                .meleeHarvest()
                .fallbacks("slime_wood","crystal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF1D070A)
                        .addARGB(102,0xFF41141E)
                        .addARGB(140,0xFF56212C)
                        .addARGB(178,0xFFA52548)
                        .addARGB(216,0xFFED494E)
                        .addARGB(255,0xFFF27D81)
                        .build());

        buildMaterial(ModMaterialIds.copshowium)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF962B07)
                        .addARGB(102,0xFFBB5407)
                        .addARGB(140,0xFFD9760D)
                        .addARGB(178,0xFFF86C25)
                        .addARGB(216,0xFFFF9727)
                        .addARGB(255,0xFFFFA900)
                        .build());

        buildMaterial(ModMaterialIds.stewium)
                .meleeHarvest().ranged().armor()
                .fallbacks("slime_metal","metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF8C421A)
                        .addARGB(102,0xFFAB5F36)
                        .addARGB(140,0xFFAB5F36)
                        .addARGB(178,0xFFBC6D42)
                        .addARGB(216,0xFFD28155)
                        .addARGB(255,0xFFE09267)
                        .build());

        buildMaterial(ModMaterialIds.burnt_ashes)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF43170A)
                        .addARGB(102,0xFF622412)
                        .addARGB(140,0xFF79280E)
                        .addARGB(178,0xFF8A2F13)
                        .addARGB(216,0xFF9A2E0C)
                        .addARGB(255,0xFFB4340C)
                        .build());

        buildMaterial(ModMaterialIds.solidified_emptiness)
                .meleeHarvest().ranged()
                .fallbacks("crystal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF00513F)
                        .addARGB(102,0xFF007259)
                        .addARGB(140,0xFF00AB86)
                        .addARGB(178,0xFF00CCA0)
                        .addARGB(216,0xFF00E7B5)
                        .addARGB(255,0xFF8FFFE7)
                        .build());

        buildMaterial(ModMaterialIds.warden_steel)
                .meleeHarvest().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF40576C)
                        .addARGB(102,0xFF6E757B)
                        .addARGB(140,0xFF819988)
                        .addARGB(178,0xFFA2AF86)
                        .addARGB(216,0xFFBBC39B)
                        .addARGB(255,0xFFD1D6B6)
                        .build());

        buildMaterial(ModMaterialIds.zith)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFFCD446F)
                        .addARGB(102,0xFFDB557E)
                        .addARGB(140,0xFFEA6E97)
                        .addARGB(178,0xFFE98FAB)
                        .addARGB(216,0xFFF793B1)
                        .addARGB(255,0xFFFE9FBF)
                        .build());

        buildMaterial(ModMaterialIds.bound_chain)
                .statType(StatlessMaterialStats.BINDING)
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF886D5A)
                        .addARGB(102,0xFFA4836C)
                        .addARGB(140,0xFFB99983)
                        .addARGB(178,0xFFBF9C81)
                        .addARGB(216,0xFFD0B096)
                        .addARGB(255,0xFFD2BCAA)
                        .build());

        buildMaterial(ModMaterialIds.shimmerslime)
                .meleeHarvest().ranged().armor()
                .fallbacks("slime_metal","metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFFA39B01)
                        .addARGB(102,0xFFC0B600)
                        .addARGB(140,0xFFDCD200)
                        .addARGB(178,0xFFF3E334)
                        .addARGB(216,0xFFFFF264)
                        .addARGB(255,0xFFFFF691)
                        .build());

        buildMaterial(ModMaterialIds.soul_vine)
                .maille().shieldCore()
                .fallbacks("wood")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF2D7826)
                        .addARGB(102,0xFF218F16)
                        .addARGB(140,0xFF089F06)
                        .addARGB(178,0xFF20B519)
                        .addARGB(216,0xFF4BC83D)
                        .addARGB(255,0xFF80CB80)
                        .build());

        buildMaterial(ModMaterialIds.colorite)
                .meleeHarvest().ranged()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF5D0000)
                        .addARGB(102,0xFF750000)
                        .addARGB(140,0xFFE88C08)
                        .addARGB(178,0xFFFAAB1C)
                        .addARGB(216,0xFF82D7D5)
                        .addARGB(255,0xFFFFFFFF)
                        .build());

        buildMaterial(ModMaterialIds.errorite)
                .meleeHarvest().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,0xFF120012)
                        .addARGB(102,0xFF300030)
                        .addARGB(140,0xFF4E004E)
                        .addARGB(178,0xFF6C006C)
                        .addARGB(216,0xFF9E009E)
                        .addARGB(255,0xFFD000D0)
                        .build());

        buildMaterial(ModMaterialIds.adamantium)
                .meleeHarvest().ranged().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, 0xFF5D0000)
                        .addARGB(102, 0xFF750000)
                        .addARGB(140, 0xFF820000)
                        .addARGB(178, 0xFFA00000)
                        .addARGB(216, 0xFFB80000)
                        .addARGB(255, 0xFFE82323)
                        .build());
    }

    @Override
    public @NotNull String getName() {
        return "TiT Materials";
    }
}
