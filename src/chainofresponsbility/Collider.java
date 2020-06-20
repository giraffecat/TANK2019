package chainofresponsbility;

import java.io.Serializable;

import com.main.tank.AbstractGameObject;

public interface Collider extends Serializable {
	//return true : chain go on ; return false chain stop
	public boolean Collide (AbstractGameObject go1, AbstractGameObject go2);
}
