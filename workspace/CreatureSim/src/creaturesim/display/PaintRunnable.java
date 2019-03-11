package creaturesim.display;

import creaturesim.Main;

public class PaintRunnable implements Runnable{
	
	double tick_time = 0.01;
	
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
