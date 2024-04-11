package Opgave3;

import java.util.Scanner;

import java.io.InputStream;
import java.io.PrintStream;

import Opgave1.TrainsList;
import Opgave1.Train;
import Opgave1.PassengerTrainTypes;
import Opgave1.ComparisonOperator;

public class TrainCLI implements Runnable {
	private TrainsList trains;
	private Scanner scanner;
	private PrintStream outputstream;

	private static final String back = "back";
	private static final String exit = "exit";

	public TrainCLI(TrainsList trainslist, InputStream input, PrintStream outputstream) {
		this.trains = trainslist;
		this.scanner = new Scanner(input);
		this.outputstream = outputstream;
	}

	@Override
	public void run() {

		while (true)  {
			this.outputstream.println("What do you what to do?");
			this.outputstream.println("(s)earch, (r)emove, (a)dd, (l)ist all");

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
				this.outputstream.println("Unknown paramter: " + input);
			}
		}
	}

	private void search() {
		while (true) {
			this.outputstream.println("What do you want to search for?");
			this.outputstream.println("Search terms: (t)ype, (l)ength, (w)eight, (e)lectric");

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
				this.outputstream.println("Unknown paramter: " + input);

			}

		}

	}

	private void searchByType() {
		while (true) {
			this.outputstream.println("What type of train do you want to search for?");
			this.outputstream.println("(m)etro, (r)egional, (i)nterCity, (l)ightRailTransit, (h)ighSpeed: ");

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
				this.outputstream.println("Unknown paramter: " + input);
			}
		}
	}

	private void printTrainsByType(PassengerTrainTypes type) {
		for (Train train : this.trains) {
			if (train.type == type) {
				train.printInfo();
			}
		}
	}

	private void searchByLength() {
		while (true) {
			this.outputstream.println("Please enter your search operation.");
			this.outputstream.println("Operations: (g)reather than, (l)ess than, (e)qual to");
			String input = this.scanner.nextLine().toLowerCase();

			this.outputstream.println("Please enter the length: ");
			int input_length;
			try {
				input_length = Integer.parseInt(this.scanner.nextLine());
			} catch (NumberFormatException e) {
				this.outputstream.println("Invalid intenger!");
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
				this.outputstream.println("Unknown paramter: " + input);

			}

		}
	}

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

	private void searchByWeight() {
		while (true) {
			this.outputstream.println("Please enter your search operation.");
			this.outputstream.println("Operations: (g)reather than, (l)ess than, (e)qual to");
			String input = this.scanner.nextLine().toLowerCase();

			this.outputstream.println("Please enter the weight: ");
			int input_weight;
			try {
				input_weight = Integer.parseInt(this.scanner.nextLine());
			} catch (NumberFormatException e) {
				this.outputstream.println("Invalid intenger!");
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
				this.outputstream.println("Unknown paramter: " + input);

			}

		}
	}

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

	private void searchByElectric() {
		while (true) {
			this.outputstream.println("Please enter your search operation (t)rue, (f)alse ");
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
				this.outputstream.println("Unknown paramter: " + input);
			}
		}
	}

	private void printTrainsByElectricity(boolean isElectric) {
		for (Train train : this.trains) {
			if (train.isElectric == isElectric) {
				train.printInfo();
			}
		}
	}

	private void remove() {
		try {
			this.trains.remove(0);			
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
	}

	private void add() {
		this.outputstream.println("Please enter type of train.");
		this.outputstream.println("(m)etro, (r)egional, (i)nterCity, (l)ightRailTransit, (h)ighSpeed: ");

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
			this.outputstream.println("Unknown paramter: " + input);
			return;
		}

		this.outputstream.println("Please enter the length: ");
		int length;
		try {
			length = Integer.parseInt(this.scanner.nextLine());
		} catch (NumberFormatException e) {
			this.outputstream.println("Invalid intenger!");
			return;
		}

		this.outputstream.println("Please enter the weight: ");
		int weight;
		try {
			weight = Integer.parseInt(this.scanner.nextLine());
		} catch (NumberFormatException e) {
			this.outputstream.println("Invalid intenger!");
			return;
		}

		this.outputstream.println("Please enter if the train whould be electric.");
		this.outputstream.println("Options: \"true\" or \"false\": ");
		boolean electric;
		try {
			electric = Boolean.parseBoolean(this.scanner.nextLine());
		} catch (NumberFormatException e) {
			this.outputstream.println("Invalid intenger!");
			return;
		}

		this.trains.add(new Train(type, length, weight, electric));
		return;

	}

	private void printAllTrains() {
		for (Train train : this.trains) {
			train.printInfo();
		}
	}

}
