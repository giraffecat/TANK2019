package com.main.tank;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Bullet {

	private int x,y;
	private Dir dir;
	private final static int SPEED=10;
	private Group group;
	
	public Bullet(int x, int y, Dir dir,Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
	}
	
	public void paint(Graphics g) {

		switch(dir){
		case L:
			g.drawImage(ResourceMgr.bulletL, x, y, null, null);
			break;
		case R:
			g.drawImage(ResourceMgr.bulletR, x, y, null, null);
			break;
		case U:
			g.drawImage(ResourceMgr.bulletU, x, y, null, null);
			break;
		case D:
			g.drawImage(ResourceMgr.bulletD, x, y, null, null);
			break;
	}
		
		move();
	}

	private void move() {

		switch(dir){
			case L:
				x -= SPEED;
				break;
			case R:
				x += SPEED;
				break;
			case U:
				y -= SPEED;
				break;
			case D:
				y += SPEED;
				break;
		}
	}
	
	public boolean inedge() {
		if(x>800||y>600)
		return false;
		return true;
		
	}
}
