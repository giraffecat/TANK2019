package chainofresponsbility;

import java.awt.Rectangle;

import com.main.tank.AbstractGameObject;
import com.main.tank.ResourceMgr;
import com.main.tank.Tank;
import com.main.tank.Wall;

public class TankWallCollider implements Collider {

	@Override
	public boolean Collide(AbstractGameObject go1, AbstractGameObject go2) {

		if(go1 instanceof Tank && go2 instanceof Wall) {
			
			Tank t = (Tank)go1;
			Wall w = (Wall)go2;
			
			Rectangle rect = new Rectangle(t.getX(),t.getY(),ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
			Rectangle rectWall = new Rectangle(w.getX(),w.getY(),w.getW(),w.getH());
			
			if(rect.intersects(rectWall)) {
				t.back();
			}
					
		}
		return true;
		
	}
}


