package com.creeping_creeper.tinkers_thinking.common.things.entity.renderer;

import com.creeping_creeper.tinkers_thinking.TinkersThinking;
import com.creeping_creeper.tinkers_thinking.common.things.entity.ThrownBoomerang;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import slimeknights.tconstruct.library.TinkerItemDisplays;

public class ThrownBoomerangRenderer extends EntityRenderer<ThrownBoomerang> {
    protected final ItemRenderer itemRenderer;
    public ThrownBoomerangRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ThrownBoomerang entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(yaw + 90));
        poseStack.mulPose(Axis.ZP.rotationDegrees((entity.tickCount + partialTicks) * 30 % 360));
        poseStack.translate(-0.03125, -0.09375, 0);
        this.itemRenderer.renderStatic(entity.getDisplayTool(), TinkerItemDisplays.TABLE , packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), entity.getId());
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownBoomerang entity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}

