package de.IF_EF.Bermuda;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.Camera;

/**
 * Klasse fÃ¼r die Kommunikation zwischen Logik und Bildschirmausgabe
 * @author Fynn Hauptmeier
 * @version 1.0
 */


public class GraphicsHelper extends SimpleApplication {

	private Graphics graphicsInstance;
        private Camera cam;

	public GraphicsHelper(int groundX, int groundZ, String groundTextureUrl) {
		graphicsInstance = new Graphics();
                
	}
        
        public Graphics getGraphicsInstance()  {
            return graphicsInstance;
        }
        
        
        public Camera getActiveCamera() {
            return cam;
        }

    @Override
    public void simpleInitApp() {
        
    }
        
}
