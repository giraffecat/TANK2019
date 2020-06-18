package chainofresponsbility;

import com.main.tank.AbstractGameObject;
import com.main.tank.Bullet;
import com.main.tank.Wall;

public class BulletWallCollider implements Collider {

	@Override
	public void Colide(AbstractGameObject go1, AbstractGameObject go2) {

		if(go1 instanceof Bullet && go2 instanceof Wall) {
			Bullet b = (Bullet)go1;
			Wall w = (Wall)go2;
			
			b.colidesWithWall(w);
		}
	}

}
