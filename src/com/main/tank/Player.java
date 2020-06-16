package com.main.tank;
import java.awt.Graphics;

import com.main.tank.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	private int x,y;
	private static final int SPEED=5;
	private Dir dir;
	private boolean bU,bR,bD,bL;
	private boolean moving = false;
	private Group group;
	private boolean Live = true;
	private int width, height;

	
	public boolean isLive() {
		return Live;
	}

	public void setLive(boolean live) {
		Live = live;
	}

	TankFrame tf;
	
	public Player(int x, int y, Dir dir,Group group) {
		this.x = x;
		this.y = y;
		this.dir= dir;
		this.group = group;
		this.width = ResourceMgr.goodTankD.getWidth();
		this.height = ResourceMgr.goodTankD.getHeight();


	}

	public void paint(Graphics g) {
		
		if(!this.Live) return;

		switch(dir){
		case L:
			g.drawImage(ResourceMgr.goodTankL, x, y, null, null);
			break;
		case R:
			g.drawImage(ResourceMgr.goodTankR, x, y, null, null);
			break;
		case U:
			g.drawImage(ResourceMgr.goodTankU, x, y, null, null);
			break;
		case D:
			g.drawImage(ResourceMgr.goodTankD, x, y, null, null);
			break;
	}
		
		move();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_A:
			bL = true;
			break;
		case KeyEvent.VK_D:
			bR = true;
			break;
		case KeyEvent.VK_W:
			bU = true;
			break;
		case KeyEvent.VK_S:
			bD = true;
			break;
		
		}
		setMainDir();
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

	private void move() {
		if(!moving) return;
		
		switch(dir){
		case L:
			if(x>0)
			x -= SPEED;
			break;
		case R:
			if(x+width<TankFrame.GAME_WIDTH)
			x += SPEED;
			break;
		case U:
			if(y>0)
			y -= SPEED;
			break;
		case D:
			if(y+height<TankFrame.GAME_HEIGHT)
			y += SPEED;
			break;
	}
		
		
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

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_A:
			bL = false;
			break;
		case KeyEvent.VK_D:
			bR = false;
			break;
		case KeyEvent.VK_W:
			bU = false;
			break;
		case KeyEvent.VK_S:
			bD = false;
			break;
		case KeyEvent.VK_CONTROL:
			fire();
			break;
		
		
		}
		setMainDir();
	}

	private void fire() {
		int bx = x + ResourceMgr.goodTankD.getWidth()/2 - ResourceMgr.bulletD.getWidth()/2;
		int by = y + ResourceMgr.goodTankD.getHeight()/2 - ResourceMgr.bulletD.getHeight()/2;

		Bullet bullet = new Bullet(bx,by,this.dir,this.group);
		TankFrame.Instance.add(bullet);
	}

	public void die() {

		this.setLive(false);
	}
	
	
}
