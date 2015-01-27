package com.dongbat.example;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.dongbat.example.system.StatusSystem;
import com.dongbat.example.system.ViewportSystem;
import com.dongbat.example.util.EcsUtil;

public class AiDemo extends Game {
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		EcsUtil.getWorld().getSystem(ViewportSystem.class)
				.onGameResize(width, height);
	}

	@Override
	public void create() {
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
