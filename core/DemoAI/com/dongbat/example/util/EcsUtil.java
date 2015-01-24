package com.dongbat.example.util;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Array;
import com.dongbat.example.system.GroupRenderSystem;
import com.dongbat.example.system.InputHandleSystem;
import com.dongbat.example.system.InputSystem;
import com.dongbat.example.system.MovementSystem;
import com.dongbat.example.system.PhysicsSystem;
import com.dongbat.example.system.ScriptSystem;
import com.dongbat.example.system.ShapeRenderSystem;
import com.dongbat.example.system.StatusSystem;
import com.dongbat.example.system.ViewportSystem;

public class EcsUtil {

	private static World						world;
	private static float						accumulate		= 0;
	private static final float					STEP			= 0.015f;
	private static final Array<EntitySystem>	passiveSystem	= new Array<EntitySystem>();
	private static MapGenerator					mapGenerator	= new MapGenerator();

	public static World getWorld() {
		if (world == null) {
			init();
		}
		return world;
	}

	private static void init() {
		world = new World();
		passiveSystem.clear();
		InputHandleSystem inputHandleSystem = new InputHandleSystem();
		Gdx.input.setInputProcessor(new GestureDetector(inputHandleSystem));
		setSystem(world, new MovementSystem(), false);
		setSystem(world, new PhysicsSystem(), false);
		setSystem(world, new ScriptSystem(), false);
		setSystem(world, new GroupRenderSystem(), true);
		setSystem(world, new ViewportSystem(), true);
		setSystem(world, new StatusSystem(), true);
		setSystem(world, inputHandleSystem, false);
		setSystem(world, new ShapeRenderSystem(), true);
		setSystem(world, new InputSystem(), true);
		world.setManager(new GroupManager());
		world.initialize();
		mapGenerator.generate(1, world);
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
