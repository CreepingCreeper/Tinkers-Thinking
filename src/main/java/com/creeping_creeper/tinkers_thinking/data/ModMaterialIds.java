package com.creeping_creeper.tinkers_thinking.data;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class ModMaterialIds {
    // tier 1
    public static final MaterialId sugar_cane = id("sugar_cane");
    public static final MaterialId sponge = id("sponge");
    public static final MaterialId netherrack = id("netherrack");

    // tier 2
    public static final MaterialId dusk = id("dusk");
    public static final MaterialId stabilized_gunpowder = id("stabilized_gunpowder");

    // tier 3
    public static final MaterialId obsidian_bronze = id("obsidian_bronze");
    public static final MaterialId beetron = id("beetron");
    public static final MaterialId shroomite = id("shroomite");
    public static final MaterialId shroomite_compound = id("shroomite_compound");
    public static final MaterialId tinkers_bronze = id("tinkers_bronze");
    public static final MaterialId white_chocolate = id("white_chocolate");
    public static final MaterialId black_chocolate = id("black_chocolate");
    public static final MaterialId chillslime_cryogel = id("chillslime_cryogel");
    public static final MaterialId electrical_steel = id("electrical_steel");
    public static final MaterialId lightite = id("lightite");
    public static final MaterialId lightite_compound = id("lightite_compound");
    public static final MaterialId metherite = id("metherite");
    public static final MaterialId spectre = id("spectre");
    public static final MaterialId spectre_compound = id("spectre_compound");
    public static final MaterialId chlorophyte = id("chlorophyte");
    public static final MaterialId chlorophyte_compound = id("chlorophyte_compound");
    public static final MaterialId gilded_silky_cloth = id("gilded_silky_cloth");
    public static final MaterialId ardite = id("ardite");
    public static final MaterialId bacium = id("bacium");
    public static final MaterialId tempered_glass = id("tempered_glass");
    public static final MaterialId ochre_frogcroaking = id("ochre_frogcroaking");
    public static final MaterialId verdant_frogcroaking = id("verdant_frogcroaking");
    public static final MaterialId pearlescent_frogcroaking = id("pearlescent_frogcroaking");

    // tier 4
    public static final MaterialId copshowium = id("copshowium");
    public static final MaterialId echo_bronze = id("echo_bronze");
    public static final MaterialId scarlet_slimestone = id("scarlet_slimestone");
    public static final MaterialId burnt_ashes = id("burnt_ashes");
    public static final MaterialId solidified_emptiness = id("solidified_emptiness");
    public static final MaterialId stewium = id("stewium");
    public static final MaterialId warden_steel = id("warden_steel");
    public static final MaterialId ancient_ceramic = id("ancient_ceramic");

    public static final MaterialId zith = id("zith");
    public static final MaterialId bound_chain = id("bound_chain");
    public static final MaterialId shimmerslime = id("shimmerslime");
    public static final MaterialId soul_vine = id("soul_vine");
    public static final MaterialId colorite = id("colorite");
    public static final MaterialId errorite = id("errorite");
    public static final MaterialId adamantium = id("adamantium");


    private static MaterialId id(String name) {
        return new MaterialId(TinkersThinking.MODID, name);
    }
}
