package creaturesim.neural;

import creaturesim.logic.CompetitionManager;

/**
 * Implementation of Node that can be given a value,
 * and that uses the tanh transfer function
 * 
 * @author jakesamuellawrence
 *
 */
public class TanhInputNode implements Node{
	
	double value;
	
	double bias;
	
	public TanhInputNode(){
		bias = 2*Math.random() - 1; // Random number between -1 and 1
	}
	
	/**
	 * Stores the given value in the node
	 * 
	 * @param value the value to be stored
	 */
	public void giveValue(double value){
		this.value = value;
	}
	
	/**
	 * Uses the tanh transfer function to convert
	 * a given value.
	 * 
	 * @param in the value to be transferred
	 * @return the output of the transfer function
	 */
	@Override
	public double transfer(double in){
		return(TransferFunctions.tanh(in));
	}
	
	/**
	 * Gets the output by passing the held
	 * value through the transfer function
	 * 
	 * @return the required output of the node
	 */
	@Override
	public double getOutput(){
		return(transfer(value+bias));
	}
	
	/**
	 * Makes a small change to the bias of this node.
	 * will either scale, shift, or invert the bias.
	 * Calls other private methods in this class to
	 * achieve this
	 */
	public void mutate(){
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
		double scalar = Math.random()*2*mutation_factor + (1-mutation_factor);
		bias = bias * scalar;
	}
	
	/**
	 * Adds a random number based on the mutation factor to the bias 
	 */
	private void shiftBias(){
		double mutation_factor = CompetitionManager.mutation_factor;
		double shift = Math.random()*2*mutation_factor - mutation_factor;
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
	 * Returns information about the node in human-readable format.
	 * for testing purposes
	 * 
	 * @return String describing the node
	 */
	@Override
	public String toString(){
		return("Input node. Uses sigmoid. Currently held value is: " + Double.toString(value) + 
			   " value to output is: " + Double.toString(getOutput()));
	}
}
