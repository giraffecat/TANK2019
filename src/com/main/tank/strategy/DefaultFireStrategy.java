package com.main.tank.strategy;

import com.main.tank.Bullet;
import com.main.tank.Player;
import com.main.tank.ResourceMgr;
import com.main.tank.Tank;
import com.main.tank.TankFrame;

public class DefaultFireStrategy implements FireStrategy {

	@Override
	public void fire(Player player) {
		{
			int bx = player.getX() + ResourceMgr.goodTankD.getWidth()/2 - ResourceMgr.bulletD.getWidth()/2;
			int by = player.getY() + ResourceMgr.goodTankD.getHeight()/2 - ResourceMgr.bulletD.getHeight()/2;

			Bullet bullet = new Bullet(bx,by,player.getDir(),player.getGroup());
			TankFrame.Instance.getGm().add(bullet);
		};
		
	}

	
}
