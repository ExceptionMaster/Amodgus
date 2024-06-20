package dev.gallardo.amodgus;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.gallardo.amodgus.events.MobsAttrsEvent;
import dev.gallardo.amodgus.events.MobsRendererEvents;
import dev.gallardo.amodgus.init.CreativeTabInit;
import dev.gallardo.amodgus.init.ItemsInit;
import dev.gallardo.amodgus.init.MobsInit;
import dev.gallardo.amodgus.painting.ModPaintings;
import dev.gallardo.amodgus.sound.ModSounds;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

@Mod(Amodgus.MODID)
public class Amodgus {
    public static final String MODID = "amodgus";
    public static final Logger LOGGER = LogManager.getLogger();

    public Amodgus() {
    	GeckoLib.initialize();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemsInit.register(bus);
        MobsInit.register(bus);
        ModSounds.register(bus);
        ModPaintings.register(bus);
        CreativeTabInit.register(bus);
        EVENT_BUS.register(new MobsAttrsEvent());
        EVENT_BUS.register(new MobsRendererEvents());
    }
}
