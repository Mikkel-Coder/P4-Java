package Opgave1;

import java.util.ArrayList;

public class Opgave1 {

	public static void main(String[] args) {
		int number_of_trains = 10;
		ArrayList<Train> trains = new ArrayList<Train>();

		for (int i = 0; i < number_of_trains; i++) {
			trains.add(new Train());
		};
	}
}
