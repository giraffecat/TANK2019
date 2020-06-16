package com.main.tank;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TankFrame extends Frame {
	
	public static final TankFrame Instance = new TankFrame();
	private Player mytank;
	private List<Tank> tanks;
	private List<Bullet> bullets;
	private List<Explode> explodes;
	public static int GAME_WIDTH = 800;
	public static int GAME_HEIGHT = 600;

	
	private TankFrame() {
		this.setTitle("Tank war");
		this.setLocation(400, 100);
		this.setSize(800, 600);
		this.setBackground(new Color(0, 0, 0));
		this.addKeyListener(new TankKeyListener());
		
		initiGameObjects();
		
		
	}

	private void initiGameObjects() {
		 tanks = new ArrayList<>();
		 bullets = new ArrayList<>();		

		 mytank = new Player(100,100,Dir.D,Group.good);
		 explodes = new ArrayList<>(); 
		 
		 for(int i=0; i<10; i++) {
			 tanks.add(new Tank(100+50*i,200,Dir.D,Group.bad));
		 }
	}

	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.white);
		g.drawString("Bullets.size:"+bullets.size(), 200, 50);
		g.drawString("Emeny.size:"+tanks.size(), 200, 100);

		g.setColor(Color.black);

		mytank.paint(g);

		for(int i=0; i<tanks.size(); i++) {
			if(!tanks.get(i).isLive()) 
				tanks.remove(i);
			else {
			tanks.get(i).paint(g);
			}
		}
		
		
		Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.inedge()) {
            	for(Tank tank : tanks){
            	bullet.colidesWithTank(tank);
            	}
    			bullet.paint(g);
            	
            }else {
                iterator.remove();
            }
        }
        
        
//		Iterator<Explode> iexplode = explodes.iterator();
//		while(iexplode.hasNext()) {
//			Explode iexpolde = iexplode.next();
//			if(!iexpolde.isLive()) 
//				iexplode.remove();
//			else {
//				iexpolde.paint(g);
//			}
//		}
		
        for(int i=0; i<explodes.size(); i++) {
			if(!explodes.get(i).isLive()) 
				explodes.remove(i);
			else {
				explodes.get(i).paint(g);
			}
		}
		
//		for(Bullet bullet : bullets) {
//			if(bullet.inedge())
//			bullet.paint(g);
//			else {
//				bullets.remove(bullet);
//			}
//			
//		}
		
	}
	
	public void add(Explode explode) {
		 explodes.add(explode);
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
