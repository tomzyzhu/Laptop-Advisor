import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


/*
 * This class allows the user to see a report that produces the top 3 laptops that best meet their criteria.
 * 
 * author - Tom Zhu
 */

public class LaptopAdvisorGUIReport extends JFrame implements ActionListener{

	//Optional Menu Bar
	LaptopAdvisorGUIMenuBar menuBar;
	
	//Rainbow background
	JLabel rainbow = new JLabel();
	//Report Panel elements
	static JPanel reportPanel = new JPanel();
	static JLabel reportPanelLabel = new JLabel(String.format("REPORT PANEL"));
	static JButton [] laptopImageButton = new JButton[3]; //images for all 3 laptops with hyperlinked images
	static JTextArea [] laptopTextAreaArray = new JTextArea [3]; // this one is the info for each laptop
	
	//List of laptops
	Laptop [] laptop;
	
	//List that stores the score index of top 3 laptops
	int[] scoreIndex = new int [3];
	//Stores the Labels that show which ones are #1, 2 ,3
	static JLabel [] place = new JLabel[3];
	
	//User
	User user;

	//timer used for rainbow effect
	Timer timer = new Timer();
	
	//These labels are for instruction on clicking the image hyperlink
	static JLabel [] instructions = new JLabel[2];
	
	static int color = 0; // color index to tell what colour to use currently for rainbow
	static int color2 = 0; //color index for labels
	
	/*
	 * A constructor method that reads in the full laptop list and calls methods that
	 * set up the report panel and frame
	 * and creates the report
	 */
	public LaptopAdvisorGUIReport(Laptop[] laptopArray, User user) {
		
		getContentPane().removeAll(); //This line removes all objects in case of restart
		
		//In case the user class if 0, give them a popup that says all the weightings
		//can't be 0, as restart the application
		boolean dispose = false;
		Exit:{ //Allows for exit of for loop without triggering the error msgBox
			for(int i = 0; i <7; i++){
				if(user.getWeightings()[i]!=0) //checks if all weightings are 0
					break Exit; //If one isn't 0, you break out of the loop
			}
			Information.msgBox("Error, all weightings 0!!! Please change!!!" , "ERROR!");
			dispose = true;
		}
		
		this.user = user;
		this.laptop = laptopArray;
		createReport();
		reportPanelSetup();
		//initialize menubar with laptopArray
		menuBar = new LaptopAdvisorGUIMenuBar(laptop);
		
		frameSetup();
		
		
		//Schedule a timer for rainbow effect
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  LaptopAdvisorGUIReport.changeColour();
				  LaptopAdvisorGUIReport.changeColour2();
			  }
			}, 200, 200);
		
		//
		
		if(dispose){
			new LaptopAdvisorTest();
			dispose();
		}
	}

	//This is the actual animation method that runs to change the colour of the labels every few milliseconds
		protected static void changeColour2() {
			// TODO Auto-generated method stub

			if(color2 == 0){// if index is 0, set to red colours

				instructions[0].setForeground(Color.RED);
				instructions[1].setForeground(Color.RED);

				//extra line to animate the arrows a little bit by changing x coordinate
				instructions[0].setBounds(instructions[0].getX()-5, instructions[0].getY(), instructions[0].getWidth(), instructions[0].getHeight());
				instructions[1].setBounds(instructions[1].getX()+5, instructions[1].getY(), instructions[1].getWidth(), instructions[1].getHeight());

			}else{// if index is 1, set to white colours

				instructions[0].setForeground(Color.BLACK);
				instructions[1].setForeground(Color.BLACK);

				//extra line to animate the arrows a little bit by changing x coordinate
				instructions[0].setBounds(instructions[0].getX()+5, instructions[0].getY(), instructions[0].getWidth(), instructions[0].getHeight());
				instructions[1].setBounds(instructions[1].getX()-5, instructions[1].getY(), instructions[1].getWidth(), instructions[1].getHeight());

			}

			color2 ++;//add one to color index to cycle it

			if(color2 ==2)
				color2 = 0; //resets color index if it gets too high
		}
		
	//changes the colour of the panel and panel label text
	protected static void changeColour() {
		// TODO Auto-generated method stub
		
		///chooses what colour to set it to
		if (0 == color){
			reportPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
			reportPanelLabel.setForeground(Color.RED);
			for(int i = 0; i <3; i ++){
				laptopImageButton[i].setBorder(BorderFactory.createLineBorder(Color.RED));
				laptopTextAreaArray[i].setBorder(BorderFactory.createLineBorder(Color.RED));
				place[i].setForeground(Color.RED);
			}
		}if (1 == color){
			reportPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 10));
			reportPanelLabel.setForeground(Color.ORANGE);
			for(int i = 0; i <3; i ++){
				laptopImageButton[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
				laptopTextAreaArray[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
				place[i].setForeground(Color.ORANGE);
			}
		}if (2 == color){
			reportPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 10));
			reportPanelLabel.setForeground(Color.YELLOW);
			for(int i = 0; i <3; i ++){
				laptopImageButton[i].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				laptopTextAreaArray[i].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				place[i].setForeground(Color.YELLOW);
			}
		}if (3 == color){
			reportPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
			reportPanelLabel.setForeground(Color.GREEN);
			for(int i = 0; i <3; i ++){
				laptopImageButton[i].setBorder(BorderFactory.createLineBorder(Color.GREEN));
				laptopTextAreaArray[i].setBorder(BorderFactory.createLineBorder(Color.GREEN));
				place[i].setForeground(Color.GREEN);
			}
		}if (4 == color){
			reportPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
			reportPanelLabel.setForeground(Color.BLUE);
			for(int i = 0; i <3; i ++){
				laptopImageButton[i].setBorder(BorderFactory.createLineBorder(Color.BLUE));
				laptopTextAreaArray[i].setBorder(BorderFactory.createLineBorder(Color.BLUE));
				place[i].setForeground(Color.BLUE);
			}
		}if (5 == color){
			reportPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 10));
			reportPanelLabel.setForeground(Color.CYAN);
			for(int i = 0; i <3; i ++){
				laptopImageButton[i].setBorder(BorderFactory.createLineBorder(Color.CYAN));
				laptopTextAreaArray[i].setBorder(BorderFactory.createLineBorder(Color.CYAN));
				place[i].setForeground(Color.CYAN);
			}
		}if (6 == color){
			reportPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 10));
			reportPanelLabel.setForeground(Color.MAGENTA);
			for(int i = 0; i <3; i ++){
				laptopImageButton[i].setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
				laptopTextAreaArray[i].setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
				place[i].setForeground(Color.MAGENTA);
			}
		}
		color ++; //add one to color index to cycle it
		
		if(color ==7)
			color = 0; //resets color index if it gets too high
	}

	//This method sets the frame up
	private void frameSetup() {
		
		setLayout(null);
		setSize(1366,725);
		setTitle("Report");
		getContentPane().setBackground(Color.WHITE);
		
		setupRainbow();
		
		setVisible(true);

		//initialize menubar with laptopArray
		menuBar = new LaptopAdvisorGUIMenuBar(laptop);
		setJMenuBar(menuBar);// add a menu bar
		menuBar.menuItem2.addActionListener(this); //adds action listener to restart to close frame
	
	}

	//setup rainbow spiral
	private void setupRainbow() {
		// TODO Auto-generated method stub
		
		rainbow.setBounds(0,0, 1366,725);
		rainbow.setIcon(new ImageIcon(new ImageIcon("resources/Rainbow.gif").getImage().getScaledInstance(1366,725, 0)));
		add(rainbow);
	}

	//This method sets the report panel
	private void reportPanelSetup() {
		// set up Report Panel
		reportPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
		reportPanel.setLayout(null);
		reportPanel.setBounds(20, 20, 1306, 725-110);
		add(reportPanel);
		
		//Set up Report Panel Label
		reportPanelLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		reportPanelLabel.setForeground(Color.BLACK);
		reportPanelLabel.setBounds(20, -40, 200, 100);
		add(reportPanelLabel);
		
		for(int i = 0; i < laptopTextAreaArray.length;i++){
			// setup laptopImageButton[i] 3 times
			laptopImageButton[i] = new JButton();
			laptopImageButton[i].setIcon(new ImageIcon(new ImageIcon("resources/laptop pictures/" + (scoreIndex[i]+1) + ".jpg").getImage().getScaledInstance(250, 200, 0)));
			laptopImageButton[i].setBounds(50 + (480*i), 50, 250, 200);
			laptopImageButton[i].addActionListener(this);
			reportPanel.add(laptopImageButton[i]);
			
			// setup laptopTextAreaArray[i] 3 times
			laptopTextAreaArray[i]= new JTextArea();
			laptopTextAreaArray[i].setText(getText(scoreIndex[i]));
			laptopTextAreaArray[i].setBounds(50 + (480*i), 250, 250, 300);
			laptopTextAreaArray[i].setBackground(Color.WHITE); //to match the background
			
			//These next 2 lines allow the text area to wrap around the text so it won't miss
			//any of the LaptopAdvisorGUIReport that is given
			laptopTextAreaArray[i].setLineWrap(true);
			laptopTextAreaArray[i].setWrapStyleWord(true);
			
			//disables editing
			laptopTextAreaArray[i].setEditable(false);
			reportPanel.add(laptopTextAreaArray[i]);
		}
		
		//Initialize instructions Label
		for(int i = 0; i <2; i ++){
			instructions[i] = new JLabel();
		}

		//Initialize hyperlink instruction labels with text
		instructions[0].setText("Click");
		instructions[1].setText("Laptops!");

		instructions[0].setFont(new Font("Calibri", Font.BOLD, 40));
		instructions[1].setFont(new Font("Calibri", Font.BOLD, 40));

		instructions[0].setForeground(Color.BLACK);
		instructions[1].setForeground(Color.BLACK);

		instructions[0].setBounds(370, 50, 300, 100);
		instructions[1].setBounds(825, 50, 300, 100);

		//add labels with text		
		reportPanel.add(instructions[0]);
		reportPanel.add(instructions[1]);
		
		//add labels that show which laptop is in which place
		for(int i = 0; i < 3; i ++){
			place[i] = new JLabel();
			place[i].setText("#" + (i+1));
			place[i].setBounds(150 + (480*i), 10, 100, 50);
			place[i].setFont(new Font("Calibri", Font.BOLD, 40));
			reportPanel.add(place[i]);
		}
		
		//change report panel colour
		reportPanel.setBackground(Color.WHITE);
	}
	
	//This method generates the text that will show up for every Text Area
	public String getText(int index) {
		// TODO Auto-generated method stub
		
		//store temporary string that will be outputed later
		String temp = "Name:" +laptop[index].getName() + "\n";
		
		//add all text that needs to be put into the output string
		//Note: I used this FORMAT to input the string because it LOOKS BETTER for humans
		temp = temp + "$" + laptop[index].getPrice() + "\n";
		temp = temp + laptop[index].getBrand() + "\n";
		temp = temp + laptop[index].getDisplay() + "\n";
		temp = temp + laptop[index].getCpu() + "\n";
		temp = temp + laptop[index].getGpu() + "\n";
		temp = temp + laptop[index].getDriveSize() + " " + laptop[index].getDriveType() + "\n";
		temp = temp + laptop[index].getRAM() + "\n";
		temp = temp + laptop[index].getSoftware() + "\n";
		temp = temp + laptop[index].getWeight() + " kg \n";
		temp = temp + laptop[index].getLength() + " x " + laptop[index].getWidth() + " x " + laptop[index].getHeight() + "(mm) in size \n";
		
		return temp;
	}

	//This method gets a hyperlink and opens it in default browser
	private void openWebBrowser(int index) {
		if(Desktop.isDesktopSupported()){
			try {
				Desktop.getDesktop().browse(new URI(laptop[scoreIndex[index]].getUrl()));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

		}
	}

	//This method creates the report
	//--> more specifically, it finds the 3 highest laptop scores and their indexes
	private void createReport() {
		
		//rank the laptops
		int [] score = rankLaptops();
		
		int [] highest = score.clone(); //creates an array to find the highest rated laptops
		
		Arrays.sort(highest); //sort array to find highest values
		
		//extract highest values
		int [] highVal = new int [3];
		highVal[0] = highest[29];
		highVal[1] = highest[28];
		highVal[2] = highest[27];
		
		//boolean array to check if the laptop has already been used or not
		//this needs to be used in case any 2 laptops get the same score
		boolean [] used = new boolean [30];
		
		//initialize boolean array
		for(int i = 0; i< 30; i ++){
			used[i] = false;
		}
		
		for(int i = 0; i <3; i ++){//this for loop gets all the highestValues
			
			Exit:{ //breaking out of the for loop when one of the highValues are found
			
				for(int i2 = 0; i2<30; i2++){//goes through all the laptops to find which one has the highest value
					
					if(score[i2] == highVal[i] && !used[i2]){ //checks if laptop is equal to highVal and if it was used before
						
						//tells computer that this laptop has already been used
						used[i2] = true;
						
						//sets score index to the correct index the laptop is
						scoreIndex[i]=i2;
						
						//exit for loop
						break Exit;
					}
					
				}
				
			}
		
		}
	}

	//This method gets the best laptops by using wdm (to get one laptop)
	//and comparing it with the other laptops
	private int[] rankLaptops(){
		
		int [] output = new int[30];//holds the output array
		
		for(int i = 0; i <30; i ++){//goes through all laptops to get every one of their scores
			output[i] = wdm(laptop[i], user);
		}
		
		return output;
	}

	
	//This method gets the score of one laptop
	private int wdm(Laptop currentlaptop, User user) {
		int score = 0;
		for(int i = 0; i <7 ; i ++){
			score += currentlaptop.getRating()[i] * user.getWeightings()[i];
		}
		return score;
	}

	
	//This method checks for buttons pressed
	public void actionPerformed(ActionEvent action) {
		for(int i = 0; i < 3; i++){
			if(action.getSource() == laptopImageButton[i]){
				openWebBrowser(i);
			}
		}
		if(action.getSource() == menuBar.menuItem2){
			//close frame when restart is pressed
			timer.cancel(); //stops the timer
			getContentPane().removeAll(); //This line removes all objects in case of restart
			reportPanel.removeAll(); //same as above for panels
			//all objects need to be removed from panel because its static
			this.dispose();
		}
	}
}

