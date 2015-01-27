package com.dongbat.example.component;

import com.artemis.Component;

public class TouchableComponent extends Component {
	private boolean touchable = false;

	public TouchableComponent() {
		super();
	}

	public TouchableComponent(boolean touchable) {
		super();
		this.touchable = touchable;
	}

	public boolean isTouchable() {
		return touchable;
	}

	public void setTouchable(boolean touchable) {
		this.touchable = touchable;
	}

}
