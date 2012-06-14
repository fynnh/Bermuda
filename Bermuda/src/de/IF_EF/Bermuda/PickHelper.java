package de.IF_EF.Bermuda;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Box;

public class PickHelper implements ActionListener {

	private InputManager inputManager;
	private Camera cam;

	public PickHelper(Camera cam, InputManager inputManager) {
		this.inputManager = inputManager;
		this.cam = cam;
		initKeys();
	}

	private void initKeys() {
		inputManager.addMapping("Remove", new MouseButtonTrigger(
				MouseInput.BUTTON_RIGHT)); // trigger 2:
											// left-button
											// click
		inputManager.addMapping("Add", new MouseButtonTrigger(
				MouseInput.BUTTON_LEFT)); // trigger 3: right-button click
		inputManager.addListener(this, "Remove");
		inputManager.addListener(this, "Add");
	}

	public void onAction(String name, boolean keyPressed, float tpf) {
		if (!App.getGraphicsHelper().isCubesChooserActive()) {
			if (name.equals("Remove") && !keyPressed) {

				// 1. Reset results list.
				CollisionResults results = new CollisionResults();
				// 2. Aim the ray from cam loc to cam direction.
				Ray ray = new Ray(cam.getLocation(), cam.getDirection());
				// 3. Collect intersections between Ray and Shootables in
				// results
				// list.
				App.getGraphicsHelper().getCubesNode()
						.collideWith(ray, results);

				if (results.size() > 0) {
					App.getGameHelper().removeCube(
							results.getClosestCollision().getGeometry()
									.getName());
					App.getGraphicsHelper().removeCube(
							results.getClosestCollision().getGeometry());
				}
			} else if (name.equals("Add") && !keyPressed) {

				// 1. Reset results list.
				CollisionResults results = new CollisionResults();
				// 2. Aim the ray from cam loc to cam direction.
				Ray ray = new Ray(cam.getLocation(), cam.getDirection());
				// Find Collisions
				App.getGraphicsHelper().getRootNode().collideWith(ray, results);

				// Add result
				if (results.size() > 0)

				{
					Vector3f pt = null;
					int x = 0;
					int y = 0;
					int z = 0;

					if (results.getClosestCollision().getGeometry().getName() != "ground") {
						Mesh mesh = results.getClosestCollision().getGeometry()
								.getMesh();
						if (mesh instanceof Box) {
							Box box = (Box) mesh;
							pt = box.getCenter();
							x = new Double(pt.getX()).intValue();
							y = new Double(pt.getY()).intValue();
							z = new Double(pt.getZ()).intValue();

						}
						pt = new Vector3f(x, y + 2, z);
					} else if (results.getClosestCollision().getGeometry()
							.getName() == "ground") {

						pt = results.getClosestCollision().getContactPoint();

						x = new Double(pt.getX()).intValue();
						y = new Double(pt.getY()).intValue();
						z = new Double(pt.getZ()).intValue();
						if (x % 2 != 0) {
							if (x > 0) {
								x++;
							} else {
								x--;
							}
						}

						if (y % 2 != 0) {
							if (y > 0) {
								y++;
							} else {
								y--;
							}
						}

						if (z % 2 != 0) {
							if (z > 0) {
								z++;
							} else {
								z--;
							}
						}
						pt = new Vector3f(x, y, z);
					}
					if (pt != null) {
						App.getGraphicsHelper().addCube(pt);
						System.out.println(pt);
					}

				}

			}
		}
	}
}
