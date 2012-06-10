/**
 * In dieser Klasse ist sämtliche Spiellogik
 * implementiert
 * @author Gerhard Wolfert
 * @version 1.0
 */
package de.IF_EF.Bermuda;

import java.util.HashMap;

public class GameHelper {

	private HashMap<String, Integer> inventar;
	private Quest[] quests;
	private int questCount;

	public GameHelper() {
		this.inventar = App.getConfigHelper().getInventar();
		this.quests = App.getConfigHelper().getQuests();
		this.questCount = 0;
	}

	public int countCubes(String cubeName) {
		int count = inventar.get(cubeName);
		return count;
	}

	public void removeCube(String cubeName) {
		int count = inventar.get(cubeName);
		inventar.put(cubeName, count++);
	}

	public boolean addCube(String cubeName) {
		int count = inventar.get(cubeName);
		if (count > 0) {
			inventar.put(cubeName, count--);
			return true;
		}
		return false;
	}

	public void startQuest() {
		Quest quest = quests[questCount];
		// App.getGraphicsHelper().setText(quest.getText());
	}

}