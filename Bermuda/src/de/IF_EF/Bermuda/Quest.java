package de.IF_EF.Bermuda;

public class Quest {

	private int startAtCubesCount;
	private int closeAtCubesCount;
	private int closeAtKilledObjects;
	private String text;

	public Quest(int startAtCubesCount, int closeAtCubesCount,
			int closeAtKilledObjects, String text) {

		this.startAtCubesCount = startAtCubesCount;
		this.closeAtCubesCount = closeAtCubesCount;
		this.closeAtKilledObjects = closeAtKilledObjects;
		this.text = text;
	}

	public int getStartAtCubesCount() {
		return startAtCubesCount;
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