package es.yoshibv.utils;

import java.util.Random;

import es.yoshibv.amodgus.entities.variant.AmongusVariant;

public class Util {
	private static int length = AmongusVariant.values().length;
	
	public static AmongusVariant getRandom() {
		Random random = new Random();
		int randomIndex = random.nextInt(length);
		AmongusVariant randomValue = AmongusVariant.values()[randomIndex];
		return randomValue;
	}

}
