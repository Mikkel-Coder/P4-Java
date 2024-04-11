package Opgave2;

import Opgave1.TrainsList;

public class Opgave2 {
	
	public static void main(String[] args) throws Exception {
		final int number_of_trainsimulators = 10;
		Thread[] trainsimulatos = new Thread[number_of_trainsimulators];
		
		TrainsList trains = new TrainsList();
		Thread trains_thread = new Thread();
		trains_thread.start();	
		
		for (int i = 0; i < number_of_trainsimulators; i++) {
			TrainCLISimulator trainsim = new TrainCLISimulator(trains);
			trainsimulatos[i] = new Thread(trainsim);
			trainsimulatos[i].start();
		}
	}
}
