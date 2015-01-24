package com.dongbat.example.util;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.BoundingComponent;
import com.dongbat.example.component.InputComponent;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.StatusComponent;
import com.dongbat.example.component.WallTypeComponent;

public class MapGenerator {
	private TiledMap	map;

	public MapGenerator() {
		super();
	}

	private void load(int level) {
		switch (level) {
			case 0:
			default:
				map = new TmxMapLoader().load("map.tmx");
				break;
		}
	}

	public void generate(int level, World world) {
		load(level);
		MapObjects objects = map.getLayers().get("map").getObjects();
		for (MapObject mapObject : objects) {
			if (mapObject instanceof RectangleMapObject) {

				Rectangle bound = ((RectangleMapObject) mapObject)
						.getRectangle();
				Vector2 position = new Vector2();
				bound.getCenter(position);
				Entity entity = world.createEntity().edit()
						.add(new Physics(position, new Vector2()))
						.add(new BoundingComponent(bound.width, bound.height))
						.add(new WallTypeComponent(true, 5, Color.GRAY))
						.add(new InputComponent(true))
						.add(new StatusComponent("", 1)).getEntity();
				world.getManager(GroupManager.class).add(entity, "wall");
			}
		}
		map.dispose();
	}
}
