package com.main.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends AbstractGameObject {

	private int x;
	private int y;
	private int w;
	private int h;
	
	
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getW() {
		return w;
	}


	public void setW(int w) {
		this.w = w;
	}


	public int getH() {
		return h;
	}


	public void setH(int h) {
		this.h = h;
	}


	public Wall(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}


	@Override
	public void paint(Graphics g) {

		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}


	@Override
	protected boolean isLive() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
