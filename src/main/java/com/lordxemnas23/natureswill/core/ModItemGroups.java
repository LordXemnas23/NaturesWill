package com.lordxemnas23.natureswill.core;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.lordxemnas23.natureswill.NaturesWill;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class ModItemGroups {
	public static final ItemGroup instance = new NaturesWillCreativeTab(NaturesWill.MOD_ID, () -> new ItemStack(ModItems.NATURES_ESSENCE.get()));
	
	public static final class NaturesWillCreativeTab extends ItemGroup {
		@Nonnull
		private final Supplier<ItemStack> iconSupplier;
		
		public NaturesWillCreativeTab(String label, final Supplier<ItemStack> iconSupplier) {
			super(label);
			this.iconSupplier = iconSupplier;
		}

		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
	}
}
