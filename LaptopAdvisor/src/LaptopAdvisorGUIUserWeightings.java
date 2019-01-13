import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This class contains a frame with two panels - user and report.  It allows the user to enter their    
 * subjective ratings and their weightings for all criteria.  It then allows the user to see a report 
 * that produces the top 3 laptops that best meet their criteria (i.e. score highest using the wdm).
 *
 * authors - Amna
 */

@SuppressWarnings("serial")
public class LaptopAdvisorGUIUserWeightings extends JFrame implements ActionListener{

	//Optional Menu Bar
	LaptopAdvisorGUIMenuBar menuBar;
	
	//array of laptops
	Laptop[] laptop;

	//users
	User user = new User();

	//new panel
	JPanel userWeightingsPanel = new JPanel();
	
	//all the questions will be placed on labels
	JLabel[] userWeightingsLabel = new JLabel[7];

	//button to create report
	JButton createReport = new JButton("Create Report");

	//dropdown lists
	@SuppressWarnings("unchecked")
	JComboBox<Integer>[] weightingComboBox = new JComboBox[7];
	/*0 price 
	 *1 brand
	 *2 cpumark
	 *3 gpumark
	 *4 RAM
	 *5 operating software
	 *6 screen size */
	
	//constructor
	LaptopAdvisorGUIUserWeightings(Laptop[] laptop, User user) {

		setContentPane(new JLabel(new ImageIcon("resources/lighting.jpg")));
		this.user = new User();
		this.laptop = laptop;
		frameSetup();
		userWeightingsPanelSetup();
		reportButton();
	
	}
	
	//sets up frame and menu bar
	private void frameSetup() {

		setTitle("Laptop Advisor");
		setLayout(null);
		setSize (1366, 725);
		add(userWeightingsPanel);
		setVisible(true);

		//initialize menubar with laptopArray
		menuBar = new LaptopAdvisorGUIMenuBar(laptop);
		setJMenuBar(menuBar);// add a menu bar
		menuBar.menuItem2.addActionListener(this); //adds action listener to restart to close frame
		
	}

	//sets properties for reportbutton
	private void reportButton() {

		createReport.setBounds(450, 480, 260, 50);
		createReport.addActionListener(this);
		userWeightingsPanel.add(createReport);

	}

	//sets questions to labels and sets properties labels
	private void userWeightingsPanelSetup() {

		userWeightingsPanel.setLayout(null);

		//make borders 
		userWeightingsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		userWeightingsPanel.setBounds(50, 50, 1250, 560);
		add(userWeightingsPanel);


		//settings the questions											
		userWeightingsLabel[0] = new JLabel("How much do you care about price?");
		userWeightingsLabel[1] = new JLabel("How much do you care about the brand?");
		userWeightingsLabel[2] = new JLabel("How much do you care about CPU power?");
		userWeightingsLabel[3] = new JLabel("How much do you care about GPU power?");
		userWeightingsLabel[4] = new JLabel ("How much do you care about RAM?");
		userWeightingsLabel[5] = new JLabel("How much do you care about the operating system?");
		userWeightingsLabel[6] = new JLabel("How much do you care about screen size? ");
		
		//adding to panel
		for (int num = 0; num <= 6; num++) {

			
			userWeightingsPanel.add(userWeightingsLabel[num]);
		}
		
		int coorx;
		coorx = 100;
		
		//settings bounds
		for (int x = 0; x <= 6; x++)
			
			if (x <= 2) {
				userWeightingsLabel[x].setBounds(coorx,50,300,50);
				coorx+= 400;
			}
			else if(x <= 5) {
				
				if (x == 3)
					coorx= 100;
				userWeightingsLabel[x].setBounds(coorx,200,300,50);
				coorx+= 400;
			}
			else {
				coorx= 500;
				userWeightingsLabel[x].setBounds(coorx,350,300,50);
				
			}

		importanceDropdownSets();

	}

	//method to create drop down list for user weightings
	private void importanceDropdownSets() {
		
		int coorx = 100;
		
		//add options 1 - 10 in the drop down list
		for (int i = 0; i < weightingComboBox.length; i++)  {

			weightingComboBox[i] = new JComboBox<Integer>();		
			userWeightingsPanel.add(weightingComboBox[i]);
			weightingComboBox[i].addActionListener(this);
			
			//settings bounds
			if (i <= 2) {
					weightingComboBox[i].setBounds(coorx,90,150,50);
					coorx+= 400;
				}
				else if(i <= 5) {

					if (i == 3)
						coorx= 100;
					weightingComboBox[i].setBounds(coorx,240,150,50);
					coorx+= 400;
				}
				else if (i == 6) {
					coorx= 500;
					weightingComboBox[i].setBounds(coorx,400,150,50);
				}		

			for (int x = 0; x <= 10; x ++) 
				weightingComboBox[i].addItem(x);
			
			

		}
		
	}

	public void actionPerformed(ActionEvent event) {

		//This first one if for closing the frame when the gui restarts
		if(event.getSource() == menuBar.menuItem2){
			//close frame when restart is pressed
			this.dispose();
		}

		for (int x = 0; x < 7; x++) {
			if (event.getSource() == weightingComboBox[x])
				user.setWeightings(weightingComboBox[x].getSelectedIndex(), x) ;
		}
		
		//sending info to Report class
		if (event.getSource() == createReport) {
			dispose();
			new LaptopAdvisorGUIReport(laptop, user);
		}

	}

}
