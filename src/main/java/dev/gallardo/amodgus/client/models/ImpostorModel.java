package dev.gallardo.amodgus.client.models;

import dev.gallardo.amodgus.Amodgus;
import dev.gallardo.amodgus.client.renderer.AmongusRenderer;
import dev.gallardo.amodgus.entities.ImpostorEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ImpostorModel extends GeoModel<ImpostorEntity> {

	@Override
	public ResourceLocation getModelResource(ImpostorEntity object) {
		// TODO Apéndice de método generado automáticamente
		return new ResourceLocation(Amodgus.MODID, "geo/amongus.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ImpostorEntity object) {
		// TODO Apéndice de método generado automáticamente
		return AmongusRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
	}
	
	@Override
	public ResourceLocation getAnimationResource(ImpostorEntity animatable) {
		// TODO Apéndice de método generado automáticamente
		return new ResourceLocation(Amodgus.MODID, "animations/amongus.animation.json");
	}
	
}