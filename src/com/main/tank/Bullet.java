package com.main.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Bullet extends AbstractGameObject {

	private int x,y;
	private Dir dir;
	private final static int SPEED=10;
	private Group group;
	private boolean Live = true;
	
	public boolean isLive() {
		return Live;
	}

	public void setLive(boolean live) {
		Live = live;
	}

	public Bullet(int x, int y, Dir dir,Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
	}
	
	public void paint(Graphics g) {
		if(!isLive()) return;
		
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
		boundcheck();
	}
	
	public void boundcheck() {
		if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			this.setLive(false);
		}
	}
	
	public void colidesWithTank(Tank tank){
		
		if(!tank.isLive()||!this.isLive()) return;
		if(this.group == tank.getGroup()) return;
		Rectangle rect = new Rectangle(x,y,ResourceMgr.bulletU.getWidth(),ResourceMgr.bulletU.getHeight());
		Rectangle rectRank = new Rectangle(tank.getX(),tank.getY(),
				ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
		
		if(rect.intersects(rectRank)) {
			this.die();
			tank.die();
		}
				
	}
	
	public void colidesWithWall(Wall wall){
			
			Rectangle rect = new Rectangle(x,y,ResourceMgr.bulletU.getWidth(),ResourceMgr.bulletU.getHeight());
			Rectangle rectWall = new Rectangle(wall.getX(),wall.getY(),wall.getW(),wall.getH());
			
			if(rect.intersects(rectWall)) {
				this.die();
			}
					
		}
		
	

	public void die() {
		this.setLive(false);
	}
	
}
