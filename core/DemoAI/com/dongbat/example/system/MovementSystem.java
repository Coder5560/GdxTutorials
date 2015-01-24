package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.State;

@Wire
public class MovementSystem extends EntityProcessingSystem{
	
	private ComponentMapper<Physics> physicsMapper;
	private ComponentMapper<State> stateMapper;

	public MovementSystem() {
		super(Aspect.getAspectForAll(Physics.class, State.class));
	}

	@Override
	protected void process(Entity e) {
		
		Physics physics = physicsMapper.get(e);
		State state = stateMapper.get(e);
		Vector2 target = state.getTarget();
		
		if(target == null) {
			physics.setVelocity(new Vector2());
		} else {
			Vector2 distance = target.cpy().sub(physics.getPosition());
			if(distance.len2() < (100 * world.delta * 100 * world.delta)) {
				physics.setPosition(target.cpy());
				physics.setVelocity(new Vector2());
			} else {
				physics.setVelocity(distance.nor().scl(100));
			}
		}
		if(Gdx.input.justTouched()){
			spawn(e);
		}
	}

	public void spawn(Entity e){
		State state = stateMapper.get(e);
		state.setTarget(new Vector2(MathUtils.random(-200, 200), MathUtils.random(-200, 200)));
	}
}
