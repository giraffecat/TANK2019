package chainofresponsbility;

import java.awt.Rectangle;

import com.main.tank.AbstractGameObject;
import com.main.tank.Bullet;
import com.main.tank.ResourceMgr;
import com.main.tank.Tank;

public class BulletTankCollider implements Collider {

	@Override
	public boolean Collide(AbstractGameObject go1, AbstractGameObject go2) {
		if(go1 instanceof Bullet && go2 instanceof Tank) {
			Bullet b = (Bullet)go1;
			Tank t = (Tank)go2;
			
			if(!t.isLive()||!b.isLive()) return false;
			if(b.getGroup() == t.getGroup()) return true;
			Rectangle rect = new Rectangle(b.getX(),b.getY(),ResourceMgr.bulletU.getWidth(),ResourceMgr.bulletU.getHeight());
			Rectangle rectRank = new Rectangle(t.getX(),t.getY(),
					ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
			
			if(rect.intersects(rectRank)) {
				b.die();
				t.die();
				return false;
			}		
		}
		return true;
		
	}

}
