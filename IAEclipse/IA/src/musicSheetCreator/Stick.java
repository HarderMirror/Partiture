package musicSheetCreator;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Stick {
	private int posXStick, posYStick, widthStick, heightStick;
	
	public Stick(int posXStick, int posYStick, int widthStick, int heightStick) {
		this.setHeightStick(heightStick);
		this.setPosXStick(posXStick);
		this.setPosYStick(posYStick);
		this.setWidthStick(widthStick);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(3f));
		g2d.fillRect(posXStick, posYStick, widthStick, heightStick);
	}
	
	public int getPosXStick() {
		return posXStick;
	}

	public void setPosXStick(int posXStick) {
		this.posXStick = posXStick;
	}

	public int getPosYStick() {
		return posYStick;
	}

	public void setPosYStick(int posYStick) {
		this.posYStick = posYStick;
	}

	public int getWidthStick() {
		return widthStick;
	}

	public void setWidthStick(int widthStick) {
		this.widthStick = widthStick;
	}

	public int getHeightStick() {
		return heightStick;
	}

	public void setHeightStick(int heightStick) {
		this.heightStick = heightStick;
	}

	
}
