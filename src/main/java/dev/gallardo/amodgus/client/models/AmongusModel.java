package dev.gallardo.amodgus.client.models;

import dev.gallardo.amodgus.Amodgus;
import dev.gallardo.amodgus.client.renderer.AmongusRenderer;
import dev.gallardo.amodgus.entities.AmongusEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AmongusModel extends GeoModel<AmongusEntity> {

	@Override
	public ResourceLocation getModelResource(AmongusEntity object) {
		// TODO Apéndice de método generado automáticamente
		return new ResourceLocation(Amodgus.MODID, "geo/amongus.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AmongusEntity object) {
		// TODO Apéndice de método generado automáticamente
		return AmongusRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
	}
	
	@Override
	public ResourceLocation getAnimationResource(AmongusEntity animatable) {
		// TODO Apéndice de método generado automáticamente
		return new ResourceLocation(Amodgus.MODID, "animations/amongus.animation.json");
	}
	
}