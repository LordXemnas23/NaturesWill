package com.lordxemnas23.natureswill.util;

import com.lordxemnas23.natureswill.NaturesWill;
import com.lordxemnas23.natureswill.client.render.RenderThornWhipTip;
import com.lordxemnas23.natureswill.core.ModBlocks;
import com.lordxemnas23.natureswill.core.ModEntityTypes;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NaturesWill.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(ModBlocks.THORNY_VINE.get(), RenderType.getCutout());
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.THORN_WHIP.get(), RenderThornWhipTip::new);
	}
}
