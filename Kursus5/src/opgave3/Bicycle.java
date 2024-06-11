/*
 * Use a hashmap to organize the remaining bicycles and add a possibility to 
 * input a name from the user. Use the inputted name to search and find the 
 * hashmapped bicycle and print its info. Repeat this until the user types 
 * “exit”. To get user input you can use the Scanner class, 
 * see e.g. https://www.w3schools.com/java/java_user_input.asp
 */

package opgave3;

import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

public class Bicycle implements Comparable<Bicycle> {
	private String name;
	private int speed;
	private int VIN;

	public Bicycle(String name, int VIN, int speed) {
		this.name = name;
		this.VIN = VIN;
		this.speed = speed;

	}

	public void printInfo() {
		System.out.println("Name:  " + this.name);
		System.out.println("VIN:   " + this.VIN);
		System.out.println("Speed: " + this.speed);
		System.out.println();
	}

	// This is used for the PriorityQueue to define what priority
	// the bicycles should have. We use the VIN number
	@Override
	public int compareTo(Bicycle other) {
		return Integer.compare(this.VIN, other.VIN);
	}

	public static void main(String[] args) {
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		int numberOfBicycles = 100;

		// The hashmap is structured as <Name: Bicycle>
		HashMap<String, Bicycle> bicyclesHashMap = new HashMap<String, Bicycle>();

		// Fill the hashmap with 100 random bicycles
		for (int i = 0; i < numberOfBicycles; i++) {
			String name = "myBicycle_" + i;
			int VIN = random.nextInt();
			int speed = random.nextInt(36); // Random int [0,36)
			Bicycle bicycle = new Bicycle(name, VIN, speed);

			// Key is the name and the value is the object
			bicyclesHashMap.put(bicycle.name, bicycle);
		}

		// The string to store the user input in
		String input = "";

		do {
			// Ask the user for a bicycle name
			System.out.println("Please enter the name of a bicycle.");
			System.out.print("exmaple: \"myBicycle_14\": ");

			// Read the input from the terminal
			input = scanner.nextLine();

			// Do a lookup in the hashmap
			Bicycle bicycle = bicyclesHashMap.get(input);

			// If the lookup failed
			if (bicycle == null) {
				System.out.println("The bicycle with name: " + input + " does not exist!");
				continue;
			}

			// Otherwise print the information of the bicycle
			bicycle.printInfo();

			// Continue to do this until the user wants to exit
		} while (!input.equals("exit"));

	}
}
