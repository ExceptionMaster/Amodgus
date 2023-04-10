package es.yoshibv.amodgus.entities.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum AmongusVariant {
	DEFAULT(0),
	BLACK(1),
	BLUE(2),
	BROWN(3),
	CYAN(4),
	GREEN(5),
	LIME(6),
	MAGENTA(7),
	ORANGE(8),
	PINK(9),
	PURPLE(10),
	WHITE(11),
	YELLOW(12);
	
	private static final AmongusVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
			comparingInt(AmongusVariant::getId)).toArray(AmongusVariant[]::new);
	private final int id;
	
	AmongusVariant(int id) {
		this.id = id;
	}
	
	public int getId() {return this.id; }
	public static AmongusVariant byId(int id) {return BY_ID[id % BY_ID.length]; }

}
