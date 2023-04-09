package es.yoshibv.amodgus.events;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import es.yoshibv.amodgus.Amodgus;
import es.yoshibv.amodgus.entities.AmongusEntity;
import es.yoshibv.amodgus.init.MobsInit;

@Mod.EventBusSubscriber(modid = Amodgus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobsAttrsEvent {
	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> SpawnPlacements.register(MobsInit.AMONGUS.get(), 
				SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, AmongusEntity::canSpawn));
	}
	
	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event) {
		event.put(MobsInit.AMONGUS.get(), AmongusEntity.setAttributes());
	}
	
}
