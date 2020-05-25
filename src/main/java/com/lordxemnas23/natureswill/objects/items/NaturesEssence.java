package com.lordxemnas23.natureswill.objects.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lordxemnas23.natureswill.entities.spells.ThornWhipEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class NaturesEssence extends Item {
	private static final Logger LOGGER = LogManager.getLogger();
	
	public NaturesEssence(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		
		worldIn.addEntity(new ThornWhipEntity(playerIn, worldIn));
		LOGGER.info("ThornWhip created");
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
