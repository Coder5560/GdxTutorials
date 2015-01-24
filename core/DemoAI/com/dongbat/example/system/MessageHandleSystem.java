package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.dongbat.example.component.MessageHandleComponent;

@Wire
public class MessageHandleSystem extends EntityProcessingSystem {
	ComponentMapper<MessageHandleComponent> mm;

	public MessageHandleSystem() {
		super(Aspect.getAspectForOne(MessageHandleComponent.class));
	}

	@Override
	protected void begin() {
		super.begin();
	}

	@Override
	protected void process(Entity e) {
		
	}
	
	@Override
	protected void end() {
		super.end();
	}
}
