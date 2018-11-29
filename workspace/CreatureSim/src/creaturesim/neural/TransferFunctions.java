package creaturesim.neural;

/**
 * Defines static transfer functions 
 * 
 * @author jakesamuellawrence
 */
public class TransferFunctions{
	public static double sigmoid(double x){
		return(1 / (1 + Math.pow(Math.E, -x)));	
	}
}
