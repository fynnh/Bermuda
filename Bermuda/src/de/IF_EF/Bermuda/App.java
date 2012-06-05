package de.IF_EF.Bermuda;

import com.jme3.app.SimpleApplication;

/**
 * Klasse der Anwendung
 * @author 
 * @version 1.0
 */


public class App extends SimpleApplication {

    //helper objects
        private static GameHelper gameHelper;
	private static GraphicsHelper graphicsHelper;
        private static PhysicsHelper physicsHelper;
        
    @Override
    public void simpleInitApp() {
        PreConfig con = new PreConfig();
        gameHelper = new GameHelper();
        graphicsHelper = new GraphicsHelper(125, 125, "GrassTile01.jpg");
        physicsHelper = new PhysicsHelper(this);
    }
    
    public static GameHelper getGameHelper() {
	return gameHelper;
    }

    public static GraphicsHelper getGraphicsHelper() {
	return graphicsHelper;
    }
        
    public static PhysicsHelper getPhysicsHelper() {
        return physicsHelper;
    }

}
