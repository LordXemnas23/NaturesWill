package com.lordxemnas23.natureswill.objects.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ThornyVineBlock extends VineBlock {
	public ThornyVineBlock(Properties properties) {
		super(properties);
	}
	
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		state.getCollisionShape(worldIn, pos);
	}
}
