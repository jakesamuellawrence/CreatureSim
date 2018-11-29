package creaturesim;

import creaturesim.display.CSFrame;
import creaturesim.neural.Node;
import creaturesim.neural.SigmoidInputNode;
import creaturesim.neural.SigmoidNode;

public class Main{
	public static CSFrame frame;
	
	/**
	 * Is called upon executing the program. Serves as an entry point to the program
	 * 
	 * @param args arguments passed into the program upon running it
	 */
	public static void main(String[] args){
		//frame = new CSFrame(); 						// Creates a new instance of the CSFrame class
		
		SigmoidInputNode i1 = new SigmoidInputNode();
		i1.giveValue(0.5);
		SigmoidNode h1 = new SigmoidNode(new Node[]{(Node)i1});
		System.out.println(h1.toString());
	}
}
