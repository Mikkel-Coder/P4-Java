/*
 * Place the 100 bicycles in a LinkedList, Stack and PriorityQueue 
 * and do the following:
 * 
 * 	a. Add them one-by-one to a stack. Then decrease the speed for each 
 * 	   bicycle by 25% as you take them off the stack.
 * 
 * 	b. Add them one-by-one to a priorityqueue. Then increase speed for 
 * 	   each bicycle by 25% in the same order as you inserted the bicycles.
 * 
 *  c. Structure the bicycles in a LinkedList, then remove all bicycles 
 *     that drives less than 10 km/h.
 */

package opgave2;

import java.util.Random;
import java.util.LinkedList;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Iterator;

public class Bicycle implements Comparable<Bicycle> {
	private String name;
	private int speed;
	private int VIN;

	public Bicycle(String name, int VIN, int speed) {
		this.name = name;
		this.VIN = VIN;
		this.speed = speed;

	}

	// This is used for the PriorityQueue to define what priority
	// the bicycles should have. We use the VIN number
	@Override
	public int compareTo(Bicycle other) {
		return Integer.compare(this.VIN, other.VIN);
	}

	public static void main(String[] args) {
		Random random = new Random();

		// Place the 100 bicycles in a LinkedList, Stack and PriorityQueue
		int numberOfBicycles = 100;
		LinkedList<Bicycle> bicyclesLinkedList = new LinkedList<Bicycle>();
		Stack<Bicycle> bicyclesStack = new Stack<Bicycle>();
		PriorityQueue<Bicycle> bicyclesPriorityQueue = new PriorityQueue<Bicycle>();

		/* A: */
		// Add them one-by-one to a stack.
		for (int i = 0; i < numberOfBicycles; i++) {
			String name = "myBicycle_" + i;
			int VIN = random.nextInt();
			int speed = random.nextInt(36); // Random int [0,36)
			Bicycle bicycle = new Bicycle(name, VIN, speed);
			bicyclesStack.push(bicycle);
		}

		// Then decrease the speed for each bicycle by 25%
		// as you take them off the stack.
		while (!bicyclesStack.empty()) {
			Bicycle bicycle = bicyclesStack.pop();
			bicycle.speed *= 1 - 0.25;
		}

		/* B: */
		// Add them one-by-one to a priorityqueue.
		for (int i = 0; i < numberOfBicycles; i++) {
			String name = "myBicycle_" + i;
			int VIN = random.nextInt();
			int speed = random.nextInt(36); // Random int [0,36)
			Bicycle bicycle = new Bicycle(name, VIN, speed);
			bicyclesPriorityQueue.add(bicycle);
		}

		// Then increase speed for each bicycle by 25% in the same
		// order as you inserted the bicycles.
		// NOTE: We do not know the insertion order but only there priority
		for (Bicycle bicycle : bicyclesPriorityQueue) {
			bicycle.speed *= 1 + 0.25;
		}

		/* C: */
		// Structure the bicycles in a LinkedList,
		for (int i = 0; i < numberOfBicycles; i++) {
			String name = "myBicycle_" + i;
			int VIN = random.nextInt();
			int speed = random.nextInt(36); // Random int [0,36)
			Bicycle bicycle = new Bicycle(name, VIN, speed);
			bicyclesLinkedList.add(bicycle);
		}

		// then remove all bicycles that drives less than 10 km/h
		Iterator<Bicycle> it = bicyclesLinkedList.iterator();
		while (it.hasNext()) {
			Bicycle bicycle = it.next();
			
			if (bicycle.speed < 10) {
				it.remove();
			}
		}
	}
}
