package dev.gallardo.amodgus.common;

import software.bernie.geckolib.core.animation.RawAnimation;

public class AmongusAnimations {
	public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
	public static final RawAnimation SITTING = RawAnimation.begin().thenPlayAndHold("sitting");
	public static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
	public static final RawAnimation DEATH = RawAnimation.begin().thenPlayAndHold("death");
	public static final RawAnimation SPAWN = RawAnimation.begin().thenPlay("spawn");
}
