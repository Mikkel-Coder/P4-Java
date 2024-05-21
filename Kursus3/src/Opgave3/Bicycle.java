/*
 * ASSIGNMENT 3
 * 
 * Create a bicycle class, that initiates the previous 
 * defined classes. Also add attributes and methods to
 * the bicycle as you would use a normal bicycle.
 * 
 */

package Opgave3;

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
				
		// Change the bicycle attributes (lights)
		myBicycle.frontLight = true;
		
	}
}
