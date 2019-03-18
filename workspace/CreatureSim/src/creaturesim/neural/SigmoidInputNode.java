package creaturesim.neural;

/**
 * Implementation of Node that can be given a value,
 * and that uses the sigmoid transfer function
 * 
 * @author jakesamuellawrence
 *
 */
public class SigmoidInputNode implements Node{
	double value;
	
	/**
	 * Stores the given value in the node
	 * 
	 * @param value the value to be stored
	 */
	public void giveValue(double value){
		this.value = value;
	}
	
	/**
	 * Uses the sigmoid transfer function to convert
	 * a given value.
	 * 
	 * @param in the value to be transferred
	 * @return the output of the transfer function
	 */
	@Override
	public double transfer(double in){
		return(TransferFunctions.sigmoid(in));
	}
	
	/**
	 * Gets the output by passing the held
	 * value through the transfer function
	 * 
	 * @return the required output of the node
	 */
	@Override
	public double getOutput(){
		return(transfer(value));
	}
	
	public void mutate(){
		
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
