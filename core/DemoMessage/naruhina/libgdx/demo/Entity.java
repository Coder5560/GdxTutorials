package naruhina.libgdx.demo;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.TelegramProvider;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Entity extends Image implements TelegramProvider, Telegraph {

	public Entity() {
		super();
	}

	public Entity(Texture texture) {
		super(texture);
	}


	@Override
	public boolean handleMessage(Telegram msg) {
		return true;
	}

	@Override
	public Object provideMessageInfo(int msg, Telegraph receiver) {
		return this;
	}

}
