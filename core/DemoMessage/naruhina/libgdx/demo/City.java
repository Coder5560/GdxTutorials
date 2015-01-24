package naruhina.libgdx.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.utils.Array;

/** @author avianey */
public class City implements Telegraph {

	Array<House> houses;

	public City () {
		Gdx.app.log(City.class.getSimpleName(), "A new city is born...");
		houses = new Array<House>();
		MessageDispatcher.getInstance().addListeners(this, DemoMessageHandle.MSG_TIME_TO_ACT);
	}

	@Override
	public boolean handleMessage (Telegram msg) {
		// build a new house
		if (houses.size <= 10) {
			houses.add(new House());
		}
		return false;
	}
}