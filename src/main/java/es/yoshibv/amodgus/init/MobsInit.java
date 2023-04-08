package es.yoshibv.amodgus.init;

import es.yoshibv.amodgus.Amodgus;
import es.yoshibv.amodgus.entities.AmongusEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MobsInit {
	public static final DeferredRegister<EntityType<?>> MOBS =
			DeferredRegister.create(ForgeRegistries.ENTITIES, Amodgus.MODID);
	
	public static final RegistryObject<EntityType<AmongusEntity>> AMONGUS = 
			MOBS.register("amongus", 
					() -> EntityType.Builder.of(AmongusEntity::new, MobCategory.CREATURE)
					.sized(0.4f, 1.0f)
					.build(new ResourceLocation(Amodgus.MODID,"amongus").toString()));
	
	public static void register(IEventBus eventBus) {
		MOBS.register(eventBus);
	}
}
