/*
 * ASSIGNMENT 3
 * 
 * Implement at least three subclasses and one super class to the bicycle 
 * class.
 */

package opgave3;

public class Bicycle {
	protected String color;
	protected int stelNr;
	
	protected void accelerate() {
		System.out.println("Bicycle is accelerating!");
	}
	
	public static void main(String[] args) {
		Unicycle myUnicycle = new Unicycle();
		myUnicycle.stabilize();
		myUnicycle.pedal();
		myUnicycle.accelerate();
		
		TreadmillBike myTreadMillBike = new TreadmillBike(); 
		myTreadMillBike.walk();
		myTreadMillBike.pressBreakes();
		myTreadMillBike.accelerate();
		
		ChristianiaBike myChristianiaBike = new ChristianiaBike();
		myChristianiaBike.loadOntoBed();
		myChristianiaBike.unloadFromBed();
		myChristianiaBike.accelerate();
		
	}
}
