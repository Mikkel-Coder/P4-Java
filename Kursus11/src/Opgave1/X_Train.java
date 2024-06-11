/*
 * THIS FILE IS NOT A PART OF THE ASSIGENMENT
 */

package Opgave1;

import java.io.Serializable;

public class X_Train implements Serializable {
	// Random generate serialversionUID by Eclipse
	private static final long serialVersionUID = -6997144755566067297L;
	
	public X_PassengerTrainTypes type;
	public int length;
	public int weight;
	public boolean isElectric; 
	

	public X_Train(X_PassengerTrainTypes type, int length, int weight, boolean electric) {
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
