package es.yoshibv.amodgus.entities;


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
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class AmongusEntity extends TamableAnimal implements IAnimatable {
	@SuppressWarnings("removal")
	private AnimationFactory factory = new AnimationFactory(this);
	
	public AmongusEntity(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
		// TODO Apéndice de constructor generado automáticamente
	}
	
	public static AttributeSupplier setAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.00)
				.add(Attributes.ATTACK_DAMAGE,3.0f)
				.add(Attributes.ATTACK_SPEED,1.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.4f).build();
				
	}
	
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(0, new FloatGoal(this));
	      Ingredient eggHatIngredient = Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation("amodgus","egghat")));
	      this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, eggHatIngredient, false));
	      this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	      this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0f));
	      this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
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
	
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15F, 1.0F);
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
    	

}
