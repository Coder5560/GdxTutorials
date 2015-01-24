package naruhina.libgdx.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dongbat.example.util.R;

public class DemoMessageHandle extends Game {
	Stage		stage;
	Viewport	viewport;

	public static final int MSG_TIME_TO_ACT = 0;
	public static final int MSG_EXISTING_CITIZEN = 1;City city;
	float elapsedTime;
	public DemoMessageHandle() {
		super();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height, true);
	}

	@Override
	public void create() {
		viewport = new StretchViewport(R.WIDTH_SCREEN, R.HEIGHT_SCREEN);
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		Texture icon = new Texture(Gdx.files.internal("badlogic.jpg"));
		for (int i = 0; i < 10; i++) {
			final Entity entity = new Entity(icon);
			entity.setSize(40, 40);
			entity.setPosition(MathUtils.random(0, R.WIDTH_SCREEN),
					MathUtils.random(0, R.HEIGHT_SCREEN));
			entity.addListener(new ClickListener(){
				
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					entity.clearActions();
					entity.setOrigin(Align.center);
					entity.addAction(Actions.sequence(Actions.scaleTo(1.2f, 1.2f, .05f), Actions.sequence(Actions.scaleTo(1f, 1f, .05f), Actions.run(new Runnable() {
						@Override
						public void run() {
						}
					}))));
				}
				
			});
			stage.addActor(entity);
		}
		elapsedTime = 0;
		// build a new city
		city = new City();
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		elapsedTime += Gdx.graphics.getRawDeltaTime();
		if (elapsedTime > 0.8f) {
			MessageDispatcher.getInstance().dispatchMessage(null, MSG_TIME_TO_ACT);
			elapsedTime = 0;
		}
	}
}
