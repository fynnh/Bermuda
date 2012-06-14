package de.IF_EF.Bermuda;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.SkyFactory;

import de.lessvoid.nifty.Nifty;

/**
 * Klasse fÃ¼r die Kommunikation zwischen Logik und Bildschirmausgabe
 * 
 * @author Fynn Hauptmeier
 * @version 1.0
 */

public class GraphicsHelper extends AbstractAppState {
	private Graphics graphicsInstance;
	private Camera cam;
	private App app;
	private Node root, cubes;
	private NiftyJmeDisplay niftyDisplay;
	private String activeCube;
	private boolean cubesChooserActive;
	
	public GraphicsHelper(App app) {
		graphicsInstance = new Graphics();

		this.app = app;
	}

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);
		makeNodes();
		makeGround();
		makeSky();
		addLight();
		makeCrossHairs();

	}

	public Graphics getGraphicsInstance() {
		return graphicsInstance;
	}

	public Camera getCamera() {
		return cam;
	}

	protected void makeNodes() {
		root = new Node("root");
		cubes = new Node("cubes");
		app.getRootNode().attachChild(root);
		root.attachChild(cubes);
	}

	protected void makeGround() {
		Box box = new Box(new Vector3f(0, -5, -5), App.getConfigHelper()
				.getGroundX(), .2f, App.getConfigHelper().getGroundZ());
		Geometry floor = new Geometry("ground", box);
		Material mat1 = new Material(app.getAssetManager(),
				"Common/MatDefs/Light/Lighting.j3md");
		// mat1.setBoolean("UseMaterialColors", true);
		Texture texture = app.getAssetManager().loadTexture(
				App.getConfigHelper().getGroundTextureUrl());
		texture.setWrap(WrapMode.Repeat);
		box.scaleTextureCoordinates(new Vector2f(1000, 1000));
		mat1.setTexture("DiffuseMap", texture);
		// mat1.setColor("Diffuse", ColorRGBA.Brown);
		floor.setMaterial(mat1);
		root.attachChild(floor);
		App.getPhysicsHelper().addPhysics(floor, 0.0f);
	}

	protected void makeSky() {
		app.getRootNode().attachChild(
				SkyFactory.createSky(app.getAssetManager(), App
						.getConfigHelper().getSkyUrl(), false));
	}

	protected void makeCrossHairs() {
		app.getGuiNode().detachAllChildren();
		app.setGuiFont(app.getAssetManager().loadFont(
				"Interface/Fonts/Default.fnt"));
		BitmapText ch = new BitmapText(app.getGuiFont(), false);
		ch.setSize((app.getGuiFont().getCharSet().getRenderedSize()) * 2);
		ch.setText("+"); // crosshairs
		ch.setLocalTranslation(
				// center
				app.getSettings().getWidth() / 2
						- app.getGuiFont().getCharSet().getRenderedSize() / 3
						* 2,
				app.getSettings().getHeight() / 2 + ch.getLineHeight() / 2, 0);
		app.getGuiNode().attachChild(ch);
	}

	protected void addLight() {
		DirectionalLight sun = new DirectionalLight();
		sun.setColor(ColorRGBA.Orange);
		sun.setDirection(new Vector3f(0.5f, -1.0f, 0.5f).normalizeLocal());
		app.getRootNode().addLight(sun);
	}

	public Node getCubesNode() {
		return cubes;
	}

	public Node getRootNode() {
		return root;
	}

	public void removeCube(Geometry cube) {
		cubes.detachChild(cube);
	}

	public void addCube(Vector3f center) {
		System.out.println(center);
		addCube(center, "grass");
	}

	public void addCube(Vector3f center, String name) {
		Box box = new Box(center, 1, 1, 1);
		Geometry cube = new Geometry(String.valueOf(name), box);
		Material material = new Material(app.getAssetManager(),
				"Common/MatDefs/Misc/Unshaded.j3md");
		Texture texture = app.getAssetManager().loadTexture(
				App.getConfigHelper().getCubeTextureUrl(name));
		material.setTexture("ColorMap", texture);
		cube.setMaterial(material);
		cubes.attachChild(cube);
                App.getPhysicsHelper().addPhysics(cube, 0.0f);
	}
	
	public void startCubesChooser()
	{
		if(!cubesChooserActive)
		{
		System.out.println("hallo");
		niftyDisplay = new NiftyJmeDisplay(
			    app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
			/** Create a new NiftyGUI object */
			Nifty nifty = niftyDisplay.getNifty();
			/** Read your XML and initialize your custom ScreenController */
			nifty.fromXml("Interface/cubesChooser.xml", "start", new GuiController());
			// nifty.fromXml("Interface/helloworld.xml", "start", new MySettingsScreen(data));
			// attach the Nifty display to the gui view port as a processor
			app.getGuiViewPort().addProcessor(niftyDisplay);
			// disable the fly cam
			app.getFlyCam().setDragToRotate(true);
			
			cubesChooserActive=true;
		}
			
	}
	
	public void stopCubesChooser()
	{
		if(cubesChooserActive)
		{
		app.getGuiViewPort().removeProcessor(niftyDisplay);
		app.getFlyCam().setDragToRotate(false);

		
		cubesChooserActive=false;
		}
	}

	public void setCube(String cube)
	{
		activeCube=cube;
	}
	public boolean isCubesChooserActive()
	{
		return cubesChooserActive;
	}

}
