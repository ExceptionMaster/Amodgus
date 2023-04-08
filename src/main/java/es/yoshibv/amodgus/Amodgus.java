package es.yoshibv.amodgus;

import es.yoshibv.amodgus.events.MobsAttrsEvent;
import es.yoshibv.amodgus.events.MobsRendererEvents;
import es.yoshibv.amodgus.init.InitItems;
import es.yoshibv.amodgus.init.MobsInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod(Amodgus.MODID)
public class Amodgus {
    public static final String MODID = "amodgus";

    public Amodgus() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        InitItems.ITEMS.register(bus);
        EVENT_BUS.register(new MobsAttrsEvent());
        EVENT_BUS.register(new MobsRendererEvents());
        MobsInit.register(bus);
        GeckoLib.initialize();
        
    }
}
