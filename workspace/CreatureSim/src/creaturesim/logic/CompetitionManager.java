package creaturesim.logic;

import java.awt.Rectangle;
import java.util.ArrayList;

import creaturesim.Main;

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
	
	public static double energy_loss_rate = 0.0005;
	
	public static int food_drop_time = 250000;
	
	public static double mutation_rate = 0.1;
	
	public static int generation_size = 20;
	
	public static Rectangle spawn_area = new Rectangle(-2*generation_size, -2*generation_size, 4*generation_size, 4*generation_size);
	
	static ArrayList<Generation> generations = new ArrayList<Generation>();
	static int generation_number;
	
	public static ArrayList<Creature> dead_creatures = new ArrayList<Creature>();
	
	public static ArrayList<FoodPellet> food = new ArrayList<FoodPellet>();
	public static int ticks_till_food_drop = food_drop_time;
	
	/**
	 * Sets up the CompetitionManager
	 * 
	 * Creates the initial generation, and add's 10 food pellets
	 */
	public static void initialise(){
		generations.add(new Generation());
		generation_number = 0;
	}
	
	public static void startCompetition(){
		Main.frame.canvas.switchCard("In Game");
		addFood(generation_size);
		getCurrentGeneration().scatterCreatures();
		Main.startNewLogicThread();
	}
	
	public static void endCompetition(){
		Main.logic_runnable.enabled = false;
		if(generation_number == generations.size()-1){
			generations.add(new Generation(dead_creatures));
		}
		generation_number += 1;
		dead_creatures = new ArrayList<Creature>();
		Main.frame.canvas.switchCard("Main Menu");
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
			addFood(1);
		}
		generations.get(generation_number).tick();
	}
	
	/**
	 * Adds a creature to the list of dead creatures.
	 * If the creature is the last to die, it ends the competition.
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
		if(dead_creatures.size() == generation_size){
			endCompetition();
		}
	}
	
	/**
	 * Adds a new piece of food in a random location, and resets the time until more food is added
	 */
	static void addFood(int quantity){
		for(int i = 0; i < quantity; i++){
			food.add(new FoodPellet());
			ticks_till_food_drop = food_drop_time;
		}
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
