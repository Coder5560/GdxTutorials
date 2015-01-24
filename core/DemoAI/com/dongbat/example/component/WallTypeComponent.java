package com.dongbat.example.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
public class WallTypeComponent extends Component {

	private boolean	breakable;
	private float	hp;
	private Color	color;

	public WallTypeComponent(boolean breakable, float hp, Color color) {
		super();
		this.breakable = breakable;
		this.hp = hp;
		this.color = color;
	}

	public boolean isBreakable() {
		return breakable;
	}

	public void setBreakable(boolean breakable) {
		this.breakable = breakable;
	}

	public float getHp() {
		return hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
