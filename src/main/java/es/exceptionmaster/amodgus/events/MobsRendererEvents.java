package es.exceptionmaster.amodgus.events;

import es.exceptionmaster.amodgus.Amodgus;
import es.exceptionmaster.amodgus.client.renderer.AmongusRenderer;
import es.exceptionmaster.amodgus.client.renderer.ImpostorRenderer;
import es.exceptionmaster.amodgus.init.MobsInit;
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
