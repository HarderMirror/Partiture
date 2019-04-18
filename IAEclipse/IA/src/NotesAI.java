
public class NotesAI {
	private double[] input;
	private double[][] bias;
	private double[][][] weights;
	private double[][][] hiddenLayers;
	

	//[3 2 2] = 3 first layer, 2 second, 2 third
	private int[] layers;
	
	public NotesAI(double[] input, int[] layers) {
		this.setLayers(layers);
		this.setInput(input);
		this.setRandomWeights();
		
	}
	
	//METHODS
	private double Z(int posFinalNeuron, int weightsNum,int biasLayer, double[] leftLayer) {
		//Returns the value of the operation that we put in sigmoid
		double MultRes = 0;
		for(int i = 0; i < this.getWeights()[weightsNum][posFinalNeuron].length ; i++) {
			MultRes = this.getWeights()[weightsNum][posFinalNeuron][i] * leftLayer[i];
		}
		return MultRes + bias[biasLayer][posFinalNeuron];
	}
	private double sigmoid(double Z) {
		return (double) (1 / (1 + (Math.pow(Math.E,-Z))));
	}
	private double derivativeOfSigmoid(double Z) {
		return this.sigmoid(Z) + (1-this.sigmoid(Z));
	}
	
	
	private void setRandomWeights() {
		for(int i = 1; i < this.getLayers().length; i++ ) {
			this.getWeights()[i-1] = np.random(this.getLayers()[i], this.getLayers()[i-1]);
		}
	}
	private void createHiddenLayers() {
		for(int i = 0; i < this.getLayers().length-2;i++) {
			this.getHiddenLayers()[i] = np.random(1,this.getLayers()[i+1]);
		}
		
	}
	
	private double[] feedForward(int layerNeurons, int layerNum, double[] leftLayer) {
		double[] res = new double[this.getLayers()[layerNum]];
		for(int i = 0; i < layerNeurons; i++) {
			res[i] = this.sigmoid(this.Z(i, layerNum-1, layerNum-1, leftLayer));
		}
		return res;
	}
	

	//GETTERS & SETTERS

	public double[] getInput() {
		return input;
	}

	public void setInput(double[] input) {
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

	public double[][] getBias() {
		return bias;
	}

	public void setBias(double[][] bias) {
		this.bias = bias;
	}
	public double[][][] getWeights() {
		return weights;
	}

	public void setWeights(double[][][] weights) {
		this.weights = weights;
	}

	public double[][][] getHiddenLayers() {
		return hiddenLayers;
	}

	public void setHiddenLayers(double[][][] hiddenLayers) {
		this.hiddenLayers = hiddenLayers;
	}
	

	

	
}
