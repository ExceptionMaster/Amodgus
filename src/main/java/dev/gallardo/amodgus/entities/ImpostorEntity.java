package dev.gallardo.amodgus.entities;

import org.jetbrains.annotations.Nullable;

import dev.gallardo.amodgus.common.AmongusAnimations;
import dev.gallardo.amodgus.entities.variant.AmongusVariant;
import dev.gallardo.amodgus.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ImpostorEntity extends Monster implements GeoAnimatable {
	private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
	
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(ImpostorEntity.class, EntityDataSerializers.INT);
	
	public ImpostorEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
		// TODO Apéndice de constructor generado automáticamente
	}
		
	public static AttributeSupplier setAttributes() {
		return TamableAnimal.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 14.00)
				.add(Attributes.ATTACK_DAMAGE,7.0f)
				.add(Attributes.ATTACK_SPEED,2.2f)
				.add(Attributes.MOVEMENT_SPEED, 0.5f).build();
	}
	
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(1, new FloatGoal(this));
	      this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
	      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 6.0f));
	      this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
	      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ImpostorEntity.class));
	      this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Player.class, true));
	      this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, AmongusEntity.class, false));
	}
	
	@Override
	public void registerControllers(ControllerRegistrar controllers) {
		// TODO Auto-generated method stub
		controllers.add(new AnimationController<>(this, "walk/idle", 0,
				state -> {
					if(state.isMoving()) {
						return state.setAndContinue(AmongusAnimations.WALK);
					} else {
						return state.setAndContinue(AmongusAnimations.IDLE);
					}
				}));
		controllers.add(new AnimationController<>(this, "death", 0,
				state -> {
					if(state.getAnimatable().isDeadOrDying()) {
						return state.setAndContinue(AmongusAnimations.DEATH);						
					}
					return PlayState.STOP;
				}));
	}
        
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.NETHERITE_BLOCK_PLACE, 0.15F, 1.0F);
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SHULKER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.AMONGUS_DEATH.get();
    }
        
    protected float getSoundVolume() {
        return 0.4F;
    }
		    
	@Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }
    
    /* VARIANTS */
    @SuppressWarnings("deprecation")
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_,
                                        @Nullable CompoundTag p_146750_) {
        AmongusVariant variant = AmongusVariant.BLACK;
        setVariant(variant);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }

    public AmongusVariant getVariant() {
        return AmongusVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(AmongusVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }


	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		// TODO Auto-generated method stub
		return this.factory;
	}

	@Override
	public double getTick(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
