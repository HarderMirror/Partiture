
public class NotesAI {
	private final int GROUPS_LENGHT = 10;
	private double[] input;
	private double[][] bias;
	private double[][][] weights;
	private double[][] hiddenLayers;
	private double[] res;
	private final double ALPHA_VALUE = -0.1; 

	//[3 2 2] = 3 first layer, 2 second, 2 third
	private int[] layers;
	
	public NotesAI(double[] input, int[] layers) {
		this.setLayers(layers);
		this.setInput(input);
		this.createHiddenLayers();
		this.setRandomWeights();
		
	}
	public NotesAI(double[] input, int[] layers, double[][][] weights) {
		this.setLayers(layers);
		this.setInput(input);
		this.createHiddenLayers();
		this.setWeights(weights);
		
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
	private double derivateOfZRespectBias() {
		return 1;
	}
	private double derivateOfZRespectWeight(int weightLayer, int posY, int posX) {
		return this.getWeights()[weightLayer][posY][posX];
	}
	private double derivateOfZRespectPrevNeuron() {
		return this.get
	}
	private double sigmoid(double Z) {
		return (double) (1 / (1 + (Math.pow(Math.E,-Z))));
	}
	private double derivativeOfSigmoid(double Z) {
		return this.sigmoid(Z) + (1-this.sigmoid(Z));
	}
	
	
	private void setRandomWeights() {
		for(int i = 1; i < this.getLayers().length; i++) {
			this.getWeights()[i-1] = np.random(this.getLayers()[i], this.getLayers()[i-1]);
		}
	}
	private void createHiddenLayers() {
		for(int i = 0; i < this.getLayers().length-2;i++) {
			this.getHiddenLayers()[i] = np.random(this.getLayers()[i+1],1)[0];
		}
		
	}
	
	private double[] feedForward(int layerNum, double[] leftLayer) {
		double[] res = new double[this.getLayers()[layerNum]];
		for(int i = 0; i < this.getLayers()[layerNum]; i++) {
			res[i] = this.sigmoid(this.Z(i, layerNum-1, layerNum-1, leftLayer));
		}
		return res;
	}
	private double cost(double[] testRes, double[] realRes) {
		double cost = 0;
		for(int i = 0; i < testRes.length; i++) {
			cost = cost + Math.pow((testRes[i] - realRes[i]),2);
		}
		return cost;
	}
	private double derivateOfCost(double[] testRes, double[] realRes) {
		double cost = 0;
		for(int i = 0; i < testRes.length; i++) {
			cost = cost + 2*(testRes[i] - realRes[i]);
		}
		return cost;
	}
	
	private double derivateOfCostRespectWeight(int weightLayer, int posY, int posX,
			double[] leftLayer, int posFinalNeuron, double[]testRes, double[]realRes) {
		return this.derivateOfZRespectWeight(weightLayer, posY, posX) *
				this.derivativeOfSigmoid(this.Z(posFinalNeuron, weightLayer, weightLayer, leftLayer)) *
				this.derivateOfCost(testRes, realRes);    
	}
	private double derivateOfCostRespectBias(int weightLayer, int posY, int posX,
			double[] leftLayer, int posFinalNeuron, double[]testRes, double[]realRes) {
		return this.derivateOfZRespectBias() *
				this.derivativeOfSigmoid(this.Z(posFinalNeuron, weightLayer, weightLayer, leftLayer)) *
				this.derivateOfCost(testRes, realRes);    
	}
	private double derivateOfCostRespectPrevNeuron(int weightLayer, int posY, int posX, double[]testRes, double[]realRes) {
		return this.derivateOfZRespectPrevNeuron (weightLayer, posY, posX) *
				this.derivativeOfSigmoid(this.Z(posFinalNeuron, weightsNum, biasLayer, leftLayer)) *
				this.derivateOfCost(testRes, realRes);    
	}
	
	
	private void backTrack() {
		
	}
	
	private void train(double[] input, double[] realRes) {
		this.setInput(input);
		for(int i = 0; i < this.getLayers().length; i++) {
			if(i == 0) {
				this.getHiddenLayers()[0] = this.feedForward(i+1, input);
			}else if(i == this.getLayers().length-1){
				this.setRes(this.feedForward(i+1,this.getHiddenLayers()[i-1]));
			}
			else{
				this.getHiddenLayers()[i] = this.feedForward(i+1, this.getHiddenLayers()[i-1]);
			}
						
		}
		
		//Until here we get the res by feedForward, now we have to get the cost
		
		double cost = this.cost(this.getRes(), realRes);
		
		//Now we have to backTrack to change weights, bias, and neurons
		
		//Change weights by layers
		for(int i = this.getWeights().length-1; i >= 0; i++) {
			
			for(int j = 0; j < this.getWeights()[i].length; j++) {
				for(int k = 0; k < this.getWeights()[i][j].length; k++) {
					if(i != 0) {
						this.getWeights()[i][j][k] = this.getWeights()[i][j][k] + this.ALPHA_VALUE * 
								this.derivateOfCostRespectWeight(i, j, k, this.getHiddenLayers()[i-1],
										j, this.getRes(), realRes);
					}else {
						this.getWeights()[i][j][k] = this.getWeights()[i][j][k] + this.ALPHA_VALUE * 
								this.derivateOfCostRespectWeight(i, j, k, this.getInput(),
										j, this.getRes(), realRes);
						
					}
					
				}
			}
		}
		
		
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

	public double[][] getHiddenLayers() {
		return hiddenLayers;
	}

	public void setHiddenLayers(double[][] hiddenLayers) {
		this.hiddenLayers = hiddenLayers;
	}
	public double[] getRes() {
		return res;
	}
	public void setRes(double[] res) {
		this.res = res;
	}
	
	

	

	
}
