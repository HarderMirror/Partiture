package musicSheetCreator;

import java.awt.Color;
import java.awt.Graphics2D;
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
    private int numNotes = 20;   
    
    private final int margin = 30;
    private final int lineHeight = 4;
        
    private int[] linePositions = new int[15];
    private MusicNote[] notes =  new MusicNote[numNotes];
    
    public MusicSheetCreator() {
    	
    	getLinePositions();
    	generateNotes(20,15);
    	generateImage();
    }
    
    
    
    private void generateNotes(int noteWidth, int noteHeight) {
    	Random rn = new Random();
    	for(int i=0; i < numNotes; i++) {
    		notes[i] = new MusicNote((this.getSpaceBetweenNotesCenter())*i + noteWidth, rn.nextInt(linePositions.length));
    		notes[i].setHeight(noteHeight);
    		notes[i].setWidth(noteWidth);
    		System.out.println(notes[i].getCenter().getY());
    	}
	}


	private void getLinePositions() {    	
    	int pos = (int) (margin);
    	for(int i = 0; i < linePositions.length; i++) {
    		linePositions[i] = pos;
    		pos+=getSpaceBetweenLines();
    	}
    }
	
	private int getSpaceBetweenLines() {
		return (int) (height - margin*2)/(15);
	}
	private int getSpaceBetweenNotesCenter() {
		return (int) (width-margin*2)/(numNotes);
	}
	private String getNotesText() {
		String notesText = "";
		for(int i = 0; i < notes.length; i++) {
			notesText=notesText + String.format("%d-%d\n",i, notes[i].getCenter().getY());
		}
		return notesText;
	}
    
    private void generateImage() {
    	// Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        
        // fill all the image with white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);

       
        g2d.setColor(Color.black);
        for(int i = 0; i < linePositions.length; i+=2) {
        	if(i >= 4 && i < 13) {
        		g2d.fillRect(margin, linePositions[i]-1, width-margin*2, lineHeight+1);
        	}
        	
        }
        // create a circle with black
        for(int i = 0; i < notes.length; i++) {
        	g2d.fillOval((notes[i].getCenter().getX() - notes[i].getWidth()/2)+margin,
        			linePositions[notes[i].getCenter().getY()]-notes[i].getHeight()/2, notes[i].getWidth(), notes[i].getHeight());
        	if(notes[i].getCenter().getY() < 3){
        		
        		for(int j = notes[i].getCenter().getY() ; j < 3 ; j++) {
        			if(j % 2 == 0) {
        				g2d.fillRect(notes[i].getCenter().getX()-notes[i].getWidth() + margin,
                			linePositions[j]-1,
                			notes[i].getWidth()*2, lineHeight+1);
        			}
        			
        		}
    			
        	}else if(notes[i].getCenter().getY() > 13) {
        		for(int j = notes[i].getCenter().getY() ; j > 13 ; j--) {
        			if(j%2 == 0) {
        				g2d.fillRect(notes[i].getCenter().getX()-notes[i].getWidth() + margin,
            				linePositions[j]-1,
            				notes[i].getWidth()*2, lineHeight+1);
        			}
        			
        		}
        	}
    		
    			
 		
        }
        //System.out.println(i);
        

        

        // Disposes of this graphics context and releases any system resources that it is using. 
        g2d.dispose();

        File file = new File("myimage.jpg");
        File data = new File("data.txt");
        
        try{
        	ImageIO.write(bufferedImage, "jpg", file);
        	BufferedWriter writer = new BufferedWriter(new FileWriter(data));
        	writer.write(this.getNotesText());
        	writer.close();
        	
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }
    
    

}
