/*
 * Consider that some of your trains may be electric powered, and some may be 
 * diesel (and others). Define an electric Train interface that captures the 
 * electric type, e.g. it requires charging, one needs to check status of the 
 * battery etc. and implement on a few selected Train classes (or subclasses) 
 * that interface.
 */

package Opgave3;

public class Opgave3 {

	public static void main(String[] args) {
		// NOTE: Check the UML diagram for a better overview
		
		// Create our train
		Train train = new Train();
		
		// Add a electric locomotive to the train
		ElectricLocomotive ElecLoco = new ElectricLocomotive(); 
		train.pushRollingStock(ElecLoco);
	}

}
