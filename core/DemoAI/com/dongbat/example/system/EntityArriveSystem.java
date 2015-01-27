package com.dongbat.example.system;

import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.Physics;
import com.dongbat.example.util.steer.SteerEntity;

@Wire
public class EntityArriveSystem extends VoidEntitySystem implements
		GestureListener {
	ViewportSystem viewportSystem;

	Vector2 touchPoint = new Vector2();
	SteerEntity player, target;

	public EntityArriveSystem() {
		super();
	}

	public void createBehaviors() {
		final Arrive<Vector2> arriveSB = new Arrive<Vector2>(player, target)
				.setTimeToTarget(0.1f).setArrivalTolerance(0.001f)
				.setDecelerationRadius(80);
		player.setSteeringBehavior(arriveSB);
	}

	@Override
	protected void processSystem() {
		if (player == null && target == null)
			return;
		player.process(world.delta);
		target.process(world.delta);

	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		viewportSystem.getGameViewport().unproject(touchPoint.set(x, y));
		target.getEntity().getComponent(Physics.class).setPosition(touchPoint);
		return true;
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

	public Entity getPlayer() {
		return player.getEntity();
	}

	public EntityArriveSystem setPlayer(Entity player) {
		this.player = new SteerEntity();
		this.player.setEntity(player);
		return this;
	}

	public Entity getTarget() {
		return target.getEntity();
	}

	public EntityArriveSystem setTarget(Entity target) {
		this.target = new SteerEntity();
		this.target.setEntity(target);
		return this;
	}

}
