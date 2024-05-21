/* 
 * ASSIGNMENT 1
 * 
 * Based on the below description, define a set of classes for things on a 
 * bicycle down to nuts and bolts. Define methods and attributes for each – 
 * you don’t have to overdo this. Just so you have enough to roughly model a 
 * bicycle and be able to “use” each class in a limited way. I.e. use your 
 * senses to scope yourself.
 * 
 *  - Bicycle
 *  	The main bicycle class that must have wheel to work.
 *  - Wheel
 *  	- A wheel must have a tire to work.
 *  - Tire
 *  	- The thing that holds air so we are not flat.
 * 
 * 
 * """
 * With my Bicycle I can ride it forward by stepping on the pedals, and by 
 * changing gears I can go faster if I choose a higher gearing, but it also 
 * becomes harder to step on the pedals. For that reason, I start in first 
 * gear. I have front and backlights which switch on if it becomes dark. The 
 * wheels I can take off when I need to fix holes in the tire. When I brake, 
 * I can use both front and read brakes.
 * """
 * 
 */

package Opgave1;

public class Bicycle {
	private int gear;
	private boolean frontLight;
	private boolean rearLight;
	private Wheel frontWheel;
	private Wheel rearWheel;
	
	public Bicycle() {
		this.gear = 1; // I start in first gear
		this.frontLight = false;
		this.rearLight = false; // I have front and backlights
	}
	
	public void peddel() {} //I can ride it forward by stepping on the pedals
	public void changeGearUp() {} // changing gears
	public void changeGearDown() {} // changing gears
	public void mountWheel(boolean front) {}
	public void unmountWheel(boolean front) {} // wheels I can take off
	public void pressBreakFront() {} // I can use both front and read brakes
	public void pressBreakRear() {} // I can use both front and read brakes
}
