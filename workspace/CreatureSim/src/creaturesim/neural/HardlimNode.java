package creaturesim.neural;

import java.util.Arrays;

/**
 * Implementation of Node that gets inputs from an array of
 * other nodes. Uses the hardlim transfer function
 * 
 * @author jakesamuellawrence
 */

public class HardlimNode implements Node{
	Node[] inputs;
	double[] weights;
	double bias;
	
	/**
	 * Constructor that connects the node to all
	 * given input nodes.
	 * 
	 * @param inputs an array of nodes which the node should take inputs from.
	 */
	public HardlimNode(Node[] inputs){
		this.inputs = inputs;
		weights = new double[inputs.length];
		randomiseWeights();
	}
	
	/**
	 * Uses the hardlims transfer function to convert
	 * a given value.
	 * 
	 * @param in the value to be transferred
	 * @return the output of the transfer function
	 */
	@Override
	public double transfer(double in){
		return(TransferFunctions.hardlim(in));
	}
	
	/**
	 * Calculates the output of this node.
	 * <p>
	 * Gets the inputs from all nodes and multiplies them by the weights,
	 * then adds them all together,
	 * then adds the bias value,
	 * then passes the result through the chosen transfer function.
	 */
	public double getOutput(){
		double output = 0;
		for(int i = 0; i < inputs.length; i++){
			output = output + inputs[i].getOutput()*weights[i];
		}
		output = output + bias;
		output = transfer(output);
		return(output);
	}
	
	/**
	 * Generates random values for the weights and bias of the node
	 */
	public void randomiseWeights(){
		for(int i = 0; i < weights.length; i++){
			weights[i] = Math.random()*2 - 1;			// random number between -1 and 1
		}
		bias = Math.random()*2 - 1;						// random number between -1 and 1
	}
	
	/**
	 * makes random small changes to a weight or bias in this node.
	 * Will either scale, shift, or invert the chosen weight or bias.
	 * Calls other private methods to achieve this.
	 */
	public void mutate(){
		int weight_choice = (int)Math.floor(Math.random()*weights.length);
		if(weight_choice == weights.length){
			mutateBias();
		}
		else{
			mutateWeight(weight_choice);
		}
	}
	private void mutateBias(){
		double mutation_type = Math.random();
		if(mutation_type < 0.45){ //High chance to scale
			scaleBias();
		}
		else if(mutation_type < 0.9){ // High chance to shift
			shiftBias();
		}
		else{ // Low chance to shift
			invertBias();
		}
	}
	private void scaleBias(){
		double scalar = Math.random() + 0.5; // Random number between 0.5 and 1.5
		bias *= scalar;
	}
	private void shiftBias(){
		double shift = Math.random() - 0.5; // Random number between -0.5 and 0.5
		bias += shift;
	}
	private void invertBias(){
		bias = -bias;
	}
	private void mutateWeight(int index){
		double mutation_type = Math.random();
		if(mutation_type < 0.45){
			scaleWeight(index);
		}
		else if(mutation_type < 0.9){
			shiftWeight(index);
		}
		else{
			invertWeight(index);
		}
	}
	private void scaleWeight(int i){
		double scalar = Math.random() + 0.5; // Random number between 0.5 and 1.5
		weights[i] *= scalar;
	}
	private void shiftWeight(int i){
		double shift = Math.random() - 0.5; // Random number between -0.5 and 0.5
		weights[i] += shift;
	}
	private void invertWeight(int i){
		weights[i] = -weights[i];
	}
	
	/**
	 * Returns information about the node in human-readable format
	 * for testing purposes
	 * 
	 * @return String describing the node
	 */
	@Override
	public String toString(){
		return("Layer node. Uses hardlim. \n" +
			   "Weights are: " + Arrays.toString(weights) + "\n" + 
			   "Bias is: " + Double.toString(bias) + "\n" + 
			   "value to output is: " + Double.toString(getOutput()));
	}
}
