package musicSheetCreator;

import java.awt.Graphics2D;
import java.util.Random;

public class Stave {
	private int numNotes, width, height;
	
	private final int numLinePositions = 20;
	private final int margin = 10, marginH=10;
	private final int marginNotes = 100; //15
    private final int lineHeight = 1;
        
    private int[] linePositions = new int[numLinePositions];
   
	private MusicNote[] notes =  new MusicNote[numNotes];
    
	public Stave(int numNotes, int width, int height) {
    	this.setNumNotes(numNotes);
    	this.width = width;
    	this.height = height;
    	notes = new MusicNote[numNotes];
    	
    	getLinePositions();
    	generateNotes(20,15);
	}
	
	public void paint(Graphics2D g2d) {
		for(int i = 0; i < linePositions.length; i+=2) {
        	if(i >= 6 && i < 15) {
        		g2d.fillRect(margin, linePositions[i]-1, width-margin*2, lineHeight+1);
        	}
        }
		Clef.generateRandomClef().paint(g2d);
        // create a circle with black
        for(int i = 0; i < notes.length; i++) {
        	notes[i].paint(g2d, linePositions, lineHeight, marginNotes);
        }
	}
	
    private void generateNotes(int noteWidth, int noteHeight) {
    	Random rn = new Random();
    	for(int i=0; i < numNotes; i++) {
    		notes[i] = MusicNotesTypes.generateRandomTypeNote(
		    			  (this.getSpaceBetweenNotesCenter())*i + noteWidth, 
		    			  rn.nextInt(linePositions.length), 
		    			  i>0 ? notes[i-1] : null
    				   );
    		notes[i].setHeight(noteHeight);
    		notes[i].setWidth(noteWidth);
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
		return (int) (height-marginH*2)/(numLinePositions);
	}
	private int getSpaceBetweenNotesCenter() {
		return (int) (width-marginNotes*1.2)/(numNotes);
	}
	public String getNotesText() {
		String notesText = "";
		for(int i = 0; i < notes.length; i++) {
			notesText += String.format("%d-%s", i, notes[i]); 
		}
		return notesText;
	}
	
	public int getNumNotes() {
		return numNotes;
	}

	public void setNumNotes(int numNotes) {
		this.numNotes = numNotes;
	}
}

