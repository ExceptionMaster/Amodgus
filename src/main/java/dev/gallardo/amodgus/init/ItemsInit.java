package dev.gallardo.amodgus.init;

import dev.gallardo.amodgus.Amodgus;
import dev.gallardo.amodgus.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Amodgus.MODID);

	// ITEMS

	public static final RegistryObject<SwordItem> KNIFE = CreativeTabInit.addToTab(
			ITEMS.register("knife", () -> new SwordItem(Tiers.IRON, 2, -2.0f, new Item.Properties().stacksTo(1))));

	public static final RegistryObject<Item> EGGHAT = CreativeTabInit
			.addToTab(ITEMS.register("egghat", () -> new Item(new Item.Properties().stacksTo(1))));

	public static final RegistryObject<Item> AMONGUS_SPAWN_EGG = CreativeTabInit.addToTab(ITEMS.register(
			"amongus_spawn_egg",
			() -> new ForgeSpawnEggItem(MobsInit.AMONGUS, 0xb4202a, 0xb9bffb, new Item.Properties().stacksTo(1))));

	public static final RegistryObject<Item> IMPOSTOR_SPAWN_EGG = CreativeTabInit.addToTab(ITEMS.register(
			"impostor_spawn_egg",
			() -> new ForgeSpawnEggItem(MobsInit.IMPOSTOR, 0x3f474e, 0x1e1f26, new Item.Properties().stacksTo(1))));

	public static final RegistryObject<Item> RED_TOY = CreativeTabInit
			.addToTab(ITEMS.register("red_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> BLACK_TOY = CreativeTabInit
			.addToTab(ITEMS.register("black_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> WHITE_TOY = CreativeTabInit
			.addToTab(ITEMS.register("white_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> CYAN_TOY = CreativeTabInit
			.addToTab(ITEMS.register("cyan_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> PINK_TOY = CreativeTabInit
			.addToTab(ITEMS.register("pink_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> PURPLE_TOY = CreativeTabInit
			.addToTab(ITEMS.register("purple_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> MAGENTA_TOY = CreativeTabInit
			.addToTab(ITEMS.register("magenta_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> BLUE_TOY = CreativeTabInit
			.addToTab(ITEMS.register("blue_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> BROWN_TOY = CreativeTabInit
			.addToTab(ITEMS.register("brown_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> GREEN_TOY = CreativeTabInit
			.addToTab(ITEMS.register("green_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> LIME_TOY = CreativeTabInit
			.addToTab(ITEMS.register("lime_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> YELLOW_TOY = CreativeTabInit
			.addToTab(ITEMS.register("yellow_toy", () -> new Item(new Item.Properties().stacksTo(64))));
	
	public static final RegistryObject<Item> ORANGE_TOY = CreativeTabInit
			.addToTab(ITEMS.register("orange_toy", () -> new Item(new Item.Properties().stacksTo(64))));

	public static final RegistryObject<Item> FLASK = CreativeTabInit
			.addToTab(ITEMS.register("flask", () -> new Item(new Item.Properties().stacksTo(64))));

	public static final RegistryObject<Item> AMONGUS_DRIP_SONG_MUSIC_DISC = CreativeTabInit
			.addToTab(ITEMS.register("music_disc_amongus_drip",
					() -> new RecordItem(15, ModSounds.AMONGUS_DRIP, new Item.Properties().stacksTo(1), 1440)));

	public static void register(IEventBus bus) {
		ITEMS.register(bus);
		
	}
}
