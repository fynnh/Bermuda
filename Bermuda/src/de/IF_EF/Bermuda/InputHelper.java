package de.IF_EF.Bermuda;

import com.jme3.app.Application;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;

public class InputHelper extends com.jme3.app.state.AbstractAppState implements ActionListener {

	private InputManager inputManager;
	private Vector3f walkDirection = new Vector3f();
	private boolean keys[];

	public InputHelper(InputManager inputManager) {
		this.inputManager = inputManager;
                keys = new boolean[5]; 
		setUpKeys();
	}

	private void setUpKeys() {
		inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addMapping("Choose", new KeyTrigger(KeyInput.KEY_C));
		inputManager.addListener(this, "Left");
		inputManager.addListener(this, "Right");
		inputManager.addListener(this, "Up");
		inputManager.addListener(this, "Down");
		inputManager.addListener(this, "Jump");
		inputManager.addListener(this, "Choose");
	}

	/**
	 * These are our custom actions triggered by key presses. We do not walk
	 * yet, we just keep track of the direction the user pressed.
	 */
	public void onAction(String binding, boolean value, float tpf) {
            if (binding.equals("Left")) {
			keys[0] = value;
		} else if (binding.equals("Right")) {
			keys[1] = value;
		} else if (binding.equals("Up")) {
			keys[2] = value;
		} else if (binding.equals("Down")) {
			keys[3] = value;
		} else if (binding.equals("Jump")) {
			keys[4] = value;
		}
		else if (binding.equals("Choose")&& !value) {
			System.out.println("hallo");
			if(!App.getGraphicsHelper().isCubesChooserActive())
			{
				App.getGraphicsHelper().startCubesChooser();
			}
			else
			{
				App.getGraphicsHelper().stopCubesChooser();
			}
			
		}

	}

	/**
	 * This is the main event loop--walking happens here. We check in which
	 * direction the player is walking by interpreting the camera direction
	 * forward (camDir) and to the side (camLeft). The setWalkDirection()
	 * command is what lets a physics-controlled player walk. We also make sure
	 * here that the camera moves with player.
	 */
    @Override
	public void update(float tpf) {
            App.getPhysicsHelper().moveCamera(keys);
        }

}
