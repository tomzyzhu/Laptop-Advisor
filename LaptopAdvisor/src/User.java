/* 
* This is a template class that defines a user. It includes the user’s name and a list (array) of the 
* user's weightings (0 to 10) corresponding to each of the laptop's ratings.
*
* author- Amna
*/

public class User {
	
	public  int [] weightings = new int[8];

	public int[] getWeightings() {
		return weightings;
	}

	public void setWeightings(int weighting , int index) {
		this.weightings[index] = weighting;
	}

	
}
