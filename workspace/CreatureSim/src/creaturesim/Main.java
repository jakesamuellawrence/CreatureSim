package creaturesim;

import creaturesim.display.CSFrame;
import creaturesim.display.PaintRunnable;
import creaturesim.logic.Creature;
import creaturesim.logic.CompetitionManager;
import creaturesim.logic.LogicRunnable;

public class Main{
		
	public static CSFrame frame;
	
	public static LogicRunnable logic_runnable;
	public static Thread logic_thread;
	
	public static PaintRunnable paint_runnable;
	public static Thread paint_thread;
	
	/**
	 * Is called upon executing the program. Serves as an entry point to the program
	 * 
	 * @param args arguments passed into the program upon running it
	 */
	public static void main(String[] args){
		CompetitionManager.initialise();
		
		frame = new CSFrame(); 						// Creates a new instance of the CSFrame class
		
		logic_runnable = new LogicRunnable();
		
		paint_runnable = new PaintRunnable();
		paint_thread = new Thread(paint_runnable);
		paint_thread.start();
	}
	
	public static void startNewLogicThread(){
		logic_runnable.enabled = true;
		logic_thread = new Thread(logic_runnable);
		logic_thread.start();
	}
}
