import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/*
 * This class creates a credits window
 * Filled with the creators names
 * and images with hyperlinks
 * to their google accounts
 * 
 *  author - Tom Zhu
 */

@SuppressWarnings("serial")
public class Credits extends JFrame implements ActionListener{

	// This code allows only one of instance of the class 
	//to open at one time
	private static Credits object = null; 

	//Labels and Buttons
	JButton [] person = new JButton[3]; //object containing seperate button images
	JLabel [] label = new JLabel[3];// object that contains the labels for every person's name
	JLabel [] instructions = new JLabel[2]; //object contains instructions for using hyperlinked images
	JButton all  = new JButton(); // one object to hold one picture of all of us

	//This constructor makes a new Jframe of this object
	public Credits(){
		setFrame();
		setButtons();
		setLabels();
	}

	//sets the credit frame up
	public void setFrame(){
		setLayout(null);
		setSize(1000,725);
		setTitle("Credits");
		getContentPane().setBackground(Color.BLACK);
	}

	//this method generates the Labels that go along with the image buttons
	private void setLabels() {
		// TODO Auto-generated method stub

		//initialize all the labels to be new objects + set fonts for all of them
		for(int i = 0 ; i <3 ; i ++){
			label[i] = new JLabel();
			label[i].setFont(new Font("Calibri", Font.BOLD, 40));

		}
		//initialize where the labels will be
		label[0].setBounds(100, 0, 200, 50);
		label[1].setBounds(100 + 1080/4 +60, 0, 100, 50);
		label[2].setBounds(100 + 1080/2  + 120, 0, 100, 50);

		// initialize what the labels will say
		label[0].setText("Anthony");
		label[1].setText("Tom");
		label[2].setText("Amna");

		//change color of Name labels + add to frame
		for(int i = 0; i <3; i++){
			label[i].setForeground(Color.white);
			add(label[i]);
		}


		//Initialize the labels with instructions on them
		instructions[0]= new JLabel();
		instructions[1]= new JLabel();

		//Set text on instructions
		instructions[0].setText("Click");
		instructions[1].setText("Photos");

		//Set font of instructions
		instructions[0].setFont(new Font("Calibri", Font.BOLD, 40));
		instructions[1].setFont(new Font("Calibri", Font.BOLD, 40));

		//Set bounds of instructions
		instructions[0].setBounds(300, 100, 100, 50);
		instructions[1].setBounds(600, 100, 120, 50);

		//set colour for instructions
		instructions[0].setForeground(Color.white);
		instructions[1].setForeground(Color.white);

		//add instructions
		add(instructions[0]);
		add(instructions[1]);
	}

	//This method generates buttons with creators pictures
	public void setButtons() {
		// TODO Auto-generated method stub
		//initialize all the button objects
		for(int i = 0; i <3; i ++){
			person[i] = new JButton();
		}
		//set all the coordinates of the 3 buttons
		person[0].setBounds(100, 50, 1080/8, 1920/8);
		person[1].setBounds(100 + 1080/4 +60, 50, 1080/8, 1920/8);
		person[2].setBounds(100 + 1080/2  + 120, 50, 1080/8, 1920/8);

		//for loop for all 3 buttons
		for(int i = 0; i <3; i++){

			// this line imports the different images from the pictures files 
			person[i].setIcon(new ImageIcon(new ImageIcon("resources/pictures/" + (i+1) + ".png").getImage().getScaledInstance(1080/8, 1920/8, 0)));

			//implment action listener to the buttons
			person[i].addActionListener(this);

			//add borders to every button
			person[i].setBorder(BorderFactory.createLineBorder(Color.RED));

			//add buttons to frame
			add(person[i]);
		}

		//initialize Button containing all the creators
		all.setBounds(1000/2 - 1600/4, 300, 1600/2, 720/2);

		//change border colour of the all Button
		all.setBorder(BorderFactory.createLineBorder(Color.green)); 

		//get a scaled image of the original image and set to all's icon
		all.setIcon(new ImageIcon(new ImageIcon("resources/pictures/All.jpg").getImage().getScaledInstance(1600/2, 720/2, 0)));

		all.addActionListener(this);
		//add label containing all of us
		add(all);
	}


	//this method assists in creating only one class at once
	public Credits getObject(){
		if (object == null){
			object = new Credits();
		}
		return object;
	}

	//This method gets a hyperlink and opens it in default browser
	private void openWebBrowser(String url) {
		if(Desktop.isDesktopSupported()){
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

		}
	}

	@Override
	// this method checks for actions that happen withen the buttons
	// it allows for the buttons to be hyperlinked.
	public void actionPerformed(ActionEvent action) {
		// TODO Auto-generated method stub

		// check who gets clicked and send the user to their respective hyperlinks
		if(action.getSource() == person[0]){
			openWebBrowser("https://plus.google.com/115943130320274355834");
		}else if(action.getSource() == person[1]){
			openWebBrowser("https://plus.google.com/110704312524823037588");
		}else if(action.getSource() == person[2]){
			openWebBrowser("https://plus.google.com/u/0/103253994805911717003");
		}
		if(action.getSource() == all){
			openWebBrowser("https://drive.google.com/a/gapps.yrdsb.ca/file/d/0B0bY1M4XBgoZZmxtODVBQ0VVQVE/view?usp=sharing");
		}

	}
}
