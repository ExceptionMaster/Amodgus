package dev.gallardo.amodgus.init;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import dev.gallardo.amodgus.Amodgus;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabInit {
	public static final List<Supplier<? extends ItemLike>> TAB_ITEMS = new ArrayList<>();
	
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(
    		Registries.CREATIVE_MODE_TAB, Amodgus.MODID
	);
	
	public static final RegistryObject<CreativeModeTab> AMODGUS_TAB = TABS.register(
    		"amodgus",
    		() -> CreativeModeTab.builder()
    				.title(Component.translatable("itemGroup.Amodgus"))
    				.icon(ItemsInit.KNIFE.get()::getDefaultInstance)
    				.displayItems((displayParams, output) -> 
    					TAB_ITEMS.forEach(item -> output.accept(item.get())))
    				.build()
    );
	
	public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> item) {
		TAB_ITEMS.add(item);
		return item;
	}
	
	public static void register(IEventBus eventBus) {
		TABS.register(eventBus);
	}
}