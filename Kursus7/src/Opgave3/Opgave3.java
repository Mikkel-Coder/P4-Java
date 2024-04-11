package Opgave3;

import java.util.ArrayList;

import java.util.Scanner;
import java.util.Collections;

import Opgave2.Train;
import Opgave2.PassengerTrainTypes;
import Opgave2.SortByWeight;
import Opgave2.SortByLength;

public class Opgave3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Train> trains = new ArrayList<Train>();

		// Add some different types of trains
		trains.add(new Train(PassengerTrainTypes.LightRailTransit, 60, 40, true));
		trains.add(new Train(PassengerTrainTypes.Metro, 100, 120, true));
		trains.add(new Train(PassengerTrainTypes.InterCity, 320, 210, false));
		trains.add(new Train(PassengerTrainTypes.Regional, 600, 315, false));
		trains.add(new Train(PassengerTrainTypes.Regional, 234, 124, true));

		String input = "";

		do {
			System.out.println("What do you want to search for?");
			System.out.println("Search terms: (t)ype, (l)ength, (w)eight, (e)lectric");

			input = scanner.nextLine().toLowerCase();

			if (input.equals("t")) {
				System.out.println("What type of train do you want to search for?");
				System.out.println("(m)etro, (r)egional, (i)nterCity, (l)ightRailTransit, (h)ighSpeed: ");
				input = scanner.nextLine().toLowerCase();

				if (input.equals("m")) {
					for (Train train : trains) {
						if (train.type == PassengerTrainTypes.Metro) {
							System.out.println(train.type);
						}
					}
					continue;
				}

				if (input.equals("r")) {
					for (Train train : trains) {
						if (train.type == PassengerTrainTypes.Regional) {
							System.out.println(train.type);
						}
					}
					continue;
				}

				if (input.equals("i")) {
					for (Train train : trains) {
						if (train.type == PassengerTrainTypes.InterCity) {
							System.out.println(train.type);
						}
					}
					continue;
				}

				if (input.equals("l")) {
					for (Train train : trains) {
						if (train.type == PassengerTrainTypes.LightRailTransit) {
							System.out.println(train.type);
						}
					}
					continue;
				}

				if (input.equals("h")) {
					for (Train train : trains) {
						if (train.type == PassengerTrainTypes.HighSpeed) {
							System.out.println(train.type);
						}
					}
					continue;
				}

			}

			if (input.equals("l")) {
				System.out.println("Please enter your search operation (>,<,=): ");
				input = scanner.nextLine().toLowerCase();

				System.out.println("Please enter the length: ");
				int input_length = scanner.nextInt();

				Collections.sort(trains, new SortByLength());
				if (input.equals(">")) {
					for (Train train : trains) {
						if (train.length > input_length) {
							System.out.println(train.length);
						}
					}
				} else if (input.equals("<")) {
					for (Train train : trains) {
						if (train.length < input_length) {
							System.out.println(train.length);
						}
					}
				} else if (input.equals("=")) {
					for (Train train : trains) {
						if (train.length == input_length) {
							System.out.println(train.length);
						}
					}
				}

				Collections.shuffle(trains);
				continue;
			}

			if (input.equals("w")) {
				System.out.println("Please enter your search operation (>,<,=): ");
				input = scanner.nextLine().toLowerCase();

				System.out.println("Please enter the weight: ");
				int input_weight = scanner.nextInt();

				Collections.sort(trains, new SortByWeight());
				if (input.equals(">")) {
					for (Train train : trains) {
						if (train.weight > input_weight) {
							System.out.println(train.weight);
						}
					}
				} else if (input.equals("<")) {
					for (Train train : trains) {
						if (train.weight < input_weight) {
							System.out.println(train.weight);
						}
					}
				} else if (input.equals("=")) {
					for (Train train : trains) {
						if (train.weight == input_weight) {
							System.out.println(train.weight);
						}
					}
				}

				Collections.shuffle(trains);
				continue;
			}

			if (input.equals("e")) {
				System.out.println("Please enter your search operation (t)rue, (f)alse ");
				input = scanner.nextLine().toLowerCase();

				if (input.equals("t")) {
					for (Train train : trains) {
						if (train.isElectric == true) {
							System.out.println(train.isElectric);
						}
					}
				} else if (input.equals("f")) {
					for (Train train : trains) {
						if (train.isElectric == false) {
							System.out.println(train.isElectric);
						}
					}
				}

				continue;
			}

		} while (!input.equals("exit"));
	}

}
