package com.dongbat.example.component;

import com.artemis.Component;

public class BoundingComponent extends Component {
	public enum BoundType {
		POLYGONE, CIRCLE, RECTANGLE, POLYLINE
	}

	private BoundType	boundType	= BoundType.RECTANGLE;
	private float		radius;
	private float		width;
	private float		height;

	public BoundingComponent(float width, float height) {
		super();
		this.width = width;
		this.height = height;
		boundType = BoundType.RECTANGLE;
	}

	public BoundingComponent(float radius) {
		super();
		this.radius = radius;
		this.boundType = BoundType.CIRCLE;
	}

	public BoundType getBoundType() {
		return boundType;
	}

	public void setBoundType(BoundType boundType) {
		this.boundType = boundType;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
