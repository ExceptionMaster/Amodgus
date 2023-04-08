/*package es.yoshibv.amodgus.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import es.yoshibv.amodgus.Amodgus;
import es.yoshibv.amodgus.init.MobsInit;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Amodgus.MODID, bus = Bus.FORGE)
public class MobsCommonEvents {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void biomeLoading(BiomeLoadingEvent event) {
		if(event.getName().equals(new ResourceLocation("minecraft:plains")))	{
		event.getSpawns().addSpawn(MobCategory.CREATURE, 
				new SpawnerData(MobsInit.AMONGUS.get(),5,1,7));
		}
	}
}*/
