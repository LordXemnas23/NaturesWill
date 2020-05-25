package com.lordxemnas23.natureswill.core;

import com.lordxemnas23.natureswill.NaturesWill;
import com.lordxemnas23.natureswill.objects.blocks.ThornyVineBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, NaturesWill.MOD_ID);

	public static final RegistryObject<Block> THORNY_VINE = BLOCKS.register("thorny_vine", 
			() -> new ThornyVineBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement()
					.tickRandomly().hardnessAndResistance(0.2f).sound(SoundType.PLANT)));
}
