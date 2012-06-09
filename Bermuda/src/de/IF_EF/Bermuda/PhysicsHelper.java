package de.IF_EF.Bermuda;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.FlyByCamera;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.control.Control;


/**
 *
 * @author Linus Schrewe
 */
public class PhysicsHelper extends BulletAppState {
    
    GraphicsHelper graphicsHelper;
    CharacterControl playerCam;
    Application bermudaApp;
    
    public PhysicsHelper(App app) {
        super();
        stateManager = app.getStateManager();
    }
    

    @Override
        public void update(float tpf) {
        super.update(tpf);
            app.getCamera().setLocation(playerCam.getPhysicsLocation());
    }
    

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = app;
        graphicsHelper = App.getGraphicsHelper();
        initCamera();
    }

    private void initCamera() {
        CapsuleCollisionShape cam = new CapsuleCollisionShape(0.1f, 1.8f);
        playerCam = new CharacterControl(cam, 0.1f);
        playerCam.setJumpSpeed(20.0f);
        playerCam.setGravity(1);
        playerCam.setFallSpeed(1);
        playerCam .setPhysicsLocation(new Vector3f(0, 10, 0));
        this.getPhysicsSpace().add(playerCam);
    }
    
    public void move() {
        
    }
    
    public void addPhysics(Geometry geo) {
        CollisionShape coll = CollisionShapeFactory.createMeshShape(geo);
        Control phy = new RigidBodyControl(coll, tpf);
        geo.addControl(phy);
        getPhysicsSpace().add(phy);
    }
    

}