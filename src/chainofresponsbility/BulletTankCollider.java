package chainofresponsbility;

import com.main.tank.AbstractGameObject;
import com.main.tank.Bullet;
import com.main.tank.Tank;

public class BulletTankCollider implements Collider {

	@Override
	public void Colide(AbstractGameObject go1, AbstractGameObject go2) {
		if(go1 instanceof Bullet && go2 instanceof Tank) {
			Bullet b = (Bullet)go1;
			Tank t = (Tank)go2;
			b.colidesWithTank(t);
		}
		
	}

}
