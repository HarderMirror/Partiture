package musicSheetCreator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Defines a class in which notes can be joined each other
 * @author David
 */
public class JoinableNote extends MusicNote{
	private int numSticks;
	private boolean isJoined;
	private boolean isFirst;
	private BufferedImage stickImg, stickImgReversed;
	
	private static final int HEIGHT_STICK_IMG = 27;
	
	public JoinableNote(int posX, int posY, float duration, int numSticks, boolean isFirst, boolean isJoined) {
		super(posX, posY, duration);	
		this.setNumSticks(numSticks);
		this.setFirst(isFirst);
		this.setJoined(isJoined);
		
		try {
			this.setStickImg(ImageIO.read(new File("stickSingle.png")));
			this.setStickImgReversed(ImageIO.read(new File("stickSingleReversed.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics2D g2d, int[] linePositions, int lineHeight, int margin) {
		super.paint(g2d, linePositions, lineHeight, margin);
		
		int posXDraw = (this.getCenter().getX() - this.getWidth()/2)+margin;
		int posYDraw = linePositions[this.getCenter().getY()]-this.getHeight()/2;
		g2d.fillOval(posXDraw, posYDraw, this.getWidth(), this.getHeight());

		this.drawStick(g2d, posXDraw, posYDraw, 1, this.getCenter().getY());
	}
	
	@Override
	protected void drawStick(Graphics2D g2d, int posXDraw, int posYDraw, int offSetX, int linePosY) {
		
		int heightStick = 30 + this.getNumSticks()*4;
		int widthStick = 3;
		boolean reversed = linePosY < 8;
		int posXStick = reversed ? posXDraw+offSetX : posXDraw+this.getWidth()-widthStick-offSetX;
		int posYStick = reversed ? posYDraw+10 : posYDraw-heightStick+5;
		
		g2d.fillRect(posXStick, posYStick, widthStick, heightStick);
		
		int offSetYImg = 0;
		if(reversed) {
			for(int i = 0; i < this.getNumSticks(); i++) {
				g2d.drawImage(this.getStickImgReversed(), null, posXStick, posYStick+heightStick+offSetYImg-HEIGHT_STICK_IMG);
				offSetYImg -= 7;
			}
		} else {
			for(int i = 0; i < this.getNumSticks(); i++) {
				g2d.drawImage(this.getStickImg(), null, posXStick, posYStick+offSetYImg);
				offSetYImg += 7;
			}
		}
		
		
	}

	public int getNumSticks() {
		return numSticks;
	}

	public void setNumSticks(int numSticks) {
		this.numSticks = numSticks;
	}

	public boolean isJoined() {
		return isJoined;
	}

	public void setJoined(boolean isJoined) {
		this.isJoined = isJoined;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public BufferedImage getStickImg() {
		return stickImg;
	}

	public void setStickImg(BufferedImage stickImg) {
		this.stickImg = stickImg;
	}

	public BufferedImage getStickImgReversed() {
		return stickImgReversed;
	}

	public void setStickImgReversed(BufferedImage stickImgReversed) {
		this.stickImgReversed = stickImgReversed;
	}

}



class EightNote extends JoinableNote {
	private final static float DURATION = 1/2f;
	private final static int NUM_STICKS = 1;
	
	public EightNote(int posX, int posY, boolean isFirst, boolean isJoined) {
		super(posX, posY, DURATION, NUM_STICKS, isFirst, isJoined);
	}
}
class SixteenthNote extends JoinableNote {
	private final static float DURATION = 1/4f;
	private final static int NUM_STICKS = 2;
	
	public SixteenthNote(int posX, int posY, boolean isFirst, boolean isJoined) {
		super(posX, posY, DURATION, NUM_STICKS, isFirst, isJoined);
	}
}
class ThirtySecondNote extends JoinableNote {
	private final static float DURATION = 1/8f;
	private final static int NUM_STICKS = 3;
	
	public ThirtySecondNote(int posX, int posY, boolean isFirst, boolean isJoined) {
		super(posX, posY, DURATION, NUM_STICKS, isFirst, isJoined);
	}
}
class SixtyFourthNote extends JoinableNote {
	private final static float DURATION = 1/16f;
	private final static int NUM_STICKS = 4;
	
	public SixtyFourthNote(int posX, int posY, boolean isFirst, boolean isJoined) {
		super(posX, posY, DURATION, NUM_STICKS, isFirst, isJoined);
	}
}
class HundredTwentyEighth extends JoinableNote {
	private final static float DURATION = 1/32f;
	private final static int NUM_STICKS = 5;
	
	public HundredTwentyEighth(int posX, int posY, boolean isFirst, boolean isJoined) {
		super(posX, posY, DURATION, NUM_STICKS, isFirst, isJoined);
	}
}
class TwoHundredFiftySixthNote extends JoinableNote {
	private final static float DURATION = 1/64f;
	private final static int NUM_STICKS = 6;
	
	public TwoHundredFiftySixthNote(int posX, int posY, boolean isFirst, boolean isJoined) {
		super(posX, posY, DURATION, NUM_STICKS, isFirst, isJoined);
	}
}