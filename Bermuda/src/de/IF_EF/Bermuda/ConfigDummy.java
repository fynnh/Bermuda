package de.IF_EF.Bermuda;

import java.util.HashMap;

public class ConfigDummy {

	private int groundX;
	private int groundZ;
	private String groundTextureUrl;
	private String skyUrl;
	private HashMap<String, String> cubes;
	private String[] cubevariants;
	private HashMap<String, Integer> inventar;
	private Quest[] quests;

	public ConfigDummy() {
		groundX = 1000;
		groundZ = 1000;
		groundTextureUrl = "Textures/grassTile01.png";
		skyUrl = "Textures/Sky/Bright/FullskiesBlueClear03.dds";
		cubevariants = new String[2];
		cubevariants[0] = "grass";
		cubevariants[1] = "earth";
		cubes = new HashMap<String, String>();
		cubes.put("grass", "Textures/grassTile01.png");
		cubes.put("earth", "Textures/earth.jpg");
		inventar = new HashMap<String, Integer>();
		inventar.put("grass", 1000);
		inventar.put("earth", 1000);
		quests = new Quest[2];
		quests[0]=new Quest(10,0,"Baue 10 Bl�cke!");
		quests[1]=new Quest(10,0,"Baue 20 Bl�cke!");
		

	}

	public int getGroundX() {
		return groundX;
	}

	public void setGroundX(int groundX) {
		this.groundX = groundX;
	}

	public int getGroundZ() {
		return groundZ;
	}

	public void setGroundZ(int groundZ) {
		this.groundZ = groundZ;
	}

	public String getGroundTextureUrl() {
		return groundTextureUrl;
	}

	public void setGroundTextureUrl(String groundTextureUrl) {
		this.groundTextureUrl = groundTextureUrl;
	}

	public String getSkyUrl() {
		return skyUrl;
	}

	public void setSkyUrl(String skyUrl) {
		this.skyUrl = skyUrl;
	}

	public String getCubeTextureUrl(String cubeName) {
		return cubes.get(cubeName);
	}

	public HashMap<String, Integer> getInventar() {
		return inventar;
	}

	public Quest[] getQuests() {
		return quests;
	}


	public String[] getCubeVariants()
	{
		return cubevariants;
	}
}
