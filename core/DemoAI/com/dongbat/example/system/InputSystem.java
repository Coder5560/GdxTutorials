package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.BoundingComponent;
import com.dongbat.example.component.InputComponent;
import com.dongbat.example.component.MessageHandleComponent;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.StatusComponent;
import com.dongbat.example.util.MessageCode;

@Wire
public class InputSystem extends EntityProcessingSystem {
	ComponentMapper<Physics> pm;
	ComponentMapper<BoundingComponent> bm;
	ComponentMapper<InputComponent> im;
	ComponentMapper<MessageHandleComponent> mm;
	ComponentMapper<StatusComponent> sm;
	InputHandleSystem inputHandleSystem;

	@SuppressWarnings("unchecked")
	public InputSystem() {
		super(Aspect.getAspectForAll(Physics.class, BoundingComponent.class,
				MessageHandleComponent.class, InputComponent.class));
	}

	@Override
	protected void process(Entity e) {
		InputComponent inputComponent = im.get(e);
		if (inputComponent.isTouchable()) {
			Physics physics = pm.get(e);
			BoundingComponent boundingComponent = bm.get(e);
			Vector2 position = physics.getPosition();

			if (inputHandleSystem.hit(position, boundingComponent.getWidth(),
					boundingComponent.getHeight())) {
				StatusComponent statusComponent = sm.get(e);
				MessageHandleComponent messageHandleComponent = mm.get(e);
				MessageDispatcher.getInstance().dispatchMessage(
						messageHandleComponent,
						MessageCode.MS_ASK_NEAR_NEIGHBOUR);
				statusComponent.setStatus("Hey guys,Where are you?");
				statusComponent.setTimeExpire(1f);
			}
		}
	}

}
