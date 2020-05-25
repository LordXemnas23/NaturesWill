package com.lordxemnas23.natureswill.client.render;

import com.lordxemnas23.natureswill.NaturesWill;
import com.lordxemnas23.natureswill.client.model.ThornWhipTipModel;
import com.lordxemnas23.natureswill.entities.spells.ThornWhipEntity;
import com.lordxemnas23.natureswill.objects.items.NaturesEssence;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix3f;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RenderThornWhipTip extends EntityRenderer<ThornWhipEntity>{
	protected static final ResourceLocation TEXTURE = new ResourceLocation(NaturesWill.MOD_ID, "textures/entity/thorn_whip_tip.png");
	protected static final RenderType CUTOUT = RenderType.getEntityCutout(TEXTURE);
	final ThornWhipTipModel model = new ThornWhipTipModel();
	
	public RenderThornWhipTip(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void render(ThornWhipEntity whip, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		LivingEntity caster = whip.getCaster();
		
		if (caster != null) {
	         matrixStackIn.push();
	         matrixStackIn.push();
	         matrixStackIn.scale(0.5F, 0.5F, 0.5F);
	         matrixStackIn.rotate(this.renderManager.getCameraOrientation());
	         matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
	         MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();
	         Matrix4f matrix4f = matrixstack$entry.getMatrix();
	         Matrix3f matrix3f = matrixstack$entry.getNormal();
	         IVertexBuilder ivertexbuilder = bufferIn.getBuffer(CUTOUT);

	         model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	         matrixStackIn.pop();
	         super.render(whip, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}
	}

	@Override
	public ResourceLocation getEntityTexture(ThornWhipEntity entity) {
		return new ResourceLocation(NaturesWill.MOD_ID, "textures/entity/thorn_whip_tip.png");
	}

}
