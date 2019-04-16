package musicSheetCreator;

public class VectorXY {
	private int x;
	private int y;
	
	public VectorXY() {
		this.setX(0);
		this.setY(0);
	}
	
	public VectorXY(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
