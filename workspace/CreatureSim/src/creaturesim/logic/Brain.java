package creaturesim.logic;

import creaturesim.neural.HardlimNode;
import creaturesim.neural.Node;
import creaturesim.neural.SigmoidInputNode;
import creaturesim.neural.SigmoidNode;
import creaturesim.neural.TanhInputNode;
import creaturesim.neural.TanhNode;

public class Brain{
	
	TanhInputNode i0; // Distance to food, as proportion of diagonal of spawn area (1 if no food seen)
	TanhInputNode i1; // current radius
	TanhNode h0;
	TanhNode h1;
	HardlimNode turn_left;
	HardlimNode turn_right;
	HardlimNode move_forwards;
	HardlimNode move_backwards;
	
	Node[] nodes;
	
	/**
	 * Constructor which creates nodes with completely random weights and biases
	 */
	public Brain(){
		i0 = new TanhInputNode();
		i1 = new TanhInputNode();
		TanhInputNode[] inputs = new TanhInputNode[]{i0, i1};
		h0 = new TanhNode(inputs);
		h1 = new TanhNode(inputs);
		TanhNode[] hiddens = new TanhNode[]{h0, h1};
		turn_left = new HardlimNode(hiddens);
		turn_right = new HardlimNode(hiddens);
		move_forwards = new HardlimNode(hiddens);
		move_backwards = new HardlimNode(hiddens);
		
		nodes = new Node[]{i0, i1, h0, h1, turn_left, turn_right, move_forwards, move_backwards};
	}
	
	/**
	 * Constructor which creates nodes with the same weights and biases
	 * of the nodes of another brain
	 * 
	 * @param parent_brain the brain which is being copied
	 */
	public Brain(Brain parent_brain){
		i0 = new TanhInputNode(parent_brain.i0);
		i1 = new TanhInputNode(parent_brain.i1);
		TanhInputNode[] inputs = new TanhInputNode[]{i0, i1};
		h0 = new TanhNode(inputs, parent_brain.h0);
		h1 = new TanhNode(inputs, parent_brain.h1);
		TanhNode[] hiddens = new TanhNode[]{h0, h1};
		turn_left = new HardlimNode(hiddens, parent_brain.turn_left);
		turn_right = new HardlimNode(hiddens, parent_brain.turn_right);
		move_forwards = new HardlimNode(hiddens, parent_brain.move_forwards);
		move_backwards = new HardlimNode(hiddens, parent_brain.move_backwards);
		
		nodes = new Node[]{i0, i1, h0, h1, turn_left, turn_right, move_forwards, move_backwards};
	}
	
	/**
	 * Gives values to the input nodes, to prepare the network to be run through.
	 * 
	 * @param distance_to_object the value to be passed to i0
	 * @param size_of_object the value to be passed to i1
	 * @param radius the value to be passed to i2
	 * @param speed the value to be passed to i3
	 */
	public void loadValues(double distance_to_food, double current_radius){
		i0.giveValue(distance_to_food);
		i1.giveValue(current_radius);
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
