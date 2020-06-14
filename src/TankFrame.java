import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankFrame extends Frame {
	
	private Tank mytank;
	private Tank enemy;
	
	public TankFrame() {
		this.setTitle("Tank war");
		this.setLocation(400, 100);
		this.setSize(800, 600);
		this.addKeyListener(new TankKeyListener());
		
		 mytank = new Tank(100,100,Dir.STOP);
		 enemy = new Tank(200,200,Dir.D);
	}

	@Override
	public void paint(Graphics g) {

		mytank.paint(g);
		enemy.paint(g);
		
	}
	
	private class TankKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			
			mytank.keyPressed(e);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			mytank.keyReleased(e);
		}
		
		
	}
	
}
