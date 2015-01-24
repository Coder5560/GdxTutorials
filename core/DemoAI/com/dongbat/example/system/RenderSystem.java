package com.dongbat.example.system;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RenderSystem {
	private ShapeRenderer	shapeRenderer;
	private SpriteBatch		spriteBatch;
	public RenderSystem(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
		super();
		this.shapeRenderer = shapeRenderer;
		this.spriteBatch = spriteBatch;
	}
	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}
	public void setShapeRenderer(ShapeRenderer shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
	}
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
	public void setSpriteBatch(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}
	
}
