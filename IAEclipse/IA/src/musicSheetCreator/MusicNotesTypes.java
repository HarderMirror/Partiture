package musicSheetCreator;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.Random;

public class MusicNotesTypes {
	public static MusicNote generateRandomTypeNote(int posX, int posY) {
		Random rn = new Random();
		switch (rn.nextInt(3)) {
			case 0:
				return new WholeNote(posX, posY);
			case 1:
				return new HalfNote(posX, posY);
			case 2:
				return new QuarterNote(posX, posY);
			default:
				return new WholeNote(posX, posY);
		}
		
	}
}


class WholeNote extends MusicNote {
	private final static float DURATION = 1;
	
	public WholeNote(int posX, int posY) {
		super(posX, posY, DURATION);
	}
	
	public void paint(Graphics2D g2d, int[] linePositions, int lineHeight, int margin) {
		super.paint(g2d, linePositions, lineHeight, margin);
		g2d.setStroke(new BasicStroke(5f));
		g2d.drawOval((this.getCenter().getX() - this.getWidth()/2)+margin,
    			linePositions[this.getCenter().getY()]-this.getHeight()/2, this.getWidth(), this.getHeight());
		
	}
}

class HalfNote extends MusicNote {
	private final static float DURATION = 1/2f;
	
	public HalfNote(int posX, int posY) {
		super(posX, posY, DURATION);
	}
	
	public void paint(Graphics2D g2d, int[] linePositions, int lineHeight, int margin) {
		super.paint(g2d, linePositions, lineHeight, margin);
		g2d.setStroke(new BasicStroke(3f));
		int posXDraw = (this.getCenter().getX() - this.getWidth()/2)+margin;
		int posYDraw = linePositions[this.getCenter().getY()]-this.getHeight()/2;
		g2d.drawOval(posXDraw, posYDraw, this.getWidth(), this.getHeight());
		
		super.drawStick(g2d, posXDraw, posYDraw, 0);
	}
}

class QuarterNote extends MusicNote {
	private final static float DURATION = 1/4f;
	
	public QuarterNote(int posX, int posY) {
		super(posX, posY, DURATION);
	}
	
	public void paint(Graphics2D g2d, int[] linePositions, int lineHeight, int margin) {
		super.paint(g2d, linePositions, lineHeight, margin);
		int posXDraw = (this.getCenter().getX() - this.getWidth()/2)+margin;
		int posYDraw = linePositions[this.getCenter().getY()]-this.getHeight()/2;
		g2d.fillOval(posXDraw, posYDraw, this.getWidth(), this.getHeight());

		super.drawStick(g2d, posXDraw, posYDraw, 2);
	}
	
}
