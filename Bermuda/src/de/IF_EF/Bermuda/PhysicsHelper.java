package de.IF_EF.Bermuda;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;


/**
 *
 * @author Linus Schrewe
 */
public class PhysicsHelper extends BulletAppState {
    
    GraphicsHelper graphicsHelper;
    CharacterControl playerCam;
    Camera camera;
    
    public PhysicsHelper(App app) {
        super();
        stateManager = app.getStateManager();
        if(stateManager != null) {
            stateManager.attach(this);
        }
    }
    

    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (camera != null) {
            camera.setLocation(playerCam.getPhysicsLocation());
        } else {
            System.out.println("couldn't find camera");
        }
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        graphicsHelper = App.getGraphicsHelper();
        camera = graphicsHelper.getCamera();
        initCamera();
    }

    private void initCamera() {
        CapsuleCollisionShape cam = new CapsuleCollisionShape(0.1f, 1.8f);
        playerCam = new CharacterControl(cam, 0.1f);
        playerCam.setJumpSpeed(20.0f);
        playerCam.setGravity(30);
        playerCam.setFallSpeed(playerCam.getGravity());
        this.getPhysicsSpace().add(playerCam);
    }
    
    public void move() {
        
    }
    
}