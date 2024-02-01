package com.creeping_creeper.tinkers_thinking.base.block.renderer;
import com.creeping_creeper.tinkers_thinking.base.block.DryingRackBlock;
import com.creeping_creeper.tinkers_thinking.base.block.entity.DryingRackBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
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
        pPoseStack.translate(0.5f,0.5f,0.345f);
        pPoseStack.scale(1f, 1f, 1f);
        switch (pBlockEntity.getBlockState().getValue(DryingRackBlock.FACING)){
        case NORTH -> pPoseStack.mulPose(Vector3f.YP.rotationDegrees(0));
        case EAST -> pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        case SOUTH -> pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180));
        case WEST -> pPoseStack.mulPose(Vector3f.YP.rotationDegrees(270));
        }
        renderer.renderStatic(itemStack, ItemTransforms.TransformType.GUI,
        getLightLevel(Objects.requireNonNull(pBlockEntity.getLevel()),pBlockEntity.getBlockPos()),
        OverlayTexture.NO_OVERLAY,pPoseStack,pBufferSource,1);
        pPoseStack.popPose();
        }
private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK,pos);
        int sLight = level.getBrightness(LightLayer.SKY,pos);
        return LightTexture.pack(bLight,sLight);
        }
}