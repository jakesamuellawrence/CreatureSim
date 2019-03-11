package creaturesim.logic;

import java.util.ArrayList;

public class GenerationManager{
	
	public static ArrayList<Generation> generations = new ArrayList<Generation>();
	public static int generation_number;
	
	public static void initialise(){
		generations.add(new Generation());
		generation_number = 0;
	}
	
	public static void tick(){
		generations.get(generation_number).tick();
	}
	
	public static Generation getCurrentGeneration(){
		return(generations.get(generation_number));
	}
}
