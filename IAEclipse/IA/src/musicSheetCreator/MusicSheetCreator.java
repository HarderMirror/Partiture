package musicSheetCreator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class MusicSheetCreator {
	private final int width = 1280;
    private final int height = 175;
    private final int numLines = 5;
    private final int numNotes = 20;
    private final int noteWidth = 20;
    private final int noteHeight = 15;
    
    
    private final int margin = 20;
    private final int lineHeight = 5;
    
    private final int diff = (int) (height - margin*2) / (numLines*2);
    private final int diffXNotes = (int) (width-margin*4) / numNotes + noteWidth;
    
    private int[] linePositions = new int[numLines*2];
    private MusicNote[] notes = new MusicNote[numNotes];
    
    public MusicSheetCreator() {
    	getLinePositions();
    	generateNotes();
    	generateImage();
    }
    
    
    private void generateNotes() {
    	Random rn = new Random();
    	for(int i=0; i < numNotes; i++) {
    		notes[i] = new MusicNote(i, rn.nextInt(linePositions.length));
    		System.out.println(notes[i].getPosY());
    	}
	}


	private void getLinePositions() {    	
    	int pos = margin;
    	for(int i = 0; i < linePositions.length; i++) {
    		linePositions[i] = pos;
    		pos+=diff;
    	}
    }
    
    private void generateImage() {
    	// Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        
        // fill all the image with white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);

        // create a circle with black
        g2d.setColor(Color.black);
        for(int i = 0; i < linePositions.length; i+=2) {
        	g2d.fillRect(margin, linePositions[i], width-margin*2, lineHeight);
        }
        for(int i = 0; i < notes.length; i++) {
        	g2d.fillOval(notes[i].getPosX()*diffXNotes+30, linePositions[notes[i].getPosY()]+noteHeight/2, noteWidth, noteHeight);
        }

        // Disposes of this graphics context and releases any system resources that it is using. 
        g2d.dispose();

        File file = new File("myimage.jpg");
        try{
        	ImageIO.write(bufferedImage, "jpg", file);
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }
    
    

}
