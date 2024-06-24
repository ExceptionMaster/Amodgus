package dev.gallardo.amodgus.client.renderer;

import dev.gallardo.amodgus.Amodgus;
import dev.gallardo.amodgus.client.models.ImpostorModel;
import dev.gallardo.amodgus.entities.ImpostorEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ImpostorRenderer extends GeoEntityRenderer<ImpostorEntity> {
	
	public ImpostorRenderer(Context renderManager) {
		super(renderManager, new ImpostorModel());
		// TODO Auto-generated constructor stub
		this.shadowRadius = 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(ImpostorEntity object) {
		// TODO Apéndice de método generado automáticamente
		return new ResourceLocation(Amodgus.MODID, "textures/entity/amongus_black.png");
	}
		
}