package es.yoshibv.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTextureGenerator {
	private List<String> listaTexturas = new ArrayList<String>();
	private Random randomizer = new Random();
	
	public String getRandomTexture() {
		listaTexturas.add("textures/entities/amongus_red.png");
		listaTexturas.add("textures/entities/amongus_yellow.png");
		listaTexturas.add("textures/entities/amongus_blue.png");
		return listaTexturas.get(randomizer.nextInt(listaTexturas.size()));
	}

}
