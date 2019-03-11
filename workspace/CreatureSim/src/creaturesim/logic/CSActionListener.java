package creaturesim.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import creaturesim.Main;

public class CSActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println(e.getActionCommand());
		if(e.getActionCommand() == "Exit To Desktop"){
			System.exit(0);
		}
		else if(e.getActionCommand() == "Slow Generation"){
			Main.frame.canvas.switchCard("In Game");
		}
	}
}
