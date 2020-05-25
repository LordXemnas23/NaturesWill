package com.lordxemnas23.natureswill;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lordxemnas23.natureswill.core.ModBlocks;
import com.lordxemnas23.natureswill.core.ModEntityTypes;
import com.lordxemnas23.natureswill.core.ModItemGroups;
import com.lordxemnas23.natureswill.core.ModItems;

@Mod("natureswill")
@Mod.EventBusSubscriber(modid = NaturesWill.MOD_ID, bus = Bus.MOD)
public class NaturesWill {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "natureswill";
	public static NaturesWill instance;

    public NaturesWill() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ModItems.ITEMS.register(modEventBus);
		ModBlocks.BLOCKS.register(modEventBus);
		ModEntityTypes.ENTITY_TYPES.register(modEventBus);
		
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(ModItemGroups.instance);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
	}

    private void setup(final FMLCommonSetupEvent event) {
        
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        
    }
    
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        
    }
}
