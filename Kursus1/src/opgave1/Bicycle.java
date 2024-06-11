/* Bicycle.java */

package opgave1;

public class Bicycle {
	public String name;
	public String color;
	
	private int speed = 0;
	private int gear = 0;
	
	private Wheel frontWheel;
	private Wheel rearWheel;
	
	Bicycle(String name, String color) {
		this.name = name;
		this.color = color;
	}
	
	public void mountWheel(Wheel wheel, String position) throws Exception {
		System.out.println("Mounting wheel with diameter of: " + wheel.diameter + " to the bicycle with name: " + this.name);
		if (position == "Front") {
			this.frontWheel = wheel;
			return;
		}
		
		if (position == "Rear") {
			this.rearWheel = wheel;
			return;
		}
		
		throw new Exception("Invalid installation of wheel!");
		
	}
	
	public void changeGear(int gear) {
		System.out.println("Changing from gear: " + this.gear + " to gear: " + gear);
		this.gear = gear;
	}
	
	public void peddel() {
		if (this.speed > 30) {
			System.out.println("We cannot go any fast!");
			return;
		}
		System.out.println("Accelerating the bicycle: " + this.name + " by 10");
		this.speed += 10;
		System.out.println("Current speed is: " + this.speed);
	}
	
	public void pressBreak() {
		if (this.speed <= 0) {
			System.out.println("We cannot break or we will fall and hit our knee!");
			return;
		}
		System.out.println("Deaccelerating the bicycle: " + this.name + " by -10");
		this.speed -= 10;
		System.out.println("Current speed is: " + this.speed);
	}
	
	public static void main(String[] args) {
		Wheel frontWheel = new Wheel(10, 14);
		Wheel rearWheel = new Wheel(10, 16);
		
		Bicycle myBicyle = new Bicycle("Peter", "Red");
		try {
			myBicyle.mountWheel(frontWheel, "Front");			
			myBicyle.mountWheel(rearWheel, "Rear");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		myBicyle.peddel();
		myBicyle.peddel();
		
		myBicyle.pressBreak();
		myBicyle.changeGear(2);
	}

}
