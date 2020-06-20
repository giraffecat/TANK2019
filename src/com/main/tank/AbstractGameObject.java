package com.main.tank;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class AbstractGameObject implements Serializable {

	public abstract void paint(Graphics g);

	protected abstract boolean isLive();
}
