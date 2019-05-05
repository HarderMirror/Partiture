package musicSheetCreator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class MusicSheetCreator {
	private final int width = 1280; //1280
    private final int height = 175;
    private final static int numNotes = 30;       
    private Stave stave;
    private static final int NUM_IMAGES = 1000;
    private static final int NUM_COLS_RESULT = numNotes * 23; //460
    public static int[][][] image;
    public static int[][] result;
    public static int posResult;
    public static int posNote;
    public MusicSheetCreator() {
    	image = new int[NUM_IMAGES][width][height];
    	result = new int[NUM_IMAGES][NUM_COLS_RESULT];
    	System.out.println("Heap memory assigned");
    	posResult = 0;
    	posNote = 0;
    	for(int i = 0; i<NUM_IMAGES; i++) {
    		this.setStave(new Stave(numNotes, width, height));
    		generateImage(i);
    		posResult++;
    		posNote = 0;
//    		System.out.println(posResult);
    	}
//    	int count = 0;
//    	for(int i=0; i<460; i++) {
//    	 	if(count == 23) {
//    			count = 0;
//    			System.out.println();
//    		}
//    		count++;
//    		System.out.print(result[0][i]+" ");
//    	}
    	
    	
    	/*try {
    		//FileOutputStream x = new FileOutputStream("x.dat");
    		FileOutputStream y = new FileOutputStream("y.dat");
        	//ObjectOutputStream xFile = new ObjectOutputStream(x);
        	ObjectOutputStream yFile = new ObjectOutputStream(y);
			//xFile.writeObject(image);
			//xFile.close();
			yFile.writeObject(result);
			yFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
        
        try{
        	BufferedImage bufferedImage = new BufferedImage(NUM_COLS_RESULT, NUM_IMAGES, BufferedImage.TYPE_BYTE_GRAY);
      
        	for(int i = 0; i<NUM_IMAGES; i++) {
        		for(int j = 0; j<NUM_COLS_RESULT; j++) { //CAMBIAR
        			bufferedImage.setRGB(j, i, result[i][j]==1 ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
            	}
        	}
        	ImageIO.write(bufferedImage, "jpg", new File("y.jpg"));
//        	data = new File("x.txt");
       		//writer = new BufferedWriter(new FileWriter(data));
        	//str="";
//        	for(int i = 0; i<1000; i++) {
//        		for(int j = 0; j<1280; j++) {
//        			for(int k = 0; k<175; k++) {
//            			str+=image[i][j][k];
//                	}
//            	}
//        	}
//        	writer.write(str);
//        	writer.close();
        	
        }catch(IOException e) {
        	e.printStackTrace();
        }
    	
    	
    }
    
    
    private void generateImage(int pos) {
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
        
        
        for(int x = 0; x<bufferedImage.getWidth(); x++) {
        	for(int y=0; y<bufferedImage.getHeight();y++) {
        		image[pos][x][y] = bufferedImage.getRGB(x, y);
        	}
       }
        
        
       File file = new File(String.format("images/%d.jpg", posResult));
        
        try{
        	ImageIO.write(bufferedImage, "jpg", file);

        	
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
