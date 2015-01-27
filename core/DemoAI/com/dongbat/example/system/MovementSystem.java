package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.dongbat.example.component.Physics;

@Wire
public class MovementSystem extends EntityProcessingSystem{
	
	ComponentMapper<Physics> physicsMapper;

	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Aspect.getAspectForAll(Physics.class));
	}

	@Override
	protected void process(Entity e) {
	}

}
