package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.Script;
import com.dongbat.example.component.State;

@Wire
public class ScriptSystem extends EntityProcessingSystem {
	private ComponentMapper<Script>	scriptMapper;
	private static float			accumulate	= 0;
	private static final float		STEP		= 0.15f;

	public ScriptSystem() {
		super(Aspect.getAspectForAll(Script.class, Physics.class, State.class));
	}

	// Process by 5*time step

	@Override
	protected void process(Entity e) {
		if (scriptMapper == null) {
			System.out.println("AAAAAA");
		}

		accumulate += e.getWorld().delta;
		while (accumulate > STEP) {
			accumulate -= STEP;
			Script script = scriptMapper.get(e);
			script.update(e);
		}
	}

}
