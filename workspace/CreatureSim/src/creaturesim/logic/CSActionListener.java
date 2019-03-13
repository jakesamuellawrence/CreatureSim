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
	
	/**
	 * Method called when a button is pressed. Responds differently depending on what button
	 * was pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println(e.getActionCommand());
		if(e.getActionCommand() == "Exit To Desktop"){
			System.exit(0);
		}
		else if(e.getActionCommand() == "Slow Generation"){
			Main.logic_runnable.tick_time = Main.logic_runnable.slow_time;
			CompetitionManager.startCompetition();
		}
	}
}
