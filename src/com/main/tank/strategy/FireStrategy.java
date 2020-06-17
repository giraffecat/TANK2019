package com.main.tank.strategy;

import com.main.tank.Bullet;
import com.main.tank.Player;
import com.main.tank.ResourceMgr;
import com.main.tank.Tank;
import com.main.tank.TankFrame;

public interface FireStrategy {
 
	public void fire(Player player);
}
