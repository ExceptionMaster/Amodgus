package dev.gallardo.amodgus.entities;

import java.util.Random;
import java.util.UUID;

import dev.gallardo.amodgus.Amodgus;
import dev.gallardo.amodgus.entities.variant.AmongusVariant;
import dev.gallardo.amodgus.init.MobsInit;
import dev.gallardo.amodgus.sound.ModSounds;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.core.GeoAPI;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationFactory;
import software.bernie.geckolib.core.animatable.Animatable;
import software.bernie.geckolib.core.builder.AnimationBuilder;
import software.bernie.geckolib.core.controller.AnimationController.IAnimationListener;
import software.bernie.geckolib.core.controller.AnimationControllerBuilder;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactoryManager;
import software.bernie.geckolib.core.manager.AnimationManager;
import software.bernie.geckolib.core.manager.IGeckoLibAddon;
import software.bernie.geckolib.core.manager.LeafBoneController;
import software.bernie.geckolib.core.renderer.geo.IGeoRenderer;
import software.bernie.geckolib.core.renderer.geo.IGeoRendererFactory;
import software.bernie.geckolib.core.renderer.geo.LeafBoneGeoRenderer;
import software.bernie.geckolib.core.renderer.geo.LeafboneGeckoRenderer;
import software.bernie.geckolib.core.renderer.geo.RendererModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;
import software.bernie.geckolib.geometry.Vector3d;
import software.bernie.geckolib.manager.EntityData;

public class AmongusEntity extends TamableAnimal implements NeutralMob, Animatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private AmongusVariant variant;
    private final EntityData data;

    public AmongusEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        data = new EntityData(this);
        AnimationControllerBuilder controllerBuilder = new AnimationControllerBuilder(this, "controller", 20, this::animationPredicate);
        controllerBuilder.addAnimation(new AnimationBuilder().addAnimation("walk"));
        controllerBuilder.addAnimation(new AnimationBuilder().addAnimation("sitting"));
        controllerBuilder.addAnimation(new AnimationBuilder().addAnimation("idle"));
        AnimationController controller = controllerBuilder.build();
        AnimationFactoryManager.addModelRenderer(this, controller);
        AnimationFactoryManager.addController(this, controller);
    }

    private <E extends Animatable> PlayState animationPredicate(AnimationData animationData) {
        if (animationData.isMoving()) {
            animationData.setAnimation("walk");
            return PlayState.CONTINUE;
        }
        if (isSitting()) {
            animationData.setAnimation("sitting");
            return PlayState.CONTINUE;
        }
        animationData.setAnimation("idle");
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    // Resto del código...
}
