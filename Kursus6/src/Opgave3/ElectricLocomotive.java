package Opgave3;

public class ElectricLocomotive extends Locomotive {
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
}
