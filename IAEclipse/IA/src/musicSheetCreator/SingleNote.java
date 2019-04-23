package musicSheetCreator;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

/**
 * Defines a class in which notes can't be joined each other
 * @author David
 */
public abstract class SingleNote extends MusicNote{

	public SingleNote(int posX, int posY, float duration) {
		super(posX, posY, duration);
	}

}

class WholeNote extends SingleNote {
	private final static float DURATION = 4;
	
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

class HalfNote extends SingleNote {
	private final static float DURATION = 2f;
	
	public HalfNote(int posX, int posY) {
		super(posX, posY, DURATION);
	}
	
	public void paint(Graphics2D g2d, int[] linePositions, int lineHeight, int margin) {
		super.paint(g2d, linePositions, lineHeight, margin);
		g2d.setStroke(new BasicStroke(3f));
		int posXDraw = (this.getCenter().getX() - this.getWidth()/2)+margin;
		int posYDraw = linePositions[this.getCenter().getY()]-this.getHeight()/2;
		g2d.drawOval(posXDraw, posYDraw, this.getWidth(), this.getHeight());
		
		super.drawStick(g2d, posXDraw, posYDraw, 0, this.getCenter().getY());
	}
}

class QuarterNote extends SingleNote {
	private final static float DURATION = 1f;
	
	public QuarterNote(int posX, int posY) {
		super(posX, posY, DURATION);
	}
	public void paint(Graphics2D g2d, int[] linePositions, int lineHeight, int margin) {
		super.paint(g2d, linePositions, lineHeight, margin);
		int posXDraw = (this.getCenter().getX() - this.getWidth()/2)+margin;
		int posYDraw = linePositions[this.getCenter().getY()]-this.getHeight()/2;
		g2d.fillOval(posXDraw, posYDraw, this.getWidth(), this.getHeight());

		super.drawStick(g2d, posXDraw, posYDraw, 1, this.getCenter().getY());
	}
}