/*
 * This is a template class that defines a laptop object.  It includes a list of fields that define the laptop (i.e. 
 * one for each column in the spreadsheet - except the ratings).  Then an array that holds all the ratings 
 * (both objective and subjective).  And finally the overall score for the laptop.
 *
 * author - Tom
 */

public class Laptop {
	public  double price;
	public  String name;
	public  String cpu;
	public  String gpu;
	public  String brand;
	public  String display;
	public  double displaylength;
	public  double displaywidth;
	public  double cpumark;
	public  double gpumark;
	public  String driveSize;
	public  String driveType;	
	public  String RAM;
	public  String audio;
	public  String inout; // input/output devices
	public  String ports;
	public  String network;
	public  String battery;
	public  String software;
	public  String warranty;
	public  String physical; //extra physical 
	public  String url; // hyperlink to website
	public  double weight;
	public  double length;
	public  double width;
	public  double height;
	


	public  int [] rating = new int[10];/*1. cpu
											 2. gpu
											 3. display
											 4. hardDrive
											 5. RAM
											 6. input/output
											 7. ports
											 8. software
											 9. warranty
											 10. physical
											 note : subtract by one cause array starts at 0-9
	 */



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCpu() {
		return cpu;
	}



	public void setCpu(String cpu) {
		this.cpu = cpu;
	}



	public String getGpu() {
		return gpu;
	}



	public void setGpu(String gpu) {
		this.gpu = gpu;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getDisplay() {
		return display;
	}



	public void setDisplay(String display) {
		this.display = display;
	}



	public double getDisplaylength() {
		return displaylength;
	}



	public void setDisplaylength(double displaylength) {
		this.displaylength = displaylength;
	}



	public double getDisplaywidth() {
		return displaywidth;
	}



	public void setDisplaywidth(double displaywidth) {
		this.displaywidth = displaywidth;
	}



	public double getCpumark() {
		return cpumark;
	}



	public void setCpumark(double cpumark) {
		this.cpumark = cpumark;
	}



	public double getGpumark() {
		return gpumark;
	}



	public void setGpumark(double gpumark) {
		this.gpumark = gpumark;
	}



	public String getDriveSize() {
		return driveSize;
	}



	public void setDriveSize(String driveSize) {
		this.driveSize = driveSize;
	}



	public String getDriveType() {
		return driveType;
	}



	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}



	public String getRAM() {
		return RAM;
	}



	public void setRAM(String rAM) {
		RAM = rAM;
	}



	public String getAudio() {
		return audio;
	}



	public void setAudio(String audio) {
		this.audio = audio;
	}



	public String getInout() {
		return inout;
	}



	public void setInout(String inout) {
		this.inout = inout;
	}



	public String getPorts() {
		return ports;
	}



	public void setPorts(String ports) {
		this.ports = ports;
	}



	public String getNetwork() {
		return network;
	}



	public void setNetwork(String network) {
		this.network = network;
	}



	public String getBattery() {
		return battery;
	}



	public void setBattery(String battery) {
		this.battery = battery;
	}



	public String getSoftware() {
		return software;
	}



	public void setSoftware(String software) {
		this.software = software;
	}



	public String getWarranty() {
		return warranty;
	}



	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}



	public String getPhysical() {
		return physical;
	}



	public void setPhysical(String physical) {
		this.physical = physical;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public double getWeight() {
		return weight;
	}



	public void setWeight(double weight) {
		this.weight = weight;
	}



	public double getLength() {
		return length;
	}



	public void setLength(double length) {
		this.length = length;
	}



	public double getWidth() {
		return width;
	}



	public void setWidth(double width) {
		this.width = width;
	}



	public double getHeight() {
		return height;
	}



	public void setHeight(double height) {
		this.height = height;
	}



	public int[] getRating() {
		return rating;
	}



	public void setRating(int rating, int index) {
		this.rating[index] = rating;
	}

	
	
}
