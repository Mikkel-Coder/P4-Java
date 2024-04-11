package Opgave3;

import Opgave1.TrainsList;

public class Opgave3 {


	public static void main(String[] args) throws Exception {
		final int number_of_trainsimulators = 16_000; // AMD Ryzen 7 6850U PRO Linux 6.6.15 about 17000
		// We begin to run into scaling problems because of the CopyOnWrite 
		
		Thread[] trainsimulatos = new Thread[number_of_trainsimulators];
		
		TrainsList trains = new TrainsList();
		Thread trains_thread = new Thread();
		trains_thread.start();
		
		new Thread(() -> {
			while (true) {
				System.out.println(trains.size());
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					
				}				
			}
        }).start();
		
		
		for (int i = 0; i < number_of_trainsimulators; i++) {
			TrainCLISimulator trainsim = new TrainCLISimulator(trains);
			trainsimulatos[i] = new Thread(trainsim);
			trainsimulatos[i].start();
			System.out.println("");
		}

	}

}
