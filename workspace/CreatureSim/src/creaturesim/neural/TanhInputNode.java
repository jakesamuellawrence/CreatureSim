package creaturesim.neural;

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
	private void scaleBias(){
		double scalar = Math.random() + 0.5; // Random number between 0.5 and 1.5
		bias = bias * scalar;
	}
	private void shiftBias(){
		double shift = Math.random() - 0.5; // Random number between -0.5 and 0.5
		bias = bias + shift;
	}
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
