/*
 * The Train class
 * 
 * Note that most of the code is a copy from previous lectures. The only 
 * thing that has change is that the Trian class is now Serializable. This is 
 * used so that it can be streamed.
 */

package Opgave1;

import java.io.Serializable;

public class Train implements Serializable {
	// Random generate serialversionUID by Eclipse
	private static final long serialVersionUID = -6997144755566067297L;
	
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
