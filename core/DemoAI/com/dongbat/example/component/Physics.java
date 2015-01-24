package com.dongbat.example.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Physics extends Component {

	private Vector2	position;
	private Vector2	velocity;

	public Physics() {
	}

	public Physics(Vector2 position, Vector2 velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

}
