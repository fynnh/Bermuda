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
	private int activeQuest;
	private int cubeCount;
	private int killedObjectsCount;

	public GameHelper() {
		this.inventar = App.getConfigHelper().getInventar();
		this.quests = App.getConfigHelper().getQuests();
		this.questCount = 0;
		this.activeQuest=-1;
		startQuest();
		this.cubeCount=0;
		this.killedObjectsCount=0;
	}

	public int countCubes(String cubeName) {
		int count = inventar.get(cubeName);
		return count;
	}

	public void removeCube(String cubeName) {
		int count = inventar.get(cubeName);
		inventar.put(cubeName, count++);
		cubeCount--;
	}

	public boolean addCube(String cubeName) {
		int count = inventar.get(cubeName);
		if (count > 0) {
			inventar.put(cubeName, count--);
			cubeCount++;
			startQuest();
			return true;
		}
		return false;
	}

	public void startQuest() {
		
		if(isQuestEnded())
		{
			if(quests.length>questCount)
			{
		Quest quest = quests[questCount];
		activeQuest=questCount;
		// App.getGraphicsHelper().setText(quest.getText());
		for(int i=0;i<100;i++)
		System.out.println(quest.getText());
		
		questCount++;
			}
			else
			{
				System.out.println("S�mtliche Quests sind erf�llt");
			}
		}
	}
	
	public boolean isQuestEnded()
	{
		if(activeQuest==-1)
			return true;
		Quest quest = quests[activeQuest];
		int cubes = quest.getCloseAtCubesCount();
		int killedObjects = quest.getCloseAtKilledObjects();
		if(cubeCount >= cubes && killedObjectsCount >= killedObjects)
		{
			return true;
		}
		return false;
	}
	
	

}