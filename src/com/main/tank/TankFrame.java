package com.main.tank;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import chainofresponsbility.BulletTankCollider;
import chainofresponsbility.BulletWallCollider;
import chainofresponsbility.Collider;

public class TankFrame extends Frame {
	
	public static final TankFrame Instance = new TankFrame();
	
	private Player mytank;
	private List<AbstractGameObject> objects;
	public static int GAME_WIDTH = 800;
	public static int GAME_HEIGHT = 600;
	private List<Collider> colliders;
	
	private TankFrame() {
		this.setTitle("Tank war");
		this.setLocation(400, 100);
		this.setSize(800, 600);
		this.setBackground(new Color(0, 0, 0));
		this.addKeyListener(new TankKeyListener());
		
		initiGameObjects();
		initColliders();
		
	}

	private void initiGameObjects() {
		
		objects = new ArrayList<>();
				
//		colliders.add(new BulletTankCollider());
//		colliders.add(new BulletWallCollider());

		 mytank = new Player(100,100,Dir.D,Group.good);
		 
		 for(int i=0; i<10; i++) {
			 objects.add(new Tank(100+50*i,200,Dir.D,Group.bad));
		 }
		 
		 this.add(new Wall(300,200,200,50));
	}
	

	private void initColliders() {

		colliders = new ArrayList<>();
		String[] collidersNames = ProperityMgr.get("Colliders").split(",");
		for(String name : collidersNames) {
			try {
				Class clazz = Class.forName("chainofresponsbility."+name);
				Collider c = (Collider)clazz.getConstructor().newInstance();
				colliders.add(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.white);
		g.drawString("objects.size:"+objects.size(), 200, 50);
//		g.drawString("Emeny.size:"+tanks.size(), 200, 100);

		g.setColor(Color.black);

		mytank.paint(g);
		
		for(int i=0; i<objects.size();i++) {
			
			if(!objects.get(i).isLive()) {
				objects.remove(i);
				break;
			}
			
			AbstractGameObject go1 = objects.get(i);
			for(int j=0; j<objects.size(); j++) {
				AbstractGameObject go2 = objects.get(j);
				for(Collider c : colliders) {
					c.Colide(go1, go2);
				}
			}
			if(objects.get(i).isLive()) {
				objects.get(i).paint(g);
			}
		}

		
//		for(int i=0; i<tanks.size(); i++) {
//			if(!tanks.get(i).isLive()) 
//				tanks.remove(i);
//			else {
//			tanks.get(i).paint(g);
//			}
//		}
//		
//		
//		Iterator<Bullet> iterator = bullets.iterator();
//        while (iterator.hasNext()) {
//            Bullet bullet = iterator.next();
//            if (bullet.inedge()) {
//            	for(Tank tank : tanks){
//            	bullet.colidesWithTank(tank);
//            	}
//    			bullet.paint(g);
//            	
//            }else {
//                iterator.remove();
//            }
//        }
        
        
//		Iterator<Explode> iexplode = explodes.iterator();
//		while(iexplode.hasNext()) {
//			Explode iexpolde = iexplode.next();
//			if(!iexpolde.isLive()) 
//				iexplode.remove();
//			else {
//				iexpolde.paint(g);
//			}
//		}
//		
//        for(int i=0; i<explodes.size(); i++) {
//			if(!explodes.get(i).isLive()) 
//				explodes.remove(i);
//			else {
//				explodes.get(i).paint(g);
//			}
//		}
//		
//		for(Bullet bullet : bullets) {
//			if(bullet.inedge())
//			bullet.paint(g);
//			else {
//				bullets.remove(bullet);
//			}
//			
//		}
		
	}
	
	public List<AbstractGameObject> getObjects() {
		return objects;
	}

	public void setObjects(List<AbstractGameObject> objects) {
		this.objects = objects;
	}

	public void add(AbstractGameObject go) {
		objects.add(go);
	}
	
//	public void add(Explode explode) {
//		 explodes.add(explode);
//	}
//	
//	public void add(Bullet bullet) {
//		bullets.add(bullet);
//	}
//	
//	public void delet(Bullet bullet) {
//		 bullets.remove(bullet);
//	}
//	
//	
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
