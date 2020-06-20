package com.main.tank;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Assert;

import chainofresponsbility.BulletTankCollider;
import chainofresponsbility.BulletWallCollider;
import chainofresponsbility.Collider;
import chainofresponsbility.Colliderchain;

public class TankFrame extends Frame {
	
	public static final TankFrame Instance = new TankFrame();
	
	public static int GAME_WIDTH = 800;
	public static int GAME_HEIGHT = 600;
//	private List<Collider> colliders;
	private GameModel gm = new GameModel(); 
	
	public GameModel getGm() {
		return gm;
	}


	public void setGm(GameModel gm) {
		this.gm = gm;
	}


	private TankFrame() {
		this.setTitle("Tank war");
		this.setLocation(400, 100);
		this.setSize(800, 600);
		this.setBackground(new Color(0, 0, 0));
		this.addKeyListener(new TankKeyListener());
		
//		initColliders();
		
	}

	
//	private void initColliders() {
//
//		colliders = new ArrayList<>();
//		String[] collidersNames = ProperityMgr.get("Colliders").split(",");
//		for(String name : collidersNames) {
//			try {
//				Class clazz = Class.forName("chainofresponsbility."+name);
//				Collider c = (Collider)clazz.getConstructor().newInstance();
//				colliders.add(c);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	@Override
	public void paint(Graphics g) {
		
		gm.paint(g);
		
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
//	
//	public List<AbstractGameObject> getObjects() {
//		return objects;
//	}
//
//	public void setObjects(List<AbstractGameObject> objects) {
//		this.objects = objects;
//	}

	
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
			
			gm.getMyTank().keyPressed(e);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_Q) save();
			else if(key == KeyEvent.VK_E) load();
			else gm.getMyTank().keyReleased(e);
		}

		private void load() {
			ObjectInputStream ois = null;
			try {
				File f = new File("./tank.dat");
				FileInputStream fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				
				gm = (GameModel)ois.readObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}finally {
					try {
						if(ois == null)
							ois.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}

		private void save() {
			try {
				File f = new File("./tank.dat");
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(gm);
				oos.flush();
				oos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}	
		}

		public void add(AbstractGameObject object) {
			gm.add(object);
		}
	
	}
}
	

