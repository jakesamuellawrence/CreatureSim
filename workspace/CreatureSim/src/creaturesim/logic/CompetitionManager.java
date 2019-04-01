package creaturesim.logic;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	public static int food_drop_time = 1000;
	
	public static double mutation_rate = 0.1;
	
	public static double mutation_factor = 0.1;
	
	public static int generation_size = 10;
	
	public static Rectangle spawn_area = new Rectangle(-generation_size, -generation_size, 2*generation_size, 2*generation_size);
	public static double average_distance = 0.52 * 2 * generation_size;
	
	public static ArrayList<Generation> generations;
	public static int generation_number;
	
	public static ArrayList<Creature> dead_creatures = new ArrayList<Creature>();
	
	public static ArrayList<FoodPellet> food = new ArrayList<FoodPellet>();
	public static int ticks_till_food_drop = (int) (food_drop_time / Math.pow((generation_size - dead_creatures.size()), 1.2));
	
	/**
	 * Sets up the CompetitionManager
	 * 
	 * makes a new list of generations, wiping whatever generations may have already been stored,
	 * then creates the first generation
	 */
	public static void initialise(){
		generations = new ArrayList<Generation>();
		generations.add(new Generation());
		generation_number = 0;
		CSActionListener.buttons_enabled = true;
	}
	
	/**
	 * Begins a competition by adding 1 food for every creature,
	 * scattering the creatures of the current generation, and beginning a new logic thread
	 */
	public static void startCompetition(){
		getCurrentGeneration().scatterCreatures();
		getCurrentGeneration().reviveCreatures();
		addFood(2*generation_size);
		Main.startNewLogicThread();
	}
	
	/**
	 * Ends the competition and stops the creature logic being stopped. Is called when all creatures are dead.
	 * 
	 * stops the logic thread, then checks to see whether the generation that's just been competed
	 * is the latest. If it is, it updates statistics about that generation, then creates a new generation.
	 * increments the tracker of what the current generation is.
	 * resets the list of dead creatures, resets the array of food.
	 * Switches to the main menu view, then enables the main menu's buttons
	 */
	public static void endCompetition(){
		Main.logic_runnable.enabled = false;
		if(generation_number == generations.size()-1){
			getCurrentGeneration().calculateStatistics();
			System.out.println(dead_creatures.size());
			generations.add(new Generation(dead_creatures));
		}
		generation_number += 1;
		dead_creatures = new ArrayList<Creature>();
		food = new ArrayList<FoodPellet>();
		Main.frame.canvas.switchCard("Main Menu");
		CSActionListener.buttons_enabled = true;
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
			ticks_till_food_drop = (int) (food_drop_time / Math.pow((generation_size - dead_creatures.size()), 1.2));
		}
		getCurrentGeneration().tick();
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
			FoodPellet pellet = new FoodPellet();
			if(pellet.overcrowded == false){
				food.add(pellet);
			}
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
	 * Picks a random name from the csv file of names.
	 * 
	 * @return A random name chosen from a list
	 */
	public static String getRandomName(){
		String name = "You've messed it up somehow :("; // This will be returned unchanged if it somehow malfunctions
		try{
			File names_list = new File("resources/names.csv");
			BufferedReader names_reader = new BufferedReader(new FileReader(names_list));
			long number_of_lines = names_reader.lines().count();
			names_reader.close();
			names_reader = new BufferedReader(new FileReader(names_list));
			int line_to_read = (int)Math.round(Math.random()*number_of_lines);
			for(int i = 0; i <= number_of_lines; i++){
				if(i == line_to_read){
					name = names_reader.readLine();
				}
				else{
					names_reader.readLine();
				}
			}
			names_reader.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return(name);
	}
	
	/**
	 * returns the generation that is currently being competed.
	 * 
	 * @return the generations currently being competed
	 */
	public static Generation getCurrentGeneration(){
		return(generations.get(generation_number));
	}
	
	/**
	 * returns the generation that has just finished competing.
	 * 
	 * @return the generation just finished
	 */
	public static Generation getPreviousGeneration(){
		if(generation_number == 0){
			return(generations.get(generation_number));
		}
		else{
			return(generations.get(generation_number-1));
		}
	}
}
