package es.yoshibv.amodgus.entities;

import java.util.Random;
import java.util.UUID;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
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
	
	public AmongusEntity(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
		// TODO Apéndice de constructor generado automáticamente
	}
	
	public static AttributeSupplier setAttributes() {
		return Mob.createMobAttributes()
				.add(ForgeMod.ENTITY_GRAVITY.get(),1.0f)
				.add(Attributes.MAX_HEALTH, 20.00)
				.add(Attributes.ATTACK_DAMAGE,6.0f)
				.add(Attributes.ATTACK_SPEED,1.8f)
				.add(Attributes.MOVEMENT_SPEED, 0.5f).build();
				
	}
	
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(1, new FloatGoal(this));
	      Ingredient eggHatIngredient = Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","egghat")));
	      this.targetSelector.addGoal(4, new OwnerHurtByTargetGoal(this));
	      this.targetSelector.addGoal(4, new OwnerHurtTargetGoal(this));
	      this.goalSelector.addGoal(3, new SitWhenOrderedToGoal(this));
	      this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, eggHatIngredient, false));
	      this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
	      this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.2D, 8.0F, 2.0F, false));
	      this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0f));
	      this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
	      this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	}
	
	@Override
	public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
		ItemStack itemstack = player.getItemInHand(interactionHand);
		if(itemstack.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","egghat")))) {
			this.customTamingParticles();
			this.tame(player);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}
	
	private void customTamingParticles() {
		ParticleOptions particleoptions = ParticleTypes.HEART;
		
		for(int i = 0; i < 7; i++) {
			double d0 = this.random.nextGaussian() * 0.02D;
			double d1 = this.random.nextGaussian() * 0.02D;
			double d2 = this.random.nextGaussian() * 0.02D;
			this.level.addParticle(particleoptions, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
		}
	}
	
	@SuppressWarnings("removal")
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }
	    
	@Override
	public void registerControllers(AnimationData data) {
		// TODO Apéndice de método generado automáticamente
		data.addAnimationController(new AnimationController<AmongusEntity>(this, "controller",
                0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		// TODO Apéndice de método generado automáticamente
		return factory;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	
	public static boolean canSpawn(EntityType<AmongusEntity> entity, LevelAccessor levelAccess, 
			MobSpawnType spawnType, BlockPos pos, Random random) {
		return checkAnimalSpawnRules(entity, levelAccess, spawnType, pos, random);
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
