package com.dongbat.example.util.steer;

import com.artemis.Entity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.Physics;

public class SteerEntity implements Steerable<Vector2> {

	Entity entity;

	Vector2 position = new Vector2();
	Vector2 linearVelocity = new Vector2();
	float rotation;
	float angularVelocity;
	float boundingRadius = 20;
	boolean tagged;

	float maxLinearSpeed = 100;
	float maxLinearAcceleration = 200;
	float maxAngularSpeed = 5;
	float maxAngularAcceleration = 10;

	boolean independentFacing;

	SteeringBehavior<Vector2> steeringBehavior;
	private static final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(
			new Vector2());

	
	
	public SteerEntity() {
		super();
	}

	public Entity getEntity() {
		return entity;
	}

	public SteerEntity setEntity(Entity entity) {
		this.entity = entity;
		return this;
	}

	public void process(float delta) {
		if (entity == null)
			return;
		
		// position.set(getX(Align.center), getY(Align.center));
		if (steeringBehavior != null) {
			// Calculate steering acceleration
			steeringBehavior.calculateSteering(steeringOutput);
			/*
			 * Here you might want to add a motor control layer filtering
			 * steering accelerations.
			 * 
			 * For instance, a car in a driving game has physical constraints on
			 * its movement: it cannot turn while stationary; the faster it
			 * moves, the slower it can turn (without going into a skid); it can
			 * brake much more quickly than it can accelerate; and it only moves
			 * in the direction it is facing (ignoring power slides).
			 */

			// Apply steering acceleration
			applySteering(steeringOutput, delta);

//			wrapAround(position, boundingRadius, boundingRadius);

			applyToEntity();
		}
	}

	public void applyToEntity() {
		Physics physics = entity.getComponent(Physics.class);
		if (physics == null)
			return;
		physics.setPosition(getPosition()).setVelocity(getLinearVelocity())
				.setRotation(getRotation());
	}

	// the display area is considered to wrap around from top to bottom
	// and from left to right
	protected static void wrapAround(Vector2 pos, float maxX, float maxY) {
		if (pos.x > maxX)
			pos.x = 0.0f;

		if (pos.x < 0)
			pos.x = maxX;

		if (pos.y < 0)
			pos.y = maxY;

		if (pos.y > maxY)
			pos.y = 0.0f;
	}

	private void applySteering(SteeringAcceleration<Vector2> steering,
			float time) {
		// Update position and linear velocity. Velocity is trimmed to maximum
		// speed
		position.mulAdd(linearVelocity, time);
		linearVelocity.mulAdd(steering.linear, time).limit(getMaxLinearSpeed());

		// Update orientation and angular velocity
		if (independentFacing) {
			setRotation(getRotation() + (angularVelocity * time)
					* MathUtils.radiansToDegrees);
			angularVelocity += steering.angular * time;
		} else {
			// If we haven't got any velocity, then we can do nothing.
			if (!linearVelocity.isZero(MathUtils.FLOAT_ROUNDING_ERROR)) {
				float newOrientation = vectorToAngle(linearVelocity);
				angularVelocity = (newOrientation - getRotation()
						* MathUtils.degreesToRadians)
						* time; // this is superfluous if
				// independentFacing is always true
				setRotation(newOrientation * MathUtils.radiansToDegrees);
			}
		}
	}

	public boolean isIndependentFacing() {
		return independentFacing;
	}

	public void setIndependentFacing(boolean independentFacing) {
		this.independentFacing = independentFacing;
	}

	public SteeringBehavior<Vector2> getSteeringBehavior() {
		return steeringBehavior;
	}

	public void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior) {
		this.steeringBehavior = steeringBehavior;
	}

	@Override
	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		this.maxLinearSpeed = maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		return maxLinearAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		this.maxLinearAcceleration = maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed() {
		return this.maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		this.maxAngularSpeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration() {

		return maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		this.maxAngularAcceleration = maxAngularAcceleration;
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public float getOrientation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public float getRotation() {
		return rotation;
	}

	@Override
	public Vector2 getLinearVelocity() {
		return linearVelocity;
	}

	@Override
	public float getAngularVelocity() {
		return angularVelocity;
	}

	@Override
	public float getBoundingRadius() {
		return boundingRadius;
	}

	@Override
	public boolean isTagged() {
		return tagged;
	}

	@Override
	public void setTagged(boolean tagged) {
		this.tagged = tagged;
	}

	@Override
	public Vector2 newVector() {
		return new Vector2();
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		return (float) Math.atan2(-vector.x, vector.y);
	}

	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		outVector.x = -(float) Math.sin(angle);
		outVector.y = (float) Math.cos(angle);
		return outVector;
	}

}
