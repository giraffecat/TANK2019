package chainofresponsbility;

import java.util.ArrayList;
import java.util.List;

import com.main.tank.AbstractGameObject;
import com.main.tank.ProperityMgr;

public class Colliderchain implements Collider {

	private List<Collider> colliders;
	public Colliderchain() {
		initColliders();
	}
	
	private void initColliders() {

		colliders = new ArrayList<>();
		String[] collidersNames = ProperityMgr.get("Colliders").split(",");
		for(String name : collidersNames) {
			try {
				Class clazz = Class.forName("chainofresponsbility."+name);
				Collider c = (Collider)clazz.getConstructor().newInstance();
				colliders.add(c);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean Collide(AbstractGameObject go1, AbstractGameObject go2) {
		for(Collider c : colliders) {
			if(!c.Collide(go1, go2)){
					return false;
			}
		}
		return true;
	}

}
