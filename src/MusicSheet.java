import java.awt.Color;
import java.awt.image.BufferedImage;

class MusicSheet {
    BufferedImage[] imgs;
    
	
	public MusicSheet(BufferedImage[] imgs){
    	this.setImgs(imgs);
    	this.convertToMusic();
    }
	
	
	
	public void convertToMusic() {
		for(BufferedImage img : this.getImgs()) {
			for (int i = 0; i < img.getWidth(); i++) {
				for (int j = 0;j  < img.getHeight(); j++) {
					Color c = new Color(img.getRGB(i, j));
					float gray = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
					if(gray!=255) {
						System.out.println(gray);
					}
					
				}
			}
		}
	}
	
	
	
	
	public BufferedImage[] getImgs() {
		return imgs;
	}
	public void setImgs(BufferedImage[] imgs) {
		this.imgs = imgs;
	}
	
	
	
}