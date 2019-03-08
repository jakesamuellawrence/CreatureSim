package creaturesim.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CSActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println(e.getActionCommand());
		if(e.getActionCommand() == "Exit To Desktop"){
			System.exit(0);
		}
	}
}
