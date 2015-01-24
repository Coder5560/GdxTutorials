package com.dongbat.example.component;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.TelegramProvider;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.MathUtils;
import com.dongbat.example.util.MessageCode;

public class MessageHandleComponent extends Component implements
		TelegramProvider, Telegraph {
	Entity e;
	public MessageHandleComponent(Entity e) {
		super();
		this.e = e;
		MessageDispatcher.getInstance().addListener(this,
				MessageCode.MS_ASK_NEAR_NEIGHBOUR);
		MessageDispatcher.getInstance().addProvider(this,
				MessageCode.MS_ASK_NEAR_NEIGHBOUR);
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		Physics psc = e.getComponent(Physics.class);
		StatusComponent sc = e.getComponent(StatusComponent.class);
		sc.setStatus("I'm at "+ psc.getPosition().toString());
		sc.setTimeExpire(1f);
		return true;
	}

	@Override
	public Object provideMessageInfo(int msg, Telegraph receiver) {
		float timeExpire = 1;
		if (msg == MessageCode.MS_ASK_NEAR_NEIGHBOUR) {
				timeExpire = MathUtils.random(0, 2f);
		}
		return timeExpire;
	}
}
