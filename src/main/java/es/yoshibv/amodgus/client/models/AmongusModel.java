package es.yoshibv.amodgus.client.models;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import es.yoshibv.amodgus.Amodgus;
import es.yoshibv.amodgus.client.renderer.AmongusRenderer;
import es.yoshibv.amodgus.entities.AmongusEntity;

public class AmongusModel extends AnimatedGeoModel<AmongusEntity> {

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