package com.main.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import chainofresponsbility.Colliderchain;

public class GameModel implements Serializable{

	private Player mytank;
	private List<AbstractGameObject> objects;
	Colliderchain chain = new Colliderchain();
	
	public GameModel() {
		
		initiGameObjects();
 
	}
	
	private void initiGameObjects() {
			
			objects = new ArrayList<>();
					
	//		colliders.add(new BulletTankCollider());
	//		colliders.add(new BulletWallCollider());
	
			 mytank = new Player(100,100,Dir.D,Group.good);
			 
			 for(int i=0; i<5 ; i++) {
				 objects.add(new Tank(100+80*i,400,Dir.D,Group.bad));
			 }
			 
			 this.add(new Wall(300,200,200,50));
	}
	
	public void add(AbstractGameObject go) {
		objects.add(go);
	}
	
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
			
		}
		
		for(int i=0; i<objects.size();i++) {
			
			
			AbstractGameObject go1 = objects.get(i);
			for(int j=0; j<objects.size(); j++) {
				AbstractGameObject go2 = objects.get(j);
				if(!chain.Collide(go1, go2))
					break;
			}
			if(objects.get(i).isLive()) {
				objects.get(i).paint(g);
			}
		}

	}
	
	public Player getMyTank() {
		return mytank;
		
	}
}
