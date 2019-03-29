package creaturesim.logic;

import creaturesim.neural.HardlimNode;
import creaturesim.neural.Node;
import creaturesim.neural.SigmoidInputNode;
import creaturesim.neural.SigmoidNode;
import creaturesim.neural.TanhInputNode;
import creaturesim.neural.TanhNode;

public class Brain{
	
	TanhInputNode i0 = new TanhInputNode(); // Can see food
	TanhInputNode[] inputs = new TanhInputNode[]{i0};
	TanhNode h0 = new TanhNode(inputs);
	TanhNode h1 = new TanhNode(inputs);
	TanhNode[] hiddens = new TanhNode[]{h0, h1};
	HardlimNode turn_left = new HardlimNode(hiddens);
	HardlimNode turn_right = new HardlimNode(hiddens);
	HardlimNode move_forwards = new HardlimNode(hiddens);
	HardlimNode move_backwards = new HardlimNode(hiddens);
	
	Node[] nodes = new Node[]{i0, h0, h1, turn_left, turn_right, move_forwards, move_backwards};
	
	/**
	 * Gives values to the input nodes, to prepare the network to be run through.
	 * 
	 * @param distance_to_object the value to be passed to i0
	 * @param size_of_object the value to be passed to i1
	 * @param radius the value to be passed to i2
	 * @param speed the value to be passed to i3
	 */
	public void loadValues(boolean can_see_food){
		if(can_see_food){
			i0.giveValue(1);
		}
		else{
			i0.giveValue(0);
		}
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
	 * Decides whether the creature should turn left, based on the output from it's neural net.
	 * 
	 * @return the decision of the creature
	 */
	public boolean shouldTurnLeft(){
		return(turn_left.getOutput() == 1);
	}
	
	/**
	 * Decides whether the creature should turn right, based on the output from it's neural net.
	 * 
	 * @return the decision of the creature
	 */
	public boolean shouldTurnRight(){
		return(turn_right.getOutput() == 1);
	}
	
	/**
	 * Decides whether the creature should move forwards
	 * 
	 * @return the output of the movement node
	 */
	public boolean shouldMoveForwards(){
		return(move_forwards.getOutput() == 1);
	}
	
	/**
	 * Decides whether the creature should move backwards
	 * 
	 * @return the output of the movement node
	 */
	public boolean shouldMoveBackwards(){
		return(move_backwards.getOutput() == 1);
	}
}
