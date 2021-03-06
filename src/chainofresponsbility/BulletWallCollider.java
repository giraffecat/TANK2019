package chainofresponsbility;

import java.awt.Rectangle;

import com.main.tank.AbstractGameObject;
import com.main.tank.Bullet;
import com.main.tank.ResourceMgr;
import com.main.tank.Wall;

public class BulletWallCollider implements Collider {

	@Override
	public boolean Collide(AbstractGameObject go1, AbstractGameObject go2) {

		if(go1 instanceof Bullet && go2 instanceof Wall) {
			Bullet b = (Bullet)go1;
			Wall w = (Wall)go2;
			
			Rectangle rect = new Rectangle(b.getX(),b.getY(),ResourceMgr.bulletU.getWidth(),ResourceMgr.bulletU.getHeight());
			Rectangle rectWall = new Rectangle(w.getX(),w.getY(),w.getW(),w.getH());
			
			if(rect.intersects(rectWall)) {
				b.die();
				return false;
			}
		}
		return true;
	}

}
