package de.IF_EF.Bermuda;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.export.binary.BinaryImporter;
import com.jme3.font.BitmapText;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.SkyFactory;

import de.lessvoid.nifty.Nifty;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		activeCube = App.getConfigHelper().getCubeVariants()[0];
		makeNodes();
		makeGround();
		makeSky();
		addLight();
		makeCrossHairs();
                //initNPC();
                super.initialize(stateManager, app);

	}

	public Graphics getGraphicsInstance() {
		return graphicsInstance;
	}

	public Camera getCamera() {
		return cam;
	}

	protected void makeNodes() {
		root = new Node("root");
                app.getRootNode().attachChild(root);
                String userHome = System.getProperty("user.home");
                File file = new File(userHome+"/Bermuda/save.j3o");
                if(!file.exists()) {
                    cubes = new Node("cubes");
                    root.attachChild(cubes);
                }
	}

	protected void makeGround() {
                String userHome = System.getProperty("user.home");
                BinaryImporter importer = BinaryImporter.getInstance();
                importer.setAssetManager(app.getAssetManager());
                File file = new File(userHome+"/Bermuda/save.j3o");
                if(file.exists()){
                   try {
                        cubes = (Node)importer.load(file);
                        cubes.setName("cubes");
                        root.attachChild(cubes);
                    } catch (IOException ex) {
                           
                    }
                    for ( Spatial s : cubes.getChildren() ) {
                        Node n = (Node) s;
                        for( Spatial s2 : n.getChildren() ) {
                            Geometry g = (Geometry) s2;
                            App.getPhysicsHelper().addPhysics(g, 0.0f);
                        }            
                    }
                } else {
                    for( int i = -16; i <= 16; i++ )  { for ( int j = -8; j <= 8; j++ ) { for ( int k = - 16; k <= 16; k++ ) { addCube( new Vector3f(i, j, k), "grass"); }}}
                }
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
                AmbientLight amb = new AmbientLight();
                amb.setColor(ColorRGBA.DarkGray);
	}
        
        public void initNPC () {
            Box test = new Box(new Vector3f(10, 10, 10), 1, 2, 1);
            Geometry cube = new Geometry("test", test);
            Material material = new Material(app.getAssetManager(),
				"Common/MatDefs/Light/Lighting.j3md");
            material.setBoolean("UseMaterialColors", true);
            material.setColor("Diffuse", ColorRGBA.Red);
            cube.setMaterial(material);
            App.getPhysicsHelper().addNPC(cube, 1.0f, 2.0f, 100, 20);
            root.attachChild(cube);
        }

	public Node getCubesNode() {
		return cubes;
	}

	public Node getRootNode() {
		return root;
	}

	public void removeCube(Geometry cube) {
		App.getPhysicsHelper().removePhysics(cube);
                Node n;
		for ( Spatial s : cubes.getChildren()) {
                    n = (Node) s;
                    n.detachChild(cube);
                }
	}

	public void addCube(Vector3f center) {
		System.out.println(center);
		if(App.getGameHelper().addCube(activeCube))
		{
                    if (center.distance(app.getCamera().getLocation()) >= 2) {
                        addCube(center, activeCube);
                    }
		}
	}

	public void addCube(Vector3f center, String name) {
                int x = (int) center.getX(); x -= x%16;
                int y = (int) center.getY(); y -= y%16;
                int z = (int) center.getZ(); z -= z%16;
		Box box = new Box(center, 1, 1, 1);
		Geometry cube = new Geometry(name, box);
		Material material = new Material(app.getAssetManager(),
				"Common/MatDefs/Light/Lighting.j3md");
		Texture texture = app.getAssetManager().loadTexture(
				App.getConfigHelper().getCubeTextureUrl(name));
		System.out.println(App.getConfigHelper().getCubeTextureUrl(name));
		material.setTexture("DiffuseMap", texture);
		cube.setMaterial(material);
                String nodeName = new String();
                nodeName += x;
                nodeName += y;
                nodeName += z;
		if( cubes.getChild( nodeName ) != null) { 
                    Node n = (Node) cubes.getChild( nodeName );
                    n.attachChild(cube);
                    System.out.println(n);
                } else {
                    Node n = new Node(nodeName);
                    n.attachChild(cube);
                    cubes.attachChild(n);
                    System.out.println(n);
                }
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
		System.out.println(activeCube);
	}
	public boolean isCubesChooserActive()
	{
		return cubesChooserActive;
	}

}
