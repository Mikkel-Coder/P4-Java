/*
 * Design and implement user input for a train management program as a 
 * thread (you can call it a Command Line Input Handler) and the part 
 * which holds the list of trains in another thread. Make the program 
 * work with those two threads. 
 * 
 * Hint: create a class specific for 
 * 1) the user input and 
 * 2) the train list management.
 */

package Opgave1;

public class Opgave1 {
	public static void main(String[] args) {
		// We create our train list and its thread
		TrainsList trains_list = new TrainsList();
		Thread trains_thread = new Thread(trains_list);
		
		// Also create for the "Command Line Input Handler" we call trainCLI
		TrainCLI trainCLI = new TrainCLI(trains_list);
		Thread trainCLI_thread = new Thread(trainCLI);
		
		// Start the train list thread
		// NOTE: The `trains_thread` does not do anything
		trains_thread.start();
		
		// Append some trains 
		trains_list.add(new Train(PassengerTrainTypes.LightRailTransit, 60, 40, true));
		trains_list.add(new Train(PassengerTrainTypes.Regional, 600, 315, false));
		
		// Now we can start the trainCLI thread!
		// Now follow the CLI in your terminal 
		trainCLI_thread.start();
	}
}