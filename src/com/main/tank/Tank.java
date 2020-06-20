package com.main.tank;
import java.awt.Graphics;

import com.main.tank.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tank extends AbstractGameObject {

	private int x,y;
	private static final int SPEED=5;
	private Dir dir;
	private boolean bU,bR,bD,bL;
	private boolean moving = true;
	private Group group;
	private boolean Live = true;
	private int width, height;
	private int OldX,OldY;

	
	public boolean isLive() {
		return Live;
	}

	public void setLive(boolean live) {
		Live = live;
	}

	TankFrame tf;
	
	public Tank(int x, int y, Dir dir,Group group) {
		this.x = x;
		this.y = y;
		this.dir= dir;
		this.group = group;
		this.width = ResourceMgr.goodTankD.getWidth();
		this.height = ResourceMgr.goodTankD.getHeight();
	}

	public void paint(Graphics g) {
		
		if(!this.isLive()) 
		 return;
		
			switch(dir){
			case L:
				g.drawImage(ResourceMgr.badTankL, x, y, null, null);
				break;
			case R:
				g.drawImage(ResourceMgr.badTankR, x, y, null, null);
				break;
			case U:
				g.drawImage(ResourceMgr.badTankU, x, y, null, null);
				break;
			case D:
				g.drawImage(ResourceMgr.badTankD, x, y, null, null);
				break;
		}
		
		move();
	}

	
	private void setMainDir() {

		if(!bU && !bR && !bD && !bL)
			moving = false;
		else {
			moving = true;
		}
		if(bU && !bR && !bD && !bL)
			dir = Dir.U;
		if(!bU && bR && !bD && !bL)
			dir = Dir.R;
		if(!bU && !bR && bD && !bL)
			dir = Dir.D;
		if(!bU && !bR && !bD && bL)
			dir = Dir.L;
	}

	public void boundcheck() {
		if (x < 0 || y < 30 || x+width > TankFrame.GAME_WIDTH || y+height > TankFrame.GAME_HEIGHT) {
			this.back();
		}
	}
	
	
	public void back() {
		this.x = OldX;
		this.y = OldY;
	}

	private void move() {
		if(!moving) return;
		OldX = x;
		OldY = y;
		
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
		randomDir();
		
		Random rf = new Random();
		if(rf.nextInt(100)>95)
		fire();
		
	}
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	private Random r = new Random();
	private void randomDir() {

		if(r.nextInt(100)>95)
		this.dir = Dir.randomDir();

		
	}

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


	private void fire() {
		int bx = x + width/2 - Bullet.W/2;
		int by = y + height/2 - Bullet.H/2;

		Bullet bullet = new Bullet(bx,by,this.dir,this.group);
		TankFrame.Instance.getGm().add(bullet);
	}

	public void die() {
		int ex = x + width/2 - Bullet.W/2;
		int ey = y + height/2 - Bullet.H/2;

		this.setLive(false);
		TankFrame.Instance.getGm().add(new Explode(ex,ey));
		
	}
	
	
}
