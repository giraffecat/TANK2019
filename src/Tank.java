import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Tank {

	private int x,y;
	private static final int SPEED=5;
	private Dir dir;
	private boolean bU,bR,bD,bL;
	
	public Tank(int x, int y, Dir dir) {
		this.x = x;
		this.y = y;
		this.dir= dir;
	}

	public void paint(Graphics g) {

		g.fillRect(x, y, 50, 50);
		move();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_A:
			bL = true;
			break;
		case KeyEvent.VK_D:
			bR = true;
			break;
		case KeyEvent.VK_W:
			bU = true;
			break;
		case KeyEvent.VK_S:
			bD = true;
			break;
		
		}
		setMainDir();
	}

	private void setMainDir() {

		if(!bU && !bR && !bD && !bL)
			dir = Dir.STOP;
		if(bU && !bR && !bD && !bL)
			dir = Dir.U;
		if(!bU && bR && !bD && !bL)
			dir = Dir.R;
		if(!bU && !bR && bD && !bL)
			dir = Dir.D;
		if(!bU && !bR && !bD && bL)
			dir = Dir.L;
	}

	private void move() {

		switch(dir){
			case L:
				x -= SPEED;
				break;
			case R:
				x += SPEED;
				break;
			case U:
				y -= SPEED;
				break;
			case D:
				y += SPEED;
				break;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_A:
			bL = false;
			break;
		case KeyEvent.VK_D:
			bR = false;
			break;
		case KeyEvent.VK_W:
			bU = false;
			break;
		case KeyEvent.VK_S:
			bD = false;
			break;
		
		}
		setMainDir();
	}
	
	
}
