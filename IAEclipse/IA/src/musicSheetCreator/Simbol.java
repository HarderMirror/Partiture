package musicSheetCreator;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Simbol {
	private VectorXY center;
	private int width, height;
	public Simbol(int x, int y) {
		this.center = new VectorXY(x,y);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
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

	@Override
	public String toString() {
		return String.format("%d\n", this.getCenter().getY());
	}
	
	
	
	
}
