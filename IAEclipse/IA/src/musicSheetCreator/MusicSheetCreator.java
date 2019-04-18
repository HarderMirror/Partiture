package musicSheetCreator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class MusicSheetCreator {
	private final int width = 1280;
    private final int height = 175;
    private final int numNotes = 20;       
    private Stave stave;
    
    public MusicSheetCreator() {
    	this.setStave(new Stave(numNotes, width, height));
    	generateImage();
    }
    
    
    private void generateImage() {
    	// Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        
        // fill all the image with white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
       
        g2d.setColor(Color.black);
        
        this.getStave().paint(g2d);
      

        // Disposes of this graphics context and releases any system resources that it is using. 
        g2d.dispose();

        File file = new File("myimage.jpg");
        File data = new File("data.txt");
        
        try{
        	ImageIO.write(bufferedImage, "jpg", file);
        	BufferedWriter writer = new BufferedWriter(new FileWriter(data));
        	writer.write(this.getStave().getNotesText());
        	writer.close();
        	
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }


	public Stave getStave() {
		return stave;
	}


	public void setStave(Stave stave) {
		this.stave = stave;
	}
    
    

}
