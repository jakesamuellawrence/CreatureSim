package creaturesim.logic;

import creaturesim.neural.HardlimNode;
import creaturesim.neural.Node;
import creaturesim.neural.SigmoidInputNode;
import creaturesim.neural.SigmoidNode;
import creaturesim.neural.TanhInputNode;
import creaturesim.neural.TanhNode;

public class Brain{
	TanhInputNode i0 = new TanhInputNode(); // Distance to object
	TanhInputNode i1 = new TanhInputNode(); // Size of object
	TanhInputNode i2 = new TanhInputNode(); // Radius
	TanhInputNode i3 = new TanhInputNode(); // Movement Speed
	TanhInputNode[] inputs = new TanhInputNode[]{i0, i1, i2, i3};
	TanhNode h0 = new TanhNode(inputs);
	TanhNode h1 = new TanhNode(inputs);
	TanhNode h2 = new TanhNode(inputs);
	TanhNode[] hiddens = new TanhNode[]{h0, h1, h2};
	TanhNode turning = new TanhNode(hiddens);
	HardlimNode movement = new HardlimNode(hiddens);
	
	Node[] nodes = new Node[]{i0, i1, i2, i3, h0, h1, h2, turning, movement};
	
	/**
	 * Gives values to the input nodes, to prepare the network to be run through.
	 * 
	 * @param distance_to_object the value to be passed to i0
	 * @param size_of_object the value to be passed to i1
	 * @param radius the value to be passed to i2
	 * @param speed the value to be passed to i3
	 */
	public void loadValues(double distance_to_object, double size_of_object, double radius, double speed){
		i0.giveValue(distance_to_object);
		i1.giveValue(size_of_object);
		i2.giveValue(radius);
		i3.giveValue(speed);
	}
	
	/**
	 * For each Node in the brain, randomly decides whether it should be mutated
	 */
	public void mutate(){
		double mutation_rate = CompetitionManager.mutation_rate;
		for(int i = 0; i < nodes.length; i++){
			if(Math.random() <= mutation_rate){
				nodes[i].mutate();
			}
		}
	}
	
	/**
	 * Returns the output from the turning output node
	 * 
	 * @return the output of the turning node
	 */
	public double getTurning(){
		return(turning.getOutput());
	}
	
	/**
	 * Returns the output from the movement output Node
	 * 
	 * @return the output of the movement node
	 */
	public double getMovement(){
		return(movement.getOutput());
	}
}
