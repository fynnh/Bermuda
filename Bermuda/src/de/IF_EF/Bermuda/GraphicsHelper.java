/**
 * Klasse für die Kommunikation zwischen Logik und Bildschirmausgabe
 * @author Fynn Hauptmeier
 * @version 1.0
 */
package de.IF_EF.Bermuda;

public class GraphicsHelper {

	private Graphics graphicsInstance;

	public GraphicsHelper(int groundX, int groundZ, String groundTextureUrl) {
		graphicsInstance = new Graphics();
	}
}
