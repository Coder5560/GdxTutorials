package com.dongbat.example;

import com.artemis.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.Script;
import com.dongbat.example.component.State;
import com.dongbat.example.component.StatusComponent;
import com.dongbat.example.system.StatusSystem;
import com.dongbat.example.system.ViewportSystem;
import com.dongbat.example.util.EcsUtil;
import com.dongbat.example.util.R;
import com.sun.org.apache.xerces.internal.util.Status;

public class AiDemo extends Game {
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		EcsUtil.getWorld().getSystem(ViewportSystem.class)
				.onGameResize(width, height);
	}

	@Override
	public void create() {
		Physics physics = new Physics(new Vector2(), new Vector2());
		State state = new State(new Vector2(150, 150));

		// Entity entity = EcsUtil.getWorld().createEntity();
		// entity.edit().add(physics).add(state);

		Entity entity1 = EcsUtil.getWorld().createEntity();
		entity1.edit().add(new Physics(new Vector2(), new Vector2()))
				.add(new State(new Vector2(300, 200)));
		entity1.edit().add(new StatusComponent("Player 1", 1f));

		Entity entity2 = EcsUtil.getWorld().createEntity();
		entity2.edit().add(new Physics(new Vector2(), new Vector2()))
				.add(new State(new Vector2(300, 200)));

		Entity entity3 = EcsUtil.getWorld().createEntity();
		entity3.edit().add(new Physics(new Vector2(), new Vector2()))
				.add(state);

		Entity entity4 = EcsUtil.getWorld().createEntity();
		entity4.edit().add(new Script()).add(physics).add(state);

//		for (int i = 0; i < 60; i++) {
//			Entity e = EcsUtil.getWorld().createEntity();
//			e.edit()
//					.add(new Physics(new Vector2(MathUtils.random(0,
//							R.WIDTH_SCREEN), MathUtils.random(0,
//							R.HEIGHT_SCREEN)), new Vector2()))
//					.add(new State(new Vector2(MathUtils.random(0,
//							R.WIDTH_SCREEN), MathUtils.random(0,
//							R.HEIGHT_SCREEN))));
//		}

		//
		// Entity entity5 = EcsUtil.getWorld().createEntity();
		// entity5.edit().add(new Script()).add(physics).add(state);
		//
		// Entity entity6 = EcsUtil.getWorld().createEntity();
		// entity6.edit().add(new Script()).add(physics).add(state);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		EcsUtil.update(Gdx.graphics.getDeltaTime());

		EcsUtil.getWorld().getSystem(StatusSystem.class)
				.drawFps(Gdx.graphics.getFramesPerSecond());
	}
}
