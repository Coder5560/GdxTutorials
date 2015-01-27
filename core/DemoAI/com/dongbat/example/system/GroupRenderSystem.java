package com.dongbat.example.system;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.BoundingComponent;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.WallTypeComponent;
import com.dongbat.example.component.BoundingComponent.BoundType;

@Wire
public class GroupRenderSystem extends VoidEntitySystem {
	ViewportSystem viewportSystem;
	InputEntityProcessing inputEntityProcessing;
	ComponentMapper<BoundingComponent> bm;
	ComponentMapper<WallTypeComponent> wm;
	ComponentMapper<Physics> pm;
	private ShapeRenderer shapeRenderer;

	public GroupRenderSystem() {
		super();
	}

	@Override
	protected void initialize() {
		super.initialize();
		this.shapeRenderer = viewportSystem.getShapeRenderer();
	}

	@Override
	protected void begin() {
		super.begin();
		shapeRenderer.setProjectionMatrix(viewportSystem.getGameViewport()
				.getCamera().combined);
		viewportSystem.getGameCamera().update();
		shapeRenderer.begin(ShapeType.Filled);
	}

	@Override
	protected void processSystem() {
		ImmutableBag<Entity> entities = world.getManager(GroupManager.class)
				.getEntities("wall");
		for (Entity entity : entities) {
			BoundingComponent boundingComponent = bm.get(entity);
			WallTypeComponent wallTypeComponent = wm.get(entity);
			Physics physics = pm.get(entity);
			Vector2 position = physics.getPosition();
			float w = boundingComponent.getWidth();
			float h = boundingComponent.getHeight();
			shapeRenderer.setColor(wallTypeComponent.getColor());
			shapeRenderer.rect(position.x - w / 2, position.y - h / 2, w, h);
		}
	}

	@Override
	protected void end() {
		super.end();
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.GREEN);
		debugEntity(shapeRenderer, inputEntityProcessing.getEntity());
		debugEntity(shapeRenderer, world.getManager(TagManager.class)
				.getEntity("player"));
		shapeRenderer.setColor(Color.RED);
		debugEntity(shapeRenderer, world.getManager(TagManager.class).getEntity("target"));
		shapeRenderer.end();
	}

	private void debugEntity(ShapeRenderer renderer, Entity e) {
		if (e == null)
			return;
		BoundingComponent boundingComponent = bm.get(e);
		Physics physics = pm.get(e);
		Vector2 position = physics.getPosition();
		if (boundingComponent.getBoundType() == BoundType.RECTANGLE) {
			float w = boundingComponent.getWidth();
			float h = boundingComponent.getHeight();
			shapeRenderer.rect(position.x - w / 2, position.y - h / 2, w, h);
		} else {
			shapeRenderer.circle(position.x, position.y,
					boundingComponent.getRadius());
		}
	}

}
