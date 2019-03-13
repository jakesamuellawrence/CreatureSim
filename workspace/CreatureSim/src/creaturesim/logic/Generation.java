package creaturesim.logic;

import java.util.ArrayList;

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
