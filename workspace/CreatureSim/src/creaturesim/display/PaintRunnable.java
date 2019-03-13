package creaturesim.display;

import creaturesim.Main;

/**
 * Runnable for constantly repainting the screen.
 * Designed to be run on a new Thread.
 * 
 * @author jakesamuellawrence
 * @see Thread, Runnable
 *
 */
public class PaintRunnable implements Runnable{
	
	double tick_time = 0.01;
	
	/**
	 * Repaints the window, then sleeps for 0.01 seconds.
	 */
	@Override
	public void run(){
		while(true){
			Main.frame.canvas.repaint();
			try{
				Thread.sleep((long)(tick_time*1000));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
