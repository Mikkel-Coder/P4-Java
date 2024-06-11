/*
 * The electric Train may need to be initiated differently as the normal 
 * version of the Train, e.g. starting charge state of the battery which is 
 * not present in the diesel version of the train. Use polymorphism to 
 * ensure your Train class can support the electric Train version in a 
 * generic fashion, i.e. it is not the subclass that initiates the battery 
 * state, but the Train class.
 */

package opgave4;

public class opgave4 {

	public static void main(String[] args) {
		
		// Create our train
		Train train = new Train();
		
		// Add a electric locomotive to the train
		ElectricLocomotive ElecLoco = new ElectricLocomotive(); 
		train.pushRollingStock(ElecLoco);
		
	}

}
