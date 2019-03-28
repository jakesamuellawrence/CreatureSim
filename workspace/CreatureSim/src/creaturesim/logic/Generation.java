package creaturesim.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an entire group of creatures whom are all competing.
 * 
 * @author jakesamuellawrence
 */
public class Generation{
	
	public int highest_survival_time;
	public int average_survival_time;
	public int lowest_survival_time;
	
	public String best_creature_name = "No previous generations";
	
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
		List<Creature> top_45 = previous.subList(previous.size()-percent_45, previous.size());
		for(int i = 0; i < previous.size(); i++){
			System.out.println(previous.get(i).survival_time
							   + " " + previous.get(i).first_name 
							   + " " + previous.get(i).last_name);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int i = 0; i < top_45.size(); i++){
			creatures[i] = top_45.get(i).makeClone();
			creatures[i+top_45.size()] = top_45.get(i).makeChild();
		}
		for(int i = 2*top_45.size(); i < creatures.length; i++){
			creatures[i] = new Creature();
		}
	}
	
	/**
	 * moves all creatures in the generation to a random unoccupied spot
	 */
	public void scatterCreatures(){
		for(int i = 0; i < creatures.length; i++){
			creatures[i].spawnInRandomLocation();
		}
	}
	
	/**
	 * makes all creatures in the generation alive and set's their radius to the default value
	 */
	public void reviveCreatures(){
		for(int i = 0; i < creatures.length; i++){
			creatures[i].revive();
		}
	}
	
	/**
	 * Calculates statistics about the current generation
	 * 
	 * Finds the best, average, and worst survival times.
	 * Stores the name of the creature with the best score.
	 */
	public void calculateStatistics(){
		highest_survival_time = creatures[0].survival_time;
		lowest_survival_time = creatures[0].survival_time;
		best_creature_name = creatures[0].first_name + " " + creatures[0].last_name;
		int total = creatures[0].survival_time;
		for(int i = 1; i < creatures.length; i++){
			if(creatures[i].survival_time > highest_survival_time){
				highest_survival_time = creatures[i].survival_time;
				best_creature_name = creatures[i].first_name + " " + creatures[i].last_name;
			}
			else if(creatures[i].survival_time < lowest_survival_time){
				lowest_survival_time = creatures[i].survival_time;
			}
			total += creatures[i].survival_time;
		}
		average_survival_time = total / creatures.length;
	}
	
	/**
	 * Ticks through the logic of the whole generation.
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
