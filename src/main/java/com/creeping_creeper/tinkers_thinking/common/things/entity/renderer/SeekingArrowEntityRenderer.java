package com.creeping_creeper.tinkers_thinking.common.things.entity.renderer;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.entity.SeekingArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class SeekingArrowEntityRenderer extends ArrowRenderer<SeekingArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TinkersThinking.MODID, "textures/entity/seeking_arrow.png");
    public SeekingArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
    protected int getBlockLightLevel(SeekingArrowEntity entity, BlockPos pos) {
        return 15;
    }

    public ResourceLocation getTextureLocation(SeekingArrowEntity entity) {
        return TEXTURE;
    }
}