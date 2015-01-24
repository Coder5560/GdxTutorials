package com.dongbat.example.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.InputComponent;
import com.dongbat.example.util.R;

@Wire
public class InputHandleSystem extends VoidEntitySystem implements
		GestureListener {
	ComponentMapper<InputComponent>	im;
	private boolean					touchdown	= false;
	private boolean					touchup		= false;
	private boolean					tap			= false;
	private boolean					longpress	= false;
	private boolean					fling		= false;
	private boolean					pan			= false;
	private boolean					panstop		= false;

	private float					x;
	private float					y;
	private float					deltaX;
	private float					deltaY;
	private float					velocityX;
	private float					velocityY;
	private int						pointer;
	private int						button;
	private int						count;

	public InputHandleSystem() {
		super();
	}

	private void resetParameter() {
		touchdown = false;
		touchup = false;
		tap = false;
		longpress = false;
		fling = false;
		pan = false;
		panstop = false;
	}

	@Override
	protected void processSystem() {

	}

	public boolean hit(Rectangle rect) {
		if (isTouchdown())
			return rect.contains(x, y);
		return false;
	}

	public boolean hit(Vector2 position, float width, float height) {
		if (!isTouchdown())
			return false;
		Vector2 pos = new Vector2(position.x, R.HEIGHT_SCREEN - position.y);
		return (x <= pos.x + width / 2) && (x >= pos.x - width / 2)
				&& (y <= pos.y + height / 2) && (y >= pos.y - height / 2);

	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		resetParameter();
		touchdown = true;
		this.x = x;
		this.y = y;
		return true;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		resetParameter();
		this.x = x;
		this.y = y;
		this.count = count;
		this.button = button;
		this.tap = true;
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		resetParameter();
		this.x = x;
		this.y = y;
		this.longpress = true;
		return true;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		resetParameter();
		this.velocityX = velocityX;
		this.velocityY = velocityX;
		this.button = button;
		this.fling = true;
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		resetParameter();
		this.x = x;
		this.y = y;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.pan = true;
		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		resetParameter();
		this.x = x;
		this.y = y;
		this.pointer = pointer;
		this.button = button;
		this.panstop = true;
		return true;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	public boolean isTouchdown() {
		return touchdown;
	}

	public void setTouchdown(boolean touchdown) {
		this.touchdown = touchdown;
	}

	public boolean isTouchup() {
		return touchup;
	}

	public void setTouchup(boolean touchup) {
		this.touchup = touchup;
	}

	public boolean isTap() {
		return tap;
	}

	public void setTap(boolean tap) {
		this.tap = tap;
	}

	public boolean isLongpress() {
		return longpress;
	}

	public void setLongpress(boolean longpress) {
		this.longpress = longpress;
	}

	public boolean isFling() {
		return fling;
	}

	public void setFling(boolean fling) {
		this.fling = fling;
	}

	public boolean isPan() {
		return pan;
	}

	public void setPan(boolean pan) {
		this.pan = pan;
	}

	public boolean isPanstop() {
		return panstop;
	}

	public void setPanstop(boolean panstop) {
		this.panstop = panstop;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(float deltaX) {
		this.deltaX = deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(float deltaY) {
		this.deltaY = deltaY;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public int getButton() {
		return button;
	}

	public void setButton(int button) {
		this.button = button;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
