/*
 * Create an array with 100 bicycles – each with individual characteristics. 
 * Use the Random class to generate some random attribute values, see 
 * https://www.geeksforgeeks.org/generating-random-numbers-in-java/
 * Add an attribute name to the bicycle class, and code a namegenerator such 
 * that you get a random and unique name for each bicycle, e.g. “myBicycle_1”.
 *
 */

package opgave1;

import java.util.Random;

public class Bicycle {
	private String name;
	private int speed;
	private int VIN;

	public Bicycle(String name, int VIN) {
		this.name = name;
		this.VIN = VIN;
		this.speed = 0;
	}

	public static void main(String[] args) {
		int numberOfBicycles = 100;

		Random random = new Random();
		Bicycle[] bicycles = new Bicycle[numberOfBicycles];

		for (int i = 0; i < bicycles.length; i++) {
			String name = "myBicycle_" + i;
			int VIN = random.nextInt();
			bicycles[i] = new Bicycle(name, VIN);
			System.out.print("Generate bicycles with name: ");
			System.out.println(name + " and VIN: " + VIN);
		}
	}
}
