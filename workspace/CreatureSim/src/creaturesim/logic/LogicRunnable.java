package creaturesim.logic;

/**
 * Runnable for ticking the logic of the competition.
 * Designed to be run on a new Thread.
 * 
 * @author jakesamuellawrence
 * @see Runnable, Thread
 */
public class LogicRunnable implements Runnable{
	
	double slow_time = 0.01;
	double quick_time = 0;
	
	public double tick_time;
	
	public boolean enabled = false;
	
	/**
	 * ticks the logic of the competition.
	 */
	@Override
	public void run(){
		while(enabled){
			CompetitionManager.tick();
			try{
				Thread.sleep((long)(tick_time*1000));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
