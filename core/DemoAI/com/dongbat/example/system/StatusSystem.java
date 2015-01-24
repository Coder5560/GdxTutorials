package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.StatusComponent;

@Wire
public class StatusSystem extends EntityProcessingSystem {
	ComponentMapper<StatusComponent>	sm;
	ComponentMapper<Physics>			pm;
	ViewportSystem						viewportSystem;
	SpriteBatch							batch;
	BitmapFont							font;

	public StatusSystem() {
		super(Aspect.getAspectForOne(StatusComponent.class));
	}

	@Override
	protected void initialize() {
		super.initialize();
		batch = new SpriteBatch();
		font = new BitmapFont();
	}

	@Override
	protected void begin() {
		super.begin();
		batch.setProjectionMatrix(viewportSystem.getGameViewport().getCamera().combined);
		batch.begin();
	}

	@Override
	protected void process(Entity e) {
		StatusComponent statusComponent = sm.get(e);
		Physics physics = pm.get(e);
		if (pm != null) {
			if (statusComponent.isSaying()) {
				say(statusComponent.getStatus(), physics.getPosition(), font,
						batch);
				statusComponent.setTimeExpire(statusComponent.getTimeExpire()
						- world.delta);
			}
		}
	}

	private void say(String message, Vector2 position, BitmapFont font,
			SpriteBatch batch) {
		float w = font.getBounds(message).width;
		float h = font.getBounds(message).height;
		float wrapped = 160;
		font.drawWrapped(batch, message, position.x
				- ((wrapped < w) ? wrapped / 2 : w / 2), position.y - h / 2
				+ 40, wrapped);
	}

	@Override
	protected void end() {
		super.end();
		batch.end();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public void drawFps(int framesPerSecond) {

		batch.begin();
		font.draw(batch, "Fps : " + framesPerSecond, 730, 460);
		batch.end();

	}

}
