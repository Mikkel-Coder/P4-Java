/* THIS FILE IS NOT A PART OF THE ASSIGMENT */

package Opgave1;

public class Train {
	public PassengerTrainTypes type;
	public int length;
	public int weight;
	public boolean isElectric; 

	public Train(PassengerTrainTypes type, int length, int weight, boolean electric) {
		this.type = type;
		this.length = length;
		this.weight = weight;
		this.isElectric = electric;
	}

	public void printInfo() {
		System.out.println("Object ID: " + System.identityHashCode(this));
	    System.out.println(" Train Type: " + this.type);
	    System.out.println(" Length:     " + this.length);
	    System.out.println(" Weight:     " + this.weight);
	    System.out.println(" Electric:   " + (this.isElectric ? "Yes" : "No"));
	    System.out.println();
	}
	
}
