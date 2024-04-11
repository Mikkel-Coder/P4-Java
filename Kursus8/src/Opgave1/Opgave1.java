package Opgave1;

public class Opgave1 {

	/*
	 * Design and implement user input for a train management program as a 
	 * thread (you can call it a Command Line Input Handler) and the part 
	 * which holds the list of trains in another thread. Make the program 
	 * work with those two threads. 
	 * 
	 * Hint: create a class specific for 
	 * 1) the user input and 
	 * 2) the train list management.
	 * 
	 * */
	
	public static void main(String[] args) {
		TrainsList trains_list = new TrainsList();
		Thread trains_thread = new Thread(trains_list);
		
		TrainCLI trainCLI = new TrainCLI(trains_list);
		Thread trainCLI_thread = new Thread(trainCLI);
		
		trains_thread.start();
		trains_list.add(new Train(PassengerTrainTypes.LightRailTransit, 60, 40, true));
		trains_list.add(new Train(PassengerTrainTypes.Regional, 600, 315, false));
		
		trainCLI_thread.start();
		
		
		
	
	}
}