package com.creeping_creeper.tinkers_thinking.common.client;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.mantle.client.book.transformer.BookTransformer;
import slimeknights.tconstruct.library.client.book.sectiontransformer.ModifierTagInjectorTransformer;
import slimeknights.tconstruct.library.client.book.sectiontransformer.ToolSectionTransformer;
import slimeknights.tconstruct.library.client.book.sectiontransformer.ToolTagInjectorTransformer;
import slimeknights.tconstruct.library.client.book.sectiontransformer.materials.TierRangeMaterialSectionTransformer;

public class ModBooks extends BookData {
    public static final ResourceLocation FANTASTIC_GADGETRY_ID = TinkersThinking.getResource("fantastic_gadgetry");
    public static final BookData FANTASTIC_GADGETRY = BookLoader.registerBook(FANTASTIC_GADGETRY_ID,    false, false);
    public static void initBook() {
        BookLoader.registerGsonTypeAdapter(Component.class, new Component.Serializer());
        addStandardData(FANTASTIC_GADGETRY, FANTASTIC_GADGETRY_ID);
    }
    private static void addStandardData(BookData book, ResourceLocation id) {
        book.addRepository(new FileRepository(new ResourceLocation(id.getNamespace(), "book/" + id.getPath())));
        book.addTransformer(BookTransformer.indexTranformer());
        book.addTransformer(TierRangeMaterialSectionTransformer.INSTANCE);
        // padding needs to be last to ensure page counts are right
        book.addTransformer(BookTransformer.paddingTransformer());
    }
    public static BookData getBook() {
        return FANTASTIC_GADGETRY;
    }
}
