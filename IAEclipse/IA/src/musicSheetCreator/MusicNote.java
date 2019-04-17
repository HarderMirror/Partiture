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
	
	protected void drawStick(Graphics2D g2d, int posXDraw, int posYDraw, int offSetX) {
		int heightStick = 40;
		int widthStick = 3;
		
		g2d.fillRect(posXDraw+offSetX, posYDraw-heightStick+5, widthStick, heightStick);
	}
	
	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return String.format("%d-%.3f\n", this.getCenter().getY(), this.getDuration());
	}
}

