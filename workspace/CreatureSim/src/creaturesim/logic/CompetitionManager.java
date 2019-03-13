package creaturesim.logic;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Stores and is in charge of all things to do with competing the creatures.
 * Stores Mutliple Generations, all food pellets, and has methods for starting
 * and stopping generations, as well as ticking the logic of the current generation.
 * Everything is done statically, as only one CompetitionManager should ever exist.
 * 
 * @author jakesamuellawrence
 *
 */
public class CompetitionManager{
	
	public static int generation_size = 10;
	
	public static Rectangle spawn_area = new Rectangle(-10, -10, 20, 20);
	
	static ArrayList<Generation> generations = new ArrayList<Generation>();
	static int generation_number;
	
	public static ArrayList<Creature> dead_creatures = new ArrayList<Creature>();
	
	public static ArrayList<FoodPellet> food = new ArrayList<FoodPellet>();
	public static int food_drop_time = 250;
	public static int ticks_till_food_drop = food_drop_time;
	
	/**
	 * Sets up the CompetitionManager
	 * 
	 * Creates the initial generation, and add's 10 food pellets
	 */
	public static void initialise(){
		generations.add(new Generation());
		generation_number = 0;
		for(int i=0; i < 10; i++){
			food.add(new FoodPellet());
		}
	}
	
	/**
	 * Runs every tick, to move the logic forward.
	 * 
	 * Decreases the time until a new food pellet is added, adds a new
	 * food pellet if necessary, then ticks the logic of the current generation.
	 */
	public static void tick(){
		ticks_till_food_drop -= 1;
		if(ticks_till_food_drop == 0){
			addFood();
		}
		generations.get(generation_number).tick();
	}
	
	/**
	 * Removes a creature from the competition and adds them to the list of dead creatures.
	 * 
	 * @param target Creature, the creature to be removed
	 */
	public static void kill(Creature target){
		Creature[] creatures = getCurrentGeneration().getCreatures();
		for(int i = 0; i < creatures.length; i++){
			if(creatures[i] == target){
				dead_creatures.add(creatures[i]);
			}
		}
	}
	
	/**
	 * Adds a new piece of food in a random location, and resets the time until more food is added
	 */
	static void addFood(){
		food.add(new FoodPellet());
		ticks_till_food_drop = food_drop_time;
	}
	
	/**
	 * Remove food from the competition.
	 * 
	 * @param target FoodPellet, the piece of food to be removed.
	 */
	public static void removeFood(FoodPellet target){
		food.remove(target);
	}
	
	/**
	 * returns the generation that is currently being competed.
	 * 
	 * @return the generations currently being competed
	 */
	public static Generation getCurrentGeneration(){
		return(generations.get(generation_number));
	}
}