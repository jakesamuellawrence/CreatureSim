package creaturesim.logic;

import java.awt.Rectangle;
import java.util.ArrayList;

public class CompetitionManager{
	
	public static int generation_size = 10;
	
	public static Rectangle spawn_area = new Rectangle(-10, -10, 20, 20);
	
	static ArrayList<Generation> generations = new ArrayList<Generation>();
	static int generation_number;
	
	public static ArrayList<Creature> dead_creatures = new ArrayList<Creature>();
	
	public static ArrayList<FoodPellet> food = new ArrayList<FoodPellet>();
	public static int ticks_till_food_drop = 500;
	
	public static void initialise(){
		generations.add(new Generation());
		generation_number = 0;
		for(int i=0; i < 10; i++){
			food.add(new FoodPellet());
		}
	}
	
	public static void tick(){
		ticks_till_food_drop -= 1;
		if(ticks_till_food_drop == 0){
			addFood();
		}
		generations.get(generation_number).tick();
	}
	
	public static void kill(Creature target){
		Creature[] creatures = getCurrentGeneration().getCreatures();
		for(int i = 0; i < creatures.length; i++){
			if(creatures[i] == target){
				dead_creatures.add(creatures[i]);
			}
		}
	}
	
	static void addFood(){
		food.add(new FoodPellet());
		ticks_till_food_drop = 500;
	}
	public static void removeFood(FoodPellet target){
		food.remove(target);
	}
	
	public static Generation getCurrentGeneration(){
		return(generations.get(generation_number));
	}
}
