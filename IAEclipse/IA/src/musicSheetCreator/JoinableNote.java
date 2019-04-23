package musicSheetCreator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Defines a class in which notes can be joined each other
 * @author David
 */
public abstract class JoinableNote extends MusicNote{
	private int numSticks;
	private boolean isJoined;
	private boolean isFirst;
	private BufferedImage stickImg, stickImgReversed;
	
	private MusicNote prevNote;
	
	private Stick stick;
	
	private static final int HEIGHT_STICK_IMG = 27;
	
	public JoinableNote(int posX, int posY, float duration, 
						int numSticks, MusicNote prevNote) {
		super(posX, posY, duration);	
		this.setNumSticks(numSticks);
		this.setFirst(this.isJoinable(prevNote));
		this.setJoined(this.isJoinable(prevNote));
		this.setPrevNote(prevNote);
		this.setReversed(this.isReversed(posY, prevNote));
		
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
		int posXStick = this.isReversed() ? posXDraw+offSetX : posXDraw+this.getWidth()-widthStick-offSetX;
		int posYStick = this.isReversed() ? posYDraw+10 : posYDraw-heightStick+5;
		
		this.setStick(new Stick(posXStick, posYStick, widthStick, heightStick));
		this.getStick().paint(g2d);
		
		int offSetYImg = 0;
		if(prevNote!=null && this.isJoined()) {
			int offSetY = 0;
			int x1,x2,y1,y2;
			if(this.isReversed()) {
				x1 = this.getPrevNote().getStick().getPosXStick();
				y1 = this.getPrevNote().getStick().getPosYStick()+this.getPrevNote().getStick().getHeightStick();
				x2 = this.getStick().getPosXStick();
				y2 = this.getStick().getPosYStick()+this.getPrevNote().getStick().getHeightStick();
				for(int i = 0; i < this.getNumSticks(); i++) {
					g2d.drawLine(x1, y1+offSetY, x2, y2+offSetY);
					offSetY -= 7;
				}
			} else {
				x1 = this.getPrevNote().getStick().getPosXStick();
				y1 = this.getPrevNote().getStick().getPosYStick();
				x2 = this.getStick().getPosXStick();
				y2 = this.getStick().getPosYStick();
				for(int i = 0; i < this.getNumSticks(); i++) {
					g2d.drawLine(x1, y1+offSetY, x2, y2+offSetY);
					offSetY += 7;
				}
			}
		} else {
			if(this.isReversed()) {
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
		
	}
	
	private boolean isReversed(int posY, MusicNote prevNote) {
		boolean reversed = posY < 8;
		if(prevNote instanceof JoinableNote) {
			reversed = prevNote.isReversed();
		}
		return reversed;
	}
	
	private boolean isJoinable(MusicNote prevNote) {
		boolean joinable = prevNote instanceof JoinableNote;
		if(joinable) {
			((JoinableNote) prevNote).setJoined(true);
		}
		return joinable;
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

	public MusicNote getPrevNote() {
		return prevNote;
	}

	public void setPrevNote(MusicNote prevNote) {
		this.prevNote = prevNote;
	}

	public Stick getStick() {
		return stick;
	}

	public void setStick(Stick stick) {
		this.stick = stick;
	}

}



class EightNote extends JoinableNote {
	private final static float DURATION = 1/2f;
	private final static int NUM_STICKS = 1;
	
	public EightNote(int posX, int posY, MusicNote prevNote) {
		super(posX, posY, DURATION, NUM_STICKS, prevNote);
	}
}
class SixteenthNote extends JoinableNote {
	private final static float DURATION = 1/4f;
	private final static int NUM_STICKS = 2;
	
	public SixteenthNote(int posX, int posY, MusicNote prevNote) {
		super(posX, posY, DURATION, NUM_STICKS, prevNote);
	}
}
class ThirtySecondNote extends JoinableNote {
	private final static float DURATION = 1/8f;
	private final static int NUM_STICKS = 3;
	
	public ThirtySecondNote(int posX, int posY, MusicNote prevNote) {
		super(posX, posY, DURATION, NUM_STICKS, prevNote);
	}
}
class SixtyFourthNote extends JoinableNote {
	private final static float DURATION = 1/16f;
	private final static int NUM_STICKS = 4;
	
	public SixtyFourthNote(int posX, int posY, MusicNote prevNote) {
		super(posX, posY, DURATION, NUM_STICKS, prevNote);
	}
}
class HundredTwentyEighth extends JoinableNote {
	private final static float DURATION = 1/32f;
	private final static int NUM_STICKS = 5;
	
	public HundredTwentyEighth(int posX, int posY, MusicNote prevNote) {
		super(posX, posY, DURATION, NUM_STICKS, prevNote);
	}
}
class TwoHundredFiftySixthNote extends JoinableNote {
	private final static float DURATION = 1/64f;
	private final static int NUM_STICKS = 6;
	
	public TwoHundredFiftySixthNote(int posX, int posY, MusicNote prevNote) {
		super(posX, posY, DURATION, NUM_STICKS, prevNote);
	}
}