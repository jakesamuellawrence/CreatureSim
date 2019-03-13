package creaturesim.logic;

/**
 * Runnable for ticking the logic of the competition.
 * Designed to be run on a new Thread.
 * 
 * @author jakesamuellawrence
 * @see Runnable, Thread
 */
public class LogicRunnable implements Runnable{
	
	double tick_time = 0.01;
	
	/**
	 * ticks the logic of the competition.
	 */
	@Override
	public void run(){
		while(true){
			CompetitionManager.tick();
			try{
				Thread.sleep((long)(tick_time*1000));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
