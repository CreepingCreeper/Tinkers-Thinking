package com.creeping_creeper.tinkers_thinking.common.things.block.renderer;

import com.creeping_creeper.tinkers_thinking.common.things.block.DryingRackBlock;
import com.creeping_creeper.tinkers_thinking.common.things.block.entity.DryingRackBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.Objects;

public class DryingRackBlockEntityRenderer implements BlockEntityRenderer<DryingRackBlockEntity> {
public DryingRackBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        }
@Override
public void render(DryingRackBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.getRenderStack();
        pPoseStack.pushPose();
        pPoseStack.scale(1f, 1f, 1f);
        switch (pBlockEntity.getBlockState().getValue(DryingRackBlock.FACING)){
        case NORTH ->
                pPoseStack.translate(0.5f,0.5f,0.345f);
        case SOUTH -> {
                pPoseStack.translate(0.5f,0.5f,0.655f);
                pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
        }
        case WEST -> {
                pPoseStack.translate(0.345f,0.5f,0.5f);
                pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
        }
        case EAST -> {
                pPoseStack.translate(0.655f,0.5f,0.5f);
                pPoseStack.mulPose(Axis.YP.rotationDegrees(270));
        }
        }
        renderer.renderStatic(itemStack, ItemDisplayContext.FIXED,
        getLightLevel(Objects.requireNonNull(pBlockEntity.getLevel()),pBlockEntity.getBlockPos()),
        OverlayTexture.NO_OVERLAY,pPoseStack,pBufferSource,pBlockEntity.getLevel(),1);
        pPoseStack.popPose();
        }
private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK,pos);
        int sLight = level.getBrightness(LightLayer.SKY,pos);
        return LightTexture.pack(bLight,sLight);
        }
}
