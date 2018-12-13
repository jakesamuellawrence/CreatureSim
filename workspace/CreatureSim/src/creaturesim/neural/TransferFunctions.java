package creaturesim.neural;

/**
 * Defines static transfer functions 
 * 
 * @author jakesamuellawrence
 */
public class TransferFunctions{
	/**
	 * Non-linear mathamatical function, also
	 * called the Logistic function.
	 * 
	 * @param x the x-value passed into the function
	 * @return f(x), where f(x) = (1)/(1 + e^-x)
	 */
	public static double sigmoid(double x){
		return(1 / (1 + Math.pow(Math.E, -x)));	
	}
	
	/**
	 * Returns 1 if the x value is greater than 0.5, 
	 * or 0 otherwise
	 * 
	 * @param x the x-value passed into the function
	 * @return 1 if x>0.5, else 0
	 */
	public static double hardlim(double x){
		if(x > 0){
			return(1);
		}
		return(0);
	}
	
	public static double tanh(double x){
		return(Math.tanh(x));
	}
}
