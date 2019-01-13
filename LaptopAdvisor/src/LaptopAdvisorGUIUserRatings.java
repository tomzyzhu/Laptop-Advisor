/*
 * This class contains a frame with a panel that allows the user to enter their subjective ratings
 * by clicking on radio buttons
 *
 * This class generates a frame with a panel on which the users can 
 * select their subjective ratings
 * 
 * author - Anthony Wong
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

public class LaptopAdvisorGUIUserRatings extends JFrame implements ActionListener {

	//Fields or Instance Variables
	//Optional Menu Bar
	LaptopAdvisorGUIMenuBar menuBar;

	//User Ratings Panel elements
	JPanel userRatingsPanel = new JPanel();   //user rating panel

	Font font = new Font("Calibri", Font.BOLD, 15);   //font and font size

	//labels of questions to ask the users
	JLabel brand = new JLabel("1.Which of the following brands do you prefer?");
	JLabel budget = new JLabel("2.How much is your budget?");
	JLabel cpu = new JLabel("3.Is it important for you to have a good processor?");
	JLabel RAM = new JLabel("4.How much RAM do you prefer?");
	JLabel software = new JLabel("5.Which of the following software do you prefer?");
	JLabel screenSize = new JLabel("6.Which of the following screen size do you prefer?");
	JLabel gpu = new JLabel("7.Is it important for you to have a good video card?");

	//Weightings button
	JButton weighting = new JButton("Continue"); 

	//all the radio buttons
	JRadioButton[] brandRating = new JRadioButton[8];   //user rating brands
	ButtonGroup brandButtonGroup = new ButtonGroup();

	JRadioButton[] budgetRating = new JRadioButton[5];   //user rating budgets
	ButtonGroup budgetButtonGroup = new ButtonGroup();

	JRadioButton[] screenSizeRating = new JRadioButton[9];    //user rating screen sizes
	ButtonGroup screenSizeButtonGroup = new ButtonGroup();

	JRadioButton[] cpuRating = new JRadioButton[4];
	ButtonGroup cpuButtonGroup = new ButtonGroup();

	JRadioButton[] gpuRating = new JRadioButton[2];
	ButtonGroup gpuButtonGroup = new ButtonGroup();

	JRadioButton[] RAMrating = new JRadioButton[6];
	ButtonGroup RAMButtonGroup = new ButtonGroup();

	JRadioButton[] softwareRating = new JRadioButton[3];
	ButtonGroup softwareButtonGroup = new ButtonGroup();

	//list of laptop
	Laptop[] laptop;

	//users
	User user;

	/*0 price 
	 *1 brand
	 *2 cpumark
	 *3 gpumark
	 *4 RAM
	 *5 operating software
	 *6 screen size */

	/*
	 * A constructor method that reads in the full laptop list, the user and sets up 
	 * the user panel rating panel and a frame
	 */

	public LaptopAdvisorGUIUserRatings(Laptop[] laptopA, User userA) {	

		int choice = 0;

		this.user = userA;
		this.laptop = laptopA;
		menuBar = new LaptopAdvisorGUIMenuBar(laptop); //initialize menubar
		frameSetup();
		userRatingsPanelSetup();
		setBrandRating(choice);
		setBudgetRating(choice);
		setCPURating(choice);
		setGPURating(choice);
		setRAMRating(choice);
		setSoftwareRating(choice);
		setScreenSizeRating(choice);
		repaint();

	}

	private void frameSetup() {

		setTitle("Laptop Advisor");
		setLayout(null);
		setSize (1366, 725);
		setResizable(false);
		setVisible(true);
		add(userRatingsPanel);

		//initialize menubar with laptopArray
		menuBar = new LaptopAdvisorGUIMenuBar(laptop);
		setJMenuBar(menuBar);// add a menu bar
		menuBar.menuItem2.addActionListener(this); //adds action listener to restart to close frame

	}

	private void userRatingsPanelSetup() {

		//border and size setup of the panel
		userRatingsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		userRatingsPanel.setLayout(null);
		userRatingsPanel.setBounds(10, 10, 1340, 670);

		//weighting button to go to the weighting panel
		weighting.setBounds(1150, 600, 140, 50);
		weighting.setFont(font);
		weighting.addActionListener(this);
		userRatingsPanel.add(weighting);


		//setup the questions to ask the users
		userRatingsPanelSetupQuestions();

		setupRadioButtons();

		//put all the radio buttons under each of the label questions
		//brand questions
		for(int x = 0; x < 8; x++) {
			brandRating[x].setBounds(50, 60 + 20 * x, 150, 20);
			brandRating[x].addActionListener(this);
			userRatingsPanel.add(brandRating[x]);
		}

		for(int x = 0; x < 5; x++) {
			budgetRating[x].setBounds(400, 60 + 20 * x, 250, 20);
			budgetRating[x].addActionListener(this);
			userRatingsPanel.add(budgetRating[x]);
		}

		for(int x = 0; x < 4; x++) {
			cpuRating[x].setBounds(680, 60 + 20 * x, 250, 20);
			cpuRating[x].addActionListener(this);
			userRatingsPanel.add(cpuRating[x]);
		}

		for(int x = 0; x < 6; x++) {
			RAMrating[x].setBounds(1050, 60 + 20 * x, 150, 20);
			RAMrating[x].addActionListener(this);
			userRatingsPanel.add(RAMrating[x]);
		}


		for(int x = 0; x < 3; x++) {
			softwareRating[x].setBounds(50, 375 + 20 * x, 150, 20);
			softwareRating[x].addActionListener(this);
			userRatingsPanel.add(softwareRating[x]);
		}

		for(int x = 0; x < 9; x++) {
			screenSizeRating[x].setBounds(450, 375 + 20 * x, 150, 20);
			screenSizeRating[x].addActionListener(this);
			userRatingsPanel.add(screenSizeRating[x]);
		}

		for(int x = 0; x < 2; x++) {
			gpuRating[x].setBounds(850, 375 + 20 * x, 150, 20);
			gpuRating[x].addActionListener(this);
			userRatingsPanel.add(gpuRating[x]);
		}

	}

	private void userRatingsPanelSetupQuestions() {

		//add the label questions to the panel
		brand.setBounds(30, 30, 350, 15);
		brand.setFont(font);
		userRatingsPanel.add(brand);

		budget.setBounds(390, 30, 350, 15);
		budget.setFont(font);
		userRatingsPanel.add(budget);

		cpu.setBounds(650, 30, 350, 15);
		cpu.setFont(font);
		userRatingsPanel.add(cpu);

		RAM.setBounds(1030, 30, 350, 15);
		RAM.setFont(font);
		userRatingsPanel.add(RAM);

		software.setBounds(30, 335, 350, 15);
		software.setFont(font);
		userRatingsPanel.add(software);

		screenSize.setBounds(410, 335, 350, 15);
		screenSize.setFont(font);
		userRatingsPanel.add(screenSize);

		gpu.setBounds(800, 335, 350, 15);
		gpu.setFont(font);
		userRatingsPanel.add(gpu);

	}

	private void setupRadioButtons() {

		//brand options
		brandRating[0] = new JRadioButton ("Dell");
		brandRating[1] = new JRadioButton ("HP");
		brandRating[2] = new JRadioButton ("Asus");
		brandRating[3] = new JRadioButton ("Apple");
		brandRating[4] = new JRadioButton ("Windows");
		brandRating[5] = new JRadioButton ("Acer");
		brandRating[6] = new JRadioButton ("Lenovo");
		brandRating[7] = new JRadioButton ("No preference");

		//budget options
		budgetRating[0] = new JRadioButton ("< $250");
		budgetRating[1] = new JRadioButton ("> $250 & < $500");
		budgetRating[2] = new JRadioButton ("> $500 & < $750");
		budgetRating[3] = new JRadioButton ("> $750 & < $1,000");
		budgetRating[4] = new JRadioButton ("> $ 1,000");

		//displays size
		screenSizeRating[0] = new JRadioButton ("13.3 inch"); 
		screenSizeRating[1] = new JRadioButton ("15.6 inch");
		screenSizeRating[2] = new JRadioButton ("12 inch");
		screenSizeRating[3] = new JRadioButton ("13.5 inch");
		screenSizeRating[4] = new JRadioButton ("15 inch");
		screenSizeRating[5] = new JRadioButton ("14 inch");
		screenSizeRating[6] = new JRadioButton ("11.6 inch");
		screenSizeRating[7] = new JRadioButton ("17.3 inch");
		screenSizeRating[8] = new JRadioButton ("12.5 inch");

		//cpu processor options
		cpuRating[0] = new JRadioButton ("I need a really good cpu");
		cpuRating[1] = new JRadioButton ("I need a good cpu");
		cpuRating[2] = new JRadioButton ("I don't really need a good cpu");
		cpuRating[3] = new JRadioButton ("I only need a cpu that can work");

		//gpu graphics options
		gpuRating[0] = new JRadioButton ("Yes, it is important");
		gpuRating[1] = new JRadioButton ("No, it isn't important");

		//RAM options
		RAMrating[0] = new JRadioButton ("32GB");
		RAMrating[1] = new JRadioButton ("16GB");
		RAMrating[2] = new JRadioButton ("12GB");
		RAMrating[3] = new JRadioButton ("8GB");
		RAMrating[4] = new JRadioButton ("4GB");
		RAMrating[5] = new JRadioButton ("2GB");

		//software options
		softwareRating[0] = new JRadioButton ("Windows 10");
		softwareRating[1] = new JRadioButton ("Chrome OS");
		softwareRating[2] = new JRadioButton ("macOS Sierra");

		for(int x = 0; x < 8; x++)
			brandButtonGroup.add(brandRating[x]);

		for(int x = 0; x < 5; x++)
			budgetButtonGroup.add(budgetRating[x]);

		for(int x = 0; x < 9; x++)
			screenSizeButtonGroup.add(screenSizeRating[x]);

		for(int x = 0; x < 4; x++)
			cpuButtonGroup.add(cpuRating[x]);

		for(int x = 0; x < 2; x++)
			gpuButtonGroup.add(gpuRating[x]);

		for(int x = 0; x < 6; x++)
			RAMButtonGroup.add(RAMrating[x]);

		for(int x = 0; x < 3; x++)
			softwareButtonGroup.add(softwareRating[x]);

	}

	private void setBrandRating(int choice) {

		for(int x = 0; x < 30; x++) {

			//if the users select Dell
			if(choice == 0) {

				//search for and give highest scores laptops that are Dell
				if(laptop[x].getName() == "Dell")
					laptop[x].getRating()[1] = 10;  
				else
					laptop[x].getRating()[1] = 9;

			}

			//if the users select HP
			if(choice == 1) {

				//search for and give highest scores laptops that are HP
				if(laptop[x].getName() == "HP")
					laptop[x].getRating()[1] = 10;   
				else
					laptop[x].getRating()[1] = 9;

			}

			//if the users select Asus
			if(choice == 2) {

				//search for and give highest scores laptops that are Dell
				if(laptop[x].getName() == "Asus")
					laptop[x].getRating()[1] = 10;   
				else
					laptop[x].getRating()[1] = 9;

			}

			//if the users select Apple
			if(choice == 3) {

				//search for and give highest scores laptops that are Dell
				if(laptop[x].getName() == "Apple")
					laptop[x].getRating()[1] = 10;   
				else
					laptop[x].getRating()[1] = 9;

			}

			//if the users select Windows
			if(choice == 4) {

				//search for and give highest scores laptops that are Dell
				if(laptop[x].getName() == "Windows")
					laptop[x].getRating()[1] = 10;   
				else
					laptop[x].getRating()[1] = 9;

			}

			//if the users select Acer
			if(choice == 5) {

				//search for and give highest scores laptops that are Dell
				if(laptop[x].getName() == "Acer")
					laptop[x].getRating()[1] = 10;   
				else
					laptop[x].getRating()[1] = 9;

			}

			//if the users select Lenovo
			if(choice == 6) {

				//search for and give highest scores laptops that are Dell
				if(laptop[x].getName() == "Lenovo")
					laptop[x].getRating()[1] =10;   
				else
					laptop[x].getRating()[1] = 9;

			}

			//if the users select No preference
			if(choice == 7) {

				//Give each of the 30 laptops the same rating 
				//since the users have no preference
				laptop[x].getRating()[1] = 8;   

			}

		}

	}

	private void setBudgetRating(int choice) {

		for(int x = 0; x < 30; x++) {

			//if the users select "less than $250"
			if(choice == 0) {

				//search for and give highest scores laptops that are less than 250
				if(laptop[x].getPrice() <= 250.00)
					laptop[x].getRating()[0] = 10;   
				else if(laptop[x].getPrice() >= 250.00 && laptop[x].getPrice() <= 500.00)
					laptop[x].getRating()[0] = 8;
				else if(laptop[x].getPrice() >= 500.00 && laptop[x].getPrice() <= 750.00)
					laptop[x].getRating()[0] = 6;
				else if(laptop[x].getPrice() >= 750.00 && laptop[x].getPrice() <= 1000.00)
					laptop[x].getRating()[0] = 4;
				else
					laptop[x].getRating()[0] = 2;

			}

			//if the users select "greater than $250 and less than $500"
			if(choice == 1) {

				//search for and give highest scores laptops that are more than 250 and less than 500
				if(laptop[x].getPrice() <= 250.00)
					laptop[x].getRating()[0] = 8;   
				else if(laptop[x].getPrice() >= 250.00 && laptop[x].getPrice() <= 500.00)
					laptop[x].getRating()[0] = 10;
				else if(laptop[x].getPrice() >= 500.00 && laptop[x].getPrice() <= 750.00)
					laptop[x].getRating()[0] = 6;
				else if(laptop[x].getPrice() >= 750.00 && laptop[x].getPrice() <= 1000.00)
					laptop[x].getRating()[0] = 4;
				else
					laptop[x].getRating()[0] = 2;

			}

			//if the users select "greater than $500 and less than $750"
			if(choice == 2) {

				//search for and give highest scores laptops that are more than 500 and less than 750
				if(laptop[x].getPrice() <= 250.00)
					laptop[x].getRating()[0] = 6;   
				else if(laptop[x].getPrice() >= 250.00 && laptop[x].getPrice() <= 500.00)
					laptop[x].getRating()[0] = 8;
				else if(laptop[x].getPrice() >= 500.00 && laptop[x].getPrice() <= 750.00)
					laptop[x].getRating()[0] = 10;
				else if(laptop[x].getPrice() >= 750.00 && laptop[x].getPrice() <= 1000.00)
					laptop[x].getRating()[0] = 4;
				else
					laptop[x].getRating()[0] = 2;

			}

			//if the users select "greater than $750 and less than $1,000"
			if(choice == 3) {

				//search for and give highest scores laptops that are greater than 750 and less than 1000
				if(laptop[x].getPrice() <= 250.00)
					laptop[x].getRating()[0] = 4;   
				else if(laptop[x].getPrice() >= 250.00 && laptop[x].getPrice() <= 500.00)
					laptop[x].getRating()[0] = 6;
				else if(laptop[x].getPrice() >= 500.00 && laptop[x].getPrice() <= 750.00)
					laptop[x].getRating()[0] = 8;
				else if(laptop[x].getPrice() >= 750.00 && laptop[x].getPrice() <= 1000.00)
					laptop[x].getRating()[0] = 10;
				else
					laptop[x].getRating()[0] = 2;

			}

			//if the users select "greater than $1,000"
			if(choice == 4) {

				//search for and give highest scores laptops that are greater than 1,000
				if(laptop[x].getPrice() < 250.00)
					laptop[x].getRating()[0] = 2;   
				else if(laptop[x].getPrice() > 250.00 && laptop[x].getPrice() < 500.00)
					laptop[x].getRating()[0] = 4;
				else if(laptop[x].getPrice() > 500.00 && laptop[x].getPrice() < 750.00)
					laptop[x].getRating()[0] = 6;
				else if(laptop[x].getPrice() > 750.00 && laptop[x].getPrice() < 1000.00)
					laptop[x].getRating()[0] = 8;
				else
					laptop[x].getRating()[0] = 10;

			}
		}

	}

	private void setCPURating(int choice) {

		for(int x = 0; x < 30; x++) {

			//if the users select "I need a really good cpu"
			if(choice == 0) {

				//search for and give highest score to the processors 
				//that are above 6,000
				if(laptop[x].getCpumark() < 1000)
					laptop[x].getRating()[2] = 1;
				else if(laptop[x].getCpumark() > 1000 && laptop[x].getCpumark() < 3000)
					laptop[x].getRating()[2] = 4;
				else if(laptop[x].getCpumark() > 3000 && laptop[x].getCpumark() < 6000)
					laptop[x].getRating()[2] = 7;
				else
					laptop[x].getRating()[2] = 10;

			}

			//if the users select "I need a good cpu"
			if(choice == 1) {

				//search for and give highest score to the processors 
				//that are above 3,000 and below 6000
				if(laptop[x].getCpumark() < 1000)
					laptop[x].getRating()[2] = 1;
				else if(laptop[x].getCpumark() > 1000 && laptop[x].getCpumark() < 3000)
					laptop[x].getRating()[2] = 4;
				else if(laptop[x].getCpumark() > 3000 && laptop[x].getCpumark() < 6000)
					laptop[x].getRating()[2] = 10;
				else
					laptop[x].getRating()[2] = 7;

			}

			//if the users select "I dont need a good cpu"
			if(choice == 2) {

				//since the users have no specific requirement regarding the processor
				//search for laptops that are above 1000 and below 3000
				if(laptop[x].getCpumark() < 1000)
					laptop[x].getRating()[2] = 1;
				else if(laptop[x].getCpumark() > 1000 && laptop[x].getCpumark() < 3000)
					laptop[x].getRating()[2] = 10;
				else if(laptop[x].getCpumark() > 3000 && laptop[x].getCpumark() < 6000)
					laptop[x].getRating()[2] = 4;
				else
					laptop[x].getRating()[2] = 7;

			}

			//if the users select "I only need a processor that can work"
			if(choice == 3) {

				//since the users don't really care about the processor
				//search for laptops that are below 1000
				if(laptop[x].getCpumark() < 1000)
					laptop[x].getRating()[2] = 10;
				else if(laptop[x].getCpumark() > 1000 && laptop[x].getCpumark() < 3000)
					laptop[x].getRating()[2] = 1;
				else if(laptop[x].getCpumark() > 3000 && laptop[x].getCpumark() < 6000)
					laptop[x].getRating()[2] = 4;
				else
					laptop[x].getRating()[2] = 7;

			}

		}
	}

	private void setGPURating(int choice) {

		for(int x = 0; x < 30; x++) {

			//if the users selected "yes, it is important"
			if(choice == 0) {

				//search for laptops that have a gpu pass mark of ver 4000
				if(laptop[x].getGpumark() > 4000)
					laptop[x].getRating()[3] = 10;
				else if(laptop[x].getGpumark() < 4000 && laptop[x].getGpumark() > 1000)
					laptop[x].getRating()[3] = 6;
				else if(laptop[x].getGpumark() < 1000)
					laptop[x].getRating()[3] = 2;

			}

			//if the users selected "no, it isn't important"
			if(choice == 1) {

				//since the users don't think video card is important
				if(laptop[x].getGpumark() > 4000)
					laptop[x].getRating()[3] = 10;
				else if(laptop[x].getGpumark() < 4000 && laptop[x].getGpumark() > 1000)
					laptop[x].getRating()[3] = 6;
				else if(laptop[x].getGpumark() < 1000)
					laptop[x].getRating()[3] = 2;

			}

		}
	}

	private void setRAMRating(int choice) {

		for(int x = 0; x < 30; x++) {

			//if the users selected "32GB"
			if(choice == 0) {

				//search for and give highest ratings for laptops 
				//that have RAM of 32GB
				if(laptop[x].getRAM().contains("32GB"))
					laptop[x].getRating()[4] = 10;
				else if(laptop[x].getRAM().contains("16GB"))
					laptop[x].getRating()[4] = 8;
				else if(laptop[x].getRAM().contains("12GB"))
					laptop[x].getRating()[4] = 6;
				else if(laptop[x].getRAM().contains("8GB"))
					laptop[x].getRating()[4] = 4;
				else if(laptop[x].getRAM().contains("4GB"))
					laptop[x].getRating()[4] = 2;
				else
					laptop[x].getRating()[4] = 1;
			}

			//if the users selected "16GB"
			if(choice == 1) {

				//search for laptops that have RAM of 16GB
				if(laptop[x].getRAM().contains("32GB"))
					laptop[x].getRating()[4] = 8;
				else if(laptop[x].getRAM().contains("16GB"))
					laptop[x].getRating()[4] = 10;
				else if(laptop[x].getRAM().contains("12GB"))
					laptop[x].getRating()[4] = 6;
				else if(laptop[x].getRAM().contains("8GB"))
					laptop[x].getRating()[4] = 4;
				else if(laptop[x].getRAM().contains("4GB"))
					laptop[x].getRating()[4] = 2;
				else
					laptop[x].getRating()[4] = 1;

			}

			//if the users selected "12GB"
			if(choice == 2) {

				//search for laptops that have RAM of 12GB
				if(laptop[x].getRAM().contains("32GB"))
					laptop[x].getRating()[4] = 8;
				else if(laptop[x].getRAM().contains("16GB"))
					laptop[x].getRating()[4] = 6;
				else if(laptop[x].getRAM().contains("12GB"))
					laptop[x].getRating()[4] = 10;
				else if(laptop[x].getRAM().contains("8GB"))
					laptop[x].getRating()[4] = 4;
				else if(laptop[x].getRAM().contains("4GB"))
					laptop[x].getRating()[4] = 2;
				else
					laptop[x].getRating()[4] = 1;

			}

			//if the users selected "8GB"
			if(choice == 3) {

				//search for laptops that have RAM of 8GB
				if(laptop[x].getRAM().contains("32GB"))
					laptop[x].getRating()[4] = 8;
				else if(laptop[x].getRAM().contains("16GB"))
					laptop[x].getRating()[4] = 6;
				else if(laptop[x].getRAM().contains("12GB"))
					laptop[x].getRating()[4] = 4;
				else if(laptop[x].getRAM().contains("8GB"))
					laptop[x].getRating()[4] = 10;
				else if(laptop[x].getRAM().contains("4GB"))
					laptop[x].getRating()[4] = 2;
				else
					laptop[x].getRating()[4] = 1;

			}

			//if the users selected "4GB"
			if(choice == 4) {

				//search for laptops that have RAM of 4GB
				if(laptop[x].getRAM().contains("32GB"))
					laptop[x].getRating()[4] = 8;
				else if(laptop[x].getRAM().contains("16GB"))
					laptop[x].getRating()[4] = 6;
				else if(laptop[x].getRAM().contains("12GB"))
					laptop[x].getRating()[4] = 4;
				else if(laptop[x].getRAM().contains("8GB"))
					laptop[x].getRating()[4] = 2;
				else if(laptop[x].getRAM().contains("4GB"))
					laptop[x].getRating()[4] = 10;
				else
					laptop[x].getRating()[4] = 1;

			}

			//if the users selected "2GB"
			if(choice == 5) {

				//search for laptops that have RAM of 2GB
				if(laptop[x].getRAM().contains("32GB"))
					laptop[x].getRating()[4] = 8;
				else if(laptop[x].getRAM().contains("16GB"))
					laptop[x].getRating()[4] = 6;
				else if(laptop[x].getRAM().contains("12GB"))
					laptop[x].getRating()[4] = 4;
				else if(laptop[x].getRAM().contains("8GB"))
					laptop[x].getRating()[4] = 2;
				else if(laptop[x].getRAM().contains("4GB"))
					laptop[x].getRating()[4] = 1;
				else
					laptop[x].getRating()[4] = 10;

			}

		}
	}

	private void setSoftwareRating(int choice) {

		for(int x = 0; x < 30; x++) {

			//if the uers selected "Windows"
			if(choice == 0) {

				//search for laptops that are windows
				if(laptop[x].getSoftware().contains("Windows 10")) 
					laptop[x].getRating()[5] = 10;
				else
					laptop[x].getRating()[5] = 9;
			}

			//if the uers selected "Chome OS"
			if(choice == 1) {

				//search for laptops that are Chrome OS
				if(laptop[x].getSoftware().contains("Chrome OS")) 
					laptop[x].getRating()[5] = 10;
				else
					laptop[x].getRating()[5] = 9;
			}

			//if the uers selected "macOS Sierra"
			if(choice == 2) {

				//search for laptops that are macOS Sierra
				if(laptop[x].getSoftware().contains("macOS Sierra")) 
					laptop[x].getRating()[5] = 10;
				else
					laptop[x].getRating()[5] = 9;
			}

		}
	}

	private void setScreenSizeRating(int choice) {

		for(int x = 0; x < 30; x++) {

			//if the users select "13.3 inch"
			if(choice == 0) {

				//search and give highest scores to laptop that is 13.3 inch
				if(laptop[x].getDisplay().contains("13.3 inch")) 
					laptop[x].getRating()[6] = 10;
				else 
					laptop[x].getRating()[6] = 5;
			}

			//if the users select "15.6 inch"
			if(choice == 1) {

				//search and give highest scores to laptop that is 15.6 inch
				if(laptop[x].getDisplay().contains("15.6 inch")) 
					laptop[x].getRating()[6] = 10;
				else 
					laptop[x].getRating()[6] = 5;
			}

			//if the users select "12 inch"
			if(choice == 2) { 

				//search and give highest scores to laptop that is 12 inch
				if(laptop[x].getDisplay().contains("12 inch")) 
					laptop[x].getRating()[6] = 10;
				else 
					laptop[x].getRating()[6] = 5;
			}

			//if the users select "13.5 inch"
			if(choice == 3) { 

				//search and give highest scores to laptop that is 13.5 inch
				if(laptop[x].getDisplay().contains("13.5 inch")) 
					laptop[x].getRating()[6] = 10;
				else 
					laptop[x].getRating()[6] = 5;
			}

			//if the users select "15 inch"
			if(choice == 4) { 

				//search and give highest scores to laptop that is 15 inch
				if(laptop[x].getDisplay() .contains("15 inch")) 
					laptop[x].getRating()[6] = 10;
				else 
					laptop[x].getRating()[6] = 5;
			}

			//if the users select "14 inch"
			if(choice == 5) { 

				//search and give highest scores to laptop that is 14 inch
				if(laptop[x].getDisplay().contains("14 inch")) 
					laptop[x].getRating()[6] = 10;
				else 
					laptop[x].getRating()[6] = 5;
			}

			//if the users select "11.6 inch"
			if(choice == 6) { 

				//search and give highest scores to laptop that is 11.6 inch
				if(laptop[x].getDisplay().contains("11.6 inch")) 
					laptop[x].getRating()[6] = 10;
				else 
					laptop[x].getRating()[6] = 5;
			}

			//if the users select "17.3 inch"
			if(choice == 7) { 

				//search and give highest scores to laptop that is 17.3 inch
				if(laptop[x].getDisplay().contains("17.3 inch")) 
					laptop[x].getRating()[6] = 10;
				else 
					laptop[x].getRating()[6] = 5;
			}


			//if the users select "12.5 inch"
			if(choice == 8) {  

				//search and give highest scores to laptop that is 12.5 inch
				if(laptop[x].getDisplay().contains("12.5 inch")) 
					laptop[x].getRating()[6] = 10;
				else 
					laptop[x].getRating()[6] = 5;
			}

		}
	}

	public void actionPerformed(ActionEvent events) {

		/* this method carries out actions performed 
		 * when the users click on each of the radio
		 * buttons
		 */

		//This first one if for closing the frame when the gui restarts
		if(events.getSource() == menuBar.menuItem2){
			//close frame when restart is pressed
			this.dispose();
		}
		
		for(int x = 0; x < 8; x++) {

			if(events.getSource() == brandRating[x])
				setBrandRating(x);

		}

		for(int x = 0; x < 3; x++) {

			if(events.getSource() == budgetRating[x])
				setBudgetRating(x);

		}

		for(int x = 0; x < 4; x++) {

			if(events.getSource() == cpuRating[x])
				setCPURating(x);

		}

		for(int x = 0; x < 2; x++) {

			if(events.getSource() == gpuRating[x])
				setGPURating(x);

		}

		for(int x = 0; x < 6; x++) {

			if(events.getSource() == RAMrating[x])
				setRAMRating(x);

		}

		for(int x = 0; x < 3; x++) {

			if(events.getSource() == softwareRating[x])
				setSoftwareRating(x);

		}

		for(int x = 0; x < 9; x++) {

			if(events.getSource() == screenSizeRating[x])
				setScreenSizeRating(x);
		}


		//close the panel and go to the weighting panel
		if(events.getSource() == weighting){
			dispose();
			new LaptopAdvisorGUIUserWeightings(laptop,user);
		}
	}

}
