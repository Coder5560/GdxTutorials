package com.dongbat.example.system;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.managers.GroupManager;
import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.BoundingComponent;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.WallTypeComponent;

@Wire
public class GroupRenderSystem extends VoidEntitySystem {
	ShapeRenderSystem					shapeRenderSystem;
	ComponentMapper<BoundingComponent>	bm;
	ComponentMapper<WallTypeComponent>	wm;
	ComponentMapper<Physics>			pm;

	public GroupRenderSystem() {
		super();
	}

	@Override
	protected void initialize() {
		super.initialize();
	}

	@Override
	protected void begin() {
		super.begin();
		shapeRenderSystem.renderer.begin(ShapeType.Filled);
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
			shapeRenderSystem.renderer.setColor(wallTypeComponent.getColor());
			shapeRenderSystem.renderer.rect(position.x - w / 2, position.y - h
					/ 2, w, h);
		}
	}

	@Override
	protected void end() {
		super.end();
		shapeRenderSystem.renderer.end();
	}
}
