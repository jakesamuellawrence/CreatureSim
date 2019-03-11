package creaturesim.logic;

import creaturesim.Main;

public class LogicRunnable implements Runnable{
	
	double tick_time = 0.001;
	
	@Override
	public void run(){
		while(true){
			GenerationManager.tick();
			try{
				Thread.sleep((long)(tick_time*1000));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}