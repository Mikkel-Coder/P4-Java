/*
 * Create a set of threads of the user input handler to emulate a number of 
 * train operators that wishes to seek, insert, delete trains from the main 
 * list. Make this run.
 */

package Opgave2;

import Opgave1.TrainsList;

public class Opgave2 {
	
	public static void main(String[] args) throws Exception {
		// We create 10 TrainCLI simulators that seek, insert and delete
		final int numberOfTrainsimulators = 10; // Try 10_000 threads? 🤔
		Thread[] trainsimulators = new Thread[numberOfTrainsimulators];
		
		// The trainlist is shared among the threads
		TrainsList trains = new TrainsList();
		Thread trains_thread = new Thread();
		
		// NOTE: This does not do anything
		trains_thread.start();	
		
		// Create and start each simulator
		for (int i = 0; i < numberOfTrainsimulators; i++) {
			TrainCLISimulator trainsim = new TrainCLISimulator(trains);
			trainsimulators[i] = new Thread(trainsim);
			trainsimulators[i].start();
		}
		
		// Await all trainSimulators to finish 
		for (Thread trainsimulator : trainsimulators) {
			trainsimulator.join();
		}
	}
}
