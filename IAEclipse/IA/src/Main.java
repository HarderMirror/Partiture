import musicSheetCreator.MusicSheetCreator;


public class Main {
	public static void main(String[] args) {
		//new MusicSheetCreator();
		double init1 = System.currentTimeMillis();
		double[][] pepe = np.random(224000, 1);
		double[][] pepeW = np.random(224000, 1);
		double init2 = System.currentTimeMillis();
		
		for(int i=0; i < 3; i++) {
			np.multiply(pepe, pepeW);
		}
		double finalT = System.currentTimeMillis();
		System.out.println("Time is: " + (finalT - init2));
	}
}
