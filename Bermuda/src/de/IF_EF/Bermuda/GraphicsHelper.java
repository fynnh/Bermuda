package de.IF_EF.Bermuda;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

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
	}
        
        @Override
        public void initialize(AppStateManager stateManager, Application app) {
            super.initialize(stateManager, app);

            
        makeGround();
        addLight();

        }
        
        public Graphics getGraphicsInstance()  {
            return graphicsInstance;
        }
        
        
        public Camera getCamera() {
            return cam;
        }

        protected void makeGround() {
                Box box = new Box(new Vector3f(0, -5, -5), 1000, .2f, 1000);
	    	Geometry floor = new Geometry("ground", box);
	    	Material mat1 = new Material(app.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
                //mat1.setBoolean("UseMaterialColors", true);
                Texture texture= app.getAssetManager().loadTexture("Textures/grassTile01.jpg");
	    	texture.setWrap(WrapMode.Repeat);
	    	box.scaleTextureCoordinates(new Vector2f(1000,1000));
	    	mat1.setTexture("DiffuseMap", texture);
                //mat1.setColor("Diffuse", ColorRGBA.Brown);
	    	floor.setMaterial(mat1);
	    	app.getRootNode().attachChild(floor);
                App.getPhysicsHelper().addPhysics(floor);    
	  }
	    
        
            protected void addLight() {
                DirectionalLight sun = new DirectionalLight();
                sun.setColor(ColorRGBA.Orange);
                sun.setDirection(new Vector3f(0.5f, -1.0f, 0.5f).normalizeLocal());
                app.getRootNode().addLight(sun);
            }
        
}
