package dev.gallardo.amodgus.client.models;

import dev.gallardo.amodgus.Amodgus;
import dev.gallardo.amodgus.client.renderer.AmongusRenderer;
import dev.gallardo.amodgus.entities.AmongusEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AmongusModel extends GeoModel<AmongusEntity> {
	@Override
	public ResourceLocation getModelResource(AmongusEntity amongusEntity) {
		return new ResourceLocation(Amodgus.MODID, "geo/amongus.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AmongusEntity amongusEntity) {
		return AmongusRenderer.LOCATION_BY_VARIANT.get(amongusEntity.getVariant());
	}

	@Override
	public ResourceLocation getAnimationResource(AmongusEntity amongusEntity) {
		return new ResourceLocation(Amodgus.MODID, "animations/amongus.animation.json");
	}
}