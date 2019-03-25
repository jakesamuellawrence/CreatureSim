package creaturesim.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import creaturesim.Main;

/**
 * Implementation of ActionListener that detects button pressed and responds to the
 * based on which button was pressed.
 * 
 * @author jakesamuellawrence
 * @see ActionListener
 */
public class CSActionListener implements ActionListener{
	
	public static boolean buttons_enabled = true; 
	
	/**
	 * Method called when a button is pressed. Responds differently depending on what button
	 * was pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if(buttons_enabled){
			if(e.getActionCommand() == "Exit To Desktop"){
				System.exit(0);
			}
			else if(e.getActionCommand() == "Slow Generation"){
				Main.logic_runnable.tick_time = LogicRunnable.slow_time;
				CompetitionManager.startCompetition();
				Main.frame.canvas.switchCard("In Game");
			}
			else if(e.getActionCommand() == "Quick Generation"){
				Main.logic_runnable.tick_time = LogicRunnable.quick_time;
				CompetitionManager.startCompetition();
			}
			else if(e.getActionCommand() == "View Previous Generation"){
				if(CompetitionManager.generation_number != 0){
					Main.logic_runnable.tick_time = LogicRunnable.slow_time;
					CompetitionManager.generation_number -= 1;
					CompetitionManager.getCurrentGeneration().reviveCreatures();
					CompetitionManager.startCompetition();
					Main.frame.canvas.switchCard("In Game");
				}
			}
		}
	}
}
