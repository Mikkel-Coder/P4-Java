/*
 * Create a list of trains from your previous assignments
 */

package Opgave1;

import java.util.ArrayList;

public class Opgave1 {

	public static void main(String[] args) {
		int numberOfTrains = 10;
		ArrayList<Train> trains = new ArrayList<Train>();

		for (int i = 0; i < numberOfTrains; i++) {
			trains.add(new Train());
		};
	}
}
