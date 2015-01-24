package naruhina.libgdx.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.utils.Array;

/** @author avianey */
public class House implements Telegraph {

	static int		NUM	= 0;

	Array<Citizen>	citizens;
	final int		num;

	public House() {
		num = NUM++;
		citizens = new Array<Citizen>();
		Gdx.app.log(House.class.getSimpleName() + " " + num,
				"New house in town");
		// Mr & Mrs
		citizens.add(new Citizen(this));
		citizens.add(new Citizen(this));
		MessageDispatcher.getInstance().addListeners(this,
				DemoMessageHandle.MSG_TIME_TO_ACT);
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		if (citizens.size < 3) {
			// new child
			Gdx.app.log(House.class.getSimpleName() + " " + num,
					"We're having a baby!");
			citizens.add(new Citizen(this));
		}
		return false;
	}
}