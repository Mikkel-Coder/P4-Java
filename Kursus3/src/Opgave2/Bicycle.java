/*
 * ASSIGNMENT 2
 * 
 * Implement the main classes from your initial design. Add some simple 
 * functionality to key method that simply prints its functionality to the 
 * screen, e.g. “changing gear”, “wheel off”
 * 
 * We are only asked to implement prints
 * 
 */


package Opgave2;

public class Bicycle {
	private int gear;
	private boolean frontLight;
	private boolean rearLight;
	private Wheel frontWheel;
	private Wheel rearWheel;
	
	public Bicycle() {
		this.gear = 1;
		this.frontLight = false;
		this.rearLight = false;
	}
	
	public void peddel() {
		System.out.println("Stepping on pedals.");
	}
	public void changeGearUp() {
		System.out.println("Chaning a gear up.");
	}
	public void changeGearDown() {
		System.out.println("Chaning a gear down.");
	}
	public void mountWheel(boolean front) {
		System.out.println("Mounting wheel.");
	}
	public void unmountWheel(boolean front) {
		System.out.println("Unmounting wheel.");
	}
	public void pressBreakFront() {
		System.out.println("Breaking on front.");
	}
	public void pressBreakRear() {
		System.out.println("Breaking on rear.");
	}
}
