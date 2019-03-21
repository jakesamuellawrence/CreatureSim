package creaturesim.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an entire group of creatures whom are all competing.
 * 
 * @author jakesamuellawrence
 */
public class Generation{
	
	public Creature[] creatures = new Creature[CompetitionManager.generation_size];
	
	/**
	 * Constructor for generation which makes an entirely random set of creatures
	 */
	public Generation(){
		for(int i = 0; i < creatures.length; i++){
			creatures[i] = new Creature();
		}
	}
	
	/**
	 * Constructor for generation which makes new creatures mutated from the
	 * best creatures of the previous generation.
	 * 
	 * @param previous ArrayList<Creature>, the previous generation's dead creatures, sorted by survival time ascending
	 */
	public Generation(ArrayList<Creature> previous){
		int generation_size = CompetitionManager.generation_size;
		int percent_45 = (int) Math.floor(generation_size * 0.45);
		List<Creature> top_45 = previous.subList(generation_size-1-percent_45, generation_size-1);
		for(int i = 0; i < top_45.size(); i++){
			creatures[i] = top_45.get(i).makeClone();
			creatures[i+top_45.size()] = top_45.get(i).makeChild();
		}
		for(int i = 2*top_45.size(); i < creatures.length; i++){
			creatures[i] = new Creature();
		}
	}
	
	/**
	 * Ticks through the logic of the generation.
	 * 
	 * Ticks the logic of each creature in the generation, if they are alive.
	 */
	void tick(){
		for(int i = 0; i < creatures.length; i++){
			if(creatures[i].isAlive()){
				creatures[i].tick();
			}
		}
	}
	
	/**
	 * returns the array of creatures that comprises the generation
	 * 
	 * @return the array of creatures
	 */
	public Creature[] getCreatures(){
		return(creatures);
	}
}
