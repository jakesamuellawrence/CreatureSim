package creaturesim.neural;

import java.util.Arrays;

/**
 * Implementation of Node that gets inputs from an array of
 * other nodes. Uses the sigmoid transfer function
 * 
 * @author jakesamuellawrence
 */

public class SigmoidNode implements Node{
	Node[] inputs;
	double[] weights;
	double bias;
	
	/**
	 * Constructor that connects the node to all
	 * given input nodes.
	 * 
	 * @param inputs an array of nodes which the node should take inputs from.
	 */
	public SigmoidNode(Node[] inputs){
		this.inputs = inputs;
		weights = new double[inputs.length];
		randomiseWeights();
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
	 * Calculates the output of this node.
	 * <p>
	 * Gets the inputs from all nodes and multiplies them by the corresponding weights,
	 * then adds them all, then adds the bias value
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
	 * Returns information about the node in human-readable format
	 * for testing purposes
	 * 
	 * @return String describing the node
	 */
	@Override
	public String toString(){
		return("Layer node. Uses sigmoid. \n" +
			   "Weights are: " + Arrays.toString(weights) + "\n" + 
			   "Bias is: " + Double.toString(bias) + "\n" + 
			   "value to output is: " + Double.toString(getOutput()));
	}
}