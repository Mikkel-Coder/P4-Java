/*
 * Now automatize the user input to emulate 10-100’s of train operators 
 * trying to query the train list (either by searching, inserting, deleting, 
 * updating bicycle entries). You should use the Random class to randomly 
 * select between the actions (previously taken as input from a real human 
 * via keyboard) a thread/train operator can take (if you forgot, then see 
 * previous assignment where you also used the Random class).
 */

package Opgave3;

import Opgave1.TrainsList;

public class Opgave3 {
	public static void main(String[] args) throws Exception {
		final int numberOfTrainsimulators = 16_000; // AMD Ryzen 7 6850U PRO Linux 6.6.15 about 17000
		// We begin to run into scaling problems because of the CopyOnWrite likely
		// "os::commit_memory() failed; error='Not enough space' (errno=12)"
		Thread[] trainsimulators = new Thread[numberOfTrainsimulators];
		
		// The trainlist is shared among the threads
		TrainsList trains = new TrainsList();
		
		// NOTE: Does not do anything
		Thread trains_thread = new Thread();
		trains_thread.start();
		
		// Periodically print the size of the train list
		new Thread(() -> {
			while (true) {
				System.out.println(trains.size());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
        }).start();
		
		// Create and start each simulator
		for (int i = 0; i < numberOfTrainsimulators; i++) {
			TrainCLISimulator trainsim = new TrainCLISimulator(trains);
			trainsimulators[i] = new Thread(trainsim);
			trainsimulators[i].start();
		}
	}
}
