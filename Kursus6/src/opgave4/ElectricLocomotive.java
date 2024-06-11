package opgave4;

public class ElectricLocomotive implements Locomotive, RollingStock, Vehicle {
	private double batteryProcentage;
	
	public ElectricLocomotive() {
		this.batteryProcentage = 100;
	}
	
	public double getBatteryPemaining() {
		return this.batteryProcentage;
	}
	
	public void changeBattery(double level) {
		this.batteryProcentage = level;
	}
	
	public void dischargeBattery(double level) {
		this.batteryProcentage = level;
	}
	
	public void honk() {
		System.out.println("🚆🚆🚆 HOOONNK!");
	}
}
