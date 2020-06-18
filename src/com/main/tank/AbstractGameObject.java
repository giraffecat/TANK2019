package com.main.tank;

import java.awt.Graphics;

public abstract class AbstractGameObject {

	public abstract void paint(Graphics g);

	protected abstract boolean isLive();
}
