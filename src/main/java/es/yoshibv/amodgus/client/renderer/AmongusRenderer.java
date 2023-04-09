package es.yoshibv.amodgus.client.renderer;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import es.yoshibv.amodgus.Amodgus;
import es.yoshibv.amodgus.client.models.AmongusModel;
import es.yoshibv.amodgus.entities.AmongusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AmongusRenderer extends GeoEntityRenderer<AmongusEntity> {

	public AmongusRenderer(Context renderManager) {
		super(renderManager, new AmongusModel());
		// TODO Apéndice de constructor generado automáticamente
		this.shadowRadius = 0.5f;
	}
	
	@Override
	public ResourceLocation getTextureLocation(AmongusEntity object) {
		// TODO Apéndice de método generado automáticamente
		return new ResourceLocation(Amodgus.MODID, "textures/entities/amongus.png");
	}
	
	@Override
    public RenderType getRenderType(AmongusEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.0f, 1.0f, 1.0f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

}