package com.dongbat.example.util;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dongbat.example.component.BoundingComponent;
import com.dongbat.example.component.Physics;
import com.dongbat.example.system.EntityArriveSystem;
import com.dongbat.example.system.GroupRenderSystem;
import com.dongbat.example.system.InputEntityProcessing;
import com.dongbat.example.system.MovementSystem;
import com.dongbat.example.system.PhysicsSystem;
import com.dongbat.example.system.StatusSystem;
import com.dongbat.example.system.ViewportSystem;

public class EcsUtil {

	private static World world;
	private static float accumulate = 0;
	private static final float STEP = 0.015f;
	private static final Array<EntitySystem> passiveSystem = new Array<EntitySystem>();
	private static MapGenerator mapGenerator = new MapGenerator();
	private static Entity player;
	private static Entity target;

	public static World getWorld() {
		if (world == null) {
			init();
		}
		return world;
	}

	private static void init() {
		world = new World();
		passiveSystem.clear();

		InputEntityProcessing inputEntityProcessing = new InputEntityProcessing();
		ViewportSystem viewportSystem = new ViewportSystem();
		EntityArriveSystem entityArriveSystem = new EntityArriveSystem();

		{
			InputMultiplexer inputMultiplexer = new InputMultiplexer();
			inputMultiplexer.addProcessor(new GestureDetector(viewportSystem));
			inputMultiplexer.addProcessor(new GestureDetector(
					inputEntityProcessing));
			inputMultiplexer.addProcessor(new GestureDetector(
					entityArriveSystem));
			Gdx.input.setInputProcessor(inputMultiplexer);
		}

		{
			setSystem(world, new MovementSystem(), false);
			setSystem(world, new PhysicsSystem(), false);
			setSystem(world, inputEntityProcessing, false);
			setSystem(world, new GroupRenderSystem(), true);
			setSystem(world, viewportSystem, true);
			setSystem(world, entityArriveSystem, false);
			setSystem(world, new StatusSystem(), true);
		}
		world.setManager(new GroupManager());
		world.setManager(new TagManager());
		world.initialize();
		mapGenerator.generate(1, world);

		player = world.createEntity().edit()
				.add(new Physics(new Vector2(200, 200), new Vector2(0, 0), 0))
				.add(new BoundingComponent(20)).getEntity();
		world.getManager(TagManager.class).register("player", player);
		target = world.createEntity().edit()
				.add(new Physics(new Vector2(400, 200), new Vector2(0, 0), 0))
				.add(new BoundingComponent(20)).getEntity();
		world.getManager(TagManager.class).register("target", target);
		entityArriveSystem.setPlayer(player).setTarget(target)
				.createBehaviors();

	}

	private static void setSystem(World world, EntitySystem system,
			boolean passive) {
		world.setSystem(system, passive);
		if (passive) {
			passiveSystem.add(system);
		}
	}

	public static void update(float delta) {
		World world = getWorld();
		accumulate += delta;
		while (accumulate > STEP) {
			world.setDelta(STEP);
			world.process();
			accumulate -= STEP;
		}
		for (EntitySystem entitySystem : passiveSystem) {
			world.setDelta(delta);
			entitySystem.process();
		}
	}

	public static <T extends Component> T getComponent(Entity e, Class<T> type) {
		return getWorld().getMapper(type).getSafe(e);
	}
}
