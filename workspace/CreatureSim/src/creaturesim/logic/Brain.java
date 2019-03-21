package creaturesim.logic;

import creaturesim.neural.HardlimNode;
import creaturesim.neural.Node;
import creaturesim.neural.SigmoidInputNode;
import creaturesim.neural.SigmoidNode;
import creaturesim.neural.TanhNode;

public class Brain{
	SigmoidInputNode i0 = new SigmoidInputNode(); // Bearing
	SigmoidInputNode i1 = new SigmoidInputNode(); // Distance to object
	SigmoidInputNode i2 = new SigmoidInputNode(); // Size of object
	SigmoidInputNode i3 = new SigmoidInputNode(); // Radius
	SigmoidInputNode i4 = new SigmoidInputNode(); // Movement Speed
	SigmoidInputNode[] inputs = new SigmoidInputNode[]{i0, i1, i2, i3, i4};
	SigmoidNode h0 = new SigmoidNode(inputs);
	SigmoidNode h1 = new SigmoidNode(inputs);
	SigmoidNode h2 = new SigmoidNode(inputs);
	SigmoidNode h3 = new SigmoidNode(inputs);
	SigmoidNode[] hiddens = new SigmoidNode[]{h0, h1, h2, h3};
	TanhNode turning = new TanhNode(hiddens);
	HardlimNode movement = new HardlimNode(hiddens);
	
	Node[] nodes = new Node[]{i0, i1, i2, i3, i4, h0, h1, h2, h3, turning, movement};
	
	public void loadValues(double bearing, double distance_to_object, double size_of_object, double radius, double speed){
		i0.giveValue(bearing);
		i1.giveValue(distance_to_object);
		i2.giveValue(size_of_object);
		i3.giveValue(radius);
		i4.giveValue(speed);
	}
	
	public void mutate(){
		double mutation_rate = CompetitionManager.mutation_rate;
		for(int i = 0; i < nodes.length; i++){
			if(Math.random() <= mutation_rate){
				nodes[i].mutate();
			}
		}
	}
	
	public double getTurning(){
		return(turning.getOutput());
	}
	
	public double getMovement(){
		return(movement.getOutput());
	}
}
