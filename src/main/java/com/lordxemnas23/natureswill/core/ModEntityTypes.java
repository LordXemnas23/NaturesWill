package com.lordxemnas23.natureswill.core;

import com.lordxemnas23.natureswill.NaturesWill;
import com.lordxemnas23.natureswill.entities.spells.ThornWhipEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, NaturesWill.MOD_ID);
	
	public static final RegistryObject<EntityType<ThornWhipEntity>> THORN_WHIP = ENTITY_TYPES.register("thorn_whip",
			() -> EntityType.Builder.<ThornWhipEntity>create(ThornWhipEntity::new, EntityClassification.MISC).disableSerialization()
			.disableSummoning().size(0.4F, 0.4F).build(new ResourceLocation(NaturesWill.MOD_ID, "thorn_whip").toString()));
}
