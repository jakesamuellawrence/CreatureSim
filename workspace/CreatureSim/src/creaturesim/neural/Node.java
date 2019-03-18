package creaturesim.neural;

/**
 * Defines a node.
 * <p>
 * A node is a container for a value. Any node will
 * be able to take in inputs, change them with some function,
 * and output it. The function the inputs are passed into
 * is known as a transfer function.
 * 
 * @author jakesamuellawrence
 */
public interface Node{
	double transfer(double in);
	double getOutput();
	void mutate();
}
