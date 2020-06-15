package com.main.tank;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
	
	public static final TankFrame Instance = new TankFrame();
	private Tank mytank;
	private Tank enemy;
	private List<Bullet> bullets;
	
	private TankFrame() {
		this.setTitle("Tank war");
		this.setLocation(400, 100);
		this.setSize(800, 600);
		this.setBackground(new Color(0, 0, 0));
		this.addKeyListener(new TankKeyListener());
		
		 mytank = new Tank(100,100,Dir.D,Group.good);
		 enemy = new Tank(200,200,Dir.D,Group.bad);
		 bullets = new ArrayList<>();
	}

	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.white);
		g.drawString("Bullets.size:"+bullets.size(), 200, 50);
		g.setColor(Color.black);

		mytank.paint(g);
		enemy.paint(g);
		
		for(Bullet bullet : bullets) {
			if(bullet.inedge())
			bullet.paint(g);
			else {
				bullets.remove(bullet);
			}
			
		}
		
	}
	
	public void add(Bullet bullet) {
		 bullets.add(bullet);
	}
	
	public void delet(Bullet bullet) {
		 bullets.remove(bullet);
	}
	
	
	private class TankKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			
			mytank.keyPressed(e);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			mytank.keyReleased(e);
		}
		
		
	}
	
}
