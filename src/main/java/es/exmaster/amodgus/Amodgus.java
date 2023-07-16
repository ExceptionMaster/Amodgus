package es.exmaster.amodgus;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.exmaster.amodgus.events.MobsAttrsEvent;
import es.exmaster.amodgus.events.MobsRendererEvents;
import es.exmaster.amodgus.init.InitItems;
import es.exmaster.amodgus.init.MobsInit;
import es.exmaster.amodgus.painting.ModPaintings;
import es.exmaster.amodgus.sound.ModSounds;

@Mod(Amodgus.MODID)
public class Amodgus {
    public static final String MODID = "amodgus";
    public static final Logger LOGGER = LogManager.getLogger();

    public Amodgus() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        InitItems.ITEMS.register(bus);
        EVENT_BUS.register(new MobsAttrsEvent());
        EVENT_BUS.register(new MobsRendererEvents());
        MobsInit.register(bus);
        GeckoLib.initialize();
        ModSounds.register(bus);
        ModPaintings.register(bus);
    }
}
