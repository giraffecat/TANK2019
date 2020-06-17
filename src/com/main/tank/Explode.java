package com.main.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Explode {

	private int x,y;
	private int width;
	private int height;
	private int step = 0;
	private boolean Live = true;
	
	

	public Explode(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.width =  ResourceMgr.explodes[0].getWidth();
		this.height =  ResourceMgr.explodes[0].getHeight();
		
		new Thread(()->new Audio("audio/explode.wav").play()).start();

	}
	
	public boolean isLive() {
		return Live;
	}

	public void setLive(boolean live) {
		Live = live;
	}
	
	public void paint(Graphics g) {

		if(step >=ResourceMgr.explodes.length) {
			setLive(false);
			return;
		}
		g.drawImage(ResourceMgr.explodes[step], x, y, null);
		step++;
			
		
	}

}
	
	