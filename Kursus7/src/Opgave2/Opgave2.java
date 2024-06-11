/*
 * Create methods to allow you to sort the trains, for example
 * a. According to type
 * b. According to length
 * c. According to weight
 */

package Opgave2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Iterator;

public class Opgave2 {
	public static void main(String[] args) {
		Random random = new Random();
		
		int numberOfTrains = 5;
		ArrayList<Train> trains = new ArrayList<Train>();
		
		for (int i = 0; i < numberOfTrains; i++) {	
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
		
		// A: Sort by Type
		Collections.sort(trains, new SortByType());		
		for (Train train : trains) {
			System.out.println(train.type);
		}
		
		// B: Sort by length
		Collections.sort(trains, new SortByLength());		
		for (Train train : trains) {
			System.out.println(train.length);
		}
		
		// C: Sort by weight
		Collections.sort(trains, new SortByWeight());		
		for (Train train : trains) {
			System.out.println(train.weight);
		}
		
		// Iterator
		Iterator<Train> it = trains.iterator();
		while (it.hasNext()) {
			Train train = it.next();
			System.out.println(train.weight);
		}
		// Se Kursus 5 opgave 2 Bicycle.java linje 96
		
	}
}
