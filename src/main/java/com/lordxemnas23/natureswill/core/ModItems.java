package com.lordxemnas23.natureswill.core;

import com.lordxemnas23.natureswill.NaturesWill;
import com.lordxemnas23.natureswill.objects.items.NaturesEssence;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, NaturesWill.MOD_ID);
	
	public static final RegistryObject<Item> NATURES_ESSENCE = ITEMS.register("natures_essence", 
			() -> new NaturesEssence(new Item.Properties().group(ModItemGroups.instance)));
}
