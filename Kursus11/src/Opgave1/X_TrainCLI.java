/*
 * THIS FILE IS NOT A PART OF THE ASSIGENMENT
 */

package Opgave1;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.InputStream;
import java.io.PrintStream;

public class X_TrainCLI implements Runnable {
	private X_TrainsList trains;
	private Scanner scanner;
	private PrintStream outputstream;

	private List<X_TrainCLIEvents> listeners = new ArrayList<X_TrainCLIEvents>();

	private static final String back = "back";
	private static final String exit = "exit";

	public X_TrainCLI(X_TrainsList trainslist, InputStream input, PrintStream outputstream) {
		this.trains = trainslist;
		this.scanner = new Scanner(input);
		this.outputstream = outputstream;
	}

	public void addListener(X_TrainCLIEvents listener) {
		listeners.add(listener);
	}

	@Override
	public void run() {

		while (true) {
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
				printTrainsByType(X_PassengerTrainTypes.Metro);
				break;

			case "r":
				printTrainsByType(X_PassengerTrainTypes.Regional);
				break;

			case "i":
				printTrainsByType(X_PassengerTrainTypes.InterCity);
				break;

			case "l":
				printTrainsByType(X_PassengerTrainTypes.LightRailTransit);
				break;

			case "h":
				printTrainsByType(X_PassengerTrainTypes.HighSpeed);
				break;

			default:
				this.outputstream.println("Unknown paramter: " + input);
			}
		}
	}

	private void printTrainsByType(X_PassengerTrainTypes type) {
		for (X_Train train : this.trains) {
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
				printTrainsByLength(X_ComparisonOperator.GREATER_THAN, input_length);
				break;

			case "l":
				printTrainsByLength(X_ComparisonOperator.LESS_THAN, input_length);
				break;

			case "e":
				printTrainsByLength(X_ComparisonOperator.EQUALS, input_length);
				break;

			default:
				this.outputstream.println("Unknown paramter: " + input);

			}

		}
	}

	private void printTrainsByLength(X_ComparisonOperator operator, int length) {
		for (X_Train train : this.trains) {
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
				printTrainsByWeight(X_ComparisonOperator.GREATER_THAN, input_weight);
				break;

			case "l":
				printTrainsByWeight(X_ComparisonOperator.LESS_THAN, input_weight);
				break;

			case "e":
				printTrainsByWeight(X_ComparisonOperator.EQUALS, input_weight);
				break;

			default:
				this.outputstream.println("Unknown paramter: " + input);

			}

		}
	}

	private void printTrainsByWeight(X_ComparisonOperator operator, int weight) {
		for (X_Train train : this.trains) {
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
		for (X_Train train : this.trains) {
			if (train.isElectric == isElectric) {
				train.printInfo();
			}
		}
	}

	private void remove() {
		for (X_TrainCLIEvents listener : listeners) {
			listener.onBeforeTrainListModification();
		}

		try {
			this.trains.remove(0);
			System.out.println("[CLIENT_TRAINCLI] Removed train. TrainList size is now: " + this.trains.size());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		for (X_TrainCLIEvents listener : listeners) {
			listener.onAfterTrainListModification();
		}
	}

	private void add() {
		this.outputstream.println("Please enter type of train.");
		this.outputstream.println("(m)etro, (r)egional, (i)nterCity, (l)ightRailTransit, (h)ighSpeed: ");

		String input = this.scanner.nextLine().toLowerCase();
		X_PassengerTrainTypes type;

		switch (input) {
		case "m":
			type = X_PassengerTrainTypes.Metro;
			break;

		case "r":
			type = X_PassengerTrainTypes.Regional;
			break;

		case "i":
			type = X_PassengerTrainTypes.InterCity;
			break;

		case "l":
			type = X_PassengerTrainTypes.LightRailTransit;
			break;

		case "h":
			type = X_PassengerTrainTypes.HighSpeed;
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

		for (X_TrainCLIEvents listener : listeners) {
			listener.onBeforeTrainListModification();
		}

		this.trains.add(new X_Train(type, length, weight, electric));
		System.out.println("[CLIENT_TRAINCLI] Added train. TrainList size is now: " + this.trains.size());

		for (X_TrainCLIEvents listener : listeners) {
			listener.onAfterTrainListModification();
		}

		return;

	}

	private void printAllTrains() {
		for (X_Train train : this.trains) {
			train.printInfo();
		}
	}

}
