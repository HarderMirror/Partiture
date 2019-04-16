package musicSheetCreator;

public abstract class Simbol {
	private VectorXY center;
	private int width, height;
	public Simbol(int x, int y) {
		this.center = new VectorXY(x,y);
		
	}
	
	public VectorXY getCenter() {
		return center;
	}
	public void setCenter(VectorXY center) {
		this.center = center;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	

	
}
