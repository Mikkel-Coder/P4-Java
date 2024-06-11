package Opgave1;

import java.util.Scanner;

/*
 * The class used to managed CLI to trains
 */
public class TrainCLI implements Runnable {
	private TrainsList trains; // A pointer to a train list
	private Scanner scanner; // Create one scanner instead of one for every user input
	private static final String back = "back";
	private static final String exit = "exit";

	public TrainCLI(TrainsList trainslist) {
		this.trains = trainslist;
		this.scanner = new Scanner(System.in); // capture user input
	}

	@Override
	public void run() {

		// Keep running the CLI until the case that the user wants to exit
		while (true) {
			System.out.println("What do you what to do?");
			System.out.println("(s)earch, (r)emove, (a)dd, (l)ist all");

			String input = this.scanner.nextLine().toLowerCase();

			switch (input) {
			case exit:
				return;

			case "s":
				search();
				break;

			case "r":
				remove();
				break;

			case "a":
				add();
				break;

			case "l":
				printAllTrains();
				break;

			default:
				System.out.println("Unknown paramter: " + input);

			}
		}
	}

	/*
	 * A CLI method used to search for different train attributes
	 */
	private void search() {
		while (true) {
			System.out.println("What do you want to search for?");
			System.out.println("Search terms: (t)ype, (l)ength, (w)eight, (e)lectric");

			String input = this.scanner.nextLine().toLowerCase();

			switch (input) {
			case back:
				return;

			case "t":
				searchByType();
				break;

			case "l":
				searchByLength();
				break;

			case "w":
				searchByWeight();
				break;

			case "e":
				searchByElectric();
				break;

			default:
				System.out.println("Unknown paramter: " + input);
			}
		}
	}

	/*
	 * A CLI method to search by type of trains
	 */
	private void searchByType() {
		while (true) {
			System.out.println("What type of train do you want to search for?");
			System.out.println("(m)etro, (r)egional, (i)nterCity, (l)ightRailTransit, (h)ighSpeed: ");

			String input = this.scanner.nextLine().toLowerCase();

			switch (input) {
			case back:
				return;

			case "m":
				printTrainsByType(PassengerTrainTypes.Metro);
				break;

			case "r":
				printTrainsByType(PassengerTrainTypes.Regional);
				break;

			case "i":
				printTrainsByType(PassengerTrainTypes.InterCity);
				break;

			case "l":
				printTrainsByType(PassengerTrainTypes.LightRailTransit);
				break;

			case "h":
				printTrainsByType(PassengerTrainTypes.HighSpeed);
				break;

			default:
				System.out.println("Unknown paramter: " + input);
			}
		}
	}

	/*
	 * Prints the different types of trains
	 */
	private void printTrainsByType(PassengerTrainTypes type) {
		for (Train train : this.trains) {
			if (train.type == type) {
				train.printInfo();
			}
		}
	}

	/*
	 * A CLI method for searching for a train by its length
	 */
	private void searchByLength() {
		while (true) {
			System.out.println("Please enter your search operation.");
			System.out.println("Operations: (g)reather than, (l)ess than, (e)qual to");
			String input = this.scanner.nextLine().toLowerCase();

			System.out.println("Please enter the length: ");
			int input_length;
			try {
				input_length = Integer.parseInt(this.scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid intenger!");
				continue;
			}

			switch (input) {
			case back:
				return;

			case "g":
				printTrainsByLength(ComparisonOperator.GREATER_THAN, input_length);
				break;

			case "l":
				printTrainsByLength(ComparisonOperator.LESS_THAN, input_length);
				break;

			case "e":
				printTrainsByLength(ComparisonOperator.EQUALS, input_length);
				break;

			default:
				System.out.println("Unknown paramter: " + input);

			}

		}
	}

	/*
	 * Prints all trains given by there length comparative
	 */
	private void printTrainsByLength(ComparisonOperator operator, int length) {
		for (Train train : this.trains) {
			switch (operator) {
			case GREATER_THAN:
				if (train.length > length) {
					train.printInfo();
				}
				break;
			case LESS_THAN:
				if (train.length < length) {
					train.printInfo();
				}
				break;
			case EQUALS:
				if (train.length == length) {
					train.printInfo();
				}
				break;
			}
		}
	}

	/*
	 * A CLI method to search among trains by their weight
	 */
	private void searchByWeight() {
		while (true) {
			System.out.println("Please enter your search operation.");
			System.out.println("Operations: (g)reather than, (l)ess than, (e)qual to");
			String input = this.scanner.nextLine().toLowerCase();

			System.out.println("Please enter the weight: ");
			int input_weight;
			try {
				input_weight = Integer.parseInt(this.scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid intenger!");
				continue;
			}

			switch (input) {
			case back:
				return;

			case "g":
				printTrainsByWeight(ComparisonOperator.GREATER_THAN, input_weight);
				break;

			case "l":
				printTrainsByWeight(ComparisonOperator.LESS_THAN, input_weight);
				break;

			case "e":
				printTrainsByWeight(ComparisonOperator.EQUALS, input_weight);
				break;

			default:
				System.out.println("Unknown paramter: " + input);

			}

		}
	}

	/*
	 * Prints all the trains by their weight comparative
	 */
	private void printTrainsByWeight(ComparisonOperator operator, int weight) {
		for (Train train : this.trains) {
			switch (operator) {
			case GREATER_THAN:
				if (train.weight > weight) {
					train.printInfo();
				}
				break;
			case LESS_THAN:
				if (train.weight < weight) {
					train.printInfo();
				}
				break;
			case EQUALS:
				if (train.weight == weight) {
					train.printInfo();
				}
				break;
			}
		}
	}

	/*
	 * A CLI method used to search by trains if they are electric or not
	 */
	private void searchByElectric() {
		while (true) {
			System.out.println("Please enter your search operation (t)rue, (f)alse ");
			String input = this.scanner.nextLine().toLowerCase();

			switch (input) {
			case back:
				return;

			case "t":
				printTrainsByElectricity(true);
				break;

			case "f":
				printTrainsByElectricity(false);
				break;

			default:
				System.out.println("Unknown paramter: " + input);
			}
		}
	}

	/*
	 * Prints all trains that are electric or not
	 */
	private void printTrainsByElectricity(boolean isElectric) {
		for (Train train : this.trains) {
			if (train.isElectric == isElectric) {
				train.printInfo();
			}
		}
	}

	/*
	 * Remove a train from the trainlist by its ID
	 */
	private void remove() {
		while (true) {
			System.out.println("Please into Object ID: ");

			int objectID;
			try {
				objectID = Integer.parseInt(this.scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid intenger!");
				continue;
			}

			for (Train train : this.trains) {
				if (System.identityHashCode(train) == objectID) {
					trains.remove(train);
					return;
				}
			}
			System.out.println("No train with Object ID: " + objectID + " was found.");
		}
	}

	/*
	 * A CLI method used to add new trains to the trainlist
	 */
	private void add() {
		System.out.println("Please enter type of train.");
		System.out.println("(m)etro, (r)egional, (i)nterCity, (l)ightRailTransit, (h)ighSpeed: ");

		String input = this.scanner.nextLine().toLowerCase();
		PassengerTrainTypes type;

		switch (input) {
		case "m":
			type = PassengerTrainTypes.Metro;
			break;

		case "r":
			type = PassengerTrainTypes.Regional;
			break;

		case "i":
			type = PassengerTrainTypes.InterCity;
			break;

		case "l":
			type = PassengerTrainTypes.LightRailTransit;
			break;

		case "h":
			type = PassengerTrainTypes.HighSpeed;
			break;

		default:
			System.out.println("Unknown paramter: " + input);
			return;
		}

		System.out.println("Please enter the length: ");
		int length;
		try {
			length = Integer.parseInt(this.scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid intenger!");
			return;
		}

		System.out.println("Please enter the weight: ");
		int weight;
		try {
			weight = Integer.parseInt(this.scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid intenger!");
			return;
		}

		System.out.println("Please enter if the train whould be electric.");
		System.out.println("Options: \"true\" or \"false\": ");
		boolean electric;
		try {
			electric = Boolean.parseBoolean(this.scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid boolean!");
			return;
		}

		// Now we can safely append a new train to the trainlist
		this.trains.add(new Train(type, length, weight, electric));
	}

	/*
	 * Prints every train in the trainlist
	 */
	private void printAllTrains() {
		for (Train train : this.trains) {
			train.printInfo();
		}
	}
}
