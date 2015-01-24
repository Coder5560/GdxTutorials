package com.dongbat.example.system;

import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dongbat.example.util.R;

@Wire
public class ViewportSystem extends VoidEntitySystem {
	private OrthographicCamera	gameCamera;
	private OrthographicCamera	uiCamera;
	private Viewport			gameViewport;
	private Viewport			uiViewport;

	public ViewportSystem() {
		super();

		gameCamera = new OrthographicCamera(R.WIDTH_SCREEN, R.HEIGHT_SCREEN);
		uiCamera = new OrthographicCamera(R.WIDTH_SCREEN, R.HEIGHT_SCREEN);

		gameViewport = new StretchViewport(R.WIDTH_SCREEN, R.HEIGHT_SCREEN,
				gameCamera);
		uiViewport = new StretchViewport(R.WIDTH_SCREEN, R.HEIGHT_SCREEN,
				uiCamera);

	}

	public void onGameResize(int width, int height) {
		gameViewport.update(width, height, true);
		uiViewport.update(width, height, true);
	}

	@Override
	protected void processSystem() {

	}

	public Viewport getGameViewport() {
		return gameViewport;
	}

	public void setGameViewport(Viewport gameViewport) {
		this.gameViewport = gameViewport;
	}

	public Viewport getUiViewport() {
		return uiViewport;
	}

	public void setUiViewport(Viewport uiViewport) {
		this.uiViewport = uiViewport;
	}

}
