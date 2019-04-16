
public class NotesAI {
	private float[] input, weights, bias;
	
	//[3 2 2] = 3 first layer, 2 second, 2 third
	private int[] layers;
	
	public NotesAI(float[] input) {
		this.setInput(input);
		
	}
	
	//METHODS
	
	private float sigmoid(float Z) {
		return (float) (1 / (1 + (Math.pow(Math.E,-Z))));
	}
	private float derivativeOfSigmoid(float Z) {
		return this.sigmoid(Z) + (1-this.sigmoid(Z));
	}
	

	//GETTERS & SETTERS

	public float[] getInput() {
		return input;
	}

	public void setInput(float[] input) {
		this.input = input;
	}
	
	public void setWeight(int index, float value){
		
	}

	public int[] getLayers() {
		return layers;
	}

	public void setLayers(int[] layers) {
		this.layers = layers;
	}

	public float[] getBias() {
		return bias;
	}

	public void setBias(float[] bias) {
		this.bias = bias;
	}

	public float[] getWeights() {
		return weights;
	}

	public void setWeights(float[] weights) {
		this.weights = weights;
	}
}
