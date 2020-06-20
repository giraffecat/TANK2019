package chainofresponsbility;

import java.awt.Rectangle;

import com.main.tank.AbstractGameObject;
import com.main.tank.ResourceMgr;
import com.main.tank.Tank;
import com.main.tank.Wall;

public class TankTankCollider implements Collider {

	@Override
	public boolean Collide(AbstractGameObject go1, AbstractGameObject go2) {
		if(go1 !=go2 && go1 instanceof Tank && go2 instanceof Tank) {
			
			Tank t1 = (Tank)go1;
			Tank t2 = (Tank)go2;
			
			Rectangle rect1 = new Rectangle(t1.getX(),t1.getY(),ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
			Rectangle rect2 = new Rectangle(t2.getX(),t2.getY(),ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
			
			if(rect1.intersects(rect2)) {
				t1.back();
				t2.back();
			}
					
		}
		return true;
	}

	
}
