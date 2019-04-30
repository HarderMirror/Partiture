package musicSheetCreator;

import java.awt.Graphics2D;

public abstract class MusicNote extends Simbol {
	private float duration;
	private boolean reversed;
	private Stick stick;
	public MusicNote(int posX, int posY, float duration, boolean reversed) {
		super(posX, posY);
		this.setDuration(duration);
		this.setReversed(reversed);
		for(int i = 0; i < 3; i++) {
			int note = 0;
			if(duration==2) {
				note = 1;
			} else if (duration==1) {
				note = 2;
			}
			MusicSheetCreator.result[MusicSheetCreator.posResult][MusicSheetCreator.posNote+i] = i==note ? 1 :0;
		}
		
		for(int i = 3; i < 23; i++) {
			MusicSheetCreator.result[MusicSheetCreator.posResult][MusicSheetCreator.posNote+i] = (i-3)==posY ? 1 : 0;
		}		
		MusicSheetCreator.posNote += 23;
		
	}
	public MusicNote(int posX, int posY, float duration) {
		this(posX, posY, duration, false);
	}
	
	public void paint(Graphics2D g2d, int[] linePositions, int lineHeight, int margin) {
		super.paint(g2d);
		if(this.getCenter().getY() < 3){
    		
    		for(int j = this.getCenter().getY() ; j < 3 ; j++) {
    			if(j % 2 == 0) {
    				g2d.fillRect(this.getCenter().getX()-this.getWidth() + margin,
            			linePositions[j]-1,
            			this.getWidth()*2, lineHeight+1);
    			}
    			
    		}
			
    	}else if(this.getCenter().getY() > 13) {
    		for(int j = this.getCenter().getY() ; j > 13 ; j--) {
    			if(j%2 == 0) {
    				g2d.fillRect(this.getCenter().getX()-this.getWidth() + margin,
        				linePositions[j]-1,
        				this.getWidth()*2, lineHeight+1);
    			}
    			
    		}
    	}
	}
	
	protected void drawStick(Graphics2D g2d, int posXDraw, int posYDraw, int offSetX, int linePosY) {
		int heightStick = 30;
		int widthStick = 3;
		int posXStick = this.isReversed() ? posXDraw+offSetX : posXDraw+this.getWidth()-widthStick-offSetX;
		int posYStick = this.isReversed() ? posYDraw+10 : posYDraw-heightStick+5;
		
		this.setStick(new Stick(posXStick, posYStick, widthStick, heightStick));
		this.getStick().paint(g2d);
	}
	
	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return String.format("%d-%.4f\n", this.getCenter().getY(), this.getDuration());
	}

	public boolean isReversed() {
		return reversed;
	}

	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}
	public Stick getStick() {
		return stick;
	}
	public void setStick(Stick stick) {
		this.stick = stick;
	}
}

