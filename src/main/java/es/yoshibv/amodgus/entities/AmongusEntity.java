package es.yoshibv.amodgus.entities;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import es.yoshibv.amodgus.entities.variant.AmongusVariant;
import es.yoshibv.amodgus.init.MobsInit;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.scores.Team;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class AmongusEntity extends TamableAnimal implements IAnimatable, NeutralMob {
	@SuppressWarnings("removal")
	private AnimationFactory factory = new AnimationFactory(this);
	
	private static final EntityDataAccessor<Boolean> SITTING = 
			SynchedEntityData.defineId(AmongusEntity.class, EntityDataSerializers.BOOLEAN);
	
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(AmongusEntity.class, EntityDataSerializers.INT);
		
	public AmongusEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
		super(entityType, world);
		// TODO Apéndice de constructor generado automáticamente
	}
	
	public static AttributeSupplier setAttributes() {
		return TamableAnimal.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 14.00)
				.add(Attributes.ATTACK_DAMAGE,6.0f)
				.add(Attributes.ATTACK_SPEED,1.8f)
				.add(Attributes.MOVEMENT_SPEED, 0.3f).build();
	}
	
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(1, new FloatGoal(this));
	      this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
	      this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
	      this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
	      this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
	      this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.2D, 8.0F, 2.0F, false));
	      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 6.0f));
	      this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
	      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	}
	
	@Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return MobsInit.AMONGUS.get().create(p_146743_);
    }
		
	@SuppressWarnings("removal")
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        if (this.isSitting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sitting", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }
		
	@Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<AmongusEntity>(this, "controller",
                0, this::predicate));
    }
	

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.NETHERITE_BLOCK_PLACE, 0.15F, 1.0F);
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.DOLPHIN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }
    
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        
        Item[] itemsForTaming = {
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","red_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","blue_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","cyan_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","green_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","lime_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","yellow_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","orange_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","pink_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","purple_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","magenta_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","black_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","white_toy")),
        		ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","brown_toy"))
        };

        if (!isTame()) {
            if (this.level.isClientSide) {
                return InteractionResult.CONSUME;
            } else {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!ForgeEventFactory.onAnimalTame(this, player)) {
                    if (!this.level.isClientSide) {
                    	if(getVariant() == AmongusVariant.RED && item == itemsForTaming[0]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.RED && item == itemsForTaming[0]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.BLUE && item == itemsForTaming[1]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.CYAN && item == itemsForTaming[2]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.GREEN && item == itemsForTaming[3]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.LIME && item == itemsForTaming[4]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.YELLOW && item == itemsForTaming[5]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.ORANGE && item == itemsForTaming[6]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.PINK && item == itemsForTaming[7]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.PURPLE && item == itemsForTaming[8]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.MAGENTA && item == itemsForTaming[9]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.BLACK && item == itemsForTaming[10]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.WHITE && item == itemsForTaming[11]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	} else if(getVariant() == AmongusVariant.BROWN && item == itemsForTaming[12]) {
                    		super.tame(player);
                    		this.navigation.recomputePath();
                    		this.setTarget(null);
                    		this.level.broadcastEntityEvent(this, (byte)7);
                    		setSitting(true);
                    	}
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }

        if(isTame() && !this.level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            setSitting(!isSitting());
            return InteractionResult.SUCCESS;
        }
        
        return super.mobInteract(player, hand);
    }
	
	
	public static boolean canSpawn(EntityType<AmongusEntity> entity, LevelAccessor levelAccess, 
			MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return checkAnimalSpawnRules(entity, levelAccess, spawnType, pos, random);
	}
    
	@Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setSitting(tag.getBoolean("isSitting"));
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("isSitting", this.isSitting());
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SITTING, false);
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    public void setSitting(boolean sitting) {
        this.entityData.set(SITTING, sitting);
        this.setOrderedToSit(sitting);
    }

    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }

    @Override
    public Team getTeam() {
        return super.getTeam();
    }

    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Override
    public void setTame(boolean tamed) {
        super.setTame(tamed);
        if (tamed) {
            getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(6.0D);
            getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)0.3f);
        } else {
            getAttribute(Attributes.MAX_HEALTH).setBaseValue(14.0D);
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.0D);
            getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)0.3f);
        }
    }
    
    /* VARIANTS */
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_,
                                        @Nullable CompoundTag p_146750_) {
        AmongusVariant variant = Util.getRandom(AmongusVariant.values(), this.random);
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
	public int getRemainingPersistentAngerTime() {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	@Override
	public void setRemainingPersistentAngerTime(int p_21673_) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public UUID getPersistentAngerTarget() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public void setPersistentAngerTarget(UUID p_21672_) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void startPersistentAngerTimer() {
		// TODO Apéndice de método generado automáticamente
		
	}
	
}
