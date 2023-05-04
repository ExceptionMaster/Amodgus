package es.yoshibv.amodgus.events;

import es.yoshibv.amodgus.Amodgus;
import es.yoshibv.amodgus.client.renderer.AmongusRenderer;
import es.yoshibv.amodgus.client.renderer.ImpostorRenderer;
import es.yoshibv.amodgus.init.MobsInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=Amodgus.MODID,bus=Mod.EventBusSubscriber.Bus.MOD, value=Dist.CLIENT)
public class MobsRendererEvents {
	
	@SubscribeEvent
	public static void entityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MobsInit.AMONGUS.get(),AmongusRenderer::new);
		event.registerEntityRenderer(MobsInit.IMPOSTOR.get(),ImpostorRenderer::new);
	}
}
