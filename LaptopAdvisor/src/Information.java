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
 * This class creates a frame that allows the user to pick
 * a laptop and a category, and a message box will open to
 * display the category information for that laptop.
 * There is also hyperlinks to the laptops
 * as there is a button image.
 * 
 *  author - Tom Zhu
 */
public class Information extends JFrame implements ActionListener{

	//Laptop array for use when loading into the setDropBoxes method
	Laptop [] laptopArray;

	//Array of String that contains all the laptop names
	String [] name = new String [30];

	//ComboBox with all the laptops inside
	JComboBox<String> laptopList;
	//ComboBox with all the categories inside
	JComboBox<String> catList;
	//This String array stores all the categories
	String [] cats = {"Price","URL","Brand","Display","Resolution Length","Resolution Width","Processor","Passmark CPU Benchmark","Hard Drive Size","Hard Drive Type","RAM","Graphics (GPU)","Passmark GPU Benchmark","Audio","Input/Output","Ports","Networking","Power(Battery)","Software(operating system; additional application)","Physical features (other)","Weight(kg)","Length(mm)","Width(mm)","Height(mm)","Warranty"};
	//This int stores which category is selected
	int catIndex = 0;

	//This button contains the image and hyperlink to the current laptop
	JButton laptopImageButton = new JButton();
	//This button generates information about the laptop regarding the category selected
	JButton cat = new JButton();

	//this int contains the current name index the Frame is on currently
	int nameIndex = 0;

	//All the JLabels I need
	JLabel title = new JLabel ("Laptop Information");
	JLabel laptopTitle = new JLabel ("Laptop Name");
	JLabel catTitle = new JLabel ("Information Type");

	//These labels are for instruction on clicking the image hyperlink
	static JLabel [] instructions = new JLabel[4];

	//timer used for animation
	Timer timer = new Timer();

	static int color = 0; // color index to tell what colour to use currently	

	//the constructor method makes a new JFrame of this object
	public Information(Laptop [] laptop){
		this.laptopArray = laptop;
		setFrame();
		setButtons();
		setLabels();
		setDropBoxes();

		//Schedule a timer for animation
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Information.changeColour();
			}
		}, 300, 300);

	}

	//This is the actual animation method that runs to change the colour every few milliseconds
	protected static void changeColour() {
		// TODO Auto-generated method stub

		if(color == 0){// if index is 0, set to red colours

			instructions[0].setForeground(Color.RED);
			instructions[2].setForeground(Color.RED);
			instructions[1].setIcon(new ImageIcon("resources/arrows/2.jpg"));
			instructions[3].setIcon(new ImageIcon("resources/arrows/4.jpg"));

			//extra line to animate the arrows a little bit by changing x coordinate
			instructions[1].setBounds(instructions[1].getX()+5, instructions[1].getY(), instructions[1].getWidth(), instructions[1].getHeight());
			instructions[3].setBounds(instructions[3].getX()-5, instructions[3].getY(), instructions[3].getWidth(), instructions[3].getHeight());

		}else{// if index is 1, set to white colours

			instructions[0].setForeground(Color.WHITE);
			instructions[2].setForeground(Color.WHITE);
			instructions[1].setIcon(new ImageIcon("resources/arrows/1.jpg"));
			instructions[3].setIcon(new ImageIcon("resources/arrows/3.jpg"));

			//extra line to animate the arrows a little bit by changing x coordinate
			instructions[1].setBounds(instructions[1].getX()-5, instructions[1].getY(), instructions[1].getWidth(), instructions[1].getHeight());
			instructions[3].setBounds(instructions[3].getX()+5, instructions[3].getY(), instructions[3].getWidth(), instructions[3].getHeight());

		}

		color ++;//add one to color index to cycle it

		if(color ==2)
			color = 0; //resets color index if it gets too high
	}

	//This method sets up all the labels
	private void setLabels() {
		// TODO Auto-generated method stub

		//Initialize the Labels that should go on it + add them
		title.setBounds(310, 0, 400, 100);
		laptopTitle.setBounds(100, 550, 120, 50);
		catTitle.setBounds(420, 550, 150, 50);

		title.setFont(new Font("Calibri", Font.BOLD, 40));
		laptopTitle.setFont(new Font("Calibri", Font.BOLD, 20));
		catTitle.setFont(new Font("Calibri", Font.BOLD, 20));

		title.setForeground(Color.WHITE);
		laptopTitle.setForeground(Color.WHITE);
		catTitle.setForeground(Color.WHITE);

		add(title);
		add(laptopTitle);
		add(catTitle);

		//Initialize instruction labels
		for(int i = 0; i <4; i ++){
			instructions[i] = new JLabel();
		}

		//Initialize hyperlink instruction labels with text
		instructions[0].setText("Buy Now!");
		instructions[2].setText("Buy Now!");

		instructions[0].setFont(new Font("Calibri", Font.BOLD, 40));
		instructions[2].setFont(new Font("Calibri", Font.BOLD, 40));

		instructions[0].setForeground(Color.WHITE);
		instructions[2].setForeground(Color.WHITE);

		instructions[0].setBounds(50, 50, 300, 100);
		instructions[2].setBounds(750, 50, 300, 100);

		//add labels with text
		add(instructions[0]);
		add(instructions[2]);

		//Initialize hyperlink instruction labels with images
		instructions[1].setBounds(50, 150, 200, 100);
		instructions[3].setBounds(750, 150, 200, 100);

		instructions[1].setIcon(new ImageIcon("resources/arrows/1.jpg"));
		instructions[3].setIcon(new ImageIcon("resources/arrows/3.jpg"));

		//add labels with image
		add(instructions[1]);
		add(instructions[3]);

	}

	//this methods sets all the buttons onto the frame
	public void setButtons() {
		// TODO Auto-generated method stub

		//initialize the button image + button added to frame
		changeImageButton();
		laptopImageButton.addActionListener(this);
		add(laptopImageButton);

		//initialize button that generates information
		cat.setBounds(720, 600, 150, 50);
		//set to text to cat button
		cat.setText("Get Laptop Info");
		//add action listener to the button
		cat.addActionListener(this);
		add(cat);
	}

	//this method will set up all the dropboxes in the JFrame
	public void setDropBoxes() {
		// TODO Auto-generated method stub

		//for loops to set string name array
		for(int i = 0; i<30; i ++){
			name[i] = this.laptopArray[i].getName();
		}
		//Initialize the laptopList ComboBox + action listener + set bounds + add to frame
		laptopList = new JComboBox(name);
		laptopList.addActionListener(this);
		laptopList.setBounds(40, 600, 270, 50);
		add(laptopList);

		//Initialize the category ComboBox + action listener + set bounds+ add to frame
		catList = new JComboBox (cats); 
		catList.addActionListener(this);
		catList.setBounds(400, 600, 200, 50);
		add(catList);

	}

	//this method sets up the frame
	public void setFrame() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(1000, 725);
		setTitle("Information");
		setVisible(true);
		getContentPane().setBackground(Color.BLACK);
	}

	@Override
	//the action listener for the frame
	public void actionPerformed(ActionEvent action) {
		// TODO Auto-generated method stub

		//this try code will see if the action was from a JComboBox
		//if not, will try to see if the action was a button press
		try{ 
			//try to see if it is from a JComboBox
			JComboBox cb = (JComboBox)action.getSource();
			Exit0:{//This Exit category index at the end
				
				Exit1:{//This Exit changes image button at the end
				
					Exit2:{ 
						//the following code changes the image icon for the hyperlinked image
						for(int i = 0; i <30; i++){ //this checks if the action was from the names
							
							if (name[i].equals((String)cb.getSelectedItem())){
		
								//Int below stores the name index when the name was found
								nameIndex = i;
		
								//breaking at Exit 2 stops the for loop forcefully
								break Exit2;
							}
							
						}
						break Exit1; //This line breaks out so the image buttons isn't changed
							//It also redirects to a line where the Category index gets changed
					}
					changeImageButton(); //changes the hyperlinked image
					break Exit0;
				}
			
				//check if categories have been changed at any point 
				catIndex = 0;
				while (!cats[catIndex].equals((String)cb.getSelectedItem())){
					catIndex++;
				}
				
			}


			
		}catch(Exception excep){ //if they action was not from a dropbox
			
			if(action.getSource() == laptopImageButton){//if action from laptop image, go to website
				openWebBrowser();
			}else if(action.getSource() == cat){//if action from infobox, make new infobox
				this.openMSGBox();
			}
			
		}
	}

	//This method allows for opening the right category by calling up the right getter
	public void openMSGBox() {
		// TODO Auto-generated method stub
		
		//initialize temporary variable that will be put into the MsgBox
		//Note: it is initialized with an error because if no code runs, that means
		//there was a problem in getting info.
		String output = "Error Loading in Info";
		if(catIndex == 0){
			output = Double.toString(laptopArray[nameIndex].getPrice());
		}else if (catIndex== 1){
			output = laptopArray[nameIndex].getUrl();
		}else if (catIndex== 2){
			output = laptopArray[nameIndex].getBrand();
		}else if (catIndex== 3){
			output = laptopArray[nameIndex].getDisplay();
		}else if (catIndex== 4){
			output = Double.toString(laptopArray[nameIndex].getDisplaylength());
		}else if (catIndex== 5){
			output = Double.toString(laptopArray[nameIndex].getDisplaywidth());
		}else if (catIndex== 6){
			output = laptopArray[nameIndex].getCpu();
		}else if (catIndex== 7){
			output = Double.toString(laptopArray[nameIndex].getCpumark());
		}else if (catIndex== 8){
			output = laptopArray[nameIndex].getDriveSize();
		}else if (catIndex== 9){
			output = laptopArray[nameIndex].getDriveType();
		}else if (catIndex== 10){
			output = laptopArray[nameIndex].getRAM();
		}else if (catIndex== 11){
			output = laptopArray[nameIndex].getGpu();
		}else if (catIndex== 12){
			output = Double.toString(laptopArray[nameIndex].getGpumark());
		}else if (catIndex== 13){
			output = laptopArray[nameIndex].getAudio();
		}else if (catIndex== 14){
			output = laptopArray[nameIndex].getInout();
		}else if (catIndex== 15){
			output = laptopArray[nameIndex].getPorts();
		}else if (catIndex== 16){
			output = laptopArray[nameIndex].getNetwork();
		}else if (catIndex== 17){
			output = laptopArray[nameIndex].getBattery();
		}else if (catIndex== 18){
			output = laptopArray[nameIndex].getSoftware();
		}else if (catIndex== 19){
			output = laptopArray[nameIndex].getPhysical();
		}else if (catIndex== 20){
			output = Double.toString(laptopArray[nameIndex].getWeight()) + "kg";
		}else if (catIndex== 21){
			output = Double.toString(laptopArray[nameIndex].getLength()) + "mm";
		}else if (catIndex== 22){
			output = Double.toString(laptopArray[nameIndex].getWidth()) + "mm";
		}else if (catIndex== 23){
			output = Double.toString(laptopArray[nameIndex].getHeight()) + "mm";
		}else if (catIndex== 24){
			output = laptopArray[nameIndex].getWarranty();
		}
		Information.msgBox(output, "Info");
	}

	//this method changes the images of the button that holds the laptop
	public void changeImageButton() {
		// TODO Auto-generated method stub

		//sets the image bounds	
		laptopImageButton.setBounds(280, 100, 400, 400);

		//Change the image of the button to scaled version from files
		laptopImageButton.setIcon(new ImageIcon(new ImageIcon("resources/laptop pictures/" + (nameIndex+1) + ".jpg").getImage().getScaledInstance(400, 400, 0)));

		repaint();
	}

	//This method gets a hyperlink and opens it in default browser
	public void openWebBrowser() {
		if(Desktop.isDesktopSupported()){
			try {
				Desktop.getDesktop().browse(new URI(laptopArray[nameIndex].getUrl()));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

		}
	}

	//Creates a message box with a title containing information about what the user wanted
	public static void msgBox(String infoMessage, String titleBar){
		infoMessage = infoMessage.replace(';', ',');
		JOptionPane.showMessageDialog(null, infoMessage,  titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

}
