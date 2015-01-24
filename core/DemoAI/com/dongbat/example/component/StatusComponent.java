package com.dongbat.example.component;

import com.artemis.Component;

public class StatusComponent extends Component {
	private String			status;
	private float	timeExpire;

	public StatusComponent(String status, float timeExpire) {
		super();
		this.status = status;
		this.timeExpire = timeExpire;
	}
	
	public boolean isSaying(){
		return timeExpire >0 && !status.equalsIgnoreCase("");
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(float timeExpire) {
		this.timeExpire = timeExpire;
	}
	
}