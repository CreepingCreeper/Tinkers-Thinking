package com.creeping_creeper.tinkers_thinking.common.register;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.item.ModifiableRepeatingCrossbowItem;
import com.creeping_creeper.tinkers_thinking.common.things.item.ToolDefinitions;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableBowItem;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModToolItems extends ModModule {
    public static final RegistryObject<CreativeModeTab> tabTool = CREATIVE_TABS.register(
            "tool", () -> CreativeModeTab.builder().title(TinkersThinking.makeTranslation("itemGroup", "tool"))
                    .icon(() -> ModToolItems.paxel.get().getRenderTool())
                    .displayItems(ModToolItems::addTabItems)
                    .withTabsBefore(ModCommonItems.tab.getId())
                    .build());
    //Tools
    public static final ItemObject<ModifiableItem> paxel = ITEMS.register( "paxel", () -> new ModifiableItem(Stack1Item, ToolDefinitions.PAXEL));
    public static final ItemObject<ModifiableItem>  knife = ITEMS.register( "knife", () -> new ModifiableItem(Stack1Item, ToolDefinitions.KNIFE));
    public static final ItemObject<ModifiableBowItem> arrow_thrower = ITEMS.register("arrow_thrower", () -> new ModifiableBowItem(Stack1Item,  ToolDefinitions.ARROW_THROWER,true));
    public static final ItemObject<ModifiableItem>  mace = ITEMS.register( "mace", () -> new ModifiableItem(Stack1Item, ToolDefinitions.MACE));
    public static final ItemObject<ModifiableSwordItem> cutlass = ITEMS.register( "cutlass", () -> new ModifiableSwordItem(Stack1Item, ToolDefinitions.CUTLASS));
    public static final ItemObject<ModifiableRepeatingCrossbowItem>  repeating_crossbow = ITEMS.register( "repeating_crossbow", () -> new ModifiableRepeatingCrossbowItem(Stack1Item,ToolDefinitions.REPEATING_CROSSBOW));
    public static final ItemObject<ModifiableItem> magma_staff = ITEMS.register("magma_staff", () -> new ModifiableItem(Stack1Item, ToolDefinitions.MAGMA_STAFF));
    public static final ItemObject<ModifiableItem> clay_staff = ITEMS.register("clay_staff", () -> new ModifiableItem(Stack1Item, ToolDefinitions.CLAY_STAFF));
    public static final ItemObject<ModifiableItem> quartz_staff = ITEMS.register("quartz_staff", () -> new ModifiableItem(Stack1Item, ToolDefinitions.QUARTZ_STAFF));
    public static final ItemObject<ModifiableItem> seared_bucket = ITEMS.register("seared_bucket", () -> new ModifiableItem(Stack1Item, ToolDefinitions.SEARED_BUCKET));
    public static final ItemObject<ModifiableItem> tinkers_bronze_bucket = ITEMS.register("tinkers_bronze_bucket", () -> new ModifiableItem(Stack1Item, ToolDefinitions.TINKERS_BRONZE_BUCKET));
    public static final ItemObject<ModifiableItem> battle_bucket = ITEMS.register("battle_bucket", () -> new ModifiableItem(Stack1Item.fireResistant(), ToolDefinitions.BATTLE_BUCKET));
    public static final ItemObject<ToolPartItem> narrow_blade = ITEMS.register("narrow_blade", () -> new ToolPartItem(GENERAL_PROPS, HeadMaterialStats.ID));
    public static final CastItemObject narrow_blade_cast = ITEMS.registerCast(narrow_blade,GENERAL_PROPS);
    @Deprecated(forRemoval = true)
    public static final ItemObject<Item> seeking_arrow = ITEMS.register("seeking_arrow", GENERAL_PROPS);
    @Deprecated(forRemoval = true)
    public static final ItemObject<Item> roving_arrow = ITEMS.register("roving_arrow", GENERAL_PROPS);
    private static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        Consumer<ItemStack> tab = output::accept;
        acceptTool(tab,paxel);
        acceptTool(tab,knife);
        acceptTool(tab,arrow_thrower);
        acceptTool(tab,mace);
        acceptTool(tab,cutlass);
        acceptTool(tab,repeating_crossbow);
        acceptTool(tab,clay_staff);
        acceptTool(tab,quartz_staff);
        acceptTool(tab,magma_staff);
        acceptTool(tab,seared_bucket);
        acceptTool(tab,tinkers_bronze_bucket);
        acceptTool(tab,battle_bucket);
        acceptPart(tab,narrow_blade);
        tab.accept(narrow_blade_cast.get().getDefaultInstance());
        tab.accept(narrow_blade_cast.getSand().getDefaultInstance());
        tab.accept(narrow_blade_cast.getRedSand().getDefaultInstance());
    }
    private static void acceptTool(Consumer<ItemStack> output, Supplier<? extends IModifiable> tool) {
        ToolBuildHandler.addVariants(output, tool.get(),"");
    }
    private static void acceptPart(Consumer<ItemStack> output, Supplier<? extends IMaterialItem> item) {
        item.get().addVariants(output,"");
    }
}
