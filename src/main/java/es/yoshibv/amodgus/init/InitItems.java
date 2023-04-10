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
    
    public static final RegistryObject<Item> RED_SUIT = ITEMS.register(
            "red_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> BLACK_SUIT = ITEMS.register(
            "black_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> WHITE_SUIT = ITEMS.register(
            "white_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> CYAN_SUIT = ITEMS.register(
            "cyan_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> PINK_SUIT = ITEMS.register(
            "pink_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> PURPLE_SUIT = ITEMS.register(
            "purple_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> MAGENTA_SUIT = ITEMS.register(
            "magenta_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> BLUE_SUIT = ITEMS.register(
            "blue_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> BROWN_SUIT = ITEMS.register(
            "brown_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> GREEN_SUIT = ITEMS.register(
            "green_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> LIME_SUIT = ITEMS.register(
            "lime_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> YELLOW_SUIT = ITEMS.register(
            "yellow_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    public static final RegistryObject<Item> ORANGE_SUIT = ITEMS.register(
            "orange_suit", () -> new Item(
                new Item.Properties().tab(AmodgusTab.AMODGUS_TAB))
            );
    
}
