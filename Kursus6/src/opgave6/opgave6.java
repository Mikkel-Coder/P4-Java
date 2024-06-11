/*
 * Create other vehicle types – just a few examples, e.g. bicycles from 
 * previous assignments. Which of the operations from previous assignments 
 * can you easily perform?
 */

package opgave6;

public class opgave6 {
	public static void main(String args[]) {
		Car car = new Car();
		Boat boat = new Boat();
		
		car.honk(); // 🚗🚗🚗 DYT!
		boat.honk(); // 🚢🚢🚢 HOOONK!
	}
}
