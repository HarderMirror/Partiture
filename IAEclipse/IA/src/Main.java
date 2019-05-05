import musicSheetCreator.*;

import org.tensorflow.Graph;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.Tensors;

public class Main {
	public static void main(String[] args) {
		createMusicSheet();
		try {
			System.loadLibrary("tensorflow_jni");
		} catch(Exception e) {
			e.printStackTrace();
		}
	/*
		try {
	      SavedModelBundle smb = SavedModelBundle.load("./model_keras", "serve");
		  Session s = smb.session();
	      
		  Tensor input_tensor = Tensors.create(MusicSheetCreator.image);
	      
	      Tensor result = s.runner()
	    		  .feed("input_tensor", input_tensor)
	    		  .fetch("output_tensor")
	    		  .run().get(0);
	      
	      //Tensor tensorSolutionY = Tensors.create(MusicSheetCreator.result);
	      
	      System.out.println(new String(result.bytesValue(), "UTF-8"));
	      
	    }catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	
	private static void createMusicSheet() {
		new MusicSheetCreator();
		/*
		int count = 0;
		for(int i=0; i<460; i++) {
    	 	if(count == 23) {
    			count = 0;
    			System.out.println();
   		}
   		count++;
    		System.out.print(MusicSheetCreator.result[0][i]+" ");
    	}
		System.out.println();
		*/
	}
}
