package com.lordxemnas23.natureswill.entities.spells;

import java.util.UUID;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lordxemnas23.natureswill.NaturesWill;
import com.lordxemnas23.natureswill.core.ModEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ObjectHolder;

public class ThornWhipEntity extends Entity {
	private static final DataParameter<Integer> DATA_HOOKED_ENTITY = EntityDataManager.createKey(ThornWhipEntity.class, DataSerializers.VARINT);
	private int ticksInAir;
	public Entity caughtEntity;
	protected LivingEntity caster;
	private UUID casterId;
	private ThornWhipEntity.State currentState = ThornWhipEntity.State.FLYING;
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	public ThornWhipEntity(EntityType<? extends ThornWhipEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public ThornWhipEntity(EntityType<? extends ThornWhipEntity> type, double x, double y, double z, World worldIn) {
		this(type, worldIn);
	}
	
	public ThornWhipEntity(LivingEntity caster, World worldIn) {
		this(ModEntityTypes.THORN_WHIP.get(), caster.getPosX(), caster.getPosYEye() - (double) 0.1F, caster.getPosZ(), worldIn);
		this.caster = caster;
		this.casterId = caster.getUniqueID();
		float f = this.caster.rotationPitch;
	    float f1 = this.caster.rotationYaw;
	    float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
	    float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
	    float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
	    float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
	    double d0 = this.caster.getPosX() - (double)f3 * 0.3D;
	    double d1 = this.caster.getPosYEye();
	    double d2 = this.caster.getPosZ() - (double)f2 * 0.3D;
	    this.setLocationAndAngles(d0, d1, d2, f1, f);
	    Vec3d vec3d = new Vec3d((double)(-f3), (double)MathHelper.clamp(-(f5 / f4), -5.0F, 5.0F), (double)(-f2));
	    double d3 = vec3d.length();
	    vec3d = vec3d.mul(0.6D / d3 + 0.5D + this.rand.nextGaussian() * 0.0045D, 0.6D / d3 + 0.5D + this.rand.nextGaussian() * 0.0045D, 0.6D / d3 + 0.5D + this.rand.nextGaussian() * 0.0045D);
	    this.setMotion(vec3d);
	    this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));
	    this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)MathHelper.sqrt(horizontalMag(vec3d))) * (double)(180F / (float)Math.PI));
	    this.prevRotationYaw = this.rotationYaw;
	    this.prevRotationPitch = this.rotationPitch;
	}

	@Override
	protected void registerData() {
		this.getDataManager().register(DATA_HOOKED_ENTITY, 0);
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isInRangeToRenderDist(double dist) {
	    return dist < 4096.0D;
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	public void tick() {
		super.tick();
		
			if(this.currentState == ThornWhipEntity.State.FLYING) {
				if (!this.world.isRemote) {
					this.checkCollision();
		        }
				if (this.ticksInAir > 600) {
					this.remove();
					this.ticksInAir = 0;
					LOGGER.info("Removed due to timeout");
					return;
				}
				++this.ticksInAir;
			}
			
			
			this.move(MoverType.SELF, this.getMotion());
			this.updateRotation();
	        this.setMotion(this.getMotion().scale(0.92D));
	        this.recenterBoundingBox();
	}
	
	private void updateRotation() {
		Vec3d vec3d = this.getMotion();
	    float f = MathHelper.sqrt(horizontalMag(vec3d));
	    this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));

	    for(this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * (double)(180F / (float)Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
	         
	    }

	    while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
	       this.prevRotationPitch += 360.0F;
	    }

	    while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
	       this.prevRotationYaw -= 360.0F;
	    }

	    while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
	         this.prevRotationYaw += 360.0F;
	    }

	    this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
	    this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
	}
	
	private void checkCollision() {
		RayTraceResult raytraceresult = ProjectileHelper.rayTrace(this, this.getBoundingBox().expand(this.getMotion()).grow(1.0D), (entity) -> {
	         return !entity.isSpectator() && (entity.canBeCollidedWith() || entity instanceof ItemEntity) && (entity != this.caster || this.ticksInAir >= 5);
	    }, RayTraceContext.BlockMode.COLLIDER, true);
		
	    if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
	    	if (raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
	    		this.caughtEntity = ((EntityRayTraceResult)raytraceresult).getEntity();
	            this.setHookedEntity();
	            
	            if(this.caughtEntity.isLiving()) {
	            	this.onLivingEntityHit(this.caughtEntity);
	            	this.bringInHookedEntity();
		            this.remove();
		            LOGGER.info("Caught Entity");
	            } else {
	            	this.bringInHookedEntity();
		            this.remove();
		            LOGGER.info("Caught Entity");
	            } 
	        } else {
	            remove();
	            LOGGER.info("Hit block");
	        }
	    }
	}
	
	private void setHookedEntity() {
		this.getDataManager().set(DATA_HOOKED_ENTITY, this.caughtEntity.getEntityId() + 1);
	}
	
	protected void bringInHookedEntity() {
		if (this.caster != null) {
			Vec3d vec3d = (new Vec3d(this.caster.getPosX() - this.getPosX(), this.caster.getPosY() - this.getPosY(), this.caster.getPosZ() - this.getPosZ())).scale(0.1D);
	        this.caughtEntity.setMotion(this.caughtEntity.getMotion().add(vec3d));
	    }
	}
	
	protected void onLivingEntityHit(Entity caughtEntity2) {
		caughtEntity2.getEntity().attackEntityFrom(DamageSource.GENERIC,4.0F);
	}
	
	@Nullable
	public LivingEntity getCaster() {
		return this.caster;
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		
	}

	
	
	static enum State {
		FLYING,
	    HOOKED_IN_ENTITY;
	}

}
