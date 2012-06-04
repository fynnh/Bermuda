package de.IF_EF.Bermuda;

import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.Camera;

/**
 *
 * @author Linus Schrewe
 */
public class PhysicsHelper extends BulletAppState {
    
    GraphicsHelper graphicsHelper;
    Camera camera;
    
    public PhysicsHelper() {
        graphicsHelper = ConfigHelper.getGraphicsHelper();
        camera = graphicsHelper.getCamera();
    }
    
    
}
