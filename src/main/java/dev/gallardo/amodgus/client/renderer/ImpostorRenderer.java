package dev.gallardo.amodgus.client.renderer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.gallardo.amodgus.Amodgus;
import dev.gallardo.amodgus.client.models.ImpostorModel;
import dev.gallardo.amodgus.entities.ImpostorEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ImpostorRenderer extends GeoEntityRenderer<ImpostorEntity> {
	
	public ImpostorRenderer(Context renderManager) {
		super(renderManager, new ImpostorModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(ImpostorEntity object) {
		return new ResourceLocation(Amodgus.MODID, "textures/entities/amongus_black.png");
	}

}