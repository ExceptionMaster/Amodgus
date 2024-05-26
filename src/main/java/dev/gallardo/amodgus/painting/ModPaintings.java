package dev.gallardo.amodgus.painting;

import dev.gallardo.amodgus.Amodgus;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
	public static final DeferredRegister<Motive> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Amodgus.MODID);
	
	public static final RegistryObject<Motive> IMPOSTOR = PAINTING_VARIANTS.register("impostor",
			() -> new Motive(64,32));
	
	public static void register(IEventBus bus) {
		PAINTING_VARIANTS.register(bus);
	}
}
