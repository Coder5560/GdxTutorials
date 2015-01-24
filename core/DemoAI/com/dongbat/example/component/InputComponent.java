package com.dongbat.example.component;

import com.artemis.Component;

public class InputComponent extends Component {
	private boolean	touchable	= false;

	public InputComponent(boolean touchable) {
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
