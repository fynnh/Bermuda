package de.IF_EF.Bermuda;

public class Quest {

	private int closeAtCubesCount;
	private int closeAtKilledObjects;
	private String text;

	public Quest(int closeAtCubesCount,
			int closeAtKilledObjects, String text) {

		this.closeAtCubesCount = closeAtCubesCount;
		this.closeAtKilledObjects = closeAtKilledObjects;
		this.text = text;
	}


	public int getCloseAtCubesCount() {
		return closeAtCubesCount;
	}

	public int getCloseAtKilledObjects() {
		return closeAtKilledObjects;
	}

	public String getText() {
		return text;
	}

}