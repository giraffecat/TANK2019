import java.io.IOException;
import java.util.Properties;

import com.main.tank.ProperityMgr;

public class TestCongif {

	public static void main(String[] args) {
		Properties pros = new Properties();
		try {
			pros.load(TestCongif.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = (String)pros.get("initTankCount");
		System.out.println(ProperityMgr.get("initTankCount"));
	}
}
