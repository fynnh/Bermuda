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
import com.jme3.scene.shape.Box;


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
        getPhysicsSpace().enableDebug(app.getAssetManager());
    }

    private void initCamera() {
        CapsuleCollisionShape cam = new CapsuleCollisionShape(1.2f, 1.8f);
        playerCam = new CharacterControl(cam, 0.1f);
        playerCam.setJumpSpeed(2);
        playerCam.setGravity(3);
        playerCam.setFallSpeed(3);
        playerCam.setPhysicsLocation(new Vector3f(0, 10, 0));
        this.getPhysicsSpace().add(playerCam);
    }
    
    public void moveCamera(boolean[] dir) {
            Vector3f direc = app.getCamera().getDirection().multLocal(0.07f);
            direc.y = 0;
            Vector3f left = app.getCamera().getLeft().multLocal(0.02f);
            left.y = 0;
            Vector3f playerDirec = new Vector3f(0f, 0f, 0f);           
            if(dir[0]) {
                playerDirec.addLocal(left);
            }
            if(dir[1]) {
                playerDirec.addLocal(left.negate());
            }
            if (dir[2]) {
                playerDirec.addLocal(direc);
            }
            if (dir[3]) {
                playerDirec.addLocal(direc.negate());
            }
            if (dir[4]) {
                playerCam.jump();
            }
            playerCam.setWalkDirection(playerDirec);
    }
    
    public void addPhysics(Geometry geo, float mass) {
        CollisionShape coll = CollisionShapeFactory.createMeshShape(geo);
        RigidBodyControl phy = new RigidBodyControl(coll, mass);
        geo.addControl(phy);
        getPhysicsSpace().add(phy);
    }
    
    public void removePhysics(Geometry geo) {
        getPhysicsSpace().removeCollisionObject(geo.getControl(RigidBodyControl.class));
        geo.removeControl(RigidBodyControl.class);
    }
    

}