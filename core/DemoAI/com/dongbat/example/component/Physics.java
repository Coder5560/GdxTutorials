package com.dongbat.example.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Physics extends Component {

	private Vector2	position;
	private Vector2	velocity;
	private float rotation;

	public Physics() {
	}

	public Physics(Vector2 position, Vector2 velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	public Physics(Vector2 position, Vector2 velocity, float rotation) {
		super();
		this.position = position;
		this.velocity = velocity;
		this.rotation = rotation;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Physics setPosition(Vector2 position) {
		this.position = position;
		return this;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public Physics setVelocity(Vector2 velocity) {
		this.velocity = velocity;
		return this;
	}

	public float getRotation() {
		return rotation;
	}

	public Physics setRotation(float rotation) {
		this.rotation = rotation;
		return this;
	}

	
}
