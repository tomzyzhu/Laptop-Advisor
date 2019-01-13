import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * This class creates a frame that introduces the user to the application
 * as well as has a by line
 * 
 *  author - Tom Zhu
 */
public class Introduction extends JFrame implements ActionListener{

	//Laptop array 
	Laptop [] laptopArray;

	//Label to hold explosion gif
	JLabel explode = new JLabel();

	//This button contains the image and hyperlink to the current laptop
	JButton start = new JButton();

	//All the JLabels I need
	JLabel title = new JLabel ("Laptop Advisor");
	JLabel subTitle = new JLabel ("By: Tom, Anthony, and Amna");

	//timer to stop gif animation
	Timer timer = new Timer();

	//the constructor method makes a new JFrame of this object
	public Introduction(Laptop [] laptop){
		this.laptopArray = laptop;
		setFrame();
		setLabels();
		setButtons();
		repaint();
		
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  explode.setVisible(false);
				  repaint();
				  start.setVisible(true);
			  }
			}, 1500);
	}

	private void setFrame() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(1000, 725);
		setTitle("Introduction");
		setVisible(true);

	}

	//This method sets up all the labels
	private void setLabels() {
		// TODO Auto-generated method stub

		//initialize and add explosion gif label
		explode.setBounds(0, 0, 1000, 725);
		explode.setIcon(new ImageIcon(new ImageIcon("resources/explosion.gif").getImage().getScaledInstance(1000, 725, 0)));
		add(explode);

		//Initialize the Labels that should go on it + add them
		title.setBounds(350, 0, 400, 100);
		subTitle.setBounds(350, 100, 400, 50);

		title.setFont(new Font("Calibri", Font.BOLD, 40));
		subTitle.setFont(new Font("Calibri", Font.BOLD, 20));

		add(title);
		add(subTitle);



	}

	//this methods sets all the buttons onto the frame
	public void setButtons() {
		// TODO Auto-generated method stub

		//initialize the button image + button added to frame
		start.setBounds(320, 270, 300, 100);
		start.setText("Click here to start!");
		start.addActionListener(this);
		start.setVisible(false);
		add(start);
	}



	@Override
	//the action listener for the frame
	public void actionPerformed(ActionEvent action) {
		//since there is only one button, it will always only be the button
		dispose();
		new LaptopAdvisorGUIUserRatings(laptopArray, null);
	}


}
