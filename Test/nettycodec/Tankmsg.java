package nettycodec;

public class Tankmsg {
	@Override
	public String toString() {
		return "Tankmsg [x=" + x + ", y=" + y + "]";
	}

	public int x,y;

	public Tankmsg(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	

}
