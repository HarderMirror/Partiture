package musicSheetCreator;

import java.awt.Graphics2D;

public abstract class MusicNote extends Simbol {
	private float duration;
	public MusicNote(int posX, int posY, float duration) {
		super(posX, posY);
		this.setDuration(duration);
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
		boolean reversed = linePosY < 8;
		int posXStick = reversed ? posXDraw+offSetX : posXDraw+this.getWidth()-widthStick-offSetX;
		int posYStick = reversed ? posYDraw+10 : posYDraw-heightStick+5;
		
		g2d.fillRect(posXStick, posYStick, widthStick, heightStick);
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
}

