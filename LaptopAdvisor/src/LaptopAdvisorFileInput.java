/*
* This is a template class that defines a laptop object.  It includes a list of fields that define the laptop (i.e. 
* one for each column in the spreadsheet - except the ratings).  Then an array that holds all the ratings 
* (both objective and subjective).  And finally the overall score for the laptop.
*
* author - Tom
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class LaptopAdvisorFileInput {

	public LaptopAdvisorFileInput(Laptop[] laptop){



		try {
			Scanner input;
			input = new Scanner (new File ("resources/ICS Project 1 - Laptop Advisor - Sheet1.txt"));
			
			int index = 0;
			input.nextLine();
			
			while (input.hasNextLine()) {
				Scanner temp = new Scanner(input.nextLine()).useDelimiter(",");
					laptop[index].setName(temp.next());
				//System.out.println(index + " <- - > " + laptop[index].getName());
				laptop[index].setPrice(Double.parseDouble(temp.next()));
				laptop[index].setUrl(temp.next());
				laptop[index].setBrand(temp.next());
				laptop[index].setDisplay(temp.next());
				laptop[index].setDisplaylength(Double.parseDouble(temp.next()));
				laptop[index].setDisplaywidth(Double.parseDouble(temp.next()));
				laptop[index].setCpu(temp.next());
				laptop[index].setCpumark(Double.parseDouble(temp.next()));
				laptop[index].setDriveSize(temp.next());
				laptop[index].setDriveType(temp.next());
				laptop[index].setRAM(temp.next());
				laptop[index].setGpu(temp.next());
				laptop[index].setGpumark(Double.parseDouble(temp.next()));
				laptop[index].setAudio(temp.next());
				laptop[index].setInout(temp.next());
				laptop[index].setPorts(temp.next());
				laptop[index].setNetwork(temp.next());
				laptop[index].setBattery(temp.next());
				laptop[index].setSoftware(temp.next());
				laptop[index].setPhysical(temp.next());
				laptop[index].setWeight(Double.parseDouble(temp.next()));
				laptop[index].setLength(Double.parseDouble(temp.next()));
				laptop[index].setWidth(Double.parseDouble(temp.next()));
				laptop[index].setHeight(Double.parseDouble(temp.next()));
				laptop[index].setWarranty(temp.next());
				index++; 
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
		}



	}

}