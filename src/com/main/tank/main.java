package com.main.tank;
import java.awt.Frame;
import java.util.concurrent.TimeUnit;

public class main {

	public static void main(String[] args) {
		
		TankFrame f = TankFrame.Instance;
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
