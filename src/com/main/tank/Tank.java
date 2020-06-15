package com.main.tank;
import java.awt.Graphics;

import com.main.tank.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank {

	private int x,y;
	private static final int SPEED=5;
	private Dir dir;
	private boolean bU,bR,bD,bL;
	private boolean moving = false;
	private Group group;
	
	TankFrame tf;
	
	public Tank(int x, int y, Dir dir,Group group) {
		this.x = x;
		this.y = y;
		this.dir= dir;
		this.group = group;
	}

	public void paint(Graphics g) {

		if(this.group == Group.good)
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
		if(this.group == Group.bad)
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
		if(x>750||y>550||x<50||y<50)return;
		
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
		Bullet bullet = new Bullet(this.x,this.y,this.dir,this.group);
		TankFrame.Instance.add(bullet);
	}
	
	
}
