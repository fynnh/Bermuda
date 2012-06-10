package de.IF_EF.Bermuda;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.input.FlyByCamera;
import com.jme3.input.JoyInput;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.TouchInput;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.lwjgl.LwjglRenderer;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import com.jme3.system.JmeContext.Type;
import com.jme3.system.SystemListener;
import com.jme3.system.Timer;

/**
 * Klasse der Anwendung
 * 
 * @author
 * @version 1.0
 */

public class App extends SimpleApplication {

	// helper objects
	private static GameHelper gameHelper;
	private static GraphicsHelper graphicsHelper;
	private static PhysicsHelper physicsHelper;
	private static InputHelper inputHelper;
	private static PickHelper pickHelper;
	// private static ConfigHelper configHelper;
	private static ConfigDummy configHelper;

	public static void main(String[] args) {
		App app = new App();
		// app.initialize();
		app.start(); // start the game
	}

	public App() {
		super();
	}

	@Override
	public void simpleInitApp() {
		// PreConfig con = new PreConfig();
		// configHelper = new ConfigHelper();
		configHelper = new ConfigDummy();
		gameHelper = new GameHelper();
		physicsHelper = new PhysicsHelper(this);
		stateManager.attach(physicsHelper);
		physicsHelper.initialize(stateManager, this);
		graphicsHelper = new GraphicsHelper(this);
		stateManager.attach(graphicsHelper);
		graphicsHelper.initialize(stateManager, this);
		inputHelper = new InputHelper(this.getInputManager());
                stateManager.attach(inputHelper);
		pickHelper = new PickHelper(cam, this.getInputManager());

	}

	@Override
	public void simpleUpdate(float tpf) {
		super.simpleUpdate(tpf);

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

	public FlyByCamera getFlyCam() {
		return flyCam;
	}

	public RenderManager getRenderManager() {
		return renderManager;
	}

	public Node getGuiNode() {
		return guiNode;
	}

	public BitmapFont getGuiFont() {
		return guiFont;
	}

	public void setGuiFont(BitmapFont guiFont) {
		this.guiFont = guiFont;
	}

	public Camera getCam() {
		return cam;
	}

	public static InputHelper getInputHelper() {
		return inputHelper;
	}

	public static ConfigDummy getConfigHelper() {
		return configHelper;
	}

	public static PickHelper getPickHelper() {
		return pickHelper;
	}

	public AppSettings getSettings() {
		return settings;
	}
}
