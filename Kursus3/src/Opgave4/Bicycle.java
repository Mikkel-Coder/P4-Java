/*
 * ASSIGNMENT 4
 * 
 * Create a main() that initiates the bicycle and show that you can execute 
 * via code, the below description (SEE ASSIGNMENT 1).
 * 
 */

package Opgave4;

public class Bicycle {
	private int gear;
	private boolean frontLight;
	private boolean rearLight;
	private Wheel frontWheel;
	private Wheel rearWheel;
	
	public Bicycle(Wheel front, Wheel rear) {
		this.gear = 1;
		this.frontLight = false;
		this.rearLight = false;
		this.frontWheel = front;
		this.rearWheel = rear;
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
	
	public static void main(String args[]) {
		// Create tires for both front and rear wheels
		Tire frontTire = new Tire();
		Tire rearTire = new Tire();
		Wheel frontWheel = new Wheel(frontTire);
		Wheel rearWheel = new Wheel(rearTire);
		
		// Create the Bicycle with wheels
		Bicycle myBicycle = new Bicycle(frontWheel, rearWheel);
				
		// "I can ride it forward by stepping on the pedals"
		myBicycle.peddel();
		
		// "by changing gears I can go faster"
		myBicycle.changeGearUp();
		
		// "I start in first gear."
		System.out.println("Gear is: " + myBicycle.gear); // 1
		
		// "have front and backlights which switch on if it becomes dark"
		Boolean dark = true;
		if (dark) {
			myBicycle.frontLight = true;
			myBicycle.rearLight = true;
		}
		
		// "The wheels I can take off when I need to fix holes in the tire."
		myBicycle.frontWheel.tire.flat = true;
		
		if (myBicycle.frontWheel.tire.flat) {
			myBicycle.frontWheel.tire.repair();
			System.out.println("Front tire is now fixed.");
		}
		
		// "When I brake, I can use both front and read brakes"
		Boolean breaking = true;
		while (breaking) {
			myBicycle.pressBreakFront();
			myBicycle.pressBreakRear();
			breaking = false;
		}	
	}
}
