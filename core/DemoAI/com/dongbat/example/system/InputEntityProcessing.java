package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dongbat.example.component.BoundingComponent;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.TouchableComponent;
import com.dongbat.example.util.R;

@Wire
public class InputEntityProcessing extends EntityProcessingSystem implements
		GestureListener {

	private ComponentMapper<Physics> pm;
	private ComponentMapper<BoundingComponent> bm;
	private ViewportSystem viewportSystem;

	private Entity entity;
	private Vector2 touchPoint = new Vector2();

	@SuppressWarnings("unchecked")
	public InputEntityProcessing() {
		super(Aspect.getAspectForOne(TouchableComponent.class));
	}

	@Override
	protected void initialize() {
		super.initialize();
	}

	@Override
	protected void begin() {
		super.begin();
	}

	@Override
	protected void process(Entity e) {

	}

	

	public boolean hitEntity(Entity e, float x, float y) {
		Physics physics = pm.get(e);
		BoundingComponent boundingComponent = bm.get(e);

		if (physics != null && boundingComponent != null) {
			return (hit(x, y, physics.getPosition(),
					boundingComponent.getWidth(), boundingComponent.getHeight()));
		}

		return false;
	}

	public boolean hit(float x, float y, Vector2 entityPosition,
			float entityBoundWidth, float entityBoundHeight) {
		return (x <= entityPosition.x + entityBoundWidth / 2)
				&& (x >= entityPosition.x - entityBoundWidth / 2)
				&& (y <= entityPosition.y + entityBoundHeight / 2)
				&& (y >= entityPosition.y - entityBoundHeight / 2);
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {

		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		viewportSystem.getGameViewport().unproject(touchPoint.set(x, y));
		ImmutableBag<Entity> actives = getActives();
		for (Entity entity : actives) {
			if (hitEntity(entity, touchPoint.x, touchPoint.y)) {
				this.entity = entity;
				return true;
			}
		}
		this.entity = null;
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {

		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {

		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

}
