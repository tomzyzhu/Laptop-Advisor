import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * This class creates a menu bar that can be used on all other screens.
 * This also could allow the user to access the help screen.
 * 
 *  author - Tom Zhu
 */

@SuppressWarnings("serial")
public class LaptopAdvisorGUIMenuBar extends JMenuBar implements ActionListener {

	//Create a menu(s)
	JMenu menu = new JMenu("Menu"); 
	//Create menu item(s);
	JMenuItem menuItem1 = new JMenuItem("Information");
	JMenuItem menuItem2 = new JMenuItem("Restart");
	JMenuItem menuItem3 = new JMenuItem("Credits");
	
	//Laptop array needs to store information if the user 
	//wants to open the information class
	Laptop [] laptopArray;

	

	//This method the constructor to create a new JMenuBar 
	// It also sets the different parts of the menu bar 
	//as well as the action listeners
	public LaptopAdvisorGUIMenuBar(Laptop [] laptop) {
		this.laptopArray = laptop;
		menuItem1.addActionListener(this);
		menu.add(menuItem1);
		menuItem2.addActionListener(this);
		menu.add(menuItem2);
		menuItem3.addActionListener(this);
		menu.add(menuItem3);
		add(menu);
	}
	
	//This method checks all actions that happen in the menu
	@Override
	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == menuItem1){
			
			//makes a new Information class 
			new Information(laptopArray);
			
		}if (action.getSource() == menuItem2){
			
			//system message
			System.out.println("Restarting!");
			
			//opens new instance of the start class
			new LaptopAdvisorTest();
			
			//note: I don't know which frame to close, so I actually CLOSE a frame
			//by having the aciton listener do it in every individual frame
		}if (action.getSource() == menuItem3){
			
			//makes a new Credits class if possible
			new Credits().getObject().setVisible(true);
		}
	}

}