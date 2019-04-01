package creaturesim.neural;

import java.util.Arrays;

import creaturesim.logic.CompetitionManager;

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
	 * given input nodes, then randomises the weights
	 * 
	 * @param inputs an array of nodes which the node should take inputs from.
	 */
	public HardlimNode(Node[] inputs){
		this.inputs = inputs;
		weights = new double[inputs.length];
		randomiseWeights();
	}
	
	/**
	 * Constructor that connects the node to all given
	 * input nodes, then sets it's weights and bias equal to the weights and bias
	 * of the node being cloned
	 * 
	 * @param inputs the other nodes this node should connect to
	 * @param node_to_clone the node this node should be a copy of
	 */
	public HardlimNode(Node[] inputs, HardlimNode node_to_clone){
		this.inputs = inputs;
		weights = node_to_clone.weights.clone();
		bias = node_to_clone.bias;
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
	
	/**
	 * Makes a small change to the bias of this node.
	 * will either scale, shift, or invert the bias.
	 * Calls other private methods in this class to
	 * achieve this
	 */
	public void mutateBias(){
		double mutation_type = Math.random();
		if(mutation_type < 0.45){ // High chance to scale
			scaleBias();
		}
		else if(mutation_type < 0.9){ // High chance to shift
			shiftBias();
		}
		else{ // Low chance to invert
			invertBias();
		}
	}
	
	/**
	 * Multiplies the bias by a random number between based on the mutation_factor
	 */
	private void scaleBias(){
		double mutation_factor = CompetitionManager.mutation_factor;
		double scalar = Math.random()*2*mutation_factor + (1-mutation_factor); // Random number between 0.5 and 1.5
		bias = bias * scalar;
	}
	
	/**
	 * Adds a random number based on the mutation factor to the bias 
	 */
	private void shiftBias(){
		double mutation_factor = CompetitionManager.mutation_factor;
		double shift = Math.random()*2*mutation_factor - mutation_factor; // Random number between -0.5 and 0.5
		bias = bias + shift;
	}
	
	/**
	 * makes the bias negative if it's currently positive, or makes
	 * the bias positive if it's currently negative
	 */
	private void invertBias(){
		bias = -bias;
	}
	
	/**
	 * Mutate's the chosen wieght in one of three ways: Scaling, shifting, or inverting
	 * 
	 * @param index the index of the weight to be mutated
	 */
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
	
	/**
	 * Multiplies the chosen weight by a random number based on the muatation_factor
	 * 
	 * @param i the index of the weight to be mutated
	 */
	private void scaleWeight(int i){
		double mutation_factor = CompetitionManager.mutation_factor;
		double scalar = Math.random()*2*mutation_factor + (1-mutation_factor);
		weights[i] *= scalar;
	}
	
	/**
	 * Adds a random number based on the mutation_factor to the chosen weight
	 * 
	 * @param i the index of the weight to be mutated
	 */
	private void shiftWeight(int i){
		double mutation_factor = CompetitionManager.mutation_factor;
		double shift = Math.random()*2*mutation_factor - mutation_factor;
		weights[i] += shift;
	}
	
	/**
	 * Makes the chosen weight negative if it's currently positive, or
	 * makes it positive if it's currently negative
	 * 
	 * @param i the index of the weight to be mutated
	 */
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
