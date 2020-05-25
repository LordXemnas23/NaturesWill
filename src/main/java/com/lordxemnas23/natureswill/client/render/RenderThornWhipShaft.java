package com.lordxemnas23.natureswill.client.render;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lordxemnas23.natureswill.NaturesWill;
import com.lordxemnas23.natureswill.entities.spells.ThornWhipEntity;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderThornWhipShaft extends EntityRenderer<ThornWhipEntity> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(NaturesWill.MOD_ID, "textures/entity/thorn_whip.png");
	protected static final RenderType STEM = RenderType.getEntityCutout(TEXTURE);
	private static final Logger LOGGER = LogManager.getLogger();
	
	public RenderThornWhipShaft(EntityRendererManager renderManager) {
		super(renderManager);
		LOGGER.info("render");
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
	         IVertexBuilder ivertexbuilder = bufferIn.getBuffer(STEM);
	         func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 0, 0, 1);
	         func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 0, 1, 1);
	         func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 1, 1, 0);
	         func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 1, 0, 0);
	         matrixStackIn.pop();
	         int i = caster.getPrimaryHand() == HandSide.RIGHT ? 1 : -1;
	         ItemStack itemstack = caster.getHeldItemMainhand();
	         if (!(itemstack.getItem() instanceof net.minecraft.item.FishingRodItem)) {
	            i = -i;
	         }

	         float f = caster.getSwingProgress(partialTicks);
	         float f1 = MathHelper.sin(MathHelper.sqrt(f) * (float)Math.PI);
	         float f2 = MathHelper.lerp(partialTicks, caster.prevRenderYawOffset, caster.renderYawOffset) * ((float)Math.PI / 180F);
	         double d0 = (double)MathHelper.sin(f2);
	         double d1 = (double)MathHelper.cos(f2);
	         double d2 = (double)i * 0.35D;
	         double d3 = 0.8D;
	         double d4;
	         double d5;
	         double d6;
	         float f3;
	         if ((this.renderManager.options == null || this.renderManager.options.thirdPersonView <= 0) && caster == Minecraft.getInstance().player) {
	            double d7 = this.renderManager.options.fov;
	            d7 = d7 / 100.0D;
	            Vec3d vec3d = new Vec3d((double)i * -0.36D * d7, -0.045D * d7, 0.4D);
	            vec3d = vec3d.rotatePitch(-MathHelper.lerp(partialTicks, caster.prevRotationPitch, caster.rotationPitch) * ((float)Math.PI / 180F));
	            vec3d = vec3d.rotateYaw(-MathHelper.lerp(partialTicks, caster.prevRotationYaw, caster.rotationYaw) * ((float)Math.PI / 180F));
	            vec3d = vec3d.rotateYaw(f1 * 0.5F);
	            vec3d = vec3d.rotatePitch(-f1 * 0.7F);
	            d4 = MathHelper.lerp((double)partialTicks, caster.prevPosX, caster.getPosX()) + vec3d.x;
	            d5 = MathHelper.lerp((double)partialTicks, caster.prevPosY, caster.getPosY()) + vec3d.y;
	            d6 = MathHelper.lerp((double)partialTicks, caster.prevPosZ, caster.getPosZ()) + vec3d.z;
	            f3 = caster.getEyeHeight();
	         } else {
	            d4 = MathHelper.lerp((double)partialTicks, caster.prevPosX, caster.getPosX()) - d1 * d2 - d0 * 0.8D;
	            d5 = caster.prevPosY + (double)caster.getEyeHeight() + (caster.getPosY() - caster.prevPosY) * (double)partialTicks - 0.45D;
	            d6 = MathHelper.lerp((double)partialTicks, caster.prevPosZ, caster.getPosZ()) - d0 * d2 + d1 * 0.8D;
	            f3 = caster.isCrouching() ? -0.1875F : 0.0F;
	         }

	         double d9 = MathHelper.lerp((double)partialTicks, whip.prevPosX, whip.getPosX());
	         double d10 = MathHelper.lerp((double)partialTicks, whip.prevPosY, whip.getPosY()) + 0.25D;
	         double d8 = MathHelper.lerp((double)partialTicks, whip.prevPosZ, whip.getPosZ());
	         float f4 = (float)(d4 - d9);
	         float f5 = (float)(d5 - d10) + f3;
	         float f6 = (float)(d6 - d8);
	         IVertexBuilder ivertexbuilder1 = bufferIn.getBuffer(RenderType.getLines());
	         Matrix4f matrix4f1 = matrixStackIn.getLast().getMatrix();
	         int j = 16;

	         for(int k = 0; k < 16; ++k) {
	            func_229104_a_(f4, f5, f6, ivertexbuilder1, matrix4f1, func_229105_a_(k, 16));
	            func_229104_a_(f4, f5, f6, ivertexbuilder1, matrix4f1, func_229105_a_(k + 1, 16));
	         }

	         matrixStackIn.pop();
	         super.render(whip, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}
	}

	private static float func_229105_a_(int p_229105_0_, int p_229105_1_) {
		return (float)p_229105_0_ / (float)p_229105_1_;
	}

	private static void func_229106_a_(IVertexBuilder p_229106_0_, Matrix4f p_229106_1_, Matrix3f p_229106_2_, int p_229106_3_, float p_229106_4_, int p_229106_5_, int p_229106_6_, int p_229106_7_) {
		p_229106_0_.pos(p_229106_1_, p_229106_4_ - 0.5F, (float)p_229106_5_ - 0.5F, 0.0F).color(255, 255, 255, 255).tex((float)p_229106_6_, (float)p_229106_7_).overlay(OverlayTexture.NO_OVERLAY).lightmap(p_229106_3_).normal(p_229106_2_, 0.0F, 1.0F, 0.0F).endVertex();
	}

	private static void func_229104_a_(float p_229104_0_, float p_229104_1_, float p_229104_2_, IVertexBuilder p_229104_3_, Matrix4f p_229104_4_, float p_229104_5_) {
	     p_229104_3_.pos(p_229104_4_, p_229104_0_ * p_229104_5_, p_229104_1_ * (p_229104_5_ * p_229104_5_ + p_229104_5_) * 0.5F + 0.25F, p_229104_2_ * p_229104_5_).color(0, 0, 0, 255).endVertex();
	}
	
	@Override
	public ResourceLocation getEntityTexture(ThornWhipEntity entity) {
		return TEXTURE;
	}
}
