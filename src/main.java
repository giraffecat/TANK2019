import java.awt.Frame;
import java.util.concurrent.TimeUnit;

public class main {

	public static void main(String[] args) {
		
		Frame f =new TankFrame();
		f.setVisible(true);

		for(;;) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			f.repaint();
		}
		
	}
}
