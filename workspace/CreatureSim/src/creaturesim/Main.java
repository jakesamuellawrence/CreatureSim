package creaturesim;

import creaturesim.display.CSFrame;
import creaturesim.neural.HardlimNode;
import creaturesim.neural.Node;
import creaturesim.neural.SigmoidInputNode;
import creaturesim.neural.SigmoidNode;
import creaturesim.neural.TanhNode;

public class Main{
	public static CSFrame frame;
	
	/**
	 * Is called upon executing the program. Serves as an entry point to the program
	 * 
	 * @param args arguments passed into the program upon running it
	 */
	public static void main(String[] args){
		//frame = new CSFrame(); 						// Creates a new instance of the CSFrame class
		
		int rotation_1s = 0;
		int rotation_0s = 0;
		int movement_1s = 0;
		int movement_0s = 0;
		
		for(int i = 0; i < 10000; i++){
			// Setup net
			SigmoidInputNode i0 = new SigmoidInputNode();	// Bearing from north, clockwise, in radians
			SigmoidInputNode i1 = new SigmoidInputNode();	// Distance to closest object in the direction they're facing
			SigmoidInputNode i2 = new SigmoidInputNode();	// Size (radius) of closest object, in metres
			SigmoidInputNode i3 = new SigmoidInputNode();	// Size (radius) of creature, in metres
			SigmoidInputNode i4 = new SigmoidInputNode();	// Creature's current movement speed, in metres per second
			SigmoidInputNode[] inputs = new SigmoidInputNode[]{i0, i1, i2, i3, i4};
			SigmoidNode h0 = new SigmoidNode(inputs);
			SigmoidNode h1 = new SigmoidNode(inputs);
			SigmoidNode h2 = new SigmoidNode(inputs);
			SigmoidNode h3 = new SigmoidNode(inputs);
			SigmoidNode[] hiddens = new SigmoidNode[]{h0, h1, h2, h3};
			HardlimNode u0 = new HardlimNode(hiddens);		// Rotation of the creature. 1 for clockwise, 0 for anti-clockwise
			HardlimNode u1 = new HardlimNode(hiddens);		// Movement of the creature. 1 for forwards, 0 for backwards
			
			// Give net test data
			i0.giveValue(Math.random());
			i1.giveValue(Math.random());
			i2.giveValue(Math.random());
			i3.giveValue(Math.random());
			i4.giveValue(Math.random());
			
			// Count frequency of 1s and 0s
			if(u1.getOutput() == 0){
				movement_0s++;
			}
			else{
				movement_1s++;
			}
			if(u0.getOutput() == 0){
				rotation_0s++;
			}
			else{
				rotation_1s++;
			}
		}
		System.out.println("Output of 1 was obtained from u0: " + Integer.toString(rotation_1s));
		System.out.println("Output of 0 was obtained from u0: " + Integer.toString(rotation_0s));
		System.out.println("Output of 1 was obtained from u1: " + Integer.toString(movement_1s));
		System.out.println("Output of 0 was obtained from u1: " + Integer.toString(movement_0s));
	}
}
