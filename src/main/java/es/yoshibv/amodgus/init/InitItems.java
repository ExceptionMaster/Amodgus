package es.yoshibv.amodgus.init;

import es.yoshibv.amodgus.Amodgus;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
        ForgeRegistries.ITEMS, Amodgus.MODID
    	);
    public static final RegistryObject<SwordItem> KNIFE = ITEMS.register(
        "knife", () -> new SwordItem(
            Tiers.IRON, 
            2, 
            -2.0f, 
            new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
    	);
    
    public static final RegistryObject<Item> EGGHAT = ITEMS.register(
        "egghat", () -> new Item(
            new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
        );
    
    public static final RegistryObject<Item> AMONGUS_SPAWN_EGG = ITEMS.register(
    	"amongus_spawn_egg", () -> new ForgeSpawnEggItem(
    		MobsInit.AMONGUS, 0xb4202a, 0xb9bffb, new Item.Properties().tab(AmodgusTab.AMODGUS_TAB).stacksTo(1))
        );
}
