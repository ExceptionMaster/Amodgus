package es.yoshibv.amodgus.client.models;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import es.yoshibv.amodgus.Amodgus;
import es.yoshibv.amodgus.entities.AmongusEntity;

public class AmongusModel extends AnimatedGeoModel<AmongusEntity> {

	@Override
	public ResourceLocation getModelLocation(AmongusEntity object) {
		// TODO Apéndice de método generado automáticamente
		return new ResourceLocation(Amodgus.MODID, "geo/amongus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(AmongusEntity object) {
		// TODO Apéndice de método generado automáticamente
		return new ResourceLocation(Amodgus.MODID, "textures/entities/amongus_red.png");
	}
	
	@Override
	public ResourceLocation getAnimationFileLocation(AmongusEntity animatable) {
		// TODO Apéndice de método generado automáticamente
		return new ResourceLocation(Amodgus.MODID, "animations/amongus.animation.json");
	}
	
}