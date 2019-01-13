//Header - place at top of test class
/*
 * Name(s): Anthony, Amna, Tom
 * Date: April 24, 2017
 * Course code: ICS3U1-03/04
 * Purpose: To create an application that...
 * Major skills: File Input, GUI, Arrays, Weighted Decision Matrix...
 * Extra features: 
 * Areas of concern: 
 *author-Anthony
 */

/*
 * This is a test class that runs the application.  It includes an array that stores the laptops.  This 
 * class then runs the LaptopAdvisorFileInput class and the LaptopAdvisorGUI class.
 */

public class LaptopAdvisorTest {

	// A list of laptops that is read from an external csv file and is then sent to the GUI
	public static Laptop[] laptop = new Laptop[30];

	// This method is used to run the program by first running the file input and then the GUI
	public static void main(String[] args) {

		new LaptopAdvisorTest();
	}
	
	public LaptopAdvisorTest(){

		for(int i = 0; i<30;i++){
			laptop[i] = new Laptop();
		}

		new LaptopAdvisorFileInput(laptop);
		new Introduction(laptop);
	}

}