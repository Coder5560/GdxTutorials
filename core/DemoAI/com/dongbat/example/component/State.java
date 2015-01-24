package com.dongbat.example.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class State extends Component {

	private Vector2	target;

	public Vector2 getTarget() {
		return target;
	}

	public void setTarget(Vector2 target) {
		this.target = target;
	}

	public State() {

	}

	public State(Vector2 target) {
		this.target = target;
	}

}
