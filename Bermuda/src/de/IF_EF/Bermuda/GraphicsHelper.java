package de.IF_EF.Bermuda;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.renderer.Camera;

/**
 * Klasse fÃ¼r die Kommunikation zwischen Logik und Bildschirmausgabe
 * @author Fynn Hauptmeier
 * @version 1.0
 */


public class GraphicsHelper extends AbstractAppState {
	private Graphics graphicsInstance;
        private Camera cam;
        private App app;

	public GraphicsHelper(int groundX, int groundZ, String groundTextureUrl, App app) {
		graphicsInstance = new Graphics();
                this.app = app;
                app.getStateManager().attach(this);
	}
        
        @Override
        public void initialize(AppStateManager stateManager, Application app) {
            super.initialize(stateManager, app);
            cam = app.getCamera();
        }
        
        public Graphics getGraphicsInstance()  {
            return graphicsInstance;
        }
        
        
        public Camera getCamera() {
            return cam;
        }

        
        
}
