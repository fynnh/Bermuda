package de.IF_EF.Bermuda;

import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 *
 * @author Linus Schrewe
 */
public class PhysicsHelper extends BulletAppState {
    
    GraphicsHelper graphicsHelper;
    Camera camera;
    
    public PhysicsHelper(App app) {
        graphicsHelper = App.getGraphicsHelper();
        camera = graphicsHelper.getCamera();
        initCamera();
        stateManager = app.getStateManager();
        stateManager.attach(this);
    }
    

    @Override
    public void update(float tpf) {
        super.update(tpf);
        Vector3f location = camera.getLocation().clone();
        camera.getDirection();
        camera.setLocation(location);
    }

    private void initCamera() {
        
    }
    
}