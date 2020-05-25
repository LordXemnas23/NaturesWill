package com.lordxemnas23.natureswill.client.model;

import com.lordxemnas23.natureswill.entities.spells.ThornWhipEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ThornWhipTipModel extends EntityModel<ThornWhipEntity> {
	private final ModelRenderer Stem;
	private final ModelRenderer StemNeck;
	private final ModelRenderer StemTip;

	public ThornWhipTipModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 32;
		textureHeight = 32;

		Stem = new ModelRenderer(this);
		Stem.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		StemNeck = new ModelRenderer(this);
		StemNeck.setRotationPoint(0.0F, 0.0F, 0.0F);
		Stem.addChild(StemNeck);
		StemNeck.setTextureOffset(0, 0).addBox(-1.0F, -6.0F, -8.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);
		StemNeck.setTextureOffset(0, 10).addBox(0.0F, -6.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		StemNeck.setTextureOffset(11, 10).addBox(-1.0F, -7.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		StemNeck.setTextureOffset(4, 6).addBox(-2.0F, -6.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		StemNeck.setTextureOffset(0, 6).addBox(1.0F, -5.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		StemNeck.setTextureOffset(4, 3).addBox(-1.0F, -4.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		StemTip = new ModelRenderer(this);
		StemTip.setRotationPoint(0.0F, 0.0F, 0.0F);
		Stem.addChild(StemTip);
		StemTip.setTextureOffset(0, 0).addBox(-1.0F, -6.0F, 3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		StemTip.setTextureOffset(7, 10).addBox(0.0F, -5.0F, 3.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		StemTip.setTextureOffset(0, 3).addBox(0.0F, -6.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ThornWhipEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Stem.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
