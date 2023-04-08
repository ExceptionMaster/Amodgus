package es.yoshibv.amodgus.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import es.yoshibv.amodgus.Amodgus;
import es.yoshibv.amodgus.entities.AmongusEntity;
import es.yoshibv.amodgus.init.MobsInit;

@Mod.EventBusSubscriber(modid = Amodgus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobsAttrsEvent {
	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event) {
		event.put(MobsInit.AMONGUS.get(), AmongusEntity.setAttributes());
	}
	
}
