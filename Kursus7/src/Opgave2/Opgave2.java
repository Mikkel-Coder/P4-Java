package Opgave2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Opgave2 {
	public static void main(String[] args) {
		Random random = new Random();
		
		int number_of_trains = 5;
		ArrayList<Train> trains = new ArrayList<Train>();
		
		for (int i = 0; i < number_of_trains; i++) {
			
			// Chose a random Enum
			PassengerTrainTypes randomType = PassengerTrainTypes.values()[random.nextInt(PassengerTrainTypes.values().length)];
			
			// Chose a random length between 100 and 3000 meters inclusive
			int randomLength = random.nextInt(2901) + 100;
			
			// Chose a random weight between 100 and 300 tons
			int randomWeight = random.nextInt(201) + 100;
			
			// Random boolean for electric
			boolean randomElectric = random.nextBoolean();
			
			trains.add(new Train(randomType, randomLength, randomWeight, randomElectric));
		};
		
		// Sort by Type
		Collections.sort(trains, new SortByType());		
		for (Train train : trains) {
			System.out.println(train.type);
		}
		
		// Sort by length
		Collections.sort(trains, new SortByLength());		
		for (Train train : trains) {
			System.out.println(train.length);
		}
		
		// Sort by weight
		Collections.sort(trains, new SortByWeight());		
		for (Train train : trains) {
			System.out.println(train.weight);
		}
		
	}
}
